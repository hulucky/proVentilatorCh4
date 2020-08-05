package com.ventilator.test.fragment.childfragment;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sensor.SensorData;
import com.sensor.view.SensorView;
import com.ventilator.Utils.SharedPrefrenceUtils;
import com.ventilator.administrator.DATAbase.R;
import com.ventilator.test.TestActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class TestSensorfragment extends Fragment {

    @BindView(R.id.jy_test)
    SensorView sjy;
    @BindView(R.id.wsd_test)
    SensorView swsd;
    @BindView(R.id.cy_test)
    SensorView scy;
    @BindView(R.id.gl1_test)
    SensorView sgl1;
    @BindView(R.id.gl2_test)
    SensorView sgl2;
    @BindView(R.id.zj1_test)
    SensorView zj1Test;
    @BindView(R.id.zj2_test)
    SensorView zj2Test;
    @BindView(R.id.ch4_test)
    SensorView ch4test;

    TestActivity mActivity;
    Unbinder unbinder;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_test_testsensor, null);
        mActivity = (TestActivity) getParentFragment().getActivity();
        unbinder = ButterKnife.bind(this, view);
        view.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (SharedPrefrenceUtils.getCanShowCh4(getContext())) {
            ch4test.setVisibility(View.VISIBLE);
        }
    }

    private void SetSensorState(SensorView msv, float mpower, float msignal, int minf) {
        SensorData svData = new SensorData();
        // 第二步：设置 SensorData 属性
        svData.setStatus(minf)
                .setPower(mpower)
                .setSignal(msignal);

        // 第三步：给SensorView 赋值
        msv.setData(svData);
    }

    public void SetSensor(String str, float mpower, float msignal, int minf) {
        try {
            switch (str) {
                case "温湿度":
                    SetSensorState(swsd, mpower, msignal, minf);
                    break;
                case "静压":
                    SetSensorState(sjy, mpower, msignal, minf);
                    break;
                case "差压":
                    SetSensorState(scy, mpower, msignal, minf);
                    break;
                case "功率1":
                    SetSensorState(sgl1, mpower, msignal, minf);
                    break;
                case "功率2":
                    SetSensorState(sgl2, mpower, msignal, minf);
                    break;
                case "发射器":
                    SetSensorState(zj1Test, mpower, msignal, minf);
                    break;
                case "收集器":
                    SetSensorState(zj2Test, mpower, msignal, minf);
                    break;
                case "甲烷":
                    if (SharedPrefrenceUtils.getCanShowCh4(getContext())) {
                        ch4test.setVisibility(View.VISIBLE);
                        SetSensorState(ch4test, mpower, msignal, minf);
                    }
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
//        setUserVisibleHint(true);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
