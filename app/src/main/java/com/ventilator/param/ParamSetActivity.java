package com.ventilator.param;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.format.Time;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import android.widget.Toast;


import com.flyco.tablayout.SlidingTabLayout;
import com.greendao.dbUtils.GreateTaskUtils;
import com.jaeger.library.StatusBarUtil;
import com.ventilator.administrator.DATAbase.R;
import com.ventilator.administrator.DATAbase.greendao.TaskEntity;
import com.ventilator.administrator.DATAbase.greendao.TaskResEnity;
import com.ventilator.app.MyApp;
import com.ventilator.test.TestActivity;
import com.xzkydz.function.motor.module.ConstantData;
import com.xzkydz.function.utils.DateUtil;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindInt;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import es.dmoral.toasty.Toasty;

import static android.text.TextUtils.isEmpty;
import static com.ventilator.Tools.Method.IsEmpty;

public class ParamSetActivity extends AppCompatActivity {

    private MyApp myApp;
    private int openlimit = 0; //任务信息 展开、关闭设位置
    TaskEntity mTask;
    private boolean ParamIsSet = false;//设定参数是否展开
    private boolean canSavedjxl1 = false;
    private boolean canSavedjxl2 = false;
    private boolean canSavesd = false;
    private boolean newTask = true;


    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.toolbar_layout)
    CollapsingToolbarLayout collapsingToolbarLayout;
    @BindView(R.id.app_bar)
    AppBarLayout appBar;
    @BindView(R.id.et_task_name)
    public EditText etTaskName;
    @BindView(R.id.et_number)
    public EditText etNumber;
    @BindView(R.id.et_people_name)
    public EditText etPeopleName;
    @BindView(R.id.task_inf_ll)
    LinearLayout llTittle;
    @BindView(R.id.fab)
    FloatingActionButton fab;
    @BindView(R.id.sp_input_param_venti)
    Spinner spventi;
    @BindView(R.id.sp_input_param_csff)
    Spinner spcsff;
    @BindView(R.id.sp_input_param_cdxl1)
    Spinner spcdxl1;
    @BindView(R.id.sp_input_param_motor_csff1)
    Spinner spmcsff1;
    @BindView(R.id.sp_input_param_qblc1)
    Spinner spqblc1;
    @BindView(R.id.sp_input_param_cdxl2)
    Spinner spcdxl2;
    @BindView(R.id.sp_input_param_motor_csff2)
    Spinner spmcsff2;
    @BindView(R.id.sp_input_param_qblc2)
    Spinner spqblc2;
    @BindView(R.id.ly_param_cymj)
    LinearLayout lycymj;
    @BindView(R.id.ly_param_ptgxs)
    LinearLayout lyptgxs;
    @BindView(R.id.ly_param_set)
    LinearLayout lyset;
    @BindView(R.id.tv_param_dlbb)
    TextView tvdlbb;
    @BindView(R.id.tv_param_dybb)
    TextView tvdybb;
    @BindView(R.id.et_param_cfmj)
    EditText etcfmj;
    @BindView(R.id.et_param_cymjd)
    EditText etcymjd;
    @BindView(R.id.et_param_cymjx)
    EditText etcymjx;
    @BindView(R.id.et_param_dlbb1)
    EditText etdlbb1;
    @BindView(R.id.et_param_dybb1)
    EditText etdybb1;
    @BindView(R.id.et_param_dlbb2)
    EditText etdlbb2;
    @BindView(R.id.et_param_dybb2)
    EditText etdybb2;
    @BindView(R.id.et_param_ksqckmj)
    EditText etksqckmj;
    @BindView(R.id.et_param_ptgxs)
    EditText etptgxs;
    @BindView(R.id.et_param_set_cy)
    EditText etcy;
    @BindView(R.id.et_param_set_jy)
    EditText etjy;
    @BindView(R.id.et_param_set_wd)
    EditText etwd;
    @BindView(R.id.et_param_set_sd)
    EditText etsd;
    @BindView(R.id.et_param_set_dqy)
    EditText etdqy;
    @BindView(R.id.et_param_set_fs)
    EditText etfs;
    @BindView(R.id.et_param_set_djgl1)
    EditText etdjgl1;
    @BindView(R.id.et_param_set_djgl2)
    EditText etdjgl2;
    @BindView(R.id.et_param_set_djxl1)
    EditText etdjxl1;
    @BindView(R.id.et_param_set_djxl2)
    EditText etdjxl2;

    @BindView(R.id.et_param_set_edzs)
    EditText etedzs;
    @BindView(R.id.et_param_set_sczs)
    EditText etsczs;

    @BindView(R.id.btn_go_test)
    Button btnGoTest;
    @BindView(R.id.nestedScrollView)
    NestedScrollView nestedscrollview;
    @BindView(R.id.imageview)
    ImageView imageView;
    @BindView(R.id.img_param_rwxx)
    ImageView imageRwxx;
    @BindView(R.id.tv_param_djxl1)
    TextView tvdjxl1;
    @BindView(R.id.tv_param_djxl2)
    TextView tvdjxl2;
    private boolean canSaveptg;
    private Animation mShakeAnim;
    private String errstr = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_param);
        ButterKnife.bind(this);
        myApp = MyApp.getInstance();
        mTask = new TaskEntity();
        mShakeAnim = AnimationUtils.loadAnimation(ParamSetActivity.this, R.anim.shake_x);
        setSupportActionBar(toolbar);

        StatusBarUtil.setColor(ParamSetActivity.this, getResources().getColor(R.color.tittleBar), 0);
        setDamp();
        collapsingToolbarLayout.setTitle(getResources().getString(R.string.title_activity_create_task));
        initView();
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        spmcsff1.setSelection(2);
        spmcsff2.setSelection(2);
        //*********************************************************
//        String sfsdf = "你好啊";
//        float parseFloat = Float.parseFloat(sfsdf);
//        Log.i("ssdgdfgd", "onCreate: " + parseFloat);
        //*********************************************************
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();


    }

    private void initView() {
        spcsff.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String[] test_type = getResources().getStringArray(R.array.test_type);
                mTask.setCsff(test_type[position]);
                switch (mTask.getCsff()) {
                    case "风杯法":
                        lycymj.setVisibility(View.GONE);
                        lyptgxs.setVisibility(View.GONE);
                        break;
                    case "静压全压法":
                        lycymj.setVisibility(View.GONE);
                        lyptgxs.setVisibility(View.VISIBLE);
                        break;
                    case "静压差法":
                        lycymj.setVisibility(View.VISIBLE);
                        lyptgxs.setVisibility(View.GONE);
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spventi.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String[] venti_type = getResources().getStringArray(R.array.venti_type);
                mTask.setTffs(venti_type[position]);


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        TextChangeDLBB textChangedl = new TextChangeDLBB();
        TextChangeDYBB textChangedy = new TextChangeDYBB();
        etdybb1.addTextChangedListener(textChangedy);
        etdybb2.addTextChangedListener(textChangedy);
        etdlbb1.addTextChangedListener(textChangedl);
        etdlbb2.addTextChangedListener(textChangedl);
        etdybb2.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (etdybb2.getText().equals(0)) {
                    etdybb2.startAnimation(mShakeAnim);
                }
            }
        });
        etdlbb2.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (etdlbb2.getText().equals(0)) {
                    etdlbb2.startAnimation(mShakeAnim);
                }
            }
        });
        etdjxl1.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                canSavedjxl1 = false;
                double medxl = 0.0;
                try {
                    medxl = Double.parseDouble(etdjxl1.getText().toString());
                    if (0.0d <= medxl || medxl <= 100.0d) {
                        canSavedjxl1 = true;
                        etdjxl1.setTextColor(Color.BLACK);


                    } else {
                        canSavedjxl1 = false;
                        etdjxl1.setTextColor(Color.RED);
                    }
                } catch (
                        NumberFormatException e)

                {
                    e.printStackTrace();
                }
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void afterTextChanged(Editable s) {

            }

        });
        etdjxl2.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                canSavedjxl2 = false;
                double medxl = 0.0;
                try {
                    medxl = Double.parseDouble(etdjxl2.getText().toString());
                    if (0.0d <= medxl || medxl <= 100.0d) {
                        canSavedjxl2 = true;
                        etdjxl2.setTextColor(Color.BLACK);


                    } else {
                        canSavedjxl2 = false;
                        etdjxl2.setTextColor(Color.RED);
                    }
                } catch (
                        NumberFormatException e)

                {
                    e.printStackTrace();
                }
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void afterTextChanged(Editable s) {

            }

        });
        etsd.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                canSavesd = false;
                double medxl = 0.0;
                try {
                    medxl = Double.parseDouble(etsd.getText().toString());
                    if (0.0d <= medxl || medxl <= 100.0d) {
                        canSavesd = true;
                        etsd.setTextColor(Color.BLACK);

                    } else {
                        canSavesd = false;
                        etsd.setTextColor(Color.RED);
                    }
                } catch (
                        NumberFormatException e)

                {
                    e.printStackTrace();
                }
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void afterTextChanged(Editable s) {

            }

        });
        etptgxs.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                canSaveptg = false;
                double medxl = 0.0;
                try {
                    medxl = Double.parseDouble(etsd.getText().toString());
                    if (0.998d <= medxl || medxl <= 1.004d) {
                        canSaveptg = true;
                        etptgxs.setTextColor(Color.BLACK);

                    } else {
                        canSaveptg = false;
                        etptgxs.setTextColor(Color.RED);
                    }
                } catch (
                        NumberFormatException e)

                {
                    e.printStackTrace();
                }
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void afterTextChanged(Editable s) {

            }

        });
    }
//

    private Boolean CanSave() {
        boolean res = true;
        errstr = "";
        try {
            if ((IsEmpty(etcfmj))) {
                res = res & false;
                etcfmj.startAnimation(mShakeAnim);
                errstr = errstr + "\n" + "未输入测风面积";
            } else if (0 > Float.parseFloat(etcfmj.getText().toString()) || Float.parseFloat(etcfmj.getText().toString()) > 50) {
                etcfmj.startAnimation(mShakeAnim);
                res = res & false;
                errstr = errstr + "\n" + "测风面积：0 ～ 50 ㎡";
            }
            if ((IsEmpty(etksqckmj))) {
                res = res & false;
                etksqckmj.startAnimation(mShakeAnim);

                errstr = errstr + "\n" + "未输入扩散器出口面积";
            } else if (0 > Float.parseFloat(etksqckmj.getText().toString()) || Float.parseFloat(etksqckmj.getText().toString()) > 50) {
                etksqckmj.startAnimation(mShakeAnim);
                res = res & false;
                errstr = errstr + "\n" + "扩散器出口面积：0 ～ 50 ㎡";
            }
            if (mTask.getCsff().equals("静压差法")) {
                if (IsEmpty(etcymjx)) {
                    res = res & false;
                    etcymjx.startAnimation(mShakeAnim);
                    errstr = errstr + "\n" + "未输入测压面积小";
                } else if (0 > Float.parseFloat(etcymjx.getText().toString()) || Float.parseFloat(etcymjx.getText().toString()) > 50) {
                    etcymjx.startAnimation(mShakeAnim);
                    res = res & false;
                    errstr = errstr + "\n" + "测压面积小：0 ～ 50 ㎡";
                }

                if (IsEmpty(etcymjd)) {
                    res = res & false;
                    etcymjd.startAnimation(mShakeAnim);

                    errstr = errstr + "\n" + "未输入测压面积大";
                } else if (0 > Float.parseFloat(etcymjd.getText().toString()) || Float.parseFloat(etcymjd.getText().toString()) > 50) {
                    etcymjd.startAnimation(mShakeAnim);
                    res = res & false;
                    errstr = errstr + "\n" + "测压面积大：0 ～ 50 ㎡";
                }

                if (!IsEmpty(etcymjx) && !IsEmpty(etcymjd)) {
                    float mcyx = Float.parseFloat(etcymjx.getText().toString());
                    float mcyd = Float.parseFloat(etcymjd.getText().toString());
                    if (mcyx >= mcyd) {
                        res = res & false;
                        etcymjd.startAnimation(mShakeAnim);
                        etcymjx.startAnimation(mShakeAnim);
                        errstr = errstr + "\n" + "测压面积设置错误！";
                    }
                }
            }
            if (mTask.getCsff().equals("静压全压法")) {
                if (IsEmpty(etptgxs)) {
                    res = res & false;
                    etptgxs.startAnimation(mShakeAnim);
                    errstr = errstr + "\n" + "未输入皮托管系数";
                } else if (0.998 > Float.parseFloat(etptgxs.getText().toString()) || Float.parseFloat(etptgxs.getText().toString()) > 1.004) {
                    etptgxs.startAnimation(mShakeAnim);
                    res = res & false;
                    errstr = errstr + "\n" + "皮托管系数：0.998 ～ 1.004 ";
                }
            }
            if (isEmpty(mTask.getDjk1())) {
                res = res & false;
                tvdjxl1.startAnimation(mShakeAnim);

                errstr = errstr + "\n" + "未选择电机1";
            }
            if (isEmpty(mTask.getDjk2())) {
                res = res & false;
                tvdjxl2.startAnimation(mShakeAnim);

                errstr = errstr + "\n" + "未选择电机2";
            }
            if (isEmpty(tvdybb.getText().toString())) {
                res = res & false;
                tvdybb.startAnimation(mShakeAnim);

                errstr = errstr + "\n" + "电压变比设置不正确";
            }
            if (isEmpty(tvdlbb.getText().toString())) {
                res = res & false;
                tvdlbb.startAnimation(mShakeAnim);

                errstr = errstr + "\n" + "电流变比设置不正确";
            }
//            设定功率，必须同时设定效率，相应电机电参数不显示
//            仅设定效率，功率可测量，修改时，（修改平均电压和平均电流）电参数仅影响功率，效率使用设定值
//                    都未设定则正常进行修改流程
            if (isEmpty(etdjxl1.getText().toString()) && !isEmpty(etdjgl1.getText().toString())) {
                res = res & false;
                etdjxl1.startAnimation(mShakeAnim);

                errstr = errstr + "\n" + "未设定电机1效率";
            } else if (!IsEmpty(etdjxl1)) {
                if (0 > Float.parseFloat(etdjxl1.getText().toString()) || Float.parseFloat(etdjxl1.getText().toString()) > 100) {
                    res = res & false;
                    etdjxl1.startAnimation(mShakeAnim);
                    errstr = errstr + "\n" + "电机1效率：0 ～ 100 %";
                }
            }
            if (isEmpty(etdjxl2.getText().toString()) && !isEmpty(etdjgl2.getText().toString())) {
                res = res & false;
                etdjxl2.startAnimation(mShakeAnim);

                errstr = errstr + "\n" + "未设定电机2效率";
            } else if (!IsEmpty(etdjxl2)) {
                if (0 > Float.parseFloat(etdjxl2.getText().toString()) || Float.parseFloat(etdjxl2.getText().toString()) > 100) {
                    res = res & false;
                    etdjxl2.startAnimation(mShakeAnim);
                    errstr = errstr + "\n" + "电机2效率：0 ～ 100 %";
                }
            }
            if (!IsEmpty(etfs)) {
                if (0 > Float.parseFloat(etfs.getText().toString()) || Float.parseFloat(etfs.getText().toString()) > 50) {
                    res = res & false;
                    etfs.startAnimation(mShakeAnim);
                    errstr = errstr + "\n" + "风速：0 ～ 50 m/s";
                }
            }
            if (!IsEmpty(etjy)) {
                if (0 > Float.parseFloat(etjy.getText().toString()) || Float.parseFloat(etjy.getText().toString()) > 8000) {
                    res = res & false;
                    etjy.startAnimation(mShakeAnim);
                    errstr = errstr + "\n" + "静压：0 ～ 8000 Pa";
                }
            }
            if (!IsEmpty(etcy)) {
                if (0 > Float.parseFloat(etcy.getText().toString()) || Float.parseFloat(etcy.getText().toString()) > 2000) {
                    res = res & false;
                    etcy.startAnimation(mShakeAnim);
                    errstr = errstr + "\n" + "差压：0 ～ 2000 Pa";
                }
            }
            if (!IsEmpty(etdjgl1)) {
                if (0 > Float.parseFloat(etdjgl1.getText().toString()) || Float.parseFloat(etdjgl1.getText().toString()) > 750) {
                    res = res & false;
                    etdjgl1.startAnimation(mShakeAnim);
                    errstr = errstr + "\n" + "电机1功率：0 ～ 750 kW";
                }
            }
            if (!IsEmpty(etdjgl2)) {
                if (0 > Float.parseFloat(etdjgl2.getText().toString()) || Float.parseFloat(etdjgl2.getText().toString()) > 750) {
                    res = res & false;
                    etdjgl2.startAnimation(mShakeAnim);
                    errstr = errstr + "\n" + "电机2功率：0 ～ 750 kW";
                }
            }
            if (!IsEmpty(etedzs)) {
                if (0 > Float.parseFloat(etedzs.getText().toString()) || Float.parseFloat(etedzs.getText().toString()) > 3000) {
                    res = res & false;
                    etedzs.startAnimation(mShakeAnim);
                    errstr = errstr + "\n" + "额定转速：0 ～ 3000 r/min";
                }
            }
            if (!IsEmpty(etsczs)) {
                if (0 > Float.parseFloat(etsczs.getText().toString()) || Float.parseFloat(etsczs.getText().toString()) > 3000) {
                    res = res & false;
                    etsczs.startAnimation(mShakeAnim);
                    errstr = errstr + "\n" + "实测转速：0 ～ 3000 r/min";
                }
            }
            if (!IsEmpty(etwd)) {
                if (-40 > Float.parseFloat(etwd.getText().toString()) || Float.parseFloat(etwd.getText().toString()) > 100) {
                    res = res & false;
                    etwd.startAnimation(mShakeAnim);
                    errstr = errstr + "\n" + "温度：-40 ～ 100 ℃";
                }
            }
            if (!IsEmpty(etsd)) {
                if (0 > Float.parseFloat(etsd.getText().toString()) || Float.parseFloat(etsd.getText().toString()) > 100) {
                    res = res & false;
                    etsd.startAnimation(mShakeAnim);
                    errstr = errstr + "\n" + "湿度：0 ～ 100 %RH";
                }
            }
            if (!IsEmpty(etdqy)) {
                if (100 > Float.parseFloat(etdqy.getText().toString()) || Float.parseFloat(etdqy.getText().toString()) > 1200) {
                    res = res & false;
                    etdqy.startAnimation(mShakeAnim);
                    errstr = errstr + "\n" + "大气压：100 ～ 1200hPa";
                }
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
        return res;
    }

    public void setDamp() {
        nestedscrollview.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {

                if (motionEvent.getAction() == KeyEvent.ACTION_UP) {
                    appBar.setExpanded((openlimit > -170 ? true : false), true);
                    if (openlimit > -170 ? true : false) {
                        llTittle.setAlpha(1);
                        collapsingToolbarLayout.setTitle("");
                    } else {
                        collapsingToolbarLayout.setTitle(getResources().getString(R.string.title_activity_create_task));
                    }
                    return true;
                }

                return false;
            }
        });

        //缩放中心点坐标
        final float height = 0;    // 根据布局文件中Linearlayout的宽度获得
        final float width = 720;     // 我屏幕宽度
        appBar.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                openlimit = verticalOffset;
                float vel = 1f - (float) Math.abs(verticalOffset) / 262f;
                llTittle.setAlpha(vel);
                llTittle.setScaleX(vel);
                llTittle.setScaleY(vel);
                llTittle.setPivotX(width / 2);
                llTittle.setPivotY(height);
            }
        });
    }

    @OnClick({R.id.fab, R.id.btn_go_test, R.id.img_param_rwxx, R.id.tv_param_djxl1, R.id.tv_param_djxl2})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.fab:
                Intent intent = new Intent();
                intent.setClass(ParamSetActivity.this, com.ventilator.param.HistoryTaskSearchActivity.class);
                startActivityForResult(intent, ConstantData.HistoryTask_resultCode_TESTING);
                break;
            case R.id.btn_go_test:
                // 参数合理性检测
                if (CanSave()) {
                    SaveTask();
                    Intent intent1 = new Intent();

                    Toasty.success(ParamSetActivity.this, "开始测试！").show();
                    intent1.setClass(ParamSetActivity.this, TestActivity.class);
                    startActivity(intent1);
                } else {
                    Toasty.error(ParamSetActivity.this, "参数输入不正确！" + errstr).show();
                }
                break;
            case R.id.img_param_rwxx:
                if (ParamIsSet) {
                    lyset.setVisibility(View.GONE);
                    imageRwxx.setImageResource(R.drawable.rwxx_icon_open);
                    ParamIsSet = false;
                } else {
                    lyset.setVisibility(View.VISIBLE);
                    imageRwxx.setImageResource(R.drawable.rwxx_icon_close);
                    ParamIsSet = true;
                }
                break;
            case R.id.tv_param_djxl1:
                myApp.getInstance().setIsSetMotor1(true);
                intent = new Intent();
                intent.setClass(ParamSetActivity.this, MotorSelectorActivity.class);
                startActivityForResult(intent, ConstantData.Motor_requestCode);
                break;
            case R.id.tv_param_djxl2:
                myApp.getInstance().setIsSetMotor1(false);
                intent = new Intent();
                intent.setClass(ParamSetActivity.this, MotorSelectorActivity.class);
                startActivityForResult(intent, ConstantData.Motor_requestCode);

        }
    }

    private void SaveTask() {

        try {
            if (!etTaskName.getText().toString().equals("")) {
                mTask.setUnitName(etTaskName.getText().toString());
            } else {
                mTask.setUnitName("未设定单位");
            }
            if (!etNumber.getText().toString().equals("")) {
                mTask.setGasePumpNumber(etNumber.getText().toString());
            } else {
                mTask.setGasePumpNumber("未设定编号");
            }
            if (!etPeopleName.getText().toString().equals("")) {
                mTask.setPeopleName(etPeopleName.getText().toString());
            } else {
                mTask.setPeopleName("未设定人员");
            }

            if (!spcsff.getSelectedItem().toString().equals("")) {
                mTask.setCsff(spcsff.getSelectedItem().toString());
            } else {
                mTask.setCsff("风杯法");
            }

            if (!spventi.getSelectedItem().toString().equals("")) {
                mTask.setTffs(spventi.getSelectedItem().toString());
            } else {
                mTask.setTffs("抽出式");
            }

            if (!isEmpty(etdybb1.getText())) {
                mTask.setDybb1(etdybb1.getText().toString());
            }
            if (!isEmpty(etdybb2.getText())) {
                mTask.setDybb2(etdybb2.getText().toString());
            }
            if (!isEmpty(etdlbb1.getText())) {
                mTask.setDlbb1(etdlbb1.getText().toString());
            }
            if (!isEmpty(etdlbb2.getText())) {
                mTask.setDlbb2(etdlbb2.getText().toString());
            }
            if (!isEmpty(etcfmj.getText())) {
                mTask.setCfmj(etcfmj.getText().toString());
            }
            if (!isEmpty(etksqckmj.getText())) {
                mTask.setKsqckmj(etksqckmj.getText().toString());
            }
            if (!isEmpty(etptgxs.getText())) {
                mTask.setPtgxs(etptgxs.getText().toString());
            }
            if (!isEmpty(etcymjx.getText())) {
                mTask.setCymjx(etcymjx.getText().toString());
            }
            if (!isEmpty(etcymjd.getText())) {
                mTask.setCymjd(etcymjd.getText().toString());
            }
            mTask.setDjqblc1(spqblc1.getSelectedItem().toString());
            mTask.setDjqblc2(spqblc2.getSelectedItem().toString());
            mTask.setDjcsff1(spmcsff1.getSelectedItem().toString());
            mTask.setDjcsff2(spmcsff2.getSelectedItem().toString());
            mTask.setDjcdxl1(spcdxl1.getSelectedItem().toString());
            mTask.setDjcdxl2(spcdxl2.getSelectedItem().toString());

            if (!etfs.getText().toString().equals("")) {
                mTask.setFs(etfs.getText().toString());
                mTask.setSdfs(true);
            } else {
                mTask.setSdfs(false);
            }
            if (!etwd.getText().toString().equals("")) {
                mTask.setWd(etwd.getText().toString());
                mTask.setSdwd(true);
            } else {
                mTask.setSdwd(false);
            }
            if (!etjy.getText().toString().equals("")) {
                mTask.setJy(etjy.getText().toString());
                mTask.setSdjy(true);
            } else {
                mTask.setSdjy(false);
            }
            if (!etcy.getText().toString().equals("")) {
                mTask.setCy(etcy.getText().toString());
                mTask.setSdcy(true);
            } else {
                mTask.setSdcy(false);
            }
            if (!etdqy.getText().toString().equals("")) {
                mTask.setDqy(etdqy.getText().toString());
                mTask.setSddqy(true);
            } else {
                mTask.setSddqy(false);
            }
            if (!etdjgl1.getText().toString().equals("")) {
                mTask.setDj1gl(etdjgl1.getText().toString());
                mTask.setSddj1gl(true);
            } else {
                mTask.setSddj1gl(false);
            }
            if (!etdjgl2.getText().toString().equals("")) {
                mTask.setDj2gl(etdjgl2.getText().toString());
                mTask.setSddj2gl(true);
            } else {
                mTask.setSddj2gl(false);
            }
            if (!etdjxl1.getText().toString().equals("")) {
                mTask.setDj1xl(etdjxl1.getText().toString());
                mTask.setSddj1xl(true);
            } else {
                mTask.setSddj1xl(false);
            }
            if (!etdjxl2.getText().toString().equals("")) {
                mTask.setDj2xl(etdjxl2.getText().toString());
                mTask.setSddj2xl(true);
            } else {
                mTask.setSddj2xl(false);
            }
            if (!etsd.getText().toString().equals("")) {
                mTask.setSd(etsd.getText().toString());
                mTask.setSdsd(true);
            } else {
                mTask.setSdsd(false);
            }
            if (!etedzs.getText().toString().equals("")) {
                mTask.setEdzs(etedzs.getText().toString());

            } else {
                mTask.setEdzs("1000");
            }

            if (!etsczs.getText().toString().equals("")) {
                mTask.setSczs(etsczs.getText().toString());

            } else {
                mTask.setSczs("1000");
            }

            if (!mTask.get_IsCompleteTask()) {

                mTask.setGreateTaskTime(DateUtil.getGreatedTaskTime());

            }
            TaskEntity tmpTask = new TaskEntity();
            CopyTask(tmpTask, mTask);
            tmpTask.setGreateTaskTime(DateUtil.getGreatedTaskTime());
            new GreateTaskUtils().insert(tmpTask);
            MyApp.getInstance().setTaskEnity(tmpTask);
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    //EditText的监听器
    class TextChangeDYBB implements TextWatcher {

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            try {

                Animation mShakeAnim = AnimationUtils.loadAnimation(ParamSetActivity.this, R.anim.shake_x);
                DecimalFormat df4 = new DecimalFormat("0.0");
                double mDybbone, mDybbtwo;
                double dybb;
                mDybbone = Double.parseDouble(etdybb1.getText().toString());
                mDybbtwo = Double.parseDouble(etdybb2.getText().toString());
                if (mDybbtwo == 0.0f) {
                    etdybb2.startAnimation(mShakeAnim);
                    tvdybb.setText((""));

                } else {
                    if (mDybbtwo > mDybbone) {
                        dybb = mDybbtwo / mDybbone;
                        etdybb1.setTextColor(Color.argb(160, 255, 140, 0));

                        etdybb2.setTextColor(Color.argb(160, 255, 140, 0));
                    } else {
                        dybb = mDybbone / mDybbtwo;
                        etdybb1.setTextColor(Color.BLACK);

                        etdybb2.setTextColor(Color.BLACK);
                    }
                    tvdybb.setText(df4.format(dybb));
                }

            } catch (NumberFormatException e) {
                e.printStackTrace();
            }

        }

    }//EditText的监听器

    class TextChangeDLBB implements TextWatcher {

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            try {


                DecimalFormat df4 = new DecimalFormat("0.0");
                double mDlbbone, mDlbbtwo;
                double dlbb;
                mDlbbone = Double.parseDouble(etdlbb1.getText().toString());
                mDlbbtwo = Double.parseDouble(etdlbb2.getText().toString());
                if (mDlbbtwo == 0.0f) {
                    etdlbb2.startAnimation(mShakeAnim);
                    tvdlbb.setText((""));
                } else {
                    if (mDlbbtwo > mDlbbone) {
                        dlbb = mDlbbtwo / mDlbbone;
                        etdlbb1.setTextColor(Color.argb(160, 255, 140, 0));

                        etdlbb2.setTextColor(Color.argb(160, 255, 140, 0));
                    } else {
                        dlbb = mDlbbone / mDlbbtwo;
                        etdlbb1.setTextColor(Color.BLACK);

                        etdlbb2.setTextColor(Color.BLACK);
                    }
                    tvdlbb.setText(df4.format(dlbb));
                }
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }

        }

    }

    /**
     * 根据值, 设置spinner默认选中:
     *
     * @param spinner
     * @param value
     */
    public static void setSpinnerItemSelectedByValue(Spinner spinner, String value) {
        SpinnerAdapter apsAdapter = spinner.getAdapter(); //得到SpinnerAdapter对象
        int k = apsAdapter.getCount();
        for (int i = 0; i < k; i++) {
            if (value.equals(apsAdapter.getItem(i).toString())) {
                spinner.setSelection(i, true);// 默认选中项
                break;
            }
        }
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        int motorResultCode = ConstantData.Motor_resultCode; //

        switch (resultCode) {
            case 22: // 电机库返回码 ConstantData.Motor_resultCode
                if (myApp.getInstance().getIsSetMotor1()) {
                    tvdjxl1.setText(data.getStringExtra(ConstantData.MotorName_resultCode));
                    mTask.setDjk1(data.getStringExtra(ConstantData.MotorName_resultCode));
                    mTask.setDjeddy1(data.getStringExtra(ConstantData.MotorEddy_resultCode));
                    mTask.setDjeddl1(data.getStringExtra(ConstantData.MotorEddl_resultCode));
                    mTask.setDjedgl1(data.getStringExtra(ConstantData.MotorEdgl_resultCode));
                    mTask.setDjedxl1(data.getStringExtra(ConstantData.MotorEdxl_resultCode));
                    mTask.setDjkzdl1(data.getStringExtra(ConstantData.MotorKzdl_resultCode));
                    mTask.setDjkzgl1(data.getStringExtra(ConstantData.MotorKzgl_resultCode));

                    mTask.setDjjs1(data.getStringExtra(ConstantData.MotorJs_resultCode));
                    mTask.setDjwgjjdl1(data.getStringExtra(ConstantData.MotorWgjjdl_resultCode));

                } else {
                    tvdjxl2.setText(data.getStringExtra(ConstantData.MotorName_resultCode));
                    mTask.setDjk2(data.getStringExtra(ConstantData.MotorName_resultCode));
                    mTask.setDjeddy2(data.getStringExtra(ConstantData.MotorEddy_resultCode));
                    mTask.setDjeddl2(data.getStringExtra(ConstantData.MotorEddl_resultCode));
                    mTask.setDjedgl2(data.getStringExtra(ConstantData.MotorEdgl_resultCode));
                    mTask.setDjedxl2(data.getStringExtra(ConstantData.MotorEdxl_resultCode));
                    mTask.setDjkzdl2(data.getStringExtra(ConstantData.MotorKzdl_resultCode));
                    mTask.setDjkzgl2(data.getStringExtra(ConstantData.MotorKzgl_resultCode));

                    mTask.setDjjs2(data.getStringExtra(ConstantData.MotorJs_resultCode));
                    mTask.setDjwgjjdl2(data.getStringExtra(ConstantData.MotorWgjjdl_resultCode));
                }
                break;

            case 55: // 历史任务返回码  ConstantData.HistoryTask_resultCode
                Long taskId = data.getLongExtra(ConstantData.HistoryTask_ID_resultCode, 1L);
                GreateTaskUtils greateTaskUtils = new GreateTaskUtils();
                // 这个只是复用参数的历史任务，参数有可能在此基础上更改，不一定就是测试任务。


                TaskEntity mmTask = greateTaskUtils.query(taskId);
                if (mmTask.get_IsCompleteTask() == true) {
                    mTask = new TaskEntity();
                }
                CopyTask(mTask, mmTask);
                this.setParForHistoryTask(mTask);

                break;
            default:
                break;
        }
    }

    private void CopyTask(TaskEntity mTask, TaskEntity mmTask) {
        mTask.setPtgxs(mmTask.getPtgxs());
        mTask.setBy12(mmTask.getBy12());
        mTask.setBy11(mmTask.getBy11());
        mTask.setBy10(mmTask.getBy10());
        mTask.setBy9(mmTask.getBy9());
        mTask.setBy8(mmTask.getBy8());
        mTask.setBy7(mmTask.getBy7());
        mTask.setBy6(mmTask.getBy6());
        mTask.setBy5(mmTask.getBy5());
        mTask.setBy4(mmTask.getBy4());
        mTask.setBy3(mmTask.getBy3());
        mTask.setBy2(mmTask.getBy2());
        mTask.setBy1(mmTask.getBy1());
        mTask.setSdsczs(mmTask.getSdsczs());
        mTask.setSdedzs(mmTask.getSdedzs());

        mTask.setSddj2xl(mmTask.getSddj2xl());
        mTask.setSddj1xl(mmTask.getSddj1xl());
        mTask.setSddj2gl(mmTask.getSddj2gl());
        mTask.setSddj1gl(mmTask.getSddj1gl());
        mTask.setSddqy(mmTask.getSddqy());
        mTask.setSdsd(mmTask.getSdsd());
        mTask.setSdwd(mmTask.getSdwd());
        mTask.setSdcy(mmTask.getSdcy());
        mTask.setSdjy(mmTask.getSdjy());
        mTask.setSdfs(mmTask.getSdfs());
        mTask.setSczs(mmTask.getSczs());
        mTask.setEdzs(mmTask.getEdzs());
        mTask.setDj2xl(mmTask.getDj2xl());
        mTask.setDj1xl(mmTask.getDj1xl());
        mTask.setDj2gl(mmTask.getDj2gl());
        mTask.setDj1gl(mmTask.getDj1gl());
        mTask.setDqy(mmTask.getDqy());
        mTask.setSd(mmTask.getSd());
        mTask.setWd(mmTask.getWd());
        mTask.setCy(mmTask.getCy());
        mTask.setDy(mmTask.getDy());
        mTask.setJy(mmTask.getJy());
        mTask.setFs(mmTask.getFs());
        mTask.setDjwgjjdl2(mmTask.getDjwgjjdl2());
        mTask.setDjjs2(mmTask.getDjjs2());
        mTask.setDjkzgl2(mmTask.getDjkzgl2());
        mTask.setDjkzdl2(mmTask.getDjkzdl2());
        mTask.setDjedxl2(mmTask.getDjedxl2());
        mTask.setDjedgl2(mmTask.getDjedgl2());
        mTask.setDjeddl2(mmTask.getDjeddl2());
        mTask.setDjeddy2(mmTask.getDjeddy2());
        mTask.setDjcdfs2(mmTask.getDjcdfs2());
        mTask.setDjcdxl2(mmTask.getDjcdxl2());
        mTask.setDjcsff2(mmTask.getDjcsff2());
        mTask.setDjqblc2(mmTask.getDjqblc2());
        mTask.setDjksfxz2(mmTask.getDjksfxz2());
        mTask.setDjk2(mmTask.getDjk2());
        mTask.setDjwgjjdl1(mmTask.getDjwgjjdl1());
        mTask.setDjjs1(mmTask.getDjjs1());
        mTask.setDjkzgl1(mmTask.getDjkzgl1());
        mTask.setDjkzdl1(mmTask.getDjkzdl1());
        mTask.setDjedxl1(mmTask.getDjedxl1());
        mTask.setDjedgl1(mmTask.getDjedgl1());
        mTask.setDjeddl1(mmTask.getDjeddl1());
        mTask.setDjeddy1(mmTask.getDjeddy1());
        mTask.setDjcdfs1(mmTask.getDjcdfs1());
        mTask.setDjcdxl1(mmTask.getDjcdxl1());
        mTask.setDjcsff1(mmTask.getDjcsff1());
        mTask.setDjqblc1(mmTask.getDjqblc1());
        mTask.setDjksfxz1(mmTask.getDjksfxz1());
        mTask.setDjk1(mmTask.getDjk1());
        mTask.setDybb2(mmTask.getDybb2());
        mTask.setDybb1(mmTask.getDybb1());
        mTask.setDlbb2(mmTask.getDlbb2());
        mTask.setDlbb1(mmTask.getDlbb1());
        mTask.setCymjd(mmTask.getCymjd());
        mTask.setCymjx(mmTask.getCymjx());
        mTask.setKsqckmj(mmTask.getKsqckmj());
        mTask.setCfmj(mmTask.getCfmj());
        mTask.setTffs(mmTask.getTffs());
        mTask.setCsff(mmTask.getCsff());
//        mTask.setGreateTaskTime(mmTask.getGreateTaskTime());
//        mTask.setTaskHaveGetData(mmTask. getTaskHaveGetData());
//        mTask.set_IsCompleteTask(mmTask. get_IsCompleteTask());
        mTask.setPeopleName(mmTask.getPeopleName());
        mTask.setGasePumpNumber(mmTask.getGasePumpNumber());
        mTask.setUnitName(mmTask.getUnitName());

    }

    public void setParForHistoryTask(TaskEntity taskEnity) {


        try {
            etTaskName.setText(taskEnity.getUnitName());
            etNumber.setText(taskEnity.getGasePumpNumber());
            etPeopleName.setText(taskEnity.getPeopleName());
            etcfmj.setText(taskEnity.getCfmj());
            etksqckmj.setText(taskEnity.getKsqckmj());
            etptgxs.setText(taskEnity.getPtgxs());
            etcymjx.setText(taskEnity.getCymjx());
            etcymjd.setText(taskEnity.getCymjd());
            etdybb1.setText(taskEnity.getDybb1());
            etdybb2.setText(taskEnity.getDybb2());
            etdlbb1.setText(taskEnity.getDlbb1());
            etdlbb2.setText(taskEnity.getDlbb2());

            if (taskEnity.getSdfs()) {
                etfs.setText(taskEnity.getFs());
            }
            if (taskEnity.getSdwd()) {
                etwd.setText(taskEnity.getWd());
            }
            if (taskEnity.getSdjy()) {
                etjy.setText(taskEnity.getJy());
            }
            if (taskEnity.getSdsd()) {
                etsd.setText(taskEnity.getSd());
            }
            if (taskEnity.getSdcy()) {
                etcy.setText(taskEnity.getCy());
            }
            if (taskEnity.getSddqy()) {
                etdqy.setText(taskEnity.getDqy());
            }
            if (taskEnity.getSddj1gl()) {
                etdjgl1.setText(taskEnity.getDj1gl());
            }
            if (taskEnity.getSddj2gl()) {
                etdjgl2.setText(taskEnity.getDj2gl());
            }
            if (taskEnity.getSddj1xl()) {
                etdjxl1.setText(taskEnity.getDj1xl());
            }
            if (taskEnity.getSddj2xl()) {
                etdjxl2.setText(taskEnity.getDj2xl());
            }

            etedzs.setText(taskEnity.getEdzs());


            etsczs.setText(taskEnity.getSczs());


            setSpinnerItemSelectedByValue(spcsff, taskEnity.getCsff());
            setSpinnerItemSelectedByValue(spventi, taskEnity.getTffs());
            setSpinnerItemSelectedByValue(spqblc1, taskEnity.getDjqblc1());
            setSpinnerItemSelectedByValue(spqblc2, taskEnity.getDjqblc2());
            setSpinnerItemSelectedByValue(spmcsff1, taskEnity.getDjcsff1());
            setSpinnerItemSelectedByValue(spmcsff2, taskEnity.getDjcsff2());
            setSpinnerItemSelectedByValue(spcdxl1, taskEnity.getDjcdxl1());
            setSpinnerItemSelectedByValue(spcdxl2, taskEnity.getDjcdxl2());
            tvdjxl1.setText(taskEnity.getDjk1());
            tvdjxl2.setText(taskEnity.getDjk2());
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
