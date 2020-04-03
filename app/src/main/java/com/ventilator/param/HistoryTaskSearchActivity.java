package com.ventilator.param;



import com.ventilator.administrator.DATAbase.greendao.TaskEntity;
import com.ventilator.app.MyApp;
import com.ventilator.test.TestActivity;
import com.xzkydz.function.search.module.ITaskRoot;
import com.xzkydz.function.search.view.SearchHistoryTaskActivity;

import java.util.ArrayList;
import java.util.List;

public class HistoryTaskSearchActivity extends SearchHistoryTaskActivity {


    @Override
    protected List<ITaskRoot> getTaskDataList() {
        List<ITaskRoot> list = new ArrayList<>();
        List<TaskEntity> taskEnities = MyApp.getDaoInstant().getTaskEntityDao().loadAll();
        list.addAll(taskEnities);
        return list;
    }



    @Override
    protected Class getTestClass(ITaskRoot iTaskRoot) {
        if (iTaskRoot!=null){
            TaskEntity taskEntity = MyApp.getDaoInstant().getTaskEntityDao().load(iTaskRoot.getTaskId());
            MyApp.setTaskEnity(taskEntity);
        }
        return TestActivity.class;
    }

}
