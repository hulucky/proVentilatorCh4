package com.ventilator.test.fragment;


import android.app.Activity;
import android.app.AlertDialog;
import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.greendao.manager.DataTFJ;
import com.greendao.manager.TaskEntityDao;
import com.greendao.manager.TaskResEnityDao;
import com.ventilator.Adapter.TestShowPagerAdapter;
import com.ventilator.administrator.DATAbase.R;
import com.ventilator.administrator.DATAbase.greendao.TaskEntity;
import com.ventilator.administrator.DATAbase.greendao.TaskResEnity;
import com.ventilator.app.MyApp;
import com.ventilator.bean.ListBean;
import com.ventilator.data.DataDetailActivity;
import com.ventilator.print.PrintActivity;
import com.ventilator.service.FileService;
import com.ventilator.test.TestActivity;
import com.ventilator.test.fragment.DataChildFragment.DataCurvefragment;
import com.ventilator.test.fragment.DataChildFragment.DataDetailfragment;
import com.ventilator.view.MyNoScrollViewPager;

import java.io.File;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import es.dmoral.toasty.Toasty;
import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableCell;
import jxl.write.WritableImage;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

public class DataFragment extends Fragment {

    @BindView(R.id.fr_detailcurve)
    MyNoScrollViewPager flmain;
    @BindView(R.id.btn_testdetail_delete)
    Button btndelete;
    @BindView(R.id.btn_testdetail_curve)
    Button btncurve;
    @BindView(R.id.btn_testdetail_report)
    Button btnreport;
    @BindView(R.id.btn_testdetail_print)
    Button btnprint;

    @BindView(R.id.lv_testdata_list)
    ListView lv_list;

    List<ListBean> mbeanlist;
    List<TaskResEnity> mreslist;
    DataCurvefragment mDataCurvefragment;
    DataDetailfragment mDataDetailfragment;
    List<Fragment> list_fragments;
    Activity mActivity;
    TaskEntity mTask;
    MyApp myApp;
    Unbinder unbinder;
    DecimalFormat df4 = new DecimalFormat("####0.00");
    DecimalFormat df2 = new DecimalFormat("####0");
    DecimalFormat df3 = new DecimalFormat("####0.0");
    @BindView(R.id.ll_bottom)
    LinearLayout llBottom;
    private FileService fileService;
    long SystemTime = 0;
    List<Long> Res_id;

    private Map<Integer, Integer> selected;


    DecimalFormat df1 = new DecimalFormat("####0.0");
    TestDataListAdapter mAdapter;
    private boolean IsShowCurve = false;
    private ArrayList<DataTFJ> mData;

    public boolean isDataManage() {
        return isDataManage;
    }

    public void setDataManage(boolean dataManage) {
        isDataManage = dataManage;
    }

    boolean isDataManage = true;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        list_fragments = new ArrayList<Fragment>();
        mDataCurvefragment = new DataCurvefragment();
        mDataDetailfragment = new DataDetailfragment();
        list_fragments.add(mDataDetailfragment);
        list_fragments.add(mDataCurvefragment);
        mbeanlist = new ArrayList<>();
        selected = new HashMap<Integer, Integer>();

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_test_res_data, null);
        myApp = MyApp.getInstance();
        mTask = myApp.getTaskEnity();
        unbinder = ButterKnife.bind(this, view);
        try {
            mActivity = (TestActivity) getActivity();
        } catch (Exception e) {
            mActivity = (DataDetailActivity) getActivity();
            llBottom.setVisibility(View.GONE);
            e.printStackTrace();
        }

        flmain.setNoScroll(true);//MyNoScrollViewPager,上面的
        if (list_fragments != null) {
            TestShowPagerAdapter msgAdapter = new TestShowPagerAdapter(getChildFragmentManager(), list_fragments);
            flmain.setAdapter(msgAdapter);
            flmain.setCurrentItem(0);
        }
        GetData();

        return view;
    }

    public void RefreshList() {
        int IndexSel = lv_list.getSelectedItemPosition();
        InitList();
        lv_list.setSelection(IndexSel);
    }

    public void InitList() {
        mbeanlist = new ArrayList<>();
        Long taskId = mTask.getId();
        TaskResEnityDao mdao = MyApp.getDaoInstant().getTaskResEnityDao();
        mreslist = mdao.queryBuilder().where(TaskResEnityDao.Properties.TaskId.eq(taskId)).list();
        for (TaskResEnity mres : mreslist) {
            ListBean mbean = new ListBean();
            mbean.setId(mres.getId());
            mbean.setStr0(df1.format(mres.getMgFl()));

            mbean.setStr1(df1.format(mres.getMgFjjy()));
            mbean.setStr2(df4.format(mres.getMgZgl1()));
            mbean.setStr3(df4.format(mres.getMgZgl2()));
            String mxl = "";
            if (mTask.getTffs().equals("压入式")) {
                mxl = df4.format(mres.getMgQyxl());
            } else {
                mxl = df4.format(mres.getMgJyxl());
            }
            mbean.setStr4(mxl);
            mbean.setStr5(mres.getSaveTime().replace(" ", "\n"));
            mbeanlist.add(mbean);
        }
        mAdapter = new TestDataListAdapter(getActivity(), mbeanlist);

        lv_list.setAdapter(mAdapter);
        lv_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //
                mDataDetailfragment.RefreshByRes(mreslist.get(position));
                flmain.setCurrentItem(0);
                IsShowCurve = false;
                //修改部分
                DataTFJ mdata = new DataTFJ();
                mdata.SetResOnly(mreslist.get(position));
                mDataDetailfragment.Refresh(mdata);
            }
        });
    }

    public void GetData() {
        try {
            InitList();
            lv_list.setSelection(0);

            DataTFJ mdata = new DataTFJ();

            mdata.SetResOnly(mreslist.get(0));
            mDataDetailfragment.Refresh(mdata);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


    }

    @Override
    public void onStart() {
        super.onStart();
        init();
    }

    public void init() {
        try {
            GetData();
            mAdapter = new TestDataListAdapter(getActivity(), mbeanlist);
            lv_list.setAdapter(mAdapter);
            flmain.setCurrentItem(0);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @OnClick({R.id.btn_testdetail_delete, R.id.btn_testdetail_print, R.id.btn_testdetail_curve, R.id.btn_testdetail_report})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_testdetail_curve:

                Res_id = new ArrayList<Long>();
                for (Map.Entry<Integer, Integer> entry : selected.entrySet()) {
                    Res_id.add(mreslist.get(entry.getValue()).getId());
                }

                if (Res_id.size() == 0) {
                    Toasty.error(mActivity, "请先勾选生成曲线的数据！", Toast.LENGTH_SHORT, true).show();
                } else {
                    flmain.setCurrentItem(1);
                    mDataCurvefragment.setResIds(Res_id);
                    IsShowCurve = true;
                }
                break;
            case R.id.btn_testdetail_delete:
                if (mreslist != null) {
                    Res_id = new ArrayList<Long>();
                    for (Map.Entry<Integer, Integer> entry : selected.entrySet()) {
                        Res_id.add(mreslist.get(entry.getValue()).getId());
                    }

                    if (Res_id.size() == 0) {
                        Toasty.error(mActivity, "请先勾选需要删除的数据！", Toast.LENGTH_SHORT, true).show();
                    } else {
                        new AlertDialog.Builder(mActivity)
                                .setTitle("系统提示")
                                // 设置对话框标题

                                .setMessage("确认要删除选中数据？")
                                // 设置显示的内容

                                .setPositiveButton("确定",
                                        new DialogInterface.OnClickListener() {// 添加确定按钮

                                            public void onClick(
                                                    DialogInterface dialog,
                                                    int which) {// 确定按钮的响应事件

                                                // TODO Auto-generated method stub
                                                for (Long id : Res_id
                                                        ) {
                                                    MyApp.getDaoInstant().getTaskResEnityDao().deleteByKey(id);
                                                }
                                                InitList();
                                                if (mreslist.size() == 0) {
                                                    if (isDataManage) {
                                                        mActivity.finish();
                                                    }
                                                }

                                            }

                                        })
                                .setNegativeButton("取消",
                                        new DialogInterface.OnClickListener() {// 添加返回按钮

                                            public void onClick(
                                                    DialogInterface dialog,
                                                    int which) {// 响应事件

                                                // TODO Auto-generated method stub

                                            }

                                        }).show();// 在按键响应事件中显示此对话框

                    }
                }

                break;
            case R.id.btn_testdetail_print:

                Res_id = new ArrayList<Long>();
                for (Map.Entry<Integer, Integer> entry : selected.entrySet()) {
                    Res_id.add(mreslist.get(entry.getValue()).getId());
                }

                if (Res_id.size() == 0) {
                    Toasty.error(mActivity, "请先勾选需要打印的数据！", Toast.LENGTH_SHORT, true).show();
                } else {
                    Intent intent1 = new Intent();
                    intent1.setClass(mActivity, PrintActivity.class);
                    intent1.putExtra("testResID", Res_id.get(0));

                    startActivity(intent1);
                }
                break;
            case R.id.btn_testdetail_report:

                Res_id = new ArrayList<Long>();
                for (Map.Entry<Integer, Integer> entry : selected.entrySet()) {
                    Res_id.add(mreslist.get(entry.getValue()).getId());
                }

                if (Res_id.size() == 0) {
                    Toasty.error(mActivity, "请先勾选生成报告的数据！", Toast.LENGTH_SHORT, true).show();
                } else {
                    GetmData(Res_id);
                    updateExcel(mData, SystemTime);
                }
                break;

        }
    }

    public class TestDataListAdapter extends BaseAdapter {

        private Activity mContext;
        private List<ListBean> list;


        public TestDataListAdapter(Activity mContext, List<ListBean> mlist) {
            super();
            this.mContext = mContext;
            this.list = mlist;
            selected = new HashMap<Integer, Integer>();

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
                convertView = LayoutInflater.from(mContext).inflate(R.layout.item_listview_testdatalistl, parent, false);
                viewHolder.checkBox = convertView.findViewById(R.id.chb_testdetaillist_xh);
                viewHolder.textView0 = convertView.findViewById(R.id.tv_testdatalist_tab0);
                viewHolder.textView1 = convertView.findViewById(R.id.tv_testdatalist_tab1);
                viewHolder.textView2 = convertView.findViewById(R.id.tv_testdatalist_tab2);
                viewHolder.textView3 = convertView.findViewById(R.id.tv_testdatalist_tab3);
                viewHolder.textView4 = convertView.findViewById(R.id.tv_testdatalist_tab4);
                viewHolder.textView5 = convertView.findViewById(R.id.tv_testdatalist_tab5);

                convertView.setTag(viewHolder);


            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }
            ListBean gridBean = list.get(position);
            viewHolder.checkBox.setText((position + 1) + "");
            viewHolder.checkBox.setTag(position);
            viewHolder.textView0.setText(gridBean.getStr0());
            viewHolder.textView1.setText(gridBean.getStr1());
            viewHolder.textView2.setText(gridBean.getStr2());
            viewHolder.textView3.setText(gridBean.getStr3());
            viewHolder.textView4.setText(gridBean.getStr4());
            viewHolder.textView5.setText(gridBean.getStr5());
            viewHolder.textView0.setBackgroundResource(R.drawable.listviewselector);
            viewHolder.textView1.setBackgroundResource(R.drawable.listviewselector);
            viewHolder.textView2.setBackgroundResource(R.drawable.listviewselector);
            viewHolder.textView3.setBackgroundResource(R.drawable.listviewselector);
            viewHolder.textView4.setBackgroundResource(R.drawable.listviewselector);

            viewHolder.textView5.setBackgroundResource(R.drawable.listviewselector);
            if (selected.containsKey(position)) {
                viewHolder.checkBox.setChecked(true);
            } else {
                viewHolder.checkBox.setChecked(false);
            }
            viewHolder.checkBox
                    .setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                        @Override
                        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                            // TODO Auto-generated method stub
                            if (isChecked) {
                                if (!selected.containsKey(buttonView.getTag()))
                                    selected.put((Integer) buttonView.getTag(),
                                            position);
                                // Toast.makeText(Data_Detail_Temp.this,
                                // (Integer) buttonView.getTag()+"", 0).show();
                            } else {
                                selected.remove((Integer) buttonView.getTag());
                            }
                            if (selected.size() == 1) {
                                btnprint.setVisibility(View.VISIBLE);
                            } else {
                                btnprint.setVisibility(View.GONE);
                            }
                            if (selected.size() > 3) {
                                btncurve.setVisibility(View.VISIBLE);
                            } else {
                                btncurve.setVisibility(View.GONE);
                            }
                        }
                    });


            return convertView;
        }

        private class ViewHolder {
            CheckBox checkBox;
            TextView textView0;
            TextView textView1;
            TextView textView2;
            TextView textView3;
            TextView textView4;
            TextView textView5;
        }


        /**
         * 返回 GridBean
         *
         * @param item0
         * @param item1
         * @return
         */
        private ListBean getTSGridItem(String item0, String item1) {
            ListBean gridBean = new ListBean();
            gridBean.setStr0(item0);
            gridBean.setStr1(item1);
            gridBean.setStr2(item0);
            gridBean.setStr3(item1);
            gridBean.setStr4(item0);
            gridBean.setStr5(item1);

            return gridBean;
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    /**
     * jxl��ʱ���ṩ�޸��Ѿ����ڵ����ݱ�,����ͨ��һ��С�취���ﵽ���Ŀ��,���ʺϴ������ݸ���! ������ͨ������ԭ�ļ������µ�.
     */
    public void updateExcel(List<DataTFJ> mDataList, Long systimeLong) {
        SimpleDateFormat sDateFormat = new SimpleDateFormat("yyMMdd-HHmmss");
        String date = sDateFormat.format(new Date());
        try {

            // Workbook workbook = Workbook.getWorkbook(new
            // File(Environment.getExternalStorageDirectory().getAbsolutePath()+"/ͨ����ۺϲ�����/.SystemFiles/SystemFiles/flow(��ֹɾ��).ky"));
            Workbook workbook = Workbook.getWorkbook(new File(Environment
                    .getExternalStorageDirectory().getAbsolutePath()
                    + "/矿用通风机无线多参数测试仪/.报告模板/通风机报告.xls"));

            WritableWorkbook writableWorkbook = Workbook.createWorkbook(
                    new File(Environment.getExternalStorageDirectory()
                            .getAbsolutePath()
                            + "/矿用通风机无线多参数测试仪/测试报告/"
                            + mTask.getUnitName()
                            + "_"
                            + mTask.getNumber()
                            + "_"
                            + date
                            + ".xls"), workbook);// copy
            WritableSheet ws7 = writableWorkbook.getSheet(7);
            WritableSheet ws8 = writableWorkbook.getSheet(8);

            Log.d("book", "mResList.size()" + mDataList.size() + "");
            //排序,大于0表示正序，小于0表示逆序,正数：就交换进行比较的两个元素的位置
            Collections.sort(mDataList, new Comparator<DataTFJ>() {
                @Override
                public int compare(DataTFJ o1, DataTFJ o2) {
                    return (int) o1.getMId() - (int) o2.getMId();//升序
                }
            });


            for (int i = 0; i < Math.min(7, mDataList.size()); i++) {
                DataTFJ mdata = mDataList.get(i);
                String[] mStrings = new String[50];
                mStrings[0] = i + 1 + "";
                mStrings[1] = df3.format(mdata.getmWd());
                mStrings[2] = df3.format(mdata.getmDqy()) + "";
                mStrings[3] = df3.format(mdata.getmJy()) + "";
                mStrings[4] = df3.format(mdata.getmPjfs()) + "";
                mStrings[5] = df3.format(mdata.getmFl()) + "";
                mStrings[6] = df3.format(mdata.getmSd()) + "";
                mStrings[7] = df3.format(mdata.getmDy()) + "";
                if (mdata.GetHisTask().getSddj1gl()) {
                    mStrings[8] = df3.format(mdata.getmDjgl()) + "";
                } else {
                    mStrings[8] = df3.format(mdata.getDjgl()) + "";
                }
                if (mdata.GetHisTask().getSddj2gl()) {
                    mStrings[9] = df3.format(mdata.getmDjgl2()) + "";
                } else {
                    mStrings[9] = df3.format(mdata.getDjgl2()) + "";
                }
                mStrings[10] = df3.format((mdata.getDj1cdxl() + mdata.getDj2cdxl()) / 2) + "";
                mStrings[11] = df3.format((Float.parseFloat(mTask.getDjedgl1()) + Float.parseFloat(mTask.getDjedgl2()))) + "";

                mStrings[12] = mTask.getCsff();


                mStrings[13] = i + 1 + "";
                mStrings[14] = df3.format(mdata.getmFjjy()) + "";
                mStrings[15] = df4.format(mdata.getmKqmd()) + "";
                mStrings[16] = df3.format(mdata.getmCfmj()) + "";
                mStrings[17] = df3.format(mdata.getmCymjx()) + "";
                mStrings[18] = df3.format(mdata.getmCymjd()) + "";
                mStrings[19] = df3.format(mdata.getmFjqy()) + "";
                mStrings[20] = df3.format(mdata.getmJygl()) + "";
                mStrings[21] = df3.format(mdata.getmQygl()) + "";
                mStrings[22] = df3.format(mdata.getmFjyxxl()) + "";
                mStrings[23] = "";
                mStrings[24] = "";
                mStrings[25] = i + 1 + "";
                mStrings[26] = df3.format(mdata.getmFl()) + "";
                mStrings[27] = df3.format(mdata.getmFjqy()) + "";
                mStrings[28] = df3.format(mdata.getmFjjy()) + "";
                mStrings[29] = df3.format(mdata.getmZgl1() + mdata.getmZgl2())
                        + "";
                mStrings[30] = df3.format(mdata.getmQygl()) + "";
                mStrings[31] = df3.format(mdata.getmJygl()) + "";
                mStrings[32] = df3.format(mdata.getmQyxl()) + "";
                mStrings[33] = df3.format(mdata.getmJyxl()) + "";
                mStrings[34] = df3.format(mdata.getmSczs()) + "";
                mStrings[35] = df3.format(mdata.getMcKqmdzhxs()) + "";
                mStrings[36] = i + 1 + "";
                mStrings[37] = df3.format(mdata.getMcZszhxs()) + "";
                mStrings[38] = df3.format(mdata.getMcZszhxs()
                        * mdata.getMcZszhxs())
                        + "";
                mStrings[39] = df3.format(mdata.getMcZszhxs()
                        * mdata.getMcZszhxs() * mdata.getMcZszhxs())
                        + "";
                mStrings[40] = df3.format(mdata.getMgFl()) + "";
                mStrings[41] = df3.format(mdata.getMgFjqy()) + "";
                mStrings[42] = df3.format(mdata.getMgFjjy()) + "";
                mStrings[43] = df3
                        .format((mdata.getMgZgl1()) + mdata.getMgZgl2()) + "";
                mStrings[44] = df3.format(mdata.getMgQygl()) + "";
                mStrings[45] = df3.format(mdata.getMgJygl()) + "";
                mStrings[46] = df3.format(mdata.getMgQyxl()) + "";
                mStrings[47] = df3.format(mdata.getMgJyxl()) + "";

                for (int j = 0; j < 13; j++) {
                    WritableCell wc = ws7.getWritableCell(j + 1, i + 5); // �� Ȼ��
                    // ��
                    // ��
                    Label l = (Label) wc; // (ǿת)�õ���Ԫ���Label()����
                    l.setString((mStrings[j])); // ��������ֵ
                }
                for (int j = 0; j < 12; j++) {
                    WritableCell wc = ws7.getWritableCell(j + 1, i + 16); // ��
                    // Ȼ��
                    // ��
                    Label l = (Label) wc; // (ǿת)�õ���Ԫ���Label()����
                    l.setString((mStrings[j + 13])); // ��������ֵ
                }
                for (int j = 0; j < 11; j++) {
                    WritableCell wc = ws8.getWritableCell(j + 1, i + 8); // �� Ȼ��
                    Label l = (Label) wc; // (ǿת)�õ���Ԫ���Label()����
                    l.setString((mStrings[j + 25])); // ��������ֵ
                }
                for (int j = 0; j < 12; j++) {
                    WritableCell wc = ws8.getWritableCell(j + 1, i + 22); // ��
                    Label l = (Label) wc; // (ǿת)�õ���Ԫ���Label()����
                    l.setString((mStrings[j + 36])); // ��������ֵ
                }
            }

            // ���ͼƬ

            WritableSheet ws6 = writableWorkbook.getSheet(5);
            if (IsShowCurve && myApp.getSnapName() != null) {
                File mCurve = new File("mnt/sdcard" + "/矿用通风机无线多参数测试仪/.通风机性能曲线图/"
                        + myApp.getSnapName() + "" + ".png");
                if (mCurve != null) {
                    ws6.addImage(new WritableImage(1, 12, 8, 7, mCurve));
                }
            }
            writableWorkbook.write();
            writableWorkbook.close();
            workbook.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            Intent intent = getExcelFileIntent(new File(Environment
                    .getExternalStorageDirectory().getAbsolutePath()
                    + "/矿用通风机无线多参数测试仪/测试报告/"
                    + mTask.getUnitName()
                    + "_"
                    + mTask.getNumber()
                    + "_"
                    + date
                    + ".xls"));
            startActivity(intent);
        } catch (ActivityNotFoundException e) {
            e.printStackTrace();
        }
    }

    // ��Word
    public static Intent getWordFileIntent(String param) {
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.addCategory("android.intent.category.DEFAULT");
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        Uri uri = Uri.fromFile(new File(param));
        intent.setDataAndType(uri, "application/msword");
        return intent;
    }

    //android��ȡһ�����ڴ�Excel�ļ���intent
    public static Intent getExcelFileIntent(File file) {
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.addCategory("android.intent.category.DEFAULT");
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        Uri uri = Uri.fromFile(file);
        intent.setDataAndType(uri, "application/vnd.ms-excel");
        return intent;
    }

    private void initSize(WebSettings settings) {

        settings.setBuiltInZoomControls(true);

        int screenDensity = getResources().getDisplayMetrics().densityDpi;
        WebSettings.ZoomDensity zoomDensity = WebSettings.ZoomDensity.MEDIUM;
        switch (screenDensity) {
            // ����Ĭ�����ŷ�ʽ�ߴ���far ?��MEDIUM�ɣ�
            case DisplayMetrics.DENSITY_LOW:
                zoomDensity = WebSettings.ZoomDensity.CLOSE;
                break;
            case DisplayMetrics.DENSITY_MEDIUM:
                zoomDensity = WebSettings.ZoomDensity.MEDIUM;
                break;
            case DisplayMetrics.DENSITY_HIGH:
                zoomDensity = WebSettings.ZoomDensity.FAR;
                break;
        }
        settings.setDefaultZoom(zoomDensity);
    }

    private void GetmData(List<Long> ResIdLists) {
        mData = new ArrayList<DataTFJ>();
        TaskResEnityDao mdao = MyApp.getDaoInstant().getTaskResEnityDao();
        TaskEntityDao mtaskdao = MyApp.getDaoInstant().getTaskEntityDao();
        TaskEntity mHisTask = null;
        for (int i = 0; i < ResIdLists.size(); i++) {

            long resId = ResIdLists.get(i);


            TaskResEnity mRes = mdao.queryBuilder()
                    .where(TaskResEnityDao.Properties.Id.eq(resId)).list().get(0);


            if (mHisTask == null) {
                mHisTask = mtaskdao.queryBuilder()
                        .where(TaskEntityDao.Properties.Id.eq(mRes.getTaskId())).list().get(0);
            }
            DataTFJ md = new DataTFJ();

            md.SetResOnly(mRes);
            md.Refresh();
            mData.add(md);
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
//        setUserVisibleHint(true);
    }
}
