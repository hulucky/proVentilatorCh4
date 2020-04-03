package com.ventilator.test.fragment.DataChildFragment;


import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.TextView;

import com.greendao.manager.DataTFJ;
import com.greendao.manager.TaskResEnityDao;
import com.ventilator.Adapter.TestDataDetailAdapter;
import com.ventilator.Adapter.TestShowAdapter;
import com.ventilator.administrator.DATAbase.R;
import com.ventilator.administrator.DATAbase.greendao.TaskEntity;
import com.ventilator.administrator.DATAbase.greendao.TaskResEnity;
import com.ventilator.app.MyApp;
import com.ventilator.test.TestActivity;
import com.ventilator.test.fragment.DataFragment;
import com.ventilator.view.MyListView;

import butterknife.BindView;
import butterknife.BindViews;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class DataDetailfragment extends Fragment implements TestDataDetailAdapter.Callback {


    @BindView(R.id.lv_testdatadetail_calculator)
    MyListView lvcal;
    @BindView(R.id.lv_testdatadetail_dj1)
    MyListView lvdj1;
    @BindView(R.id.lv_testdatadetail_dj2)
    MyListView lvdj2;
    @BindView(R.id.lv_testdatadetail_gk)
    MyListView lvgk;
    @BindView(R.id.lv_testdatadetail_hsh)
    MyListView lvhsh;
    @BindView(R.id.lv_testdatadetail_param)
    MyListView lvparam;
    @BindView(R.id.lv_testdatadetail_test)
    MyListView lvtest;

    @BindView(R.id.sc_datadetail)
    ScrollView scdetail;
    private Activity mActivity;
    Unbinder unbinder;
    MyApp myApp;
    TaskEntity mtask;
    TaskResEnity mres;
    DataTFJ mdata;
    DataFragment mDataFragment;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivity = getParentFragment().getActivity();
        mDataFragment = (DataFragment) getParentFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_test_datadetail, null);
        mActivity = getParentFragment().getActivity();
        unbinder = ButterKnife.bind(this, view);
        mdata = new DataTFJ();

        try {
            myApp = MyApp.getInstance();
            mtask = myApp.getTaskEnity();
            mres = myApp.getDaoInstant().getTaskResEnityDao().queryBuilder().where(TaskResEnityDao.Properties.TaskId.eq(mtask.getId())).list().get(0);
//            mdata.SetHisTask(mtask);
            mdata.SetResOnly(mres);
            mdata.Refresh();
            Refresh(mdata);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mActivity = getParentFragment().getActivity();


    }

    @Override
    public void onStart() {
        super.onStart();
        mActivity = getParentFragment().getActivity();
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    public void RefreshByRes(TaskResEnity mres) {
        try {
            myApp = MyApp.getInstance();
            mdata.SetResOnly(mres);
            mdata.Refresh();
            Refresh(mdata);
            Log.i("qwsd", "RefreshByRes: OK");
        } catch (Exception e) {
            Log.i("qwsd", "RefreshByRes: Exception");
            e.printStackTrace();
        }

    }

    public void Refresh(DataTFJ mmdata) {
        TestDataDetailAdapter adparam = new TestDataDetailAdapter(mActivity, mmdata, 6, this);//预设参数
        TestDataDetailAdapter adtest = new TestDataDetailAdapter(mActivity, mmdata, 2, this);//测量值
        TestDataDetailAdapter adcal = new TestDataDetailAdapter(mActivity, mmdata, 3, this);//计算值
        TestDataDetailAdapter adgk = new TestDataDetailAdapter(mActivity, mmdata, 4, this);//工况换算系数
        TestDataDetailAdapter adhsh = new TestDataDetailAdapter(mActivity, mmdata, 5, this);//换算后数据
        TestDataDetailAdapter addj1 = new TestDataDetailAdapter(mActivity, mmdata, 0, this);//电机1参数
        TestDataDetailAdapter addj2 = new TestDataDetailAdapter(mActivity, mmdata, 1, this);//电机2参数

        lvparam.setAdapter(adparam);
        lvtest.setAdapter(adtest);
        lvcal.setAdapter(adcal);
        lvgk.setAdapter(adgk);
        lvhsh.setAdapter(adhsh);
        lvdj1.setAdapter(addj1);
        lvdj2.setAdapter(addj2);

        scdetail.fullScroll(ScrollView.FOCUS_UP);
    }

    /**
     * 接口方法，响应ListView按钮点击事件
     */
    @Override
    public void click(DataTFJ mmdata) {
        try {
            mmdata.Refresh();
            Refresh(mmdata);
            TaskResEnity mmres = mmdata.GetRes();
            myApp.getDaoInstant().getTaskResEnityDao().update(mmres);
            mDataFragment.RefreshList();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        setUserVisibleHint(true);
    }
}
