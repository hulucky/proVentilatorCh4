package com.ventilator.main;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.hardware.Sensor;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.greendao.manager.DataTFJ;
import com.jaeger.library.StatusBarUtil;
import com.sensor.SensorData;
import com.sensor.view.SensorView;
import com.ventilator.Tools.MyFunction;
import com.ventilator.Utils.SharedPrefrenceUtils;
import com.ventilator.administrator.DATAbase.R;
import com.ventilator.app.MyApp;
import com.ventilator.serialport.ComAssistant.SerialHelper;
import com.ventilator.serialport.bean.ComBean;
import com.ventilator.test.TestActivity;


import java.io.IOException;
import java.security.InvalidParameterException;
import java.text.DecimalFormat;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import butterknife.BindView;
import butterknife.BindViews;
import butterknife.ButterKnife;
import es.dmoral.toasty.Toasty;

public class SensorActivity extends AppCompatActivity {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.wsd_sensor)
    SensorView wsdSensor;
    @BindView(R.id.wd_sensor)
    TextView tvwdSensor;
    @BindView(R.id.sd_sensor)
    TextView tvsdSensor;
    @BindView(R.id.dqy_sensor)
    TextView tvdqySensor;
    @BindView(R.id.jy_sensor)
    SensorView jySensor;
    @BindView(R.id.tvjy_sensor)
    TextView tvjySensor;
    @BindView(R.id.cy_sensor)
    SensorView cySensor;
    @BindView(R.id.tvcy_sensor)
    TextView tvcySensor;
    @BindView(R.id.gl1_sensor)
    SensorView gl1Sensor;
    @BindView(R.id.UAB1_sensor)
    TextView UAB1Sensor;
    @BindView(R.id.UBC1_sensor)
    TextView UBC1Sensor;
    @BindView(R.id.UCA1_sensor)
    TextView UCA1Sensor;
    @BindView(R.id.IA1_sensor)
    TextView IA1Sensor;
    @BindView(R.id.IB1_sensor)
    TextView IB1Sensor;
    @BindView(R.id.IC1_sensor)
    TextView IC1Sensor;
    @BindView(R.id.P1_sensor)
    TextView P1Sensor;
    @BindView(R.id.Q1_sensor)
    TextView Q1Sensor;
    @BindView(R.id.cos1_sensor)
    TextView cos1Sensor;
    @BindView(R.id.gl2_sensor)
    SensorView gl2Sensor;
    @BindView(R.id.UAB2_sensor)
    TextView UAB2Sensor;
    @BindView(R.id.UBC2_sensor)
    TextView UBC2Sensor;
    @BindView(R.id.UCA2_sensor)
    TextView UCA2Sensor;
    @BindView(R.id.IA2_sensor)
    TextView IA2Sensor;
    @BindView(R.id.IB2_sensor)
    TextView IB2Sensor;
    @BindView(R.id.IC2_sensor)
    TextView IC2Sensor;
    @BindView(R.id.P2_sensor)
    TextView P2Sensor;
    @BindView(R.id.Q2_sensor)
    TextView Q2Sensor;
    @BindView(R.id.cos2_sensor)
    TextView cos2Sensor;
    @BindView(R.id.tv_ch4)
    TextView tvCh41;

    @BindView(R.id.fs_sensor)
    SensorView zjfs;
    @BindView(R.id.sj_sensor)
    SensorView zjsj;
    @BindView(R.id.ch4_sensor)
    SensorView ch4Sensor;
    @BindView(R.id.tvCH4_sensor)
    TextView tvch4;
    //未连接时一直隐藏
    @BindView(R.id.linearLayout7)
    LinearLayout linearLayout7;
    @BindView(R.id.linearLayout8)
    LinearLayout linearLayout8;
    @BindView(R.id.llhu1)
    LinearLayout llhu1;
    @BindView(R.id.llhu2)
    LinearLayout llhu2;
    @BindViews({R.id.fb_sensor_1,
            R.id.fb_sensor_2,
            R.id.fb_sensor_3,
            R.id.fb_sensor_4,
            R.id.fb_sensor_5,
            R.id.fb_sensor_6,
            R.id.fb_sensor_7,
            R.id.fb_sensor_8,
            R.id.fb_sensor_9,
            R.id.fb_sensor_10,
            R.id.fb_sensor_11,
            R.id.fb_sensor_12,
            R.id.fb_sensor_13,
            R.id.fb_sensor_14,
            R.id.fb_sensor_15,
            R.id.fb_sensor_16,
            R.id.fb_sensor_17,
            R.id.fb_sensor_18,
            R.id.fb_sensor_19,
            R.id.fb_sensor_20,
            R.id.fb_sensor_21,
            R.id.fb_sensor_22,
            R.id.fb_sensor_23,
            R.id.fb_sensor_24
    })
    List<SensorView> fblist;//sensorView集合

    @BindViews({R.id.fs_sensor_1,
            R.id.fs_sensor_2,
            R.id.fs_sensor_3,
            R.id.fs_sensor_4,
            R.id.fs_sensor_5,
            R.id.fs_sensor_6,
            R.id.fs_sensor_7,
            R.id.fs_sensor_8,
            R.id.fs_sensor_9,
            R.id.fs_sensor_10,
            R.id.fs_sensor_11,
            R.id.fs_sensor_12,
            R.id.fs_sensor_13,
            R.id.fs_sensor_14,
            R.id.fs_sensor_15,
            R.id.fs_sensor_16,
            R.id.fs_sensor_17,
            R.id.fs_sensor_18,
            R.id.fs_sensor_19,
            R.id.fs_sensor_20,
            R.id.fs_sensor_21,
            R.id.fs_sensor_22,
            R.id.fs_sensor_23,
            R.id.fs_sensor_24
    })
    List<TextView> fslist;//textView集合


    private boolean shuaXin;
    private static float[] mFsList;
    private static long pretime = 0;


    public static volatile long[] IsTx = new long[32]; // 测试线程修改参数
    public static volatile long CaijiTime = 0;
    public static int TxDelay = 5;
    private float JyZero = 0f;
    private float CyZero = 0f;
    private static float Jy = 0f;
    private static float Cy = 0f;
    public static DataTFJ mdata;

    //<editor-fold desc="Description">
    private boolean IsStart;
    //</editor-fold>
    private Handler handler;
    static DecimalFormat df4 = new DecimalFormat("####0.00");

    SerialControl ComA;
    DispQueueThread DispQueue;
    public static Context context;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        // 隐藏状态栏
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        //保持屏幕常亮
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        this.setContentView(R.layout.activity_sensor);

        getWindow().setFlags(
                WindowManager.LayoutParams.FLAG_HARDWARE_ACCELERATED,
                WindowManager.LayoutParams.FLAG_HARDWARE_ACCELERATED);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);  //决定左上角的图标是否可以点击
        StatusBarUtil.setColor(this, getResources().getColor(R.color.tittleBar), 0);
        mdata = new DataTFJ();
        mdata.initSensors();
        handler = new Handler();
        //mFsList  16个风杯的数据
        mFsList = new float[24];
        shuaXin = true;

        context = this;

        jiaolingdialog();

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        ComA = new SerialControl();
        setControls();
        DispQueueStart();
        ComA.setiDelay(10);  // 设置读取串口的时间间隔
        if (IsStart == false) {
            handler.postDelayed(runnable, 1000);
            IsStart = true;
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        // DataType.DATA_OK_PARSE : 返回整的串口数据包
        // DataType.DATA_NO_PARSE : 返回不进行校验的数据，不按完整数据包返回。

        if (!SharedPrefrenceUtils.getCanShowEight(this)) {
            linearLayout7.setVisibility(View.GONE);
            linearLayout8.setVisibility(View.GONE);
            llhu1.setVisibility(View.GONE);
            llhu2.setVisibility(View.GONE);
        }
        if (SharedPrefrenceUtils.getCanShowCh4(context)) {
            ch4Sensor.setVisibility(View.VISIBLE);
            tvCh41.setVisibility(View.VISIBLE);
            tvch4.setVisibility(View.VISIBLE);
        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
//        ComControl.CloseComPort(ComA);
        handler.removeCallbacksAndMessages(null);
    }

    //----------------------------------------------------刷新显示线程


    void jiaolingdialog() {
        final AlertDialog dialog = new AlertDialog.Builder(this, R.style.dialog).create();
        dialog.setView(LayoutInflater.from(this).inflate(R.layout.dialogjiaoling, null));
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();
        dialog.getWindow().setContentView(R.layout.dialogjiaoling);
        Button btnjy = (Button) dialog.findViewById(R.id.btn_jy);
        Button btncy = (Button) dialog.findViewById(R.id.btn_cy);
        Button btngb = (Button) dialog.findViewById(R.id.btn_gb);
        btnjy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                JyZero = Jy;
                Toasty.success(SensorActivity.this, "静压传感器已校零！", Toast.LENGTH_SHORT, true).show();
            }
        });
        btncy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CyZero = Cy;
                Toasty.success(SensorActivity.this, "差压传感器已校零！", Toast.LENGTH_SHORT, true).show();
            }
        });
        btngb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
    }

    //----------------------------------------------------刷新显示线程
    private class DispQueueThread extends Thread {
        private Queue<ComBean> QueueList = new LinkedList<ComBean>();

        @Override
        public void run() {
            super.run();
            while (!isInterrupted()) {
                final ComBean ComData;

                try {
                    while ((ComData = QueueList.poll()) != null) {
                        runOnUiThread(new Runnable() {
                            public void run() {
                                DispRecData(ComData);
                            }
                        });
                        try {
                            Thread.sleep(10);//显示性能高的话，可以把此数值调小。
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        break;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        public synchronized void AddQueue(ComBean ComData) {
            QueueList.add(ComData);
        }
    }

    private void DispRecData(ComBean comRecData) {
        xianshi(shuaXin, comRecData);
    }

    // ----------------smilekun---------------------------------——显示线程中测试数据解析加显示

    void xianshi(boolean xianshi, ComBean ComRecData) {
        int msingal, mpower;
        if (System.currentTimeMillis() > pretime + 1000) {
            pretime = System.currentTimeMillis();
        }
        int Leixing = Math.abs((int) ComRecData.bRec[9]);
        // DecimalFormat df4 = new DecimalFormat("####00.00");
        switch (Leixing) {
            case 16: // 10 温度传感器
                if (ComRecData.bRec.length == 30) {
                    String wendu = df4.format((float) MyFunction.byte2float(
                            ComRecData.bRec, 14));
                    mdata.setmWd(Float.parseFloat(wendu));
//                    tvwdSensor.setText(wendu + "℃");
                    String Shidu = df4.format((float) MyFunction.byte2float(
                            ComRecData.bRec, 18));
                    mdata.setmSd(Float.parseFloat(Shidu));
//                    tvsdSensor.setText(Shidu + "%RH");
                    String Dqy = df4.format((float) MyFunction.byteArrayToInt(
                            ComRecData.bRec, 22) / 100);
                    mdata.setmDqy(Float.parseFloat(Dqy));
//                    tvdqySensor.setText(Dqy + "hPA");
                    msingal = ComRecData.bRec[28] < 0 ? 256 + ComRecData.bRec[28] : ComRecData.bRec[28];
                    mpower = MyFunction.twoBytesToInt(ComRecData.bRec, 26);
                    mdata.setSensor(0, mpower, msingal, 1);
//                    testFragment.msensorfragment.SetSensor("温湿度", mpower, msingal, 1);
                    SetFiveOne(0);
                }
                break;
            case 80:// 风速
                float mFs = 0.0f;
                if (ComRecData.bRec.length == 20) {
                    float a = ((float) MyFunction.HexToInt(MyFunction.ByteArrToHex(
                            ComRecData.bRec, 14, 16)) / 100);
                    if (a > 0 || a < 50) {
                        mFs = a;
                    }
                }
                if (mFs > 5 && mFs <= 7) {
                    mFs = (float) (mFs - (mFs - 5) / 2 * 0.5);
                } else if (mFs > 7) {
                    mFs = (float) (mFs - 0.5);
                }
                int fsindex = (int) ComRecData.bRec[10];
                //判断当第17个及之后的传感器连上时，就显示最后两排
                if (fsindex >= 17) {
                    SharedPrefrenceUtils.setCanShowEight(context, true);
                }
                boolean canShowEight = SharedPrefrenceUtils.getCanShowEight(context);
                if (canShowEight) {
                    showEight.canShowChanged();
                }
                msingal = ComRecData.bRec[18] < 0 ? 256 + ComRecData.bRec[18] : ComRecData.bRec[18];
                mpower = MyFunction.twoBytesToInt(ComRecData.bRec, 16);
                if (fsindex > 0) {
//                    SetSensorState(fblist.get(fsindex - 1), mpower, msingal, 1);
//                    fslist.get(fsindex - 1).setText(df4.format(mFs) + "m/s");
                    mdata.setSensor(fsindex + 4, mpower, msingal, 1);
                    mFsList[fsindex - 1] = mFs;
                }
                SetFiveOne(fsindex - 1 + 5);
                break;
            case 82://中继
                if (ComRecData.bRec.length == 60)//风杯包
                {
                    int fszjindex = 0;//风速中继
                    if (ComRecData.bRec[13] == 2)//二号包9-16
                    {
                        fszjindex = 8;
                    } else if (ComRecData.bRec[13] == 1)//一号包1-8
                    {
                        fszjindex = 0;
                    } else if (ComRecData.bRec[13] == 4) {//三号包17-24
                        fszjindex = 16;
                        SharedPrefrenceUtils.setCanShowEight(context, true);
//                        Log.d("dfgodfgd", "ComRecData.bRec[13] == 4");
                    }
                    if (SharedPrefrenceUtils.getCanShowEight(context)) {
                        showEight.canShowChanged();
                    }
                    for (int i = 0; i < 8; i++) {
                        mFs = 0.0f;
                        float a = ((float) MyFunction.HexToInt(MyFunction.ByteArrToHex(
                                ComRecData.bRec, 14 + i * 5, 16 + i * 5)) / 100);
                        msingal = ComRecData.bRec[18 + i * 5] < 0 ? 256 + ComRecData.bRec[18 + i * 5] : ComRecData.bRec[18 + i * 5];
                        mpower = MyFunction.twoBytesToInt(ComRecData.bRec, 16 + i * 5);
                        if (a > 0 || a < 50) {
                            mFs = a;
                        }
                        if (mFs > 5 && mFs <= 7) {
                            mFs = (float) (mFs - (mFs - 5) / 2 * 0.5);
                        } else if (mFs > 7) {
                            mFs = (float) (mFs - 0.5);
                        }
                        if (msingal != 0) {
                            mFsList[fszjindex + i] = mFs;

                            SetFiveOne(fszjindex + i + 5);
                            mdata.setSensor(fszjindex + i + 5, mpower, msingal, 1);
                        }
                    }
                    int mcjpower = MyFunction.twoBytesToInt(ComRecData.bRec, 54);
                    int mfspower = MyFunction.twoBytesToInt(ComRecData.bRec, 56);
                    int mzjsingal = ComRecData.bRec[58] < 0 ? 256 + ComRecData.bRec[58] : ComRecData.bRec[58];
                    mdata.setSensor(29, mcjpower, mzjsingal, 1);
                    mdata.setSensor(30, mfspower, mzjsingal, 1);
                    SetFiveOne(29);
                    SetFiveOne(30);
                } else if (ComRecData.bRec.length == 35)//中继温湿度
                {
                    String wendu = df4.format((float) MyFunction.byte2float(
                            ComRecData.bRec, 14));
                    String Shidu = df4.format((float) MyFunction.byte2float(
                            ComRecData.bRec, 18));
                    String Dqy = df4.format((float) MyFunction.byteArrayToInt(
                            ComRecData.bRec, 22) / 100);

                    mdata.setmWd(Float.parseFloat(wendu));
                    mdata.setmSd(Float.parseFloat(Shidu));
                    mdata.setmDqy(Float.parseFloat(Dqy));

                    msingal = ComRecData.bRec[28] < 0 ? 256 + ComRecData.bRec[28] : ComRecData.bRec[28];
                    mpower = MyFunction.twoBytesToInt(ComRecData.bRec, 26);
                    int mcjpower = MyFunction.twoBytesToInt(ComRecData.bRec, 29);
                    int mfspower = MyFunction.twoBytesToInt(ComRecData.bRec, 31);
                    int mzjsingal = ComRecData.bRec[33] < 0 ? 256 + ComRecData.bRec[33] : ComRecData.bRec[33];
                    if ((Float.parseFloat(wendu) != 0.0f)) {
                        mdata.setSensor(0, mpower, msingal, 1);
                        SetFiveOne(0);
                    }
                    mdata.setSensor(29, mcjpower, mzjsingal, 1);
                    mdata.setSensor(30, mfspower, mzjsingal, 1);
                    SetFiveOne(29);
                    SetFiveOne(30);
                }
                break;
            case 34: // 22 静压传感器
                if (ComRecData.bRec.length == 33) {
                    String jingya = df4.format((float) MyFunction.twoByte2int(
                            ComRecData.bRec, 27));
                    int FuHAo = ComRecData.bRec[26] | 0x00;
                    String jingyaliString;
                    jingyaliString = jingya;
                    Jy = Float.parseFloat(jingyaliString);
                    mdata.setmJy(Jy);
//                    tvjySensor.setText(df4.format(Jy - JyZero) + "Pa");
                    msingal = ComRecData.bRec[31] < 0 ? 256 + ComRecData.bRec[31] : ComRecData.bRec[31];
                    mpower = MyFunction.twoBytesToInt(ComRecData.bRec, 29);
                    mdata.setSensor(1, mpower, msingal, 1);
//                    testFragment.msensorfragment.SetSensor("静压", mpower, msingal, 1);
                    SetFiveOne(1);
                }
                break;
            case 48: // 30 //差压
                if (ComRecData.bRec.length == 33) {
                    String jingya = df4.format((float) MyFunction.twoByte2int(
                            ComRecData.bRec, 27));
                    int FuHAo = ComRecData.bRec[26] | 0x00;
                    String chukouyaString;
                    chukouyaString = jingya;

                    Cy = Float.parseFloat(chukouyaString);
                    mdata.setmDy(Cy);
//                    tvcySensor.setText(df4.format(Cy - CyZero) + "Pa");
                    msingal = ComRecData.bRec[31] < 0 ? 256 + ComRecData.bRec[31] : ComRecData.bRec[31];
                    mpower = MyFunction.twoBytesToInt(ComRecData.bRec, 29);
                    mdata.setSensor(2, mpower, msingal, 1);
//                    SetSensorState(cySensor, mpower, msingal, 1);
//                    testFragment.msensorfragment.SetSensor("差压", mpower, msingal, 1);
                    SetFiveOne(2);
                }
                break;
            case 128: // 80 功率传感器
                // （Ua）： A相电压值。 实际值＝（Ua）／10000*（U0）*（UbB） V
                // （Ub）： B相电压值。 实际值＝（Ub）／10000*（U0）*（UbB） V
                // （UC）： C相电压值。 实际值＝（UC）／10000*（U0）*（UbB） V
                // （IA）： A相电流值。 实际值＝（IA）／10000*（I0）*（IBB） A
                // （IB）： B相电流值。 实际值＝（IB）／10000*（I0）*（IBB） A
                // （IC）： C相电流值。 实际值＝（IC）／10000*（I0）*（IBB） A
                // （P）： 总有功功率值。 实际值＝ ±（P）／10000 *3*（U0）*（I0）*（UbB）*（IBB） W
                // （Q）： 总无功功率值。 实际值＝±（Q）／10000 *3*（U0）*（I0）*（UbB）*（IBB） Var
                if (ComRecData.bRec.length == 36) {
                    float mDybb = 500f, mDlbb = 500f;
                    float Ua = ((float) MyFunction.twoByte2int(ComRecData.bRec, 14) / 10000) * mDybb;
                    float Ub = ((float) MyFunction.twoByte2int(ComRecData.bRec, 18) / 10000 * mDybb);
                    float Uc = ((float) MyFunction.twoByte2int(ComRecData.bRec, 22) / 10000 * mDybb);
                    float Ia = ((float) MyFunction.twoByte2int(ComRecData.bRec, 16) / 10000 * mDlbb);
                    float Ib = ((float) MyFunction.twoByte2int(ComRecData.bRec, 20) / 10000 * mDlbb);
                    float Ic = ((float) MyFunction.twoByte2int(ComRecData.bRec, 24) / 10000 * mDlbb);

                    float P = ((float) MyFunction.twoBytesToIntHave(
                            ComRecData.bRec[26], ComRecData.bRec[27])
                            / 10000000 * 3 * mDybb * mDlbb);
                    float Q = ((float) MyFunction.twoBytesToIntHave(
                            ComRecData.bRec[28], ComRecData.bRec[29])
                            / 10000000 * 3 * mDybb * mDlbb);
                    float S = 0f;
                    float Cos = ((float) MyFunction.twoByte2int_(ComRecData.bRec,
                            30) / 10000);
                    // 低电流电压高变比情况下的功率计算
                    // if (szgl == 0.0d)
                    if ((Ua / 1) <= 150) {
                        float aszgl = (Ua * Ia
                                // / 1000
                        );
                        float ayggl = (aszgl * Cos);
                        float awggl = (float) (Math.sqrt(Math.pow(aszgl, 2)
                                - Math.pow(ayggl, 2)));

                        float bszgl = (Ub * Ib
                                // / 1000
                        );
                        float byggl = (bszgl * Cos);
                        float bwggl = (float) (Math.sqrt(Math.pow(bszgl, 2)
                                - Math.pow(byggl, 2)));

                        float cszgl = (Uc * Ic
                                // / 1000
                        );
                        float cyggl = (cszgl * Cos);
                        float cwggl = (float) (Math.sqrt(Math.pow(cszgl, 2)
                                - Math.pow(cyggl, 2)));


                        S = (aszgl + bszgl + cszgl) / 1000;
                        P = S * Cos;
                        Q = (float) Math.sqrt(Math.pow(S, 2) - Math.pow(P, 2));
                    }
                    float cs = (float) Math.sqrt(3);
                    if (ComRecData.bRec[10] == 1) {
                        float u0 = (Ua + Ub + Uc) / 3;
                        float i0 = (Ia + Ib + Ic) / 3;
                        mdata.setUab(cs * Ua);
                        mdata.setUbc(cs * Ub);
                        mdata.setUca(cs * Uc);
                        mdata.setIa(Ia);
                        mdata.setIb(Ib);
                        mdata.setIc(Ic);
                        mdata.setP1(P);
                        mdata.setQ1(Q);
                        mdata.setCos1(Cos);
//                        UAB1Sensor.setText(df4.format(cs * Ua) + "V");
//                        UBC1Sensor.setText(df4.format(cs * Ub) + "V");
//                        UCA1Sensor.setText(df4.format(cs * Uc) + "V");
//                        IA1Sensor.setText(df4.format(Ia) + "A");
//                        IB1Sensor.setText(df4.format(Ib) + "A");
//                        IC1Sensor.setText(df4.format(Ic) + "A");
//                        P1Sensor.setText(df4.format(P) + "kW");
//                        Q1Sensor.setText(df4.format(Q) + "kVA");
//                        cos1Sensor.setText(df5.format(Cos));
                        msingal = ComRecData.bRec[34] < 0 ? 256 + ComRecData.bRec[34] : ComRecData.bRec[34];
                        mpower = MyFunction.twoBytesToInt(ComRecData.bRec, 32);
                        mdata.setSensor(3, mpower, msingal, 1);
//                        testFragment.msensorfragment.SetSensor("功率1", mpower, msingal, 1);
                        //   mwgglTextView.setText(df4.format(Q));
                        SetFiveOne(3);
                    } else if (ComRecData.bRec[10] == 2) {
                        float u1 = (Ua + Ub + Uc) / 3;
                        float i1 = (Ia + Ib + Ic) / 3;
                        mdata.setUab2(cs * Ua);
                        mdata.setUbc2(cs * Ub);
                        mdata.setUca2(cs * Uc);
                        mdata.setIa2(Ia);
                        mdata.setIb2(Ib);
                        mdata.setIc2(Ic);
                        mdata.setP2(P);
                        mdata.setQ2(Q);
                        mdata.setCos2(Cos);
//                        UAB2Sensor.setText(df4.format(cs * Ua) + "V");
//                        UBC2Sensor.setText(df4.format(cs * Ub) + "V");
//                        UCA2Sensor.setText(df4.format(cs * Uc) + "V");
//                        IA2Sensor.setText(df4.format(Ia) + "A");
//                        IB2Sensor.setText(df4.format(Ib) + "A");
//                        IC2Sensor.setText(df4.format(Ic) + "A");
//                        P2Sensor.setText(df4.format(P) + "kW");
//                        Q2Sensor.setText(df4.format(Q) + "kVA");
//                        cos2Sensor.setText(df5.format(Cos));
                        msingal = ComRecData.bRec[34] < 0 ? 256 + ComRecData.bRec[34] : ComRecData.bRec[34];
                        mpower = MyFunction.twoBytesToInt(ComRecData.bRec, 32);
                        mdata.setSensor(4, mpower, msingal, 1);
//                        testFragment.msensorfragment.SetSensor("功率2", mpower, msingal, 1);
                        //     mwgglTextView2.setText(df4.format(Q));
                        //   mwgglTextView.setText(df4.format(Q));
                        SetFiveOne(4);
                    }
                }
                break;
            case 78:// B2 甲烷传感器
            case -89:// B2甲烷传感器
                if (ComRecData.bRec.length == 33) {
                    //允许显示甲烷
                    SharedPrefrenceUtils.setCanShowCh4(context, true);
                    ch4Sensor.setVisibility(View.VISIBLE);
                    tvCh41.setVisibility(View.VISIBLE);
                    tvch4.setVisibility(View.VISIBLE);
                    double ch4 = 0;
//                    double SumCh4 = 0;
//                    for (int i = 0; i < 5; i++) {
//                        String jingya = df4.format((float) MyFunction.twoByte2int(
//                                ComRecData.bRec, 14 + i * 3));
////                    int FuHAo = ComRecData.bRec[26] | 0x00;
//                        String chukouyaString;
//                        chukouyaString = jingya;
//                        ch4 = Float.parseFloat(chukouyaString);
//                        SumCh4 += ch4;
//                    }
////                    mdata.setCh4(SumCh4 / 5);
                    String jingya = df4.format((float) MyFunction.twoByte2int(
                            ComRecData.bRec, 27));
                    //       int FuHAo = ComRecData.bRec[26] | 0x00;
                    String chukouyaString;
                    chukouyaString = jingya;

                    ch4 = Float.parseFloat(chukouyaString);
                    mdata.setCh4(ch4 / 100);

//                    tvcySensor.setText(df4.format(Cy - CyZero) + "Pa");
                    msingal = ComRecData.bRec[31] < 0 ? 256 + ComRecData.bRec[31] : ComRecData.bRec[31];
                    mpower = MyFunction.twoBytesToInt(ComRecData.bRec, 29);
                    mdata.setSensor(31, mpower, msingal, 1);
//                    SetSensorState(cySensor, mpower, msingal, 1);
//                    testFragment.msensorfragment.SetSensor("差压", mpower, msingal, 1);
                    SetFiveOne(31);
                }
            default:
                break;
        }
        // ShowData();
    }


    public interface ShowEight {
        void canShowChanged();
    }

    public ShowEight showEight = new ShowEight() {
        @Override
        public void canShowChanged() {
            Log.i("dfgdfsasda", "==============setVisibility: View.VISIBLE");
            //显示后两排
            linearLayout7.setVisibility(View.VISIBLE);
            linearLayout8.setVisibility(View.VISIBLE);
            llhu1.setVisibility(View.VISIBLE);
            llhu2.setVisibility(View.VISIBLE);
        }
    };


    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            DataTFJ.sensor[] ms = mdata.getSensors();
            if (TimeBetween(IsTx[0]) > TxDelay * 1000) {
                tvwdSensor.setText("--");
                tvsdSensor.setText("--");
                tvdqySensor.setText("--");

            } else if (TimeBetween(IsTx[0]) < 1000) {
                tvwdSensor.setText(df4.format(mdata.getmWd()) + " ℃");
                tvsdSensor.setText(df4.format(mdata.getmSd()) + " %RH");
                tvdqySensor.setText(df4.format(mdata.getmDqy()) + " hPa");
                SetSensor(wsdSensor, 0);
            }
            // jingya
            if (TimeBetween(IsTx[1]) > TxDelay * 1000) {

                tvjySensor.setText("--");
            } else {
                tvjySensor.setText(df4.format(mdata.getmJy() - JyZero) + " Pa");
                if (TimeBetween(IsTx[1]) < 1000) {
                    SetSensor(jySensor, 1);
                }
            }
            // chaya
            if (TimeBetween(IsTx[2]) > TxDelay * 1000) {


                tvcySensor.setText("--");
            } else {
                tvcySensor.setText(df4.format(mdata.getmDy() - CyZero) + " Pa");
                if (TimeBetween(IsTx[2]) < 1000) {
                    SetSensor(cySensor, 2);
                }
            }
            // gonglv1
            if (TimeBetween(IsTx[3]) > TxDelay * 1000) {

                UAB1Sensor.setText("--");
                UBC1Sensor.setText("--");
                UCA1Sensor.setText("--");
                IA1Sensor.setText("--");
                IB1Sensor.setText("--");
                IC1Sensor.setText("--");
                P1Sensor.setText("--");
                Q1Sensor.setText("--");
                cos1Sensor.setText("--");
            } else {
                UAB1Sensor.setText(df4.format(mdata.getUab()) + " V");
                UBC1Sensor.setText(df4.format(mdata.getUbc()) + " V");
                UCA1Sensor.setText(df4.format(mdata.getUca()) + " V");
                IA1Sensor.setText(df4.format(mdata.getIa()) + " A");
                IB1Sensor.setText(df4.format(mdata.getIb()) + " A");
                IC1Sensor.setText(df4.format(mdata.getIc()) + " A");
                P1Sensor.setText(df4.format(mdata.getP1()) + " kW");
                Q1Sensor.setText(df4.format(mdata.getQ1()) + " kVA");
                cos1Sensor.setText(df4.format(mdata.getCos1()));
                if (TimeBetween(IsTx[3]) < 1000) {
                    SetSensor(gl1Sensor, 3);
                }
            }
            // gonglv2
            if (TimeBetween(IsTx[4]) > TxDelay * 1000) {

                UAB2Sensor.setText("--");
                UBC2Sensor.setText("--");
                UCA2Sensor.setText("--");
                IA2Sensor.setText("--");
                IB2Sensor.setText("--");
                IC2Sensor.setText("--");
                P2Sensor.setText("--");
                Q2Sensor.setText("--");
                cos2Sensor.setText("--");
            } else {
                UAB2Sensor.setText(df4.format(mdata.getUab2()) + " V");
                UBC2Sensor.setText(df4.format(mdata.getUbc2()) + " V");
                UCA2Sensor.setText(df4.format(mdata.getUca2()) + " V");
                IA2Sensor.setText(df4.format(mdata.getIa2()) + " A");
                IB2Sensor.setText(df4.format(mdata.getIb2()) + " A");
                IC2Sensor.setText(df4.format(mdata.getIc2()) + " A");
                P2Sensor.setText(df4.format(mdata.getP2()) + " kW");
                Q2Sensor.setText(df4.format(mdata.getQ2()) + " kVA");
                cos2Sensor.setText(df4.format(mdata.getCos2()));
                if (TimeBetween(IsTx[4]) < 1000) {
                    SetSensor(gl2Sensor, 4);
                }
            }
            // fengsu

            for (int i = 0; i < 24; i++) {
                if (TimeBetween(IsTx[i + 5]) > TxDelay * 1000) {

                    fslist.get(i).setText("--");

                } else {
                    fslist.get(i).setText(df4.format(mFsList[i]) + " m/s");

//                    //显示延迟
//                    fslist.get(i).setText( TimeBetween(IsTx[i + 5])+"");
                    if (TimeBetween(IsTx[i + 5]) < 1000) {
                        SetSensor(fblist.get(i), i + 5);
                    }
                }
            }
            //中继
            if (TimeBetween(IsTx[29]) < 1000 && ms[29] != null) {
                SetSensor(zjsj, 29);
            }
            if (TimeBetween(IsTx[30]) < 1000 && ms[30] != null) {
                SetSensor(zjfs, 30);
            }
            //甲烷
            if (TimeBetween(IsTx[31]) < 1000 && ms[31] != null) {
                SetSensor(ch4Sensor, 31);
                tvch4.setText(df4.format(mdata.getCh4()));
            } else if ((TimeBetween(IsTx[31]) > TxDelay * 1000)) {
                tvch4.setText("--");
            }

            handler.postDelayed(this, 300);
        }
    };

    public static synchronized void SetFiveOne(int index) {
        IsTx[index] = System.currentTimeMillis();
    }


    private long TimeBetween(Long mTime) {
        return System.currentTimeMillis() - mTime;
    }

    private static void SetSensorState(SensorView msv, float mpower, float msignal, int minf) {
        SensorData svData = new SensorData();
        // 第二步：设置 SensorData 属性
        svData.setStatus(minf)
                .setPower(mpower)
                .setSignal(msignal);

        // 第三步：给SensorView 赋值
        msv.setData(svData);
    }

    private static void SetSensor(SensorView msv, Integer index) {
        try {
            DataTFJ.sensor mS = mdata.getSensors()[index];
            if (mS != null) {
                SensorData svData = new SensorData();
                // 第二步：设置 SensorData 属性
                svData.setStatus(mS.getMstate())
                        .setPower(mS.getMpower())
                        .setSignal(mS.getMsignal());

                // 第三步：给SensorView 赋值
                msv.setData(svData);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //    ==============================串口控制相关方法================================================//
    // ----------------------------------------------------设置串口
    private void setControls() {

        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.KITKAT_WATCH) {
            ComA.setPort("/dev/ttyMT2");
        } else {
            ComA.setPort("/dev/ttyMT1");
        }

        ComA.setBaudRate("115200");
        OpenComPort(ComA);
    }

    //----------------------------------------------------串口控制类
    private class SerialControl extends SerialHelper {

        public SerialControl() {
        }

        protected void onDataReceived(ComBean ComRecData) {
            // TODO Auto-generated method stub
            DispQueue.AddQueue(ComRecData); //线程定时刷新显示(推荐)
        }
    }

    public void DispQueueStart() {
        //串口控制
        ComA = new SerialControl();
        DispQueue = new DispQueueThread();
        setControls();
        DispQueue.start();
    }

    /**
     * function ：打开串口
     */
    public void OpenComPort(SerialHelper ComPort) {
        try {
            ComPort.open();
        } catch (SecurityException e) {
            Toasty.info(this, "打开串口失败:没有串口读/写权限!");
        } catch (IOException e) {
            Toasty.info(this, "打开串口失败:未知错误!");
        } catch (InvalidParameterException e) {
            Toasty.info(this, "打开串口失败:参数错误!");
        }
    }
}

