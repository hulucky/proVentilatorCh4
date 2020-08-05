package com.ventilator.test.fragment;


import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Toast;

import com.ventilator.Adapter.TestShowPagerAdapter;
import com.ventilator.administrator.DATAbase.R;
import com.ventilator.administrator.DATAbase.greendao.TaskEntity;
import com.ventilator.app.MyApp;
import com.ventilator.test.TestActivity;
import com.ventilator.test.fragment.childfragment.TestPressfragment;
import com.ventilator.test.fragment.childfragment.TestSensorfragment;
import com.ventilator.test.fragment.childfragment.TestShowfragment;
import com.ventilator.test.fragment.childfragment.TestTestfragment;
import com.ventilator.test.fragment.childfragment.TestWindcupfragment;


import com.ventilator.view.MyNoScrollViewPager;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import es.dmoral.toasty.Toasty;

import static com.ventilator.app.MyApp.getInstance;

public class TestFragment extends Fragment {

    @BindView(R.id.fr_main)
    MyNoScrollViewPager flmain;
    @BindView(R.id.giv_gif)
    pl.droidsonroids.gif.GifImageView gifImageView;
    @BindView(R.id.rbn_test_fs)
    Button btnfb;


    static CheckBox ckLock;
    //    @BindView(R.id.cb_testdj_lock)
    static CheckBox ckdjLock;
    TaskEntity mTask;
    MyApp myApp;
    Unbinder unbinder;
    public TestSensorfragment msensorfragment;// 1 传感器界面
    public TestWindcupfragment mwindcupfragment;// 2 风速
    static TestShowfragment mshowfragment;// 3 电机
    static TestPressfragment mpressfragment;// 4 压力采集
    static TestTestfragment mtestfragment;//
    List<Fragment> list_fragments;
    TestActivity mActivity;


    static Button btnTestSave;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        list_fragments = new ArrayList<Fragment>();

        //把两个子fragment实例化然后装到集合里
        mTask = myApp.getTaskEnity();
        msensorfragment = new TestSensorfragment();//传感器
        list_fragments.add(msensorfragment);
        mshowfragment = new TestShowfragment();//电机、工况、标况
        list_fragments.add(mshowfragment);
        mpressfragment = new TestPressfragment();//压力采集
        list_fragments.add(mpressfragment);

        if (mTask.getCsff() != null) {
            if (mTask.getCsff().equals("风杯法")) {
                mwindcupfragment = new TestWindcupfragment();//风速
                list_fragments.add(mwindcupfragment);
            }
        }
        mtestfragment = new TestTestfragment();//此界面未显示
        list_fragments.add(mtestfragment);


    }

    @Override
    public void onResume() {
        super.onResume();
//        gifImageView.play();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_test, null);
        myApp = getInstance();
        mTask = myApp.getTaskEnity();
        unbinder = ButterKnife.bind(this, view);
        btnTestSave = (Button) view.findViewById(R.id.btn_test_save);
        ckdjLock = (CheckBox) view.findViewById(R.id.cb_testdj_lock);
        ckLock = (CheckBox) view.findViewById(R.id.cb_test_lock);
        mActivity = (TestActivity) getActivity();
        flmain.setNoScroll(true);
        if (list_fragments != null) {
            TestShowPagerAdapter msgAdapter = new TestShowPagerAdapter(getChildFragmentManager(), list_fragments);
            flmain.setAdapter(msgAdapter);
            flmain.setCurrentItem(0);
        }
        loadgif();


        ckLock.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                mActivity.setShuaXin(!isChecked);
//                mActivity.clearAllCache(mActivity);
//                mActivity.reStartActivity();
            }
        });
        ckdjLock.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
             mActivity.setDjShuaXin(!isChecked);
//                MyApp.getInstance().setStrTest1( "|");
//                if (mTask.getCsff().equals("风杯法")) {
//                    flmain.setCurrentItem(4, false);
//                } else {
//                    flmain.setCurrentItem(3, false);
//                }


            }
        });


        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        mActivity.initdata();
    }

    private void loadgif() {
        switch (mTask.getCsff()) {
            case "风杯法":
                gifImageView.setBackgroundResource(R.drawable.gif_fb);
                break;
            case "静压全压法":
                gifImageView.setBackgroundResource(R.drawable.gif_jy);
                btnfb.setVisibility(View.GONE);
                break;
            case "静压差法":
                gifImageView.setBackgroundResource(R.drawable.gif_jyc);
                btnfb.setVisibility(View.GONE);
                break;
        }

    }

    @Override
    public void onPause() {
        super.onPause();
//        gifImageView.pause();
    }


    @OnClick({R.id.rbn_test_bk, R.id.rbn_test_cgq, R.id.rbn_test_dj, R.id.rbn_test_fs, R.id.rbn_test_gk, R.id.rbn_test_ylcj, R.id.btn_test_save})
    public void onViewClicked(View view) {
//        try {
            switch (view.getId()) {
                case R.id.rbn_test_bk://标况
                    flmain.setCurrentItem(1, false);
                    mshowfragment.initListViewbk();
                    mshowfragment.fragflag = 3;
                    break;
                case R.id.rbn_test_dj://电机
                    flmain.setCurrentItem(1, false);
                    mshowfragment.initListViewdj();
                    mshowfragment.fragflag = 1;
                    break;
                case R.id.rbn_test_cgq://传感器
                    flmain.setCurrentItem(0, false);
                    break;
                case R.id.rbn_test_fs://风速
                    flmain.setCurrentItem(3, false);
                    float[] mfs = mActivity.getmLockFsList();
                    float pjfs = 0.0f, sumfs = 0.0f;
                    int fscount = 0;
                    for (int i = 0; i < 24; i++) {
                        mwindcupfragment.SetWindSpeedByIndex(i + 1, mfs[i]);
                        if (mfs[i] != 0) {
                            sumfs = sumfs + mfs[i];
                            fscount++;
                        }
                    }
                    if (fscount != 0) {
                        pjfs = sumfs / fscount;
                        mwindcupfragment.SetPjfs(pjfs);
                    }

                    break;
                case R.id.rbn_test_gk://工况
                    flmain.setCurrentItem(1, false);
                    mshowfragment.initListViewgk();
                    mshowfragment.fragflag = 2;
                    break;
                case R.id.rbn_test_ylcj://压力采集

                    flmain.setCurrentItem(2, false);

                    break;
                case R.id.btn_test_save://保存
                    btnTestSave.setEnabled(false);
                    CountDownTimer timer = new CountDownTimer(3000 + 500, 1000) {
                        @Override
                        public void onTick(long millisUntilFinished) {
                            if (getActivity() != null && !getActivity().isFinishing()) {
                                btnTestSave.setEnabled(false);
                                btnTestSave.setText(String.valueOf(millisUntilFinished / 1000));
                            }
                        }

                        @Override
                        public void onFinish() {
                            //非空判断
                            if (getActivity() != null && !getActivity().isFinishing()) {
                                btnTestSave.setEnabled(true);
                                btnTestSave.setText("保存");
                            }
                        }
                    }.start();
                    if (mActivity.SaveData()) {
                        Toasty.success(mActivity, "已保存一组数据！", Toast.LENGTH_SHORT, true).show();
                    } else {
                        Toasty.error(mActivity, "数据保存失败！", Toast.LENGTH_SHORT, true).show();
                    }


            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();


    }

    public static void refresh() {
        mshowfragment.refresh();
//        ckdjLock.setText(MyApp.getInstance().getStrTest());
//        ckLock.setText(MyApp.getInstance().getStrstate());
//        MyApp.getInstance().initStrstate();
        mtestfragment.refresh("");
    }


    public static void setJy(float v) {
        mpressfragment.SetJy(v);
    }

    public static void setCy(float v) {
        mpressfragment.SetCy(v);
    }


    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
//        setUserVisibleHint(true);
    }


}
