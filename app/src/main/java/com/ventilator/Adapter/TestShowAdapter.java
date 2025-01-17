package com.ventilator.Adapter;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.greendao.dbUtils.GreateTaskUtils;
import com.greendao.manager.DataTFJ;
import com.ventilator.administrator.DATAbase.R;
import com.ventilator.administrator.DATAbase.greendao.TaskEntity;
import com.ventilator.app.MyApp;
import com.ventilator.bean.GridBean;
import com.ventilator.test.TestActivity;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import es.dmoral.toasty.Toasty;


/**
 * 测试 --> 参数  --->  listView 适配器
 */

public class TestShowAdapter extends BaseAdapter {
    private DataTFJ mdata;
    private TestActivity mContext;
    private List<GridBean> list;
    private int type;

    DecimalFormat df0 = new DecimalFormat("####0");
    DecimalFormat df1 = new DecimalFormat("####0.0");
    DecimalFormat df2 = new DecimalFormat("####0.00");

    DecimalFormat df3 = new DecimalFormat("####0.000");


    private String functionStr;
    //	private final TaskEnityDao taskDaoUtils;

    public void setListViewType() {

        switch (type) {
            case 0://电机1参数
                List<GridBean> dj1List = new ArrayList<>();
                if (MyApp.getInstance().getSensorstateByIndex(3) == 1) {
                    dj1List.add(getTSGridItem("AB线电压", df2.format(mdata.getUab()) + " V", "A相电流", df2.format(mdata.getIa()) + " A"));
                    dj1List.add(getTSGridItem("BC线电压", mdata.getDj1csff().equals("单瓦法") ? "-- V" : df2.format(mdata.getUbc()) + " V", "B相电流", !mdata.getDj1csff().equals("三瓦法") ? "-- A" : df2.format(mdata.getIb()) + " A"));
                    dj1List.add(getTSGridItem("CA线电压", mdata.getDj1csff().equals("单瓦法") ? "-- V" : df2.format(mdata.getUca()) + " V", "C相电流", mdata.getDj1csff().equals("单瓦法") ? "-- A" : df2.format(mdata.getIc()) + " A"));
                    dj1List.add(getTSGridItem("平均电压", df2.format(mdata.getPjU()) + " V", "平均电流", df2.format(mdata.getPjI()) + " A"));
                    dj1List.add(getTSGridItem("电机功率", df2.format(mdata.GetHisTask().getSddj1gl() ? mdata.getmDjgl() : mdata.getDjgl()) + " kW", "电机效率", df2.format(mdata.GetHisTask().getSddj1xl() ? mdata.getmDjxl() : mdata.getDjxl()) + " %"));
                    dj1List.add(getTSGridItem("轴  功  率", df2.format(mdata.getmZgl1()) + " kW", "输出功率", df2.format(mdata.getScgl()) + " kW"));
                    dj1List.add(getTSGridItem("功率因数", df3.format(mdata.getGlys()) + " ", "综合效率", df2.format(mdata.getZhxl()) + " %"));
                    dj1List.add(getTSGridItem("负载系数", df3.format(mdata.getFzxs()) + " ", "运行状态", mdata.getYxzt() + " "));
                } else {
                    dj1List.add(getTSGridItem("AB线电压", "-- V", "A相电流", "-- A"));
                    dj1List.add(getTSGridItem("BC线电压", "-- V", "B相电流", "-- A"));
                    dj1List.add(getTSGridItem("CA线电压", "-- V", "C相电流", "-- A"));
                    dj1List.add(getTSGridItem("平均电压", "-- V", "平均电流", "-- A"));
                    dj1List.add(getTSGridItem("电机功率", "-- kW", "电机效率", "-- %"));
                    dj1List.add(getTSGridItem("轴  功  率", "-- kW", "输出功率", "-- kW"));
                    dj1List.add(getTSGridItem("功率因数", "-- ", "综合效率", "-- %"));
                    dj1List.add(getTSGridItem("负载系数", "-- ", "运行状态", "断开 "));
                }
                this.list = dj1List;
                break;

            case 1: // 电机2参数
                List<GridBean> dj2List = new ArrayList<>();
                if (MyApp.getInstance().getSensorstateByIndex(4) == 1) {
                    dj2List.add(getTSGridItem("AB线电压", df2.format(mdata.getUab2()) + " V", "A相电流", df2.format(mdata.getIa2()) + " A"));
                    dj2List.add(getTSGridItem("BC线电压", mdata.getDj2csff().equals("单瓦法") ? "-- V" : df2.format(mdata.getUbc2()) + " V", "B相电流", !mdata.getDj2csff().equals("三瓦法") ? "-- A" : df2.format(mdata.getIb2()) + " A"));
                    dj2List.add(getTSGridItem("CA线电压", mdata.getDj2csff().equals("单瓦法") ? "-- V" : df2.format(mdata.getUca2()) + " V", "C相电流", mdata.getDj2csff().equals("单瓦法") ? "-- A" : df2.format(mdata.getIc2()) + " A"));
                    dj2List.add(getTSGridItem("平均电压", df2.format(mdata.getPjU2()) + " V", "平均电流", df2.format(mdata.getPjI2()) + " A"));
                    dj2List.add(getTSGridItem("电机功率", df2.format(mdata.GetHisTask().getSddj2gl() ? mdata.getmDjgl2() : mdata.getDjgl2()) + " kW", "电机效率", df2.format(mdata.GetHisTask().getSddj2xl() ? mdata.getmDjxl2() : mdata.getDjxl2()) + " %"));
                    dj2List.add(getTSGridItem("轴  功  率", df2.format(mdata.getmZgl2()) + " kW", "输出功率", df2.format(mdata.getScgl2()) + " kW"));
                    dj2List.add(getTSGridItem("功率因数", df3.format(mdata.getGlys2()) + " ", "综合效率", df2.format(mdata.getZhxl2()) + " %"));
                    dj2List.add(getTSGridItem("负载系数", df3.format(mdata.getFzxs2()) + " ", "运行状态", mdata.getYxzt2() + " "));
                } else {
                    dj2List.add(getTSGridItem("AB线电压", "-- V", "A相电流", "-- A"));
                    dj2List.add(getTSGridItem("BC线电压", "-- V", "B相电流", "-- A"));
                    dj2List.add(getTSGridItem("CA线电压", "-- V", "C相电流", "-- A"));
                    dj2List.add(getTSGridItem("平均电压", "-- V", "平均电流", "-- A"));
                    dj2List.add(getTSGridItem("电机功率", "-- kW", "电机效率", "-- %"));
                    dj2List.add(getTSGridItem("轴  功  率", "-- kW", "输出功率", "-- kW"));
                    dj2List.add(getTSGridItem("功率因数", "-- ", "综合效率", "-- %"));
                    dj2List.add(getTSGridItem("负载系数", "-- ", "运行状态", "断开 "));
                }
                this.list = dj2List;
                break;
            case 2: // 工况测量值
                List<GridBean> gk1list = new ArrayList<>();
                String strwd = "--", strsd = "--", strdqy = "--", strpjfs = "--",strch4="--";
                if (MyApp.getInstance().getSensorstateByIndex(0) == 1) {
                    strwd = df2.format(mdata.getmWd()) + "";
                    strsd = df2.format(mdata.getmSd()) + "";
                    strdqy = df2.format(mdata.getmDqy()) + "";
                }
                if (mdata.getMff().equals("风杯法")) {
                    if (MyApp.getInstance().getAllSensorDisconnect(5, 28) != 0) {
                        strpjfs = df1.format(mdata.getmPjfs()) + "";
                    }
                }else if(mdata.getMff().equals("静压全压法"))
                {
                    strpjfs = df1.format(mdata.getmPjfs()) + "";
                }
                if(MyApp.getInstance().getSensorstateByIndex(31) == 1) {
                    strch4=df2.format(mdata.getCh4());
                }


                gk1list.add(getTSGridItem("温        度", (mdata.GetHisTask().getSdwd() ? mdata.GetHisTask().getWd() : strwd) + " ℃", "平均风速", (mdata.GetHisTask().getSdfs() ? mdata.GetHisTask().getFs() : strpjfs) + " m/s"));
                gk1list.add(getTSGridItem("湿        度", (mdata.GetHisTask().getSdsd() ? mdata.GetHisTask().getSd() : strsd) + " %RH", "静        压", (mdata.GetHisTask().getSdjy() ? mdata.GetHisTask().getJy() : df1.format(mdata.getmJy())) + " Pa"));
                if (mdata.getMff().equals("静压全压法")) {
                    gk1list.add(getTSGridItem("大  气  压", (mdata.GetHisTask().getSddqy() ? mdata.GetHisTask().getDqy() : strdqy) + " hPa", "动        压", (mdata.GetHisTask().getSdcy() ? mdata.GetHisTask().getCy() : df2.format(mdata.getmDy())) + " Pa"));
                } else if (mdata.getMff().equals("静压差法")) {
                    gk1list.add(getTSGridItem("大  气  压", (mdata.GetHisTask().getSddqy() ? mdata.GetHisTask().getDqy() : strdqy) + " hPa", "静  压  差", (mdata.GetHisTask().getSdcy() ? mdata.GetHisTask().getCy() : df2.format(mdata.getmDy())) + " Pa"));
                } else {
                    gk1list.add(getTSGridItem("大  气  压", (mdata.GetHisTask().getSddqy() ? mdata.GetHisTask().getDqy() : strdqy) + " hPa", "", ""));
                }
//                gk1list.add(getTSGridItem("甲烷浓度",strch4+ " %", "", ""));
                this.list = gk1list;
                break;
            case 3: // 输入的参数
                List<GridBean> gk2list = new ArrayList<>();
                gk2list.add(getTSGridItem("风        量", df1.format(mdata.getmFl()) + " m³/s", "空气密度", df2.format(mdata.getmKqmd()) + " kg/m³"));
                gk2list.add(getTSGridItem("饱和蒸气压", df1.format(mdata.getmBhzqy()) + " Pa", "扩  散  器\n出口动压", df1.format(mdata.getmKsckdy()) + " Pa"));
                gk2list.add(getTSGridItem("轴 功 率1", df2.format(mdata.getmZgl1()) + " kW", "轴 功 率2", df2.format(mdata.getmZgl2()) + " kW"));
                gk2list.add(getTSGridItem("风机静压", df1.format(mdata.getmFjjy()) + " Pa", "风机全压", df1.format(mdata.getmFjqy()) + " Pa"));
                gk2list.add(getTSGridItem("静压功率", df2.format(mdata.getmJygl()) + " kW", "全压功率", df2.format(mdata.getmQygl()) + " kW"));
                gk2list.add(getTSGridItem("静压效率", df2.format(mdata.getmJyxl()) + " %", "全压效率", df2.format(mdata.getmQyxl()) + " %"));
                gk2list.add(getTSGridItem("风        机\n运行效率", df2.format(mdata.getmFjyxxl()) + " %", "工序能耗", df1.format(mdata.getmGxnh()) + "\n kW·h/(m³·MPa)"));

                this.list = gk2list;
                break;
            case 4://
                List<GridBean> bkhs1list = new ArrayList<>();
                bkhs1list.add(getTSGridItem("标        准\n空气密度", "1.29 kg/m³", "额定转速", df0.format(mdata.getmEdzs()) + " r/min"));
                bkhs1list.add(getTSGridItem("实        测\n空气密度", df2.format(mdata.getmKqmd()) + " kg/m³", "实测转速", df0.format(mdata.getmSczs()) + " r/min"));
                bkhs1list.add(getTSGridItem("空气密度\n转换系数", df1.format(mdata.getMcKqmdzhxs()) + " ", "转        速\n转换系数", df1.format(mdata.getMcZszhxs()) + " "));
                this.list = bkhs1list;
                break;
            case 5://
                List<GridBean> bkhshlist = new ArrayList<>();
                bkhshlist.add(getTSGridItem("风        量", df1.format(mdata.getMgFl()) + " m³/s", "", ""));
                bkhshlist.add(getTSGridItem("轴 功 率1", df2.format(mdata.getMgZgl1()) + " kW", "轴 功 率2", df2.format(mdata.getMgZgl2()) + " kW"));
                bkhshlist.add(getTSGridItem("风机静压", df1.format(mdata.getMgFjjy()) + " Pa", "风机全压", df1.format(mdata.getMgFjqy()) + " Pa"));
                bkhshlist.add(getTSGridItem("静压功率", df2.format(mdata.getMgJygl()) + " kW", "全压功率", df2.format(mdata.getMgQygl()) + " kW"));
                bkhshlist.add(getTSGridItem("静压效率", df2.format(mdata.getMgJyxl()) + " %", "全压效率", df2.format(mdata.getMgQyxl()) + " %"));
                this.list = bkhshlist;
                break;
        }
    }


    public TestShowAdapter(TestActivity mContext, DataTFJ mdata, int type) {
        super();
        this.mContext = mContext;
        this.type = type;
        this.mdata = mdata;
        setListViewType();
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {


        ViewHolder viewHolder;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_listview_testshow, parent, false);
            viewHolder.textView0 = convertView.findViewById(R.id.tv_item_tab0);
            viewHolder.textView1 = convertView.findViewById(R.id.tv_item_tab1);
            viewHolder.textView2 = convertView.findViewById(R.id.tv_item_tab2);
            viewHolder.textView3 = convertView.findViewById(R.id.tv_item_tab3);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        GridBean gridBean = list.get(position);
        viewHolder.textView0.setText(gridBean.getStr0());
        viewHolder.textView1.setText(gridBean.getStr1());
        viewHolder.textView2.setText(gridBean.getStr2());
        viewHolder.textView3.setText(gridBean.getStr3());

        return convertView;
    }

    private class ViewHolder {
        TextView textView0;
        TextView textView1;
        TextView textView2;
        TextView textView3;
    }


    /**
     * 返回 GridBean
     *
     * @param item0
     * @param item1
     * @param item2
     * @param item3
     * @return
     */
    private GridBean getTSGridItem(String item0, String item1, String item2, String item3) {
        GridBean gridBean = new GridBean();
        gridBean.setStr0(item0);
        gridBean.setStr1(item1);
        gridBean.setStr2(item2);
        gridBean.setStr3(item3);
        return gridBean;
    }
}
