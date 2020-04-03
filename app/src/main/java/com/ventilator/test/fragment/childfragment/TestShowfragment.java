package com.ventilator.test.fragment.childfragment;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.TextView;

import com.ventilator.Adapter.GridAdapter;
import com.ventilator.Adapter.TestShowAdapter;
import com.ventilator.administrator.DATAbase.R;
import com.ventilator.administrator.DATAbase.greendao.TaskEntity;
import com.ventilator.test.TestActivity;
import com.ventilator.view.MyListView;

import butterknife.BindView;
import butterknife.BindViews;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class TestShowfragment extends Fragment {

    @BindView(R.id.tv_tittle_testshow_1)
    TextView title1;
    @BindView(R.id.tv_tittle_testshow_2)
    TextView title2;
    @BindView(R.id.lv_testshow_1)
    MyListView lv1;
    @BindView(R.id.lv_testshow_2)
    MyListView lv2;

    TestShowAdapter adp1;
    TestShowAdapter adp2;
    private TestActivity mActivity;
    Unbinder unbinder;
    public int fragflag;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivity = (TestActivity) getParentFragment().getActivity();
        fragflag = 0;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_test_testshow, null);
        mActivity = (TestActivity) getParentFragment().getActivity();
        unbinder = ButterKnife.bind(this, view);
        adp1 = new TestShowAdapter(mActivity, mActivity.mdata, 0);
        adp2 = new TestShowAdapter(mActivity, mActivity.mdata, 1);



        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mActivity = (TestActivity) getParentFragment().getActivity();


    }

    @Override
    public void onStart() {
        super.onStart();
        mActivity = (TestActivity) getParentFragment().getActivity();
    }

    private TaskEntity getTaskEntity() {
        TaskEntity TaskEntity = mActivity.myApp.getInstance().getTaskEnity();

        return TaskEntity;
    }

    public void initListViewdj() {//电机
        adp1 = new TestShowAdapter(mActivity, mActivity.mdata, 0);
        adp2 = new TestShowAdapter(mActivity, mActivity.mdata, 1);


        lv1.setAdapter(adp1);
        lv2.setAdapter(adp2);
        title1.setText("电机1参数");
        title2.setText("电机2参数");

    }

    public void initListViewgk() {//工况
        adp1 = new TestShowAdapter(mActivity, mActivity.mdata, 2);
        adp2 = new TestShowAdapter(mActivity, mActivity.mdata, 3);


        lv1.setAdapter(adp1);
        lv2.setAdapter(adp2);
        title1.setText("测量值");
        title2.setText("计算值");

    }

    public void initListViewbk() {//标况
        adp1 = new TestShowAdapter(mActivity, mActivity.mdata, 4);
        adp2 = new TestShowAdapter(mActivity, mActivity.mdata, 5);


        lv1.setAdapter(adp1);
        lv2.setAdapter(adp2);
        title1.setText("工况换算系数");
        title2.setText("换算后数据");

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }


    public void refresh() {
        if (lv1 != null && lv2 != null) {
            switch (fragflag) {
                case 1:
                    initListViewdj();
                    break;
                case 2:
                    initListViewgk();
                    break;
                case 3:
                    initListViewbk();
                    break;
            }
        }
    }
    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
//        setUserVisibleHint(true);
    }
}
