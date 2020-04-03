package com.ventilator.test.fragment.childfragment;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.ventilator.Adapter.TestPagerAdapter;
import com.ventilator.Adapter.TestPressAdapter;
import com.ventilator.Tools.Method;
import com.ventilator.administrator.DATAbase.R;
import com.ventilator.app.MyApp;
import com.ventilator.bean.PressBean;
import com.ventilator.test.TestActivity;
import com.ventilator.view.MyListView;

import junit.framework.Test;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class TestPressfragment extends Fragment {

    @BindView(R.id.tv_testpress_ave_cy)
    TextView tvAveCy;
    @BindView(R.id.tv_testpress_ave_jy)
    TextView tvAveJy;
    @BindView(R.id.lv_testpress)
    MyListView lvPress;
    @BindView(R.id.tv_testpress_cur_cy)
    TextView tvCurCy;
    @BindView(R.id.tv_testpress_cur_jy)
    TextView tvCurJy;
    @BindView(R.id.tv_testpress_title_cy)
    TextView tvTitleCy;
    @BindView(R.id.btn_testpress)
    Button btnCaiji;

    TestActivity mActivity;
    Unbinder unbinder;
    TestPressAdapter mAdpter;
    List<PressBean> mList;
    PressBean mBean;

    DecimalFormat df1 = new DecimalFormat("####0.0");
    DecimalFormat df2 = new DecimalFormat("####0.00");

    DecimalFormat df3 = new DecimalFormat("####0.000");
    private float tmpJy;
    private float tmpCy;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivity = (TestActivity) getParentFragment().getActivity();

        mBean = new PressBean();
        mList = new ArrayList<PressBean>();
        //   TestData();

    }
    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
//        setUserVisibleHint(true);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_test_testpress, null);
        unbinder = ButterKnife.bind(this, view);
        mAdpter = new TestPressAdapter(mActivity, mList);
        lvPress.setAdapter(mAdpter);
        return view;
    }

    private void TestData() {
        mBean = new PressBean();
        mBean.setStr0(df2.format(100 + (Math.random() - 1) * 10));
        mBean.setStr1(df2.format(100 + (Math.random() - 1) * 10));
        mList.add(mBean);
        mBean = new PressBean();
        mBean.setStr0(df2.format(100 + (Math.random() - 1) * 10));
        mBean.setStr1(df2.format(100 + (Math.random() - 1) * 10));
        mList.add(mBean);
        mBean = new PressBean();
        mBean.setStr0(df2.format(100 + (Math.random() - 1) * 10));
        mBean.setStr1(df2.format(100 + (Math.random() - 1) * 10));
        mList.add(mBean);
        mBean = new PressBean();
        mBean.setStr0(df2.format(100 + (Math.random() - 1) * 10));
        mBean.setStr1(df2.format(100 + (Math.random() - 1) * 10));
        mList.add(mBean);

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick(R.id.btn_testpress)
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_testpress:
                //测试
                if(mActivity.getshuaxin())
                {mBean = new PressBean();
                mBean.setStr0(df1.format(tmpJy));
                mBean.setStr1(df1.format(tmpCy));
                mList.add(mBean);
                mAdpter.notifyDataSetChanged();
                float[] mave = Method.AvePress(mList);
                mActivity.mdata.setmJy(mave[0]);
                mActivity.mdata.setmDy(mave[1]);
                mActivity.mdata.setmJyc(mave[1]);
                mActivity.mdata.Refresh();}
                //
                break;

        }
    }

    public void SetJy(float mjy) {
        tmpJy = mjy;
        if (tvCurJy != null) {
            if (MyApp.getInstance().getSensorstateByIndex(1)==1){
            tvCurJy.setText(df1.format(mjy) + " Pa");
            }
            else{
                tvCurJy.setText("-- Pa");
            }

            if (mList.size() > 0) {
                float[] mave = Method.AvePress(mList);
                tvAveJy.setText(df1.format(mave[0])+ " Pa");
                tvAveCy.setText(df1.format(mave[1])+ " Pa");
            } else {
                mActivity.mdata.setmJy(tmpJy);
                mActivity.mdata.setmDy(tmpCy);
                mActivity.mdata.setmJyc(tmpCy);
                mActivity.mdata.Refresh();
                tvAveJy.setText("0.0 Pa");
                tvAveCy.setText("0.0 Pa");
            }
        }
    }

    public void SetCy(float mjy) {
        tmpCy = mjy;
        if (tvCurCy != null) {
            if (MyApp.getInstance().getSensorstateByIndex(2)==1){
                tvCurCy.setText(df1.format(mjy) + " Pa");
            }
            else{
                tvCurCy.setText("-- Pa");
            }



            if (mList.size() > 0) {
                float[] mave = Method.AvePress(mList);
                tvAveJy.setText(df1.format(mave[0])+ " Pa");
                tvAveCy.setText(df1.format(mave[1])+ " Pa");
            } else {
                mActivity.mdata.setmJy(tmpJy);
                mActivity.mdata.setmDy(tmpCy);
                mActivity.mdata.setmJyc(tmpCy);
                mActivity.mdata.Refresh();
                tvAveJy.setText("0.0 Pa");
            tvAveCy.setText("0.0 Pa");
            }
        }
    }

}
