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
import com.ventilator.app.MyApp;
import com.ventilator.test.TestActivity;
import com.ventilator.view.MyListView;

import butterknife.BindView;
import butterknife.BindViews;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class TestTestfragment extends Fragment {


    @BindView(R.id.tv_tittle_testshow_1)
    TextView tvTittleTestshow1;
    @BindView(R.id.tv_tittle_testshow_2)
    TextView tvTittleTestshow2;

    private TestActivity mActivity;
    Unbinder unbinder;
    public int fragflag;
    String res = "";

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivity = (TestActivity) getParentFragment().getActivity();
        fragflag = 0;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_test_testtest, null);
        mActivity = (TestActivity) getParentFragment().getActivity();
        unbinder = ButterKnife.bind(this, view);


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


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }


    public void refresh(String mres) {
        try {
            tvTittleTestshow1.setText(MyApp.getInstance().getQueueStr());
            tvTittleTestshow2.setText(MyApp.getInstance().getStrstate());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
//        setUserVisibleHint(true);
    }

    @butterknife.OnClick(R.id.tv_tittle_testshow_1)
    public void onViewClicked() {
        res = "";
    }
}
