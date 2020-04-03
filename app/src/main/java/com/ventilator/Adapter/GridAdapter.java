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
import com.ventilator.administrator.DATAbase.R;
import com.ventilator.administrator.DATAbase.greendao.TaskEntity;
import com.ventilator.bean.GridBean;
import com.ventilator.test.TestActivity;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import es.dmoral.toasty.Toasty;


/**
 * 测试 --> 参数  --->  listView 适配器
 */

public class GridAdapter extends BaseAdapter {
    private TestActivity mContext;
    private List<GridBean> list;
    private int type;
    private TaskEntity taskParInf;
    private Callback mCallback;
    private String functionStr;

    DecimalFormat df0 = new DecimalFormat("####0");
    DecimalFormat df1 = new DecimalFormat("####0.0");
    DecimalFormat df2 = new DecimalFormat("####0.00");

    DecimalFormat df3 = new DecimalFormat("####0.000");
    //	private final TaskEnityDao taskDaoUtils;
    public interface Callback {
        public void click();
    }
    public void setListViewType() {

        switch (type) {
            case 0:
                List<GridBean> taskInfList = new ArrayList<>();
                taskInfList.add(getGridItem("受检单位", taskParInf.getUnitName(), "通风机编号", taskParInf.getNumber()));
                taskInfList.add(getGridItem("测试人员", taskParInf.getPeopleName(), "测试方法", taskParInf.getTestFunction()));
                taskInfList.add(getGridItem("创建日期", taskParInf.getGreateTaskTime(), "", ""));
                taskInfList.add(getGridItem("电压变比", taskParInf.getDybb1() + ":" + taskParInf.getDybb2(), "电流变比", taskParInf.getDlbb1() + ":" + taskParInf.getDlbb2()));
                taskInfList.add(getGridItem("测风面积",df3.format(Float.parseFloat( taskParInf.getCfmj())) + " ㎡", "扩 散 器\n出口面积", df3.format(Float.parseFloat( taskParInf.getKsqckmj() ))+ " ㎡"));
                if(taskParInf.getTestFunction()!=null){if (taskParInf.getTestFunction().equals("静压全压法")) {
                    taskInfList.add(getGridItem("皮托管系数", df3.format(Float.parseFloat( taskParInf.getPtgxs())), "", ""));
                } else if (taskParInf.getTestFunction().equals("静压差法")) {
                    taskInfList.add(getGridItem("测压面积小", df3.format(Float.parseFloat( taskParInf.getCymjx())) + " ㎡", "测压面积大",df3.format(Float.parseFloat(  taskParInf.getCymjd())) + " ㎡"));
                }}
                this.list = taskInfList;
                break;

            case 1: // 电机参数
                List<GridBean> djInfList = new ArrayList<>();
                djInfList.add(getGridItem("电机型号", taskParInf.getDjk1(), "钳表量程", taskParInf.getDjqblc1()));
                djInfList.add(getGridItem("测试方法", taskParInf.getDjcsff1(), "传动效率", taskParInf.getDjcdxl1()));

                this.list = djInfList;
                break;
            case 2: // 电机参数
                List<GridBean> dj1InfList = new ArrayList<>();
                dj1InfList.add(getGridItem("电机型号", taskParInf.getDjk2(), "钳表量程", taskParInf.getDjqblc2()));
                dj1InfList.add(getGridItem("测试方法", taskParInf.getDjcsff2(), "传动效率", taskParInf.getDjcdxl2()));
                this.list = dj1InfList;
                break;
            case 3: // 输入的参数
                List<GridBean> inputInfList = new ArrayList<>();
                String fsStr = taskParInf.getSdfs() ? df1.format(Float.parseFloat( taskParInf.getFs())) : "--";
                String wdStr = taskParInf.getSdwd() ? df2.format(Float.parseFloat( taskParInf.getWd())) : "--";
                String jyStr = taskParInf.getSdjy() ? df1.format(Float.parseFloat( taskParInf.getJy())) : "--";
                String sdStr = taskParInf.getSdsd() ? df2.format(Float.parseFloat( taskParInf.getSd())) : "--";
                String cyStr = taskParInf.getSdcy() ? df1.format(Float.parseFloat( taskParInf.getCy() )): "--";
                String dqyStr = taskParInf.getSddqy() ? df2.format(Float.parseFloat( taskParInf.getDqy())) : "--";
                String dj1glStr = taskParInf.getSddj1gl() ? df2.format(Float.parseFloat( taskParInf.getDj1gl())) : "--";
                String dj2glStr = taskParInf.getSddj2gl() ? df2.format(Float.parseFloat( taskParInf.getDj2gl() )): "--";
                String dj1xlStr = taskParInf.getSddj1xl() ? df2.format(Float.parseFloat( taskParInf.getDj1xl() )): "--";
                String dj2xlStr = taskParInf.getSddj2xl() ? df2.format(Float.parseFloat( taskParInf.getDj2xl() )): "--";

                inputInfList.add(getGridItem("风       速", fsStr + " m/s", "温       度", wdStr + " ℃"));
                inputInfList.add(getGridItem("静       压", jyStr + " Pa", "湿       度", sdStr + " %RH"));
                inputInfList.add(getGridItem("差       压", cyStr + " Pa", "大  气  压", dqyStr + " hPa"));
                inputInfList.add(getGridItem("电机1功率", dj1glStr + " kW", "电机2功率", dj2glStr + " kW"));
                inputInfList.add(getGridItem("电机1效率", dj1xlStr + " %", "电机2效率", dj2xlStr + " %"));
                inputInfList.add(getGridItem("额定转速", taskParInf.getEdzs() + " r/min", "实测转速", taskParInf.getSczs() + " r/min"));
                this.list = inputInfList;
                break;

        }
    }


    public GridAdapter(TestActivity mContext, TaskEntity taskParInf, int type,Callback callback) {
        super();
        this.mContext = mContext;
        this.type = type;
        this.taskParInf = taskParInf;
        mCallback = callback;
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
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_listview_parameter, parent, false);
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

        //任务信息
        if (type == 0) {
            if (position == 0 || position == 1 || position == 2) {
                viewHolder.textView1.setTextColor(mContext.getResources().getColor(R.color.color_radiobutton_text));
                viewHolder.textView3.setTextColor(mContext.getResources().getColor(R.color.color_radiobutton_text));
                viewHolder.textView1.setClickable(false);
                viewHolder.textView3.setClickable(false);
            }
        }


        //设置电机型号不可点击
        if (type == 1) {
            if (position == 0) {
                viewHolder.textView1.setTextColor(mContext.getResources().getColor(R.color.black_reduce));
                viewHolder.textView1.setClickable(false);
            }
        }

        //设置电机型号不可点击
        if (type == 2) {
            if (position == 0) {
                viewHolder.textView1.setTextColor(mContext.getResources().getColor(R.color.black_reduce));
                viewHolder.textView1.setClickable(false);
            }
        }

        //输入
        if (type == 3) {
            viewHolder.textView1.setTextColor(mContext.getResources().getColor(R.color.input));
            viewHolder.textView3.setTextColor(mContext.getResources().getColor(R.color.input));
            viewHolder.textView1.setEnabled(true);
            viewHolder.textView3.setEnabled(true);
            switch (position) {
                case 0: // 风速 - 温度
                    if (!taskParInf.getSdfs()) {
                        viewHolder.textView1.setTextColor(mContext.getResources().getColor(R.color.black_reduce));
                        viewHolder.textView1.setEnabled(false);
                    }

                    if (!taskParInf.getSdwd()) {
                        viewHolder.textView3.setTextColor(mContext.getResources().getColor(R.color.black_reduce));
                        viewHolder.textView3.setEnabled(false);
                    }
                    break;
                case 1: // 静压 - 湿度
                    if (!taskParInf.getSdjy()) {
                        viewHolder.textView1.setTextColor(mContext.getResources().getColor(R.color.black_reduce));
                        viewHolder.textView1.setEnabled(false);
                    }
                    if (!taskParInf.getSdsd()) {
                        viewHolder.textView3.setTextColor(mContext.getResources().getColor(R.color.black_reduce));
                        viewHolder.textView3.setEnabled(false);
                    }
                    break;
                case 2: //   差压-大气压
                    if (!taskParInf.getSdcy()) {
                        viewHolder.textView1.setTextColor(mContext.getResources().getColor(R.color.black_reduce));
                        viewHolder.textView1.setEnabled(false);
                    }
                    if (!taskParInf.getSddqy()) {
                        viewHolder.textView3.setTextColor(mContext.getResources().getColor(R.color.black_reduce));
                        viewHolder.textView3.setEnabled(false);
                    }
                    break;

                case 3: // 电机1功率-电机2功率
                    if (!taskParInf.getSddj1gl()) {
                        viewHolder.textView1.setTextColor(mContext.getResources().getColor(R.color.black_reduce));
                        viewHolder.textView1.setEnabled(false);
                    }

                    if (!taskParInf.getSddj2gl()) {
                        viewHolder.textView3.setTextColor(mContext.getResources().getColor(R.color.black_reduce));
                        viewHolder.textView3.setEnabled(false);
                    }

                case 4: // 电机1效率-电机2效率
                    if (!taskParInf.getSddj1xl()) {
                        viewHolder.textView1.setTextColor(mContext.getResources().getColor(R.color.black_reduce));
                        viewHolder.textView1.setEnabled(false);
                    }

                    if (!taskParInf.getSddj2xl()) {
                        viewHolder.textView3.setTextColor(mContext.getResources().getColor(R.color.black_reduce));
                        viewHolder.textView3.setEnabled(false);
                    }
            }
        }

        // 设置每个Item的第postion == 1 的元素的点击事件
        viewHolder.textView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                choice = -1;
                switch (type) {
                    case 0:
                        switch (position) {
                            case 3: //电压变比
                                dialogTwoEt("电压变比", taskParInf.getDybb1(), taskParInf.getDybb2(), " ", 031);
                                break;
                            case 4://测风面积
                                dialog("测风面积", taskParInf.getCfmj(), " ㎡", 041);
                                break;
                            case 5:
                                if (taskParInf.getCsff().equals("静压全压法")) {
                                    dialog("皮托管系数", taskParInf.getPtgxs(), " ", 055);
                                } else if (taskParInf.getCsff().equals("静压差法")) {
                                    dialog("测压面积小", taskParInf.getCymjx(), " ㎡", 051);
                                }
                        }
                        break;
                    case 1: //
                        switch (position) {
                            case 1: //测试方法
                                showSingleChoiceDialog(taskParInf.getDjcsff1(), 111);
                                break;

                        }
                        break;
                    case 2:  // 电机2参数
                        switch (position) {
                            case 1: //测试方法
                                showSingleChoiceDialog(taskParInf.getDjcsff2(), 211);
                                break;

                        }
                        break;
                    case 3: // 输入
                        switch (position) {
                            case 0: //风速
                                dialog("风速", taskParInf.getFs(), " m/s", 301);
                                break;
                            case 1: //静压
                                dialog("静压", taskParInf.getJy(), " Pa", 311);
                                break;
                            case 2: //差压
                                dialog("差压", taskParInf.getCy(), " Pa", 321);
                                break;
                            case 3: //电机1功率
                                dialog("电机1功率", taskParInf.getDj1gl(), " kW", 331);
                                break;
                            case 4: //电机2功率
                                dialog("电机1效率", taskParInf.getDj1xl(), " %", 341);
                                break;
                            case 5: //额定转速
                                dialog("电机1效率", taskParInf.getEdzs(), " r/min", 351);
                                break;
                        }
                        break;
                }
            }
        });


        // 设置每个Item的第postion == 3 的元素的点击事件

        viewHolder.textView3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                choice = -1;
                switch (type) {
                    case 0:
                        switch (position) {
                            case 3: //电流变比
                                dialogTwoEt("电流变比", taskParInf.getDlbb1(), taskParInf.getDlbb2(), " ", 033);
                                break;
                            case 4://测风面积
                                dialog("扩散器出口面积", taskParInf.getKsqckmj(), " ㎡", 043);
                                break;
                            case 5:
                                if (taskParInf.getCsff().equals("静压差法")) {
                                    dialog("测压面积大", taskParInf.getCymjd(), " ㎡", 053);
                                }
                        }
                        break;
                    case 1: //电机参数
                        switch (position) {
                            case 0:// 钳表量程
                                showSingleChoiceDialog(taskParInf.getDjqblc1(), 103);
                                break;
                            case 1:// 传动效率
                                showSingleChoiceDialog(taskParInf.getDjcdxl1(), 113);
                                break;

                        }
                        break;
                    case 2: //电机参数
                        switch (position) {
                            case 0:// 钳表量程
                                showSingleChoiceDialog(taskParInf.getDjqblc2(), 203);
                                break;
                            case 1:// 传动效率
                                showSingleChoiceDialog(taskParInf.getDjcdxl2(), 213);
                                break;

                        }
                        break;
                    case 3: //输入参数
                        switch (position) {
                            case 0://温度
                                dialog("温度", taskParInf.getWd(), " ℃", 303);
                                break;
                            case 1://湿度
                                dialog("湿度", taskParInf.getSd(), " ", 313);
                                break;
                            case 2://大气压
                                dialog("大气压", taskParInf.getDqy(), " hPa", 323);
                                break;
                            case 3://电机2功率
                                dialog("电机2功率", taskParInf.getDj2gl(), " kW", 333);
                                break;
                            case 4://电机2效率
                                dialog("电机2效率", taskParInf.getDj2xl(), " %", 343);
                                break;
                            case 5://实测转速
                                dialog("实测转速", taskParInf.getSczs(), " r/min", 353);
                                break;
                        }
                        break;
                }
            }
        });

        return convertView;
    }


    private class ViewHolder {
        TextView textView0;
        TextView textView1;
        TextView textView2;
        TextView textView3;
    }


    /**
     * @param parNameStr
     * @param parStr
     * @param unitStr
     * @param parType
     */
    private void dialog(String parNameStr, String parStr, String unitStr, final int parType) {
        AlertDialog.Builder customizeDialog = new AlertDialog.Builder(mContext);
        final View dialogView = LayoutInflater.from(mContext).inflate(R.layout.dialog_one_edittext, null);
        TextView nameTextView = (TextView) dialogView.findViewById(R.id.tv_dialog_name);
        final EditText inputEdittext = (EditText) dialogView.findViewById(R.id.et_dialog_input);
        TextView unitTextView = (TextView) dialogView.findViewById(R.id.tv_dialog_unit);
        nameTextView.setText(parNameStr + ":");
        inputEdittext.setText(parStr);
        unitTextView.setText(unitStr);
        customizeDialog.setTitle("参数修改");
        customizeDialog.setView(dialogView);
        customizeDialog.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        customizeDialog.setPositiveButton("确定",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // 获取EditView中的输入内容
                        String inputStr = inputEdittext.getText().toString().trim();
                        boolean notNull = inputStr.length() > 0 ? true : false;
                        float inputData = notNull ? Float.parseFloat(inputStr) : -100f;
                        switch (parType) {


                            case 041: //测风面积
                                if (notNull && inputData >= 0 && inputData <= 50) {
                                    taskParInf.setCfmj(inputStr + "");
                                } else {
                                    Toasty.error(mContext, "输入了错误的参数" + "\n" + "0 ～ 50 ㎡", 5).show();
                                }
                                break;
                            case 043: //扩散器出口面积
                                if (notNull && inputData >= 0 && inputData <= 50) {
                                    taskParInf.setKsqckmj(inputStr + "");
                                } else {
                                    Toasty.error(mContext, "输入了错误的参数" + "\n" + "0 ～ 50 ㎡", 5).show();
                                }
                                break;
                            case 051: //测压面积小
                                if (notNull && inputData >= 0 && inputData <= 50) {
                                    if(inputData>=Float.parseFloat(taskParInf.getCymjd()))
                                    {
                                        Toasty.error(mContext, "错误!应小于‘测压面积大’", 5).show();
                                    }else{
                                    taskParInf.setCymjx(inputStr + "");}
                                } else {
                                    Toasty.error(mContext, "输入了错误的参数" + "\n" + "0 ～ 50 ㎡", 5).show();
                                }
                                break;
                            case 053: //测压面积大
                                if (notNull && inputData >= 0 && inputData <= 50) {
                                    if(inputData<=Float.parseFloat(taskParInf.getCymjx()))
                                    {
                                        Toasty.error(mContext, "错误!应大于‘测压面积小’", 5).show();
                                    }else{
                                    taskParInf.setCymjd(inputStr + "");}
                                } else {
                                    Toasty.error(mContext, "输入了错误的参数" + "\n" + "0 ～ 50 ㎡", 5).show();
                                }
                                break;
                            case 055: //皮托管系数
                                if (notNull && inputData >= 0.998 && inputData <= 1.004) {
                                    taskParInf.setPtgxs(inputStr + "");
                                } else {
                                    Toasty.error(mContext, "输入了错误的参数" + "\n" + "0.998 ～ 1.004", 5).show();
                                }
                                break;
                            case 301: //风速
                                if (notNull && inputData >= 0 && inputData <= 50) {
                                    taskParInf.setSdfs(true);
                                    taskParInf.setFs(inputStr + "");
                                } else {
                                    Toasty.error(mContext, "输入了错误的参数" + "\n" + "0 ～ 50 m/s", 5).show();
                                }
                                break;
                            case 303: //温度
                                if (notNull && inputData > 0 && inputData >= -40 && inputData <= 100) {
                                    taskParInf.setSdwd(true);
                                    taskParInf.setWd(inputStr + "");
                                } else {
                                    Toasty.error(mContext, "输入了错误的参数" + "\n" + "-40 ～ 100 ℃", 5).show();
                                }
                                break;
                            case 311: //静压
                                if (notNull && inputData >= 0 && inputData <= 8000) {
                                    taskParInf.setSdjy(true);
                                    taskParInf.setJy(inputStr + "");
                                } else {
                                    Toasty.error(mContext, "输入了错误的参数" + "\n" + "0 ～ 8000 Pa", 5).show();
                                }
                                break;
                            case 313: //湿度
                                if (notNull && inputData >= 0 && inputData <= 100) {
                                    taskParInf.setSdsd(true);
                                    taskParInf.setSd(inputStr + "");
                                } else {
                                    Toasty.error(mContext, "输入了错误的参数" + "\n" + "0 ～ 100 %RH", 5).show();
                                }
                                break;
                            case 321: //差压
                                if (notNull && inputData >= 0 && inputData <= 2000) {
                                    taskParInf.setSdcy(true);
                                    taskParInf.setCy(inputStr + "");
                                } else {
                                    Toasty.error(mContext, "输入了错误的参数" + "\n" + "0 ～ 2000 Pa", 5).show();
                                }
                                break;
                            case 323: //大气压
                                if (notNull && inputData >= 100 && inputData <= 1200) {
                                    taskParInf.setSddqy(true);
                                    taskParInf.setDqy(inputStr + "");
                                } else {
                                    Toasty.error(mContext, "输入了错误的参数" + "\n" + "100 ～ 1200 hPa", 5).show();
                                }
                                break;
                            case 331: //电机1功率
                                if (notNull && inputData >= 0 && inputData <= 750) {
                                    taskParInf.setSddj1gl(true);
                                    taskParInf.setDj1gl(inputStr + "");
                                } else {
                                    Toasty.error(mContext, "输入了错误的参数" + "\n" + "0 ～ 750 kW", 5).show();
                                }
                                break;
                            case 333: //电机2功率
                                if (notNull && inputData >= 0 && inputData <= 750) {
                                    taskParInf.setSddj2gl(true);
                                    taskParInf.setDj2gl(inputStr + "");
                                } else {
                                    Toasty.error(mContext, "输入了错误的参数" + "\n" + "0 ～ 750 kW", 5).show();
                                }
                                break;
                            case 341: //电机1效率
                                if (notNull && inputData >= 0 && inputData <= 100) {
                                    taskParInf.setSddj1xl(true);
                                    taskParInf.setDj1xl(inputStr + "");
                                } else {
                                    Toasty.error(mContext, "输入了错误的参数" + "\n" + "0 ～ 100 %", 5).show();
                                }
                                break;
                            case 343: //电机2效率
                                if (notNull && inputData >= 0 && inputData <= 100) {
                                    taskParInf.setSddj2xl(true);
                                    taskParInf.setDj2xl(inputStr + "");
                                } else {
                                    Toasty.error(mContext, "输入了错误的参数" + "\n" + "0 ～ 100 %", 5).show();
                                }
                                break;
                            case 351: //额定转速
                                if (notNull && inputData >= 0 && inputData <= 3000) {

                                    taskParInf.setEdzs(inputStr + "");
                                } else {
                                    Toasty.error(mContext, "输入了错误的参数" + "\n" + "0 ～ 3000 r/min", 5).show();
                                }
                                break;
                            case 353: //实测转速
                                if (notNull && inputData >= 0 && inputData <= 3000) {

                                    taskParInf.setSczs(inputStr + "");
                                } else {
                                    Toasty.error(mContext, "输入了错误的参数" + "\n" + "0 ～ 3000 r/min", 5).show();
                                }
                                break;

                        }
                        GreateTaskUtils.update(taskParInf);
                        mCallback.click();
                        setListViewType();
                        Toasty.success(mContext, "参数已修改", 5).show();
                        notifyDataSetChanged();

                    }
                });
        customizeDialog.show();
    }


    private void dialogTwoEt(String parNameStr, String parOneStr, String parTwoStr, String unitStr, final int parType) {
        AlertDialog.Builder customizeDialog = new AlertDialog.Builder(mContext);
        final View dialogView = LayoutInflater.from(mContext).inflate(R.layout.dialog_two_edittext, null);
        TextView nameTextView = (TextView) dialogView.findViewById(R.id.tv_dialog_name);
        final EditText inputOneEdittext = (EditText) dialogView.findViewById(R.id.et_dialog_input_one);
        final EditText inputTwoEdittext = (EditText) dialogView.findViewById(R.id.et_dialog_input_two);
        TextView unitTextView = (TextView) dialogView.findViewById(R.id.tv_dialog_unit);

        nameTextView.setText(parNameStr);
        inputOneEdittext.setText(parOneStr);
        inputTwoEdittext.setText(parTwoStr);
        unitTextView.setText(unitStr);
        customizeDialog.setTitle("参数修改");
        customizeDialog.setView(dialogView);
        customizeDialog.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        customizeDialog.setPositiveButton("确定",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (parType) {
                            case 031:  //  电压变比
                                if (!TextUtils.isEmpty(inputOneEdittext.getText())) {
                                    taskParInf.setDybb1(inputOneEdittext.getText().toString().trim());
                                } else {
                                    taskParInf.setDybb1("1");
                                }

                                if (!TextUtils.isEmpty(inputTwoEdittext.getText())) {
                                    if (Float.parseFloat(inputTwoEdittext.getText().toString()) == 0) {
                                        Toasty.error(mContext, "输入了错误的参数" + "\n" + "变比分母不可为0！", 5).show();
                                    } else {
                                        taskParInf.setDybb2(inputTwoEdittext.getText().toString().trim());
                                    }
                                } else {
                                    taskParInf.setDybb2("1");
                                }

                                break;

                            case 033: // 电流变比

                                if (!TextUtils.isEmpty(inputOneEdittext.getText())) {
                                    taskParInf.setDlbb1(inputOneEdittext.getText().toString().trim());
                                } else {
                                    taskParInf.setDlbb1("1");
                                }

                                if (!TextUtils.isEmpty(inputTwoEdittext.getText())) {
                                    if (Float.parseFloat(inputTwoEdittext.getText().toString()) == 0) {
                                        Toasty.error(mContext, "输入了错误的参数" + "\n" + "变比分母不可为0！", 5).show();
                                    } else {
                                        taskParInf.setDlbb2(inputTwoEdittext.getText().toString());
                                    }
                                } else {
                                    taskParInf.setDlbb2("1");
                                }
                                break;

                        }

                        GreateTaskUtils.update(taskParInf);
                        mCallback.click();
                        setListViewType();
                        Toasty.success(mContext, "参数已修改", 5).show();
                        notifyDataSetChanged();
                    }
                });
        customizeDialog.show();
    }


    int choice = -1;
    String[] items = new String[]{};

    private void showSingleChoiceDialog(String itemStr, final int typePar) {
        final String[] cshffItems = {"单瓦法", "双瓦法", "三瓦法"};
        final String[] qblchItems = {"500A", "20A"};
        final String[] chdfshItems = {"1", "0.99", "0.98", "0.97", "0.96", "0.95", "0.94", "0.93", "0.92", "0.91", "0.90", "0.89", "0.88", "0.87"};
        switch (typePar) {
            case 203: //钳表量程
                items = qblchItems;
                break;
            case 103:
                items = qblchItems;
                break;

            case 211://电机测试方法
                items = cshffItems;
                break;
            case 111:
                items = cshffItems;
                break;

            case 213://传动方式
                items = chdfshItems;
                break;
            case 113:
                items = chdfshItems;
                break;
        }

        AlertDialog.Builder singleChoiceDialog = new AlertDialog.Builder(mContext);
        singleChoiceDialog.setTitle("选择参数");
        // 第二个参数是默认选项，此处设置为0
        int indexPostion = 0;
        for (int i = 0; i < items.length; i++) {
            if (itemStr.equals(items[i])) {
                indexPostion = i;
            }
        }

        singleChoiceDialog.setSingleChoiceItems(items, indexPostion,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        choice = which;
                    }
                });

        singleChoiceDialog.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        singleChoiceDialog.setPositiveButton("确定",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (choice != -1) {
                            String itemStr = items[choice];
                            switch (typePar) {
                                case 103: //钳表量程
                                    taskParInf.setDjqblc1(itemStr);
                                    break;
                                case 111://电机测试方法
                                    taskParInf.setDjcsff1(itemStr);
                                    break;
                                case 113://传动效率
                                    taskParInf.setDjcdxl1(itemStr);
                                    break;
                                case 203: //钳表量程
                                    taskParInf.setDjqblc2(itemStr);
                                    break;
                                case 211://电机测试方法
                                    taskParInf.setDjcsff2(itemStr);
                                    break;
                                case 213://传动效率
                                    taskParInf.setDjcdxl2(itemStr);
                                    break;
                            }
                        }
                        GreateTaskUtils.update(taskParInf);
                        mCallback.click();
                        setListViewType();
                        Toast.makeText(mContext, "参数已修改", Toast.LENGTH_SHORT).show();
                        notifyDataSetChanged();
                    }
                });
        singleChoiceDialog.show();
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
    private GridBean getGridItem(String item0, String item1, String item2, String item3) {
        GridBean gridBean = new GridBean();
        gridBean.setStr0(item0);
        gridBean.setStr1(item1);
        gridBean.setStr2(item2);
        gridBean.setStr3(item3);
        return gridBean;
    }
}
