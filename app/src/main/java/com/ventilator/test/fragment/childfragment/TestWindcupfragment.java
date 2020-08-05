package com.ventilator.test.fragment.childfragment;


import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.load.engine.Resource;
import com.sensor.SensorData;
import com.sensor.view.SensorView;
import com.ventilator.Utils.SharedPrefrenceUtils;
import com.ventilator.administrator.DATAbase.R;
import com.ventilator.app.MyApp;
import com.ventilator.test.TestActivity;

import java.text.DecimalFormat;
import java.util.List;

import butterknife.BindView;
import butterknife.BindViews;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class TestWindcupfragment extends Fragment {

    @BindViews({R.id.fb_test_1,
            R.id.fb_test_2,
            R.id.fb_test_3,
            R.id.fb_test_4,
            R.id.fb_test_5,
            R.id.fb_test_6,
            R.id.fb_test_7,
            R.id.fb_test_8,
            R.id.fb_test_9,
            R.id.fb_test_10,
            R.id.fb_test_11,
            R.id.fb_test_12,
            R.id.fb_test_13,
            R.id.fb_test_14,
            R.id.fb_test_15,
            R.id.fb_test_16,
            R.id.fb_test_17,
            R.id.fb_test_18,
            R.id.fb_test_19,
            R.id.fb_test_20,
            R.id.fb_test_21,
            R.id.fb_test_22,
            R.id.fb_test_23,
            R.id.fb_test_24
    })
    List<SensorView> sFb;

    @BindViews({R.id.fs_testwindcup_1,
            R.id.fs_testwindcup_2,
            R.id.fs_testwindcup_3,
            R.id.fs_testwindcup_4,
            R.id.fs_testwindcup_5,
            R.id.fs_testwindcup_6,
            R.id.fs_testwindcup_7,
            R.id.fs_testwindcup_8,
            R.id.fs_testwindcup_9,
            R.id.fs_testwindcup_10,
            R.id.fs_testwindcup_11,
            R.id.fs_testwindcup_12,
            R.id.fs_testwindcup_13,
            R.id.fs_testwindcup_14,
            R.id.fs_testwindcup_15,
            R.id.fs_testwindcup_16,
            R.id.fs_testwindcup_17,
            R.id.fs_testwindcup_18,
            R.id.fs_testwindcup_19,
            R.id.fs_testwindcup_20,
            R.id.fs_testwindcup_21,
            R.id.fs_testwindcup_22,
            R.id.fs_testwindcup_23,
            R.id.fs_testwindcup_24
    })
    List<TextView> tFb;


    DecimalFormat df4 = new DecimalFormat("####0.0");
    TestActivity mActivity;
    Unbinder unbinder;
    @BindView(R.id.tv_testwindspeed_pjfs)
    TextView tvTestwindspeedPjfs;
    @BindView(R.id.img_fbshow)
    ImageView imgFbshow;
    @BindView(R.id.linearLayoutfb1)
    LinearLayout linearLayoutfb1;
    @BindView(R.id.linearLayoutfb2)
    LinearLayout linearLayoutfb2;
    @BindView(R.id.linearLayoutfb3)
    LinearLayout linearLayoutfb3;
    @BindView(R.id.linearLayoutfb4)
    LinearLayout linearLayoutfb4;
    @BindView(R.id.linearLayoutfb5)
    LinearLayout linearLayoutfb5;
    @BindView(R.id.linearLayoutfb6)
    LinearLayout linearLayoutfb6;
    @BindView(R.id.linear_tv5)
    LinearLayout linearTv5;
    @BindView(R.id.linear_tv6)
    LinearLayout linearTv6;

    boolean showfb = true;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_test_testwindcup, null);
        view.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
        mActivity = (TestActivity) getParentFragment().getActivity();
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (!SharedPrefrenceUtils.getCanShowEight(getContext())) {
            linearLayoutfb5.setVisibility(View.GONE);
            linearLayoutfb6.setVisibility(View.GONE);
            linearTv5.setVisibility(View.GONE);
            linearTv6.setVisibility(View.GONE);
        }

    }


    public void showEight() {
        try {
            if (showfb) {
                linearLayoutfb5.setVisibility(View.VISIBLE);
                linearLayoutfb6.setVisibility(View.VISIBLE);
            }
            linearTv5.setVisibility(View.VISIBLE);
            linearTv6.setVisibility(View.VISIBLE);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void SetWindCupState(SensorView msv, float mpower, float msignal, int minf) {
        SensorData svData = new SensorData();
        // 第二步：设置 SensorData 属性
        svData.setStatus(minf)
                .setPower(mpower)
                .setSignal(msignal);
        // 第三步：给SensorView 赋值
        msv.setData(svData);
    }

    public void SetWindCupByIndex(Integer index, float mpower, float msignal, int minf) {
        if (sFb != null) {
            SetWindCupState(sFb.get(index - 1), mpower, msignal, minf);
        }
    }

    public void SetWindSpeedByIndex(Integer index, float mfs) {
        try {
            if (tFb != null) {
                if (MyApp.getInstance().getSensorstateByIndex(index + 4) == 0) {
                    tFb.get(index - 1).setText("-- m/s");
                } else {
                    tFb.get(index - 1).setText(df4.format(mfs) + "m/s");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void SetWindSpeedStr(Integer index, float mfs) {
        try {
            if (tFb != null) {
                if (MyApp.getInstance().getSensorstateByIndex(index + 4) == 0) {
                    tFb.get(index - 1).setText("--");
                } else {
                    tFb.get(index - 1).setText(df4.format(mfs));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void SetPjfs(float mPjfs) {
        if (tvTestwindspeedPjfs != null) {
            if (MyApp.getInstance().getAllSensorDisconnect(5, 28) != 0) {
                tvTestwindspeedPjfs.setText(df4.format(mPjfs) + " m/s");
            } else {
                tvTestwindspeedPjfs.setText("-- m/s");
            }
        }
    }

    public void SetPjfsStr(String mPjfs) {
        if (tvTestwindspeedPjfs != null) {

            tvTestwindspeedPjfs.setText((mPjfs));

        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick(R.id.img_fbshow)
    public void onViewClicked() {
        if (showfb) {
            showfb = false;
            imgFbshow.setBackgroundResource(R.drawable.fboff);
            linearLayoutfb1.setVisibility(View.GONE);
            linearLayoutfb2.setVisibility(View.GONE);
            linearLayoutfb3.setVisibility(View.GONE);
            linearLayoutfb4.setVisibility(View.GONE);
            linearLayoutfb5.setVisibility(View.GONE);
            linearLayoutfb6.setVisibility(View.GONE);
        } else {
            showfb = true;
            imgFbshow.setBackgroundResource(R.drawable.fbon);
            linearLayoutfb1.setVisibility(View.VISIBLE);
            linearLayoutfb2.setVisibility(View.VISIBLE);
            linearLayoutfb3.setVisibility(View.VISIBLE);
            linearLayoutfb4.setVisibility(View.VISIBLE);
            linearLayoutfb5.setVisibility(View.VISIBLE);
            linearLayoutfb6.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
//        setUserVisibleHint(true);
    }
}
