package com.ventilator.test;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.ViewUtils;
import android.text.format.Time;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.flyco.tablayout.listener.OnTabSelectListener;
import com.greendao.dbUtils.GreateTaskUtils;
import com.greendao.manager.DataTFJ;
import com.greendao.manager.motorData;
import com.jaeger.library.StatusBarUtil;
import com.ldoublem.loadingviewlib.view.LVPlayBall;
import com.sensor.SensorData;
import com.sensor.view.SensorView;
import com.ventilator.Adapter.TestPagerAdapter;

import com.ventilator.Tools.DataCleanManager;
import com.ventilator.Tools.MyFunction;

import com.ventilator.administrator.DATAbase.R;
import com.ventilator.administrator.DATAbase.greendao.TaskEntity;
import com.ventilator.administrator.DATAbase.greendao.TaskResEnity;
import com.ventilator.app.MyApp;

import com.ventilator.serialport.ComAssistant.SerialHelper;
import com.ventilator.serialport.bean.ComBean;
import com.ventilator.test.fragment.DataFragment;
import com.ventilator.test.fragment.ParameterFragment;
import com.ventilator.test.fragment.TestFragment;


import java.io.IOException;
import java.security.InvalidParameterException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.io.File;

import butterknife.BindView;
import butterknife.ButterKnife;
import es.dmoral.toasty.Toasty;

import static com.ventilator.Tools.MyFunction.bytes2HexString;

public class TestActivity extends AppCompatActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.viewPager)
    ViewPager viewPager;
    @BindView(R.id.tl)
    com.flyco.tablayout.SlidingTabLayout tl;
    @BindView(R.id.loadding)
    LVPlayBall loadding;
    @BindView(R.id.tv_ch4)
    TextView tvCh4;
    @BindView(R.id.tv_zhuansu)
    TextView tvZhuanSu;
    public MyApp myApp;
    public DataTFJ mdata;
    public motorData mmotor1data;
    public motorData mmotor2data;

    SerialControl ComA;
    DispQueueThread DispQueue;
    ParameterFragment parameterFragment;
    DataFragment dataFragment;
    TestFragment testFragment;
    DecimalFormat df2 = new DecimalFormat("####0.00");

    DecimalFormat df3 = new DecimalFormat("####0.000");

    DecimalFormat df1 = new DecimalFormat("####0.0");


    public boolean isShuaXin() {
        return shuaXin;
    }

    public void setShuaXin(boolean shuaXin) {
        this.shuaXin = shuaXin;
    }

    private boolean shuaXin;
    private boolean DjShuaxin;

    public float[] getmFsList() {
        return mFsList;
    }

    public void setmFsList(float[] mFsList) {
        this.mFsList = mFsList;
    }

    private float[] mFsList;
    float mPjfs = 0.0f;

    public float[] getmLockFsList() {
        return mLockFsList;
    }

    public void setmLockFsList(float[] mLockFsList) {
        this.mLockFsList = mLockFsList;
    }

    private float[] mLockFsList;
    private long pretime = 0;
    private TaskEntity mtask;

    public static volatile long[] IsTx = new long[25]; // 各传感器通讯时间最后一次通讯时间
    public static volatile long CaijiTime = 0;
    public static int TxDelay = 10;
    private float JyZero = 0f;
    private float CyZero = 0f;
    private float Jy = 0f;
    private float Cy = 0f;

    //<editor-fold desc="Description">
    private boolean IsStart;
    //</editor-fold>
    private Handler handler;


    public synchronized void SetFiveOne(int index) {
        IsTx[index] = System.currentTimeMillis();
    }

    public synchronized void SetCaiji() {
        CaijiTime = System.currentTimeMillis();
    }

    private long TimeBetween(Long mTime) {
        return System.currentTimeMillis() - mTime;
    }


    @Override
    public void onBackPressed() {

        Dialog dialog = new AlertDialog.Builder(TestActivity.this).setTitle("选择此任务状态")
                .setIcon(R.drawable.complete)
                .setMessage("测试任务是否完成？")
                .setPositiveButton("已完成", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        mtask.set_IsCompleteTask(true);

                        GreateTaskUtils.update(mtask);
                        dialog.dismiss();
                        finish();
//                        onDestroy();


                    }
                }).setNegativeButton("未完成", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        finish();
//                        onDestroy();
                    }
                }).show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        ButterKnife.bind(this);
        StatusBarUtil.setColor(this, getResources().getColor(R.color.tittleBar), 0);
        setBackArrowStyle();
        myApp = MyApp.getInstance();
        handler = new Handler();

        showLoading();
        getData();
        mFsList = new float[16];
        mLockFsList = new float[16];
        shuaXin = true;
        DjShuaxin = true;
        new Thread(new Runnable() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        initViewPager();
                        hideLoading();
                    }
                });
            }
        }).start();

        showjiaolingdialog();
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        mtask = MyApp.getTaskEnity();
        ComA = new SerialControl();
        setControls();
        DispQueueStart();
        ComA.setiDelay(10);  // 设置读取串口的时间间隔
        if (IsStart == false) {

            handler.postDelayed(runnable, 1000);
            IsStart = true;
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

    private void showjiaolingdialog() {

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
                Toasty.success(TestActivity.this, "静压传感器已校零！", Toast.LENGTH_SHORT, true).show();
            }
        });
        btncy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CyZero = Cy;
                Toasty.success(TestActivity.this, "差压传感器已校零！", Toast.LENGTH_SHORT, true).show();
            }
        });
        btngb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

    }


    private void getData() {
        mdata = new DataTFJ();
        mmotor1data = new motorData();
        mmotor2data = new motorData();
        try {
            mdata.SetHisTask(myApp.getTaskEnity());
            mdata.initSensors();
            mdata.Refresh();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void setBackArrowStyle() {
        setSupportActionBar(toolbar);
        //设置是否有返回箭头
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void showLoading() {
        loadding.setViewColor(R.color.green);
        loadding.setBallColor(R.color.red);
        loadding.startAnim(300);
    }

    private void hideLoading() {
        loadding.setVisibility(View.GONE);
        loadding.stopAnim();
    }

    /*
     * description:初始化Fragment  和 ViewPager
     */
    private void initViewPager() {
        parameterFragment = new ParameterFragment();
        dataFragment = new DataFragment();
        dataFragment.setDataManage(false);
        testFragment = new TestFragment();


        //构造适配器
        List<Fragment> fragments = new ArrayList<>();
        fragments.add(parameterFragment);
        fragments.add(testFragment);
        fragments.add(dataFragment);
        TestPagerAdapter mViewPagerAdapter = new TestPagerAdapter(getSupportFragmentManager(), fragments);
        viewPager.setAdapter(mViewPagerAdapter);
        viewPager.setCurrentItem(1);
        String[] tittle = {"参  数", "测  试", "数  据"};
        tl.setViewPager(viewPager, tittle);
        initEvent();

    }


    private void initEvent() {
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                switch (position) {
                    case 0:
                        tl.setCurrentTab(0, true);
                        break;
                    case 1:
                        tl.setCurrentTab(1, true);
                        break;
                    case 2:
                        tl.setCurrentTab(2, true);
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });


        tl.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
                switch (position) {
                    case 0:
                        viewPager.setCurrentItem(0);
                        break;
                    case 1:
                        viewPager.setCurrentItem(1);
                        break;
                    case 2:
                        viewPager.setCurrentItem(2);
                        dataFragment.GetData();
                        break;
                }
            }

            @Override
            public void onTabReselect(int position) {

            }
        });

    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            //重写ToolBar返回按钮的行为，防止重新打开父Activity重走生命周期方法
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onStart() {
        super.onStart();
        // DataType.DATA_OK_PARSE : 返回整的串口数据包
        // DataType.DATA_NO_PARSE : 返回不进行校验的数据，不按完整数据包返回。


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ComA.close();

        handler.removeCallbacksAndMessages(null);

    }

    public boolean getshuaxin() {
        return shuaXin;
    }

    public void setDjShuaXin(boolean b) {
        DjShuaxin = b;
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
                                String strcom = bytes2HexString(ComData.bRec);
//                                MyApp.getInstance().addQueue(strcom);

                            }
                        });
                        try {
                            Thread.sleep(20);//显示性能高的话，可以把此数值调小。
                        } catch (Exception e) {
                            e.printStackTrace();

                        }
                        break;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    QueueList = new LinkedList<ComBean>();

                }
            }
        }

        public synchronized void AddQueue(ComBean ComData) {
            QueueList.offer(ComData);
        }
    }

    DecimalFormat df22 = new DecimalFormat("####0.00");

    private void DispRecData(ComBean comRecData) {

        xianshi(shuaXin, comRecData);
    }

    // ----------------smilekun---------------------------------——显示线程中测试数据解析加显示

    void xianshi(boolean xianshi, ComBean ComRecData) {
        final DecimalFormat df4 = new DecimalFormat("####00.00");
        final DecimalFormat df2 = new DecimalFormat("####0.0");
        int msingal, mpower;

        int Leixing = Math.abs((int) ComRecData.bRec[9]);
        // DecimalFormat df4 = new DecimalFormat("####00.00");

        switch (Leixing) {

            case 16: // 10 温度传感器
                if (ComRecData.bRec.length == 30) {


                    String wendu = df4.format((float) MyFunction.byte2float(
                            ComRecData.bRec, 14));


                    String Shidu = df4.format((float) MyFunction.byte2float(
                            ComRecData.bRec, 18));


                    String Dqy = df4.format((float) MyFunction.byteArrayToInt(
                            ComRecData.bRec, 22) / 100);

                    if (shuaXin) {
                        mdata.setmWd(Float.parseFloat(mtask.getSdwd() ? mtask.getWd() : wendu));
                        mdata.setmSd(Float.parseFloat(mtask.getSdsd() ? mtask.getSd() : Shidu));
                        mdata.setmDqy(Float.parseFloat(mtask.getSddqy() ? mtask.getDqy() : Dqy));
                    }

                    msingal = ComRecData.bRec[28] < 0 ? 256 + ComRecData.bRec[28] : ComRecData.bRec[28];
                    mpower = MyFunction.twoBytesToInt(ComRecData.bRec, 26);
                    mdata.setSensor(0, mpower, msingal, 1);

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

                mFsList[fsindex - 1] = mFs;
                if (shuaXin) {
                    mLockFsList[fsindex - 1] = mFs;
                }

                float fssum1 = (float) 0.00;
                int fscount = 0;
                for (int i = 0; i < mFsList.length; i++) {
                    if (mFsList[i] > 0) {
                        fssum1 = fssum1 + mFsList[i];
                        fscount++;
                    }
                }

                if (fscount != 0) {
                    mPjfs = fssum1 / fscount;
                } else if (fscount == 0) {
                    mPjfs = 0.00f;
                }
                if (shuaXin) {
                    mdata.setmPjfs(mtask.getSdfs() ? Float.parseFloat(mtask.getFs()) : mPjfs);
                }
                //       mFSTextView[fsindex - 1].setText(df4.format(mFs));

                msingal = ComRecData.bRec[18] < 0 ? 256 + ComRecData.bRec[18] : ComRecData.bRec[18];
                mpower = MyFunction.twoBytesToInt(ComRecData.bRec, 16);

                try {

                    mdata.setSensor(fsindex - 1 + 5, mpower, msingal, 1);

                } catch (Exception e) {
                    e.printStackTrace();
                }
                SetFiveOne(fsindex - 1 + 5);

                break;

            case 81://中继
                if (ComRecData.bRec.length == 115)//75 89 111
                {
                    try {

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                break;
            case 82://中继
                if (ComRecData.bRec.length == 60)//风杯包
                {
                    int fszjindex = 0;
                    if (ComRecData.bRec[13] == 2)//二号包9-16
                    {
                        fszjindex = 8;
                    } else if (ComRecData.bRec[13] == 1)//一号包1-8
                    {
                        fszjindex = 0;
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
                            if (shuaXin) {
                                mLockFsList[fszjindex + i] = mFs;
                            }
                            SetFiveOne(fszjindex + i + 5);
                            mdata.setSensor(fszjindex + i + 5, mpower, msingal, 1);
                        }
                    }
                    int mcjpower = MyFunction.twoBytesToInt(ComRecData.bRec, 54);
                    int mfspower = MyFunction.twoBytesToInt(ComRecData.bRec, 56);
                    int mzjsingal = ComRecData.bRec[58] < 0 ? 256 + ComRecData.bRec[58] : ComRecData.bRec[58];
                    mdata.setSensor(21, mcjpower, mzjsingal, 1);
                    mdata.setSensor(22, mfspower, mzjsingal, 1);
                    fssum1 = (float) 0.00;
                    fscount = 0;
                    for (int i = 0; i < mFsList.length; i++) {
                        if (mFsList[i] > 0) {
                            fssum1 = fssum1 + mFsList[i];
                            fscount++;
                        }
                    }
                    if (fscount != 0) {
                        mPjfs = fssum1 / fscount;
                    } else if (fscount == 0) {
                        mPjfs = 0.00f;
                    }
                } else if (ComRecData.bRec.length == 35)//中继温湿度
                {
                    String wendu = df4.format((float) MyFunction.byte2float(
                            ComRecData.bRec, 14));
                    String Shidu = df4.format((float) MyFunction.byte2float(
                            ComRecData.bRec, 18));
                    String Dqy = df4.format((float) MyFunction.byteArrayToInt(
                            ComRecData.bRec, 22) / 100);
                    if (shuaXin) {
                        mdata.setmWd(Float.parseFloat(mtask.getSdwd() ? mtask.getWd() : wendu));
                        mdata.setmSd(Float.parseFloat(mtask.getSdsd() ? mtask.getSd() : Shidu));
                        mdata.setmDqy(Float.parseFloat(mtask.getSddqy() ? mtask.getDqy() : Dqy));
                    }
                    msingal = ComRecData.bRec[28] < 0 ? 256 + ComRecData.bRec[28] : ComRecData.bRec[28];
                    mpower = MyFunction.twoBytesToInt(ComRecData.bRec, 26);
                    int mcjpower = MyFunction.twoBytesToInt(ComRecData.bRec, 29);
                    int mfspower = MyFunction.twoBytesToInt(ComRecData.bRec, 31);
                    int mzjsingal = ComRecData.bRec[33] < 0 ? 256 + ComRecData.bRec[33] : ComRecData.bRec[33];
                    if ((Float.parseFloat(wendu) != 0.0f)) {
                        SetFiveOne(0);
                        mdata.setSensor(0, mpower, msingal, 1);
                    }
                    mdata.setSensor(21, mcjpower, mzjsingal, 1);
                    mdata.setSensor(22, mfspower, mzjsingal, 1);
                    SetFiveOne(21);
                    SetFiveOne(22);


                }
                break;
            case 34: // 22 静压传感器
                if (ComRecData.bRec.length == 33) {


                    String jingya = df4.format((float) MyFunction.twoByte2int(
                            ComRecData.bRec, 27));
                    int FuHAo = ComRecData.bRec[26] | 0x00;
                    String jingyaliString = jingya;
//                   if(FuHAo==0x01) {
//                        jingyaliString = "-" + jingya;
//                    }

                    Jy = Float.parseFloat(jingyaliString);

                    msingal = ComRecData.bRec[31] < 0 ? 256 + ComRecData.bRec[31] : ComRecData.bRec[31];
                    mpower = MyFunction.twoBytesToInt(ComRecData.bRec, 29);

                    mdata.setSensor(1, mpower, msingal, 1);
                    SetFiveOne(1);
                }

                break;

            case 48: // 30 //差压
                if (ComRecData.bRec.length == 33) {


                    String jingya = df4.format((float) MyFunction.twoByte2int(
                            ComRecData.bRec, 27));
                    int FuHAo = ComRecData.bRec[26] | 0x00;
                    String chukouyaString = jingya;
//                    if(FuHAo==0x01) {
//                        chukouyaString = "-" + jingya;
//                    }
                    Cy = Float.parseFloat(chukouyaString);

                    msingal = ComRecData.bRec[31] < 0 ? 256 + ComRecData.bRec[31] : ComRecData.bRec[31];
                    mpower = MyFunction.twoBytesToInt(ComRecData.bRec, 29);

                    mdata.setSensor(2, mpower, msingal, 1);
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
                    float mDybb = 1f, mDlbb = 1f;
                    try {
                        mDybb = Float.parseFloat(mdata.GetHisTask().getDybb1()) / Float.parseFloat(mdata.GetHisTask().getDybb2());
                        mDlbb = Float.parseFloat(mdata.GetHisTask().getDlbb1()) / Float.parseFloat(mdata.GetHisTask().getDlbb2());
                    } catch (NumberFormatException e) {
                        e.printStackTrace();
                    }
                    float tmpDybb = mDybb;
                    if (ComRecData.bRec[10] == 1) {
                        if (mdata.getDj1qblc().trim().equals("500A")) {
                            mDybb = mDybb * 500;
                            mDlbb = mDlbb * 500;
                        } else if (mdata.getDj1qblc().trim().equals("20A")) {
                            mDybb = mDybb * 500;
                            mDlbb = mDlbb * 100;
                        }
                    } else if (ComRecData.bRec[10] == 2) {
                        if (mdata.getDj2qblc().trim().equals("500A")) {
                            mDybb = mDybb * 500;
                            mDlbb = mDlbb * 500;
                        } else if (mdata.getDj2qblc().trim().equals("20A")) {
                            mDybb = mDybb * 500;
                            mDlbb = mDlbb * 100;
                        }
                    }

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


                    float cs = (float) Math.sqrt(3);
                    if (ComRecData.bRec[10] == 1) {
                        if (shuaXin && DjShuaxin) {

                            float u0 = 0f;
                            float i0 = 0f;
                            if (mdata.getDj1csff().equals("三瓦法")) {
                                u0 = (Ua + Ub + Uc) / 3;
                                i0 = (Ia + Ib + Ic) / 3;
                            } else if (mdata.getDj1csff().equals("双瓦法")) {
                                u0 = (Ua + Ub + Uc) / 3;
                                i0 = (Ia + Ic) / 2;
                            } else if (mdata.getDj1csff().equals("单瓦法")) {
                                u0 = Ua;
                                i0 = Ia;
                            }
                            if ((Ua / tmpDybb) <= 150) {
                                S = u0 * i0 / 1000 * 3;
                                P = S * Cos;
                                Q = (float) Math.sqrt(Math.pow(S, 2) - Math.pow(P, 2));
                            }

                            mdata.setUab(cs * Ua);
                            mdata.setIa(Ia);
                            mdata.setUca(cs * Uc);
                            mdata.setIc(Ic);
                            mdata.setUbc(cs * Ub);
                            mdata.setIb(Ib);
                            mdata.setPjI((i0));
                            mdata.setPjU(cs * u0);
                            mdata.setGlys(Cos);
                            mdata.setUa(Ua);
                            mdata.setUb(Ub);
                            mdata.setUc(Uc);
                            if (!mdata.GetHisTask().getSddj1gl()) {
                                if (mdata.getDj1csff().equals("单瓦法")) {
                                    mdata.setDjgl(P * 3);
                                } else {
                                    mdata.setDjgl(P);
                                }
                            }
                            msingal = ComRecData.bRec[34] < 0 ? 256 + ComRecData.bRec[34] : ComRecData.bRec[34];
                            mpower = MyFunction.twoBytesToInt(ComRecData.bRec, 32);

                            mdata.setSensor(3, mpower, msingal, 1);
                            mtask = MyApp.getTaskEnity();
                            try {
                                mmotor1data.setEddl(Double.parseDouble(mtask.getDjeddl1()));
                                mmotor1data.setEddy(Double.parseDouble(mtask.getDjeddy1()));
                                mmotor1data.setEdgl(Double.parseDouble(mtask.getDjedgl1()));
                                mmotor1data.setEdxl(Double.parseDouble(mtask.getDjedxl1()));
                                mmotor1data.setJs(Integer.parseInt(mtask.getDjjs1()));
                                mmotor1data.setKzdl(Double.parseDouble(mtask.getDjkzdl1()));
                                mmotor1data.setKzgl(Double.parseDouble(mtask.getDjkzgl1()));
                                mmotor1data.setWgjjdl(Double.parseDouble(mtask.getDjwgjjdl1()));
                                mmotor1data.setUA(Ua);
                                mmotor1data.setUB(Ub);
                                mmotor1data.setUC(Uc);
                                mmotor1data.setUAB(cs * Ua);
                                mmotor1data.setUBC(cs * Ub);
                                mmotor1data.setUCA(cs * Uc);
                                mmotor1data.setIA(Ia);
                                mmotor1data.setIB(Ib);
                                mmotor1data.setIC(Ic);
                                mmotor1data.setYggl(P);
                                mmotor1data.setWggl(Q);
                                mmotor1data.setSzgl(S);


                            } catch (NumberFormatException e) {
                                e.printStackTrace();
                            }
                            mmotor1data.Calculate();
                            mdata.setDjxl((float) mmotor1data.getXl());
                            mdata.setFzxs((float) mmotor1data.getFzxs());
                            mdata.setScgl((float) mmotor1data.getScgl());
                            mdata.setZhxl((float) mmotor1data.getZhxl());
                            mdata.setYxzt(mmotor1data.getstrDjyxzt());
                            //   mwgglTextView.setText(df4.format(Q));
                        }
                        SetFiveOne(3);

                    } else if (ComRecData.bRec[10] == 2) {
                        if (shuaXin && DjShuaxin) {
                            float u1 = 0f;
                            float i1 = 0f;
                            if (mdata.getDj2csff().equals("三瓦法")) {
                                u1 = (Ua + Ub + Uc) / 3;
                                i1 = (Ia + Ib + Ic) / 3;
                            } else if (mdata.getDj2csff().equals("双瓦法")) {
                                u1 = (Ua + Ub + Uc) / 3;
                                i1 = (Ia + Ic) / 2;
                            } else if (mdata.getDj2csff().equals("单瓦法")) {
                                u1 = Ua;
                                i1 = Ia;
                            }
                            if ((Ua / tmpDybb) <= 150) {
                                S = u1 * i1 / 1000 * 3;
                                P = S * Cos;
                                Q = (float) Math.sqrt(Math.pow(S, 2) - Math.pow(P, 2));
                            }

                            mdata.setUab2(cs * Ua);
                            mdata.setIa2(Ia);
                            mdata.setUca2(cs * Uc);
                            mdata.setIc2(Ic);
                            mdata.setUbc2(cs * Ub);
                            mdata.setIb2(Ib);
                            mdata.setPjI2((i1));
                            mdata.setPjU2(cs * u1);
                            mdata.setGlys2(Cos);
                            mdata.setUa2(Ua);
                            mdata.setUb2(Ub);
                            mdata.setUc2(Uc);
                            if (!mdata.GetHisTask().getSddj2gl()) {
                                if (mdata.getDj2csff().equals("单瓦法")) {
                                    mdata.setDjgl2(P * 3);
                                } else {
                                    mdata.setDjgl2(P);
                                }
                            }
                            msingal = ComRecData.bRec[34] < 0 ? 256 + ComRecData.bRec[34] : ComRecData.bRec[34];
                            mpower = MyFunction.twoBytesToInt(ComRecData.bRec, 32);
//迁移
//                            testFragment.msensorfragment.SetSensor("功率2", mpower, msingal, 1);
                            mdata.setSensor(4, mpower, msingal, 1);
                            //     mwgglTextView2.setText(df4.format(Q));
                            mtask = MyApp.getTaskEnity();
                            try {
                                mmotor2data.setEddl(Double.parseDouble(mtask.getDjeddl2()));
                                mmotor2data.setEddy(Double.parseDouble(mtask.getDjeddy2()));
                                mmotor2data.setEdgl(Double.parseDouble(mtask.getDjedgl2()));
                                mmotor2data.setEdxl(Double.parseDouble(mtask.getDjedxl2()));
                                mmotor2data.setJs(Integer.parseInt(mtask.getDjjs2()));
                                mmotor2data.setKzdl(Double.parseDouble(mtask.getDjkzdl2()));
                                mmotor2data.setKzgl(Double.parseDouble(mtask.getDjkzgl2()));
                                mmotor2data.setWgjjdl(Double.parseDouble(mtask.getDjwgjjdl2()));
                                mmotor2data.setUA(Ua);
                                mmotor2data.setUB(Ub);
                                mmotor2data.setUC(Uc);
                                mmotor2data.setUAB(cs * Ua);
                                mmotor2data.setUBC(cs * Ub);
                                mmotor2data.setUCA(cs * Uc);
                                mmotor2data.setIA(Ia);
                                mmotor2data.setIB(Ib);
                                mmotor2data.setIC(Ic);
                                mmotor2data.setYggl(P);
                                mmotor2data.setWggl(Q);
                                mmotor2data.setSzgl(S);


                            } catch (NumberFormatException e) {
                                e.printStackTrace();
                            }
                            mmotor2data.Calculate();
                            mdata.setDjxl2((float) mmotor2data.getXl());
                            mdata.setFzxs2((float) mmotor2data.getFzxs());
                            mdata.setScgl2((float) mmotor2data.getScgl());
                            mdata.setZhxl2((float) mmotor2data.getZhxl());
                            mdata.setYxzt2(mmotor2data.getstrDjyxzt());
                            //   mwgglTextView.setText(df4.format(Q));
                        }
                        SetFiveOne(4);
                    }
                }
                break;
            case 97://转速
                final float zhuansuFloat = (float) MyFunction.twoBytesToInt(ComRecData.bRec, 14);
                Log.i("qwer", "zhuansu: " + zhuansuFloat);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        tvZhuanSu.setVisibility(View.VISIBLE);
                        tvZhuanSu.setText("转速："+df4.format(zhuansuFloat / 10)+" r/min");
                    }
                });
                SetFiveOne(24);
                break;
            case 78:// B2 甲烷传感器
            case -89:// B2甲烷传感器
                if (ComRecData.bRec.length == 33) {

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
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            tvCh4.setVisibility(View.VISIBLE);
                            tvCh4.setText("甲烷浓度："+df22.format(mdata.getCh4()) + " %");
                        }
                    });
//                    tvcySensor.setText(df4.format(Cy - CyZero) + "Pa");
                    msingal = ComRecData.bRec[31] < 0 ? 256 + ComRecData.bRec[31] : ComRecData.bRec[31];
                    mpower = MyFunction.twoBytesToInt(ComRecData.bRec, 29);
                    mdata.setSensor(23, mpower, msingal, 1);
//                    SetSensorState(cySensor, mpower, msingal, 1);
//                    testFragment.msensorfragment.SetSensor("差压", mpower, msingal, 1);
                    SetFiveOne(23);
                }

            default:
                break;
        }


        // ShowData();
    }

    Long mtime = 0L;
    Long mBtime = 0L;
    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            mtime = System.currentTimeMillis();
//            if (shuaXin) {
            try {
                DataTFJ.sensor[] ms = mdata.getSensors();
                if (TimeBetween(IsTx[0]) > TxDelay * 1000) {
                    if (shuaXin) {
                        mdata.setmDqy(0f);
                        mdata.setmSd(0f);
                        mdata.setmWd(0f);
                    }
                    MyApp.getInstance().SetSensorConnectStateFalse(0);
                    // TVEdTgXk1.setText("00.00");
                } else if (TimeBetween(IsTx[0]) < 1000 && ms[0] != null) {

                    testFragment.msensorfragment.SetSensor("温湿度", ms[0].getMpower(), ms[0].getMsignal(), 1);
                }
                if (TimeBetween(IsTx[21]) < 1000 && ms[21] != null) {
                    testFragment.msensorfragment.SetSensor("收集器", ms[21].getMpower(), ms[21].getMsignal(), 1);
                }
                if (TimeBetween(IsTx[22]) < 1000 && ms[22] != null) {
                    testFragment.msensorfragment.SetSensor("发射器", ms[22].getMpower(), ms[22].getMsignal(), 1);
                }

                if (TimeBetween(IsTx[23]) > TxDelay * 1000) {
                    if (shuaXin) {
                        mdata.setCh4(0d);
                        tvCh4.setVisibility(View.INVISIBLE);
                    }
                    MyApp.getInstance().SetSensorConnectStateFalse(23);
                    // TVEdTgXk1.setText("00.00");
                } else if (TimeBetween(IsTx[23]) < 1000 && ms[23] != null) {

                    testFragment.msensorfragment.SetSensor("甲烷", ms[23].getMpower(), ms[23].getMsignal(), 1);
                }
                if (TimeBetween(IsTx[24]) > TxDelay * 1000) {
                    if (shuaXin) {
                        tvZhuanSu.setVisibility(View.INVISIBLE);
                    }
                }

                // jingya
                if (TimeBetween(IsTx[1]) > (TxDelay + 2) * 1000) {
                    if (shuaXin) {
                        TestFragment.setJy(0f);
                    }
                    MyApp.getInstance().SetSensorConnectStateFalse(1);
                } else {
                    if (shuaXin) {
                        TestFragment.setJy(mtask.getSdjy() ? Float.parseFloat(mtask.getJy()) : (Jy - JyZero));
                    }
                    if (TimeBetween(IsTx[1]) < 1000 && ms[1] != null) {
                        testFragment.msensorfragment.SetSensor("静压", ms[1].getMpower(), ms[1].getMsignal(), 1);
                    }
                }
                // chaya
                if (TimeBetween(IsTx[2]) > (TxDelay + 2) * 1000) {
                    if (shuaXin) {
                        TestFragment.setCy(0f);
                    }
                    MyApp.getInstance().SetSensorConnectStateFalse(2);
                } else {
                    if (shuaXin) {
                        TestFragment.setCy(mtask.getSdcy() ? Float.parseFloat(mtask.getCy()) : (Cy - CyZero));
                    }
                    if (TimeBetween(IsTx[2]) < 1000 && ms[2] != null) {
                        testFragment.msensorfragment.SetSensor("差压", ms[2].getMpower(), ms[2].getMsignal(), 1);
                    }
                }
                // gonglv1
                if (TimeBetween(IsTx[3]) > TxDelay * 1000) {
                    if (DjShuaxin && shuaXin) {
                        mdata.setUab(0);
                        mdata.setIa(0);
                        mdata.setUca(0);
                        mdata.setIc(0);
                        mdata.setUbc(0);
                        mdata.setIb(0);
                        mdata.setPjI(0);
                        mdata.setPjU(0);
                        mdata.setGlys(0);
                        mdata.setDjgl(0);
                        mdata.setDjxl(0);
                        mdata.setFzxs(0);
                        mdata.setScgl(0);
                        mdata.setZhxl(0);
                        mdata.setYxzt("断开");
                        mdata.setZgl(0);
                        mdata.setmZgl1(0);
                        MyApp.getInstance().SetSensorConnectStateFalse(3);
                    }
                } else if (TimeBetween(IsTx[3]) < 1000 && ms[3] != null) {
                    testFragment.msensorfragment.SetSensor("功率1", ms[3].getMpower(), ms[3].getMsignal(), 1);
                }
                // gonglv2
                if (TimeBetween(IsTx[4]) > TxDelay * 1000) {
                    if (DjShuaxin && shuaXin) {
                        mdata.setUab2(0);
                        mdata.setIa2(0);
                        mdata.setUca2(0);
                        mdata.setIc2(0);
                        mdata.setUbc2(0);
                        mdata.setIb2(0);
                        mdata.setPjI2(0);
                        mdata.setPjU2(0);
                        mdata.setGlys2(0);
                        mdata.setDjgl2(0);
                        mdata.setDjxl2(0);
                        mdata.setFzxs2(0);
                        mdata.setScgl2(0);
                        mdata.setZhxl2(0);
                        mdata.setYxzt2("断开");
                        mdata.setZgl2(0);
                        mdata.setmZgl2(0);
                        MyApp.getInstance().SetSensorConnectStateFalse(4);
                    }
                } else if (TimeBetween(IsTx[4]) < 1000 && ms[4] != null) {
                    testFragment.msensorfragment.SetSensor("功率2", ms[4].getMpower(), ms[4].getMsignal(), 1);
                }
                // fengsu
                float sum = 0;
                if (mtask.getCsff().equals("风杯法")) {
                    for (int i = 0; i < 16; i++) {
                        if (i == 0) {
                            sum = 0;
                        }
                        if (TimeBetween(IsTx[i + 5]) > TxDelay * 1000) {
                            mFsList[i] = 0f;
                            MyApp.getInstance().SetSensorConnectStateFalse(i + 5);
                        } else if (TimeBetween(IsTx[i + 5]) < 1000 && ms[i + 5] != null) {
                            testFragment.mwindcupfragment.SetWindCupByIndex(i + 1, ms[i + 5].getMpower(), ms[i + 5].getMsignal(), 1);
                        }
                        sum = sum + mFsList[i];
                        if (shuaXin) {
                            if (testFragment != null) {
                                if (testFragment.mwindcupfragment != null) {
                                    testFragment.mwindcupfragment.SetWindSpeedByIndex(i + 1, mFsList[i]);
                                    testFragment.mwindcupfragment.SetPjfs((float) mdata.getmPjfs());
//                                    //显示延迟
//                                    testFragment.mwindcupfragment.SetWindSpeedStr(i+1, TimeBetween(IsTx[i + 5]));
                                }
                            }
                        }
                    }
                    if (sum == 0 && shuaXin) {
                        mPjfs = 0;
                        mdata.setmPjfs(0);
                        if (testFragment != null) {
                            if (testFragment.mwindcupfragment != null) {
                                //      testFragment.mwindcupfragment.SetPjfs(0);
                            }
                        }
                    }
                }
//            }
                if (System.currentTimeMillis() > pretime + 900) {
                    pretime = System.currentTimeMillis();
                    mdata.Refresh();
                    refresh();
                }
                MyApp.getInstance().setStrTest(DataCleanManager.getCacheSize(getCacheDir()));
                // }
            } catch (Exception e) {
                e.printStackTrace();
            }
//            testFragment.mwindcupfragment.SetPjfsStr(TimeBetween(mtime)+"|"+TimeBetween(mBtime));
            mBtime = System.currentTimeMillis();
            handler.postDelayed(this, 250);
        }
    };

    public void refresh() {
        TestFragment.refresh();

    }

    public void initdata() {
        try {
            dataFragment.init();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean SaveData() {
        boolean isSave = false;
        TaskResEnity mres = new TaskResEnity();
        mdata.CopyRes(mres);
        try {
            mres.setSaveTime(getSysTime());
            MyApp.getDaoInstant().getTaskResEnityDao().insert(mres);
            isSave = true;
            SetCaiji();
        } catch (Exception e) {
            isSave = false;
        }
        return isSave;
    }

    // -------------------------------------------------——-------------------获取系统时间
    String getSysTime() {
// shijain
        String datesString;
        String monthString;
        String houString;
        String minString;
        String secondString;
        Time t = new Time(); // or Time t=new Time("GMT+8"); 加上Time Zone资料。
        t.setToNow(); // 取得系统时间。
        int year = t.year;
        int month = t.month + 1;
        int date = t.monthDay;
        int hour = t.hour; // 0-23
        int minute = t.minute;
        int second = t.second;
        if (date < 10) {
            datesString = "0" + date;
        } else {
            datesString = date + "";
        }
        if (month < 10) {
            monthString = "0" + month;
        } else {
            monthString = month + "";
        }
        if (hour < 10) {
            houString = "0" + hour;
        } else {
            houString = hour + "";
        }
        if (minute < 10) {
            minString = "0" + minute;
        } else {
            minString = minute + "";
        }
        if (second < 10) {
            secondString = "0" + second;
        } else {
            secondString = second + "";
        }
        String dString = year + "-" + monthString + "-" + datesString + "\n\t"
                + houString + ":" + minString + ":" + secondString;
        return dString;
    }

    private void SetSensor(SensorView msv, Integer index) {
        DataTFJ.sensor mS = mdata.getSensors()[index];
        SensorData svData = new SensorData();
        // 第二步：设置 SensorData 属性
        svData.setStatus(mS.getMstate())
                .setPower(mS.getMpower())
                .setSignal(mS.getMsignal());

        // 第三步：给SensorView 赋值
        msv.setData(svData);
    }

    //重启机制


    //清除缓存

    /**
     * 清除缓存
     *
     * @param context
     */
    public static void clearAllCache(Context context) {
        deleteDir(context.getCacheDir());
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            deleteDir(context.getExternalCacheDir());
        }
    }

    private static boolean deleteDir(File dir) {
        if (dir != null && dir.isDirectory()) {
            String[] children = dir.list();
            for (int i = 0; i < children.length; i++) {
                boolean success = deleteDir(new File(dir, children[i]));
                if (!success) {
                    return false;
                }
            }
        }
        return dir.delete();
    }

    public void reStartActivity() {
        Intent intent = getIntent();
        finish();
        startActivity(intent);
    }

}
