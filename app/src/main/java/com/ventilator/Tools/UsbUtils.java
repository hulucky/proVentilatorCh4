package com.ventilator.Tools;

import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.hardware.usb.UsbDevice;
import android.hardware.usb.UsbDeviceConnection;
import android.hardware.usb.UsbManager;
import android.util.Log;
import android.widget.Toast;

import com.github.mjdev.libaums.UsbMassStorageDevice;
import com.github.mjdev.libaums.fs.FileSystem;
import com.github.mjdev.libaums.fs.UsbFile;
import com.github.mjdev.libaums.fs.UsbFileOutputStream;
import com.github.mjdev.libaums.partition.Partition;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Collections;

public class UsbUtils {
    private Context context;
    UsbManager mUsbManager;
    private static final String ACTION_USB_PERMISSION = "com.android.example.USB_PERMISSION";

    public boolean isRequest() {
        return isRequest;
    }

    private boolean isRequest;

    public UsbFile getCurrentFolder() {
        return currentFolder;
    }

    public void setCurrentFolder(UsbFile currentFolder) {
        this.currentFolder = currentFolder;
    }

    //当前路径
    public  UsbFile currentFolder = null;


    public UsbUtils(Context context) {
        this.context = context;
    }
    public void openUsbDevice() {
        //before open usb device
        //should try to get usb permission
        tryGetUsbPermission();
    }
    private void tryGetUsbPermission() {
        mUsbManager = (UsbManager) context.getSystemService(Context.USB_SERVICE);

        IntentFilter filter = new IntentFilter(ACTION_USB_PERMISSION);
        context.registerReceiver(mUsbPermissionActionReceiver, filter);

        PendingIntent mPermissionIntent = PendingIntent.getBroadcast(context, 0, new Intent(ACTION_USB_PERMISSION), 0);

        //here do emulation to ask all connected usb device for permission
        for (final UsbDevice usbDevice : mUsbManager.getDeviceList().values()) {
            //add some conditional check if necessary
            //if(isWeCaredUsbDevice(usbDevice)){
            if (mUsbManager.hasPermission(usbDevice)) {
                //if has already got permission, just goto connect it
                //that means: user has choose yes for your previously popup window asking for grant perssion for this usb device
                //and also choose option: not ask again
                afterGetUsbPermission(usbDevice);
            } else {
                //this line will let android popup window, ask user whether to allow this app to have permission to operate this usb device
                mUsbManager.requestPermission(usbDevice, mPermissionIntent);
            }
            //}
        }
    }


    private void afterGetUsbPermission(UsbDevice usbDevice) {
        //call method to set up device communication
        //Toast.makeText(this, String.valueOf("Got permission for usb device: " + usbDevice), Toast.LENGTH_LONG).show();
        //Toast.makeText(this, String.valueOf("Found USB device: VID=" + usbDevice.getVendorId() + " PID=" + usbDevice.getProductId()), Toast.LENGTH_LONG).show();

        doYourOpenUsbDevice(usbDevice);
    }

    private void doYourOpenUsbDevice(UsbDevice usbDevice) {
        //now follow line will NOT show: User has not given permission to device UsbDevice
        UsbDeviceConnection connection = mUsbManager.openDevice(usbDevice);
        //add your operation code here
        readUSB(context,0);

    }
    private void readUSB(Context ctx,int position) {
        //获取存储设备
        UsbMassStorageDevice[]  storageDevices = UsbMassStorageDevice.getMassStorageDevices(context);
        Log.i("ZB","mass size="+storageDevices.length);
        if (storageDevices.length > 0) {
            //存在USB
            readDevice(storageDevices[position]);
            Log.i("ZB","read");
        //   Toast.makeText(ctx,"开始读取usb",Toast.LENGTH_SHORT).show();
        } else {
            Log.e("UsbTestActivity", "No Usb Device");
        }
    }
    /**
     *
     * 获取device 根目录文件
     *
     * @param device USB 存储设备
     * @return 设备根目录下文件列表
     */
    public ArrayList<UsbFile> readDevice(UsbMassStorageDevice device) {
        Log.i("ZB","start read0");
        ArrayList<UsbFile> usbFiles = new ArrayList<>();
        try {
            Log.i("ZB","start read");
            //初始化
            device.init();
            //获取partition
            Partition partition = device.getPartitions().get(0);
            FileSystem currentFs = partition.getFileSystem();
            //获取根目录
            UsbFile root = currentFs.getRootDirectory();
            this.currentFolder = root;
            Log.i("ZB","root-"+root.getAbsolutePath());
            //将文件列表添加到ArrayList中
            Collections.addAll(usbFiles, root.listFiles());

        } catch (Exception e) {
            Log.i("ZB","start read error"+e.toString());
            e.printStackTrace();
        }
        Log.i("ZB","usbFiles read"+usbFiles.size());
        return usbFiles;
    }

    private final BroadcastReceiver mUsbPermissionActionReceiver = new BroadcastReceiver() {
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (ACTION_USB_PERMISSION.equals(action)) {
                synchronized (this) {
                    UsbDevice usbDevice = (UsbDevice) intent.getParcelableExtra(UsbManager.EXTRA_DEVICE);
                    if (intent.getBooleanExtra(UsbManager.EXTRA_PERMISSION_GRANTED, false)) {
                        //user choose YES for your previously popup window asking for grant perssion for this usb device
                        if (null != usbDevice) {
                            afterGetUsbPermission(usbDevice);
                        }
                    } else {
                        //user choose NO for your previously popup window asking for grant perssion for this usb device
                        Toast.makeText(context, String.valueOf("Permission denied for device" + usbDevice), Toast.LENGTH_LONG).show();
                    }
                }
            }
        }
    };
    public void destroy()
    {
        context.unregisterReceiver(mUsbPermissionActionReceiver);
    }
    /**
     * 复制文件到 USB
     *
     * @param targetFile       需要复制的文件
     * @param saveFolder       复制的目标文件夹
     * @return 复制结果
     */
    public boolean saveSDFileToUsb(File targetFile, UsbFile saveFolder) {
        boolean result;
        try {
            //USB文件是否存在z
            boolean isExist = false;
            UsbFile saveFile = null;
            for (UsbFile usbFile : saveFolder.listFiles()) {
                if (usbFile.getName().equals(targetFile.getName())) {
                    isExist = true;
                    saveFile = usbFile;
                }
            }
            if (isExist) {
                //文件已存在，删除文件
                saveFile.delete();
            }
            //创建新文件
            saveFile = saveFolder.createFile(targetFile.getName());
            //开始写入
            FileInputStream fis = new FileInputStream(targetFile);//读取选择的文件的
            int avi = fis.available();
            UsbFileOutputStream uos = new UsbFileOutputStream(saveFile);
            int bytesRead;
            byte[] buffer = new byte[1024 * 8];
            int writeCount = 0;
            while ((bytesRead = fis.read(buffer)) != -1) {
                uos.write(buffer, 0, bytesRead);
                writeCount += bytesRead;
//                Log.e(TAG, "Progress : " + (writeCount * 100 / avi));
            }
            uos.flush();
            fis.close();
            uos.close();
            result = true;
        } catch (final Exception e) {
            e.printStackTrace();
            Log.i("USB","text=="+e.toString());
            result = false;
        }
        return result;
    }
}
