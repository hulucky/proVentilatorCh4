package com.ventilator.print;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.greendao.manager.TaskEntityDao;
import com.greendao.manager.TaskResEnityDao;
import com.jaeger.library.StatusBarUtil;
import com.ventilator.administrator.DATAbase.R;
import com.ventilator.administrator.DATAbase.greendao.TaskEntity;
import com.ventilator.administrator.DATAbase.greendao.TaskResEnity;
import com.ventilator.app.MyApp;
import com.xzkydz.bluetoothlib.BluetoolthPrintActivity;
import com.xzkydz.bluetoothlib.bean.Regulation;


import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class PrintActivity extends BluetoolthPrintActivity {

    private long testResID;
    private TaskResEnity testRes;
    private TaskEntity taskParInf;
    DecimalFormat df0 = new DecimalFormat("####0");
    DecimalFormat df1 = new DecimalFormat("####0.0");
    DecimalFormat df2 = new DecimalFormat("####0.00");

    DecimalFormat df3 = new DecimalFormat("####0.000");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StatusBarUtil.setColor(this, getResources().getColor(R.color.tittleBar), 0);
//        setContentView(R.layout.activity_print);
//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
//
//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });
        Intent intent = getIntent();
        testResID = intent.getLongExtra("testResID", 0);
        try {
            testRes = MyApp.getDaoInstant().getTaskResEnityDao().queryBuilder().where(TaskResEnityDao.Properties.Id.eq(testResID)).list().get(0);
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            taskParInf=MyApp.getDaoInstant().getTaskEntityDao().queryBuilder().where(TaskEntityDao.Properties.Id.eq(testRes.getTaskId())).list().get(0);
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    @Override
    public List<Regulation> setRegulations() {
        // Regulation对象是对打印规则的封装，需要传入三个参数:【规定初始化时只有两种方案，否则会报错】
        // name 方案名称
        // start 需要打印的参数起始位置(包含)
        // end 需要打印的参数结束位置(包含)
        List<Regulation> regulations = new ArrayList<>();
        Regulation reg0 = new Regulation("打印全部", 0, 100);
        Regulation reg1 = new Regulation("不打印测试数据", 100, 100);//但受检单位等项目会打印
        regulations.add(reg0);
        regulations.add(reg1);
        return regulations;
    }


    @Override
    public List<String> setParametersName() {

        List<String> parS = new ArrayList<>();
        parS.add("温度");
        parS.add("湿度");
        parS.add("大气压");

        parS.add("静压");
        parS.add("差压");
        parS.add("静压差");

        parS.add("平均风速");
        parS.add("风量");
        parS.add("空气密度");

        parS.add("饱和蒸汽压");
        parS.add("扩散器出口动压");
        parS.add("轴功率1");

        parS.add("轴功率2");
        parS.add("风机静压");
        parS.add("风机全压");

        parS.add("静压功率");
        parS.add("全压功率");
        parS.add("静压效率");

        parS.add("全压效率");
        parS.add("风机运行效率");
        parS.add("工序能耗");

        parS.add("标准空气密度");
        parS.add("实测空气密度");
        parS.add("空气密度转换系数");

        parS.add("额定转速");
        parS.add("实测转速");
        parS.add("转速转换系数");

        parS.add("换算后风量");
        parS.add("换算后轴功率1");
        parS.add("换算后轴功率2");

        parS.add("换算后风机静压");
        parS.add("换算后风机全压");
        parS.add("换算后静压功率");

        parS.add("换算后全压功率");
        parS.add("换算后静压效率");
        parS.add("换算后全压效率");

        parS.add("电机1平均电压");
        parS.add("电机1平均电流");
        parS.add("电机1AB线电压");
        
        parS.add("电机1BC线电压");
        parS.add("电机1CA线电压");
        parS.add("电机1A相电流");
        
        parS.add("电机1B相电流");
        parS.add("电机1C相电流");
        parS.add("电机1电机功率");
        
        parS.add("电机1电机效率");
        parS.add("电机1负载系数");
        parS.add("电机1输出功率");
        
        parS.add("电机1综合效率");
        parS.add("电机1功率因数");
        parS.add("电机1运行状态");

        parS.add("电机2平均电压");
        parS.add("电机2平均电流");
        parS.add("电机2AB线电压");

        parS.add("电机2BC线电压");
        parS.add("电机2CA线电压");
        parS.add("电机2A相电流");

        parS.add("电机2B相电流");
        parS.add("电机2C相电流");
        parS.add("电机2电机功率");

        parS.add("电机2电机效率");
        parS.add("电机2负载系数");
        parS.add("电机2输出功率");

        parS.add("电机2综合效率");
        parS.add("电机2功率因数");
        parS.add("电机2运行状态");



        return parS;


    }

    @Override
    public List<String> setParametersData() {
        List<String> datas = new ArrayList<>();//四个固定
        datas.add(taskParInf.getUnitName());
        datas.add(taskParInf.getNumber());
        datas.add(taskParInf.getPeopleName());
        datas.add(testRes.getSaveTime());

        datas.add(df2.format(testRes.getMWd())+"");
        datas.add(df2.format(testRes.getMSd())+"");
        datas.add(df2.format(testRes.getMDqy())+"");

        datas.add(df1.format(testRes.getMJy())+"");
        datas.add(df1.format(testRes.getMDy())+"");
        datas.add(df1.format(testRes.getMJyc())+"");

        datas.add(df1.format(testRes.getMPjfs())+"");
        datas.add(df1.format(testRes.getMFl())+"");
        datas.add(df2.format(testRes.getMKqmd())+"");

        datas.add(df1.format(testRes.getMBhzqy())+"");
        datas.add(df1.format(testRes.getMKsckdy())+"");
        datas.add(df2.format(testRes.getMZgl1())+"");

        datas.add(df2.format(testRes.getMZgl2())+"");
        datas.add(df1.format(testRes.getMFjjy())+"");
        datas.add(df1.format(testRes.getMFjqy())+"");

        datas.add(df2.format(testRes.getMJygl())+"");
        datas.add(df2.format(testRes.getMQygl())+"");
        datas.add(df2.format(testRes.getMJyxl())+"");

        datas.add(df2.format(testRes.getMQyxl())+"");
        datas.add(df2.format(testRes.getMFjyxxl())+"");
        datas.add(df1.format(testRes.getMGxnh())+"");

        datas.add("1.29");
        datas.add(df2.format(testRes.getMKqmd())+"");
        datas.add(df1.format(testRes.getMcKqmdzhxs())+"");

        datas.add(df0.format(testRes.getMEdzs())+"");
        datas.add(df0.format(testRes.getMSczs())+"");
        datas.add(df1.format(testRes.getMcZszhxs())+"");

        datas.add(df1.format(testRes.getMgFl())+"");
        datas.add(df2.format(testRes.getMgZgl1())+"");
        datas.add(df2.format(testRes.getMgZgl2())+"");

        datas.add(df1.format(testRes.getMgFjjy())+"");
        datas.add(df1.format(testRes.getMgFjqy())+"");
        datas.add(df2.format(testRes.getMgJygl())+"");

        datas.add(df2.format(testRes.getMgQygl())+"");
        datas.add(df2.format(testRes.getMgJyxl())+"");
        datas.add(df2.format(testRes.getMgQyxl())+"");

        datas.add(df2.format(testRes.getPjU())+"");
        datas.add(df2.format(testRes.getPjI())+"");
        datas.add(df2.format(testRes.getUab())+"");

        datas.add(df2.format(testRes.getUbc())+"");
        datas.add(df2.format(testRes.getUca())+"");
        datas.add(df2.format(testRes.getIa())+"");

        datas.add(df2.format(testRes.getIb())+"");
        datas.add(df2.format(testRes.getIc())+"");
        datas.add(df2.format(testRes.getDjgl())+"");

        datas.add(df2.format(testRes.getDjxl())+"");
        datas.add(df3.format(testRes.getFzxs())+"");
        datas.add(df2.format(testRes.getScgl())+"");

        datas.add(df2.format(testRes.getZhxl())+"");
        datas.add(df3.format(testRes.getGlys())+"");
        datas.add(testRes.getStryxzt());

        datas.add(df2.format(testRes.getPjU2())+"");
        datas.add(df2.format(testRes.getPjI2())+"");
        datas.add(df2.format(testRes.getUab2())+"");

        datas.add(df2.format(testRes.getUbc2())+"");
        datas.add(df2.format(testRes.getUca2())+"");
        datas.add(df2.format(testRes.getIa2())+"");

        datas.add(df2.format(testRes.getIb2())+"");
        datas.add(df2.format(testRes.getIc2())+"");
        datas.add(df2.format(testRes.getDjgl2())+"");

        datas.add(df2.format(testRes.getDjxl2())+"");
        datas.add(df3.format(testRes.getFzxs2())+"");
        datas.add(df2.format(testRes.getScgl2())+"");

        datas.add(df2.format(testRes.getZhxl2())+"");
        datas.add(df2.format(testRes.getGlys2())+"");
        datas.add(testRes.getStryxzt2());



        return datas;


    }

    @Override
    public List<String> setParametersUnit() {
        List<String> units = new ArrayList<>();
        units.add("°");
        units.add("%RH");
        units.add("hPa");

        units.add("Pa");
        units.add("Pa");
        units.add("Pa");

        units.add("m/s");
        units.add("m3/s");
        units.add("kg/m3");

        units.add("Pa");
        units.add("Pa");
        units.add("kW");

        units.add("kW");
        units.add("Pa");
        units.add("Pa");

        units.add("kW");
        units.add("kW");
        units.add("%");

        units.add("%");
        units.add("%");
        units.add("kW·h/(m3·MPa)");

        units.add("kg/m3");
        units.add("kg/m3");
        units.add("");

        units.add("r/min");
        units.add("r/min");
        units.add("");

        units.add("m3/s");
        units.add("kW");
        units.add("kW");

        units.add("Pa");
        units.add("Pa");
        units.add("kW");

        units.add("kW");
        units.add("%");
        units.add("%");

        units.add("V");
        units.add("A");
        units.add("V");

        units.add("V");
        units.add("V");
        units.add("A");

        units.add("A");
        units.add("A");
        units.add("kW");

        units.add("%");
        units.add("");
        units.add("kW");

        units.add("%");
        units.add("");
        units.add("");

        units.add("V");
        units.add("A");
        units.add("V");

        units.add("V");
        units.add("V");
        units.add("A");

        units.add("A");
        units.add("A");
        units.add("kW");

        units.add("%");
        units.add("");
        units.add("kW");

        units.add("%");
        units.add("");
        units.add("");


        return units;

    }

}
