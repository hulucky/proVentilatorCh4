package com.ventilator.serialport.ComAssistant;

import com.ventilator.app.MyApp;
import com.ventilator.serialport.bean.ComBean;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.InvalidParameterException;

import android_serialport_api.SerialPort;

import static com.ventilator.Tools.MyFunction.bytes2HexString;


public abstract class SerialHelper {
    private SerialPort mSerialPort;
    private OutputStream mOutputStream;
    private InputStream mInputStream;
    private ReadThread mReadThread;
    private SendThread mSendThread;
    private String sPort = "/dev/s3c2410_serial0";
    private int iBaudRate = 115200;
    private boolean _isOpen = false;
    private byte[] _bLoopData = new byte[]{0x30};
    private int iDelay = 500;

    //----------------------------------------------------
    public SerialHelper(String sPort, int iBaudRate) {
        this.sPort = sPort;
        this.iBaudRate = iBaudRate;
    }

    public SerialHelper() {
        this("/dev/ttyMT1", 115200);
    }

    public SerialHelper(String sPort) {
        this(sPort, 115200);
    }

    public SerialHelper(String sPort, String sBaudRate) {
        this(sPort, Integer.parseInt(sBaudRate));
    }

    //----------------------------------------------------
    public void open() throws SecurityException, IOException, InvalidParameterException {
        mSerialPort = new SerialPort(new File(sPort), iBaudRate, 0);
        mOutputStream = mSerialPort.getOutputStream();
        mInputStream = mSerialPort.getInputStream();
        mReadThread = new ReadThread();
        mReadThread.start();
        mSendThread = new SendThread();
        mSendThread.setSuspendFlag();
        mSendThread.start();
        _isOpen = true;
    }

    //----------------------------------------------------
    public void close() {
        if (mReadThread != null)
            mReadThread.interrupt();
        if (mSerialPort != null) {
            mSerialPort.close();
            mSerialPort = null;
        }
        _isOpen = false;
    }

    //----------------------------------------------------
    public void send(byte[] bOutArray) {
        try {
            mOutputStream.write(bOutArray);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //----------------------------------------------------
    public void sendHex(String sHex) {
        byte[] bOutArray = MyFunc.HexToByteArr(sHex);
        send(bOutArray);
    }

    //----------------------------------------------------
    public void sendTxt(String sTxt) {
        byte[] bOutArray = sTxt.getBytes();
        send(bOutArray);
    }

    private class ReadThread extends Thread {

        @Override
        public void run() {
            super.run();
            int currentLength = 0;
            byte[] buffers = new byte[1024];
            byte[] buffer = new byte[512];
            while (!isInterrupted()) {
                try {
                    if (mInputStream == null) return;
                    int size = mInputStream.read(buffer);
                    ComBean ComRecData1 = new ComBean(sPort, buffer, size);
                    //Object src : 原数组
                    //int srcPos : 从元数据的起始位置开始
                    //Object dest : 目标数组
                    //int destPos : 目标数组的开始起始位置
                    //int length  : 要copy的数组的长度
                    System.arraycopy(buffer, 0, buffers, currentLength, size);
                    currentLength += size;


//                    String strcom = bytes2HexString(ComRecData1.bRec);
//                    MyApp.getInstance().addQueue(strcom + "| " + size);
//                    MyApp.getInstance().addStrTest1("|" + size);

//                 --
//                    Long starttime = System.currentTimeMillis();
//                    MyApp.getInstance().setStrTest1(starttime + "|");
//                    MyApp.getInstance().addStrTest1(currentLength + "|");
                    while (currentLength >= 3) {
                        if (buffers[0] != 0x4B || buffers[1] != 0x59) {
                            System.arraycopy(buffers, 1, buffers, 0, currentLength - 1);
                            currentLength -= 1;
                            continue;
                        } else {
                            int factPackLen = (int) buffers[2] + 4;
                            if (currentLength < factPackLen) {
                                break;
                            } else {

                                byte temp = buffers[2];
                                for (int i = 3; i < factPackLen - 1; i++) {
                                    temp ^= buffers[i];
                                }

                                if (temp == buffers[factPackLen - 1]) {
                                    ComBean ComRecData = new ComBean(sPort, buffers, factPackLen);
                                    onDataReceived(ComRecData);
                                    System.arraycopy(buffers, factPackLen, buffers, 0, currentLength - factPackLen);
                                    currentLength -= (factPackLen);
//                                    MyApp.getInstance().addStrTest1("|" + 1);
                                } else {
//                                    MyApp.getInstance().addStrTest1("&" + temp + "|" + buffers[factPackLen - 1] + "|" + buffers[9] + "&");
//                                    ComBean ComRecData = new ComBean(sPort, buffers, factPackLen);
//                                    String strcom = bytes2HexString(ComRecData.bRec);
//                                    MyApp.getInstance().addQueue(strcom);
                                    int i = 2;
                                    while (buffers[i] != 0x4B || buffers[i + 1] != 0x59) {
                                        i++;
                                    }
                                    System.arraycopy(buffers, i, buffers, 0, currentLength - i);
                                    currentLength -= (i);

//                                    MyApp.getInstance().addStrTest1("|" + 0);
                                }


                            }
                        }
                    }
//                    Long endtime = System.currentTimeMillis();
//                    MyApp.getInstance().addStrTest1(endtime + "|");
//                    MyApp.getInstance().addStrTest1((endtime - starttime) + "");


                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        }
    }


    //----------------------------------------------------
    private class SendThread extends Thread {
        public boolean suspendFlag = true;

        @Override
        public void run() {
            super.run();
            while (!isInterrupted()) {
                synchronized (this) {
                    while (suspendFlag) {
                        try {
                            wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
                send(getbLoopData());
                try {
                    Thread.sleep(iDelay);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }


        public void setSuspendFlag() {
            this.suspendFlag = true;
        }


        public synchronized void setResume() {
            this.suspendFlag = false;
            notify();
        }
    }

    //----------------------------------------------------
    public int getBaudRate() {
        return iBaudRate;
    }

    public boolean setBaudRate(int iBaud) {
        if (_isOpen) {
            return false;
        } else {
            iBaudRate = iBaud;
            return true;
        }
    }

    public boolean setBaudRate(String sBaud) {
        int iBaud = Integer.parseInt(sBaud);
        return setBaudRate(iBaud);
    }

    //----------------------------------------------------
    public String getPort() {
        return sPort;
    }

    public boolean setPort(String sPort) {
        if (_isOpen) {
            return false;
        } else {
            this.sPort = sPort;
            return true;
        }
    }

    //----------------------------------------------------
    public boolean isOpen() {
        return _isOpen;
    }

    //----------------------------------------------------
    public byte[] getbLoopData() {
        return _bLoopData;
    }

    //----------------------------------------------------
    public void setbLoopData(byte[] bLoopData) {
        this._bLoopData = bLoopData;
    }

    //----------------------------------------------------
    public void setTxtLoopData(String sTxt) {
        this._bLoopData = sTxt.getBytes();
    }

    //----------------------------------------------------
    public void setHexLoopData(String sHex) {
        this._bLoopData = MyFunc.HexToByteArr(sHex);
    }

    //----------------------------------------------------
    public int getiDelay() {
        return iDelay;
    }

    //----------------------------------------------------
    public void setiDelay(int iDelay) {
        this.iDelay = iDelay;
    }

    //----------------------------------------------------
    public void startSend() {
        if (mSendThread != null) {
            mSendThread.setResume();
        }
    }

    //----------------------------------------------------
    public void stopSend() {
        if (mSendThread != null) {
            mSendThread.setSuspendFlag();
        }
    }


    protected abstract void onDataReceived(ComBean ComRecData);

// public void onNaturalData(THABean data) {
//
// }

}