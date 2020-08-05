package com.ventilator.data;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;

import com.greendao.dbUtils.GreateTaskUtils;
import com.greendao.dbUtils.TaskResUtils;
import com.greendao.manager.TaskResEnityDao;
import com.ventilator.administrator.DATAbase.greendao.TaskEntity;
import com.ventilator.administrator.DATAbase.greendao.TaskResEnity;
import com.ventilator.app.MyApp;
import com.ventilator.bean.TfjJsonBean;
import com.xuhao.didi.core.iocore.interfaces.ISendable;
import com.xzkydz.function.data.view.DataMagerActivity;
import com.xzkydz.function.search.module.ITaskRoot;
import com.xzkydz.function.socket.ShakeHandsMb;
import com.xzkydz.function.utils.DeviceUtils;

import java.util.ArrayList;
import java.util.List;

public class DataManager extends DataMagerActivity {


    @Override
    public void getTaskList() {
//        List<ITaskRoot> list = new ArrayList<>();
//        List<TaskEntity> taskEnities = MyApp.getInstance().getDaoInstant().getTaskEntityDao().loadAll();
//        list.addAll(taskEnities);
//        //注意：最设置数据
//        setDataAndPushView(list);

        new Thread(new Runnable() {
            @Override
            public void run() {
                List<ITaskRoot> list = new ArrayList<>();
                List<TaskEntity> taskEnities = MyApp.getInstance().getDaoInstant().getTaskEntityDao().loadAll();
                list.addAll(taskEnities);
                //注意：最设置数据
                setDataAndPushView(list);
            }
        }).start();

    }

    @NonNull
    @Override
    public Class<?> getDataDetailActivity() {
        return DataDetailActivity.class;
    }

    /**
     * 设置任务为完成状态：成功后返回为true
     *
     * @param task
     * @return
     */
    @Override
    public boolean setTaskComplete(ITaskRoot task) {
        TaskEntity taskEnity = (TaskEntity) task;
        taskEnity.set_IsCompleteTask(true);
        GreateTaskUtils.update(taskEnity);
        return true;
    }

    /**
     * 删除测试任务：成功后返回为true
     *
     * @param task
     * @return 成功删除后返回为True
     */
    @Override
    public boolean deleteTaskAndData(ITaskRoot task) {
        GreateTaskUtils.delete(task.getTaskId());
        TaskResUtils.deleteByTaskId(task.getTaskId());
        return true;
    }

    @Override
    public boolean setShowUpDataToPc(boolean isShowUpPc) {
        return true;
    }

    @Override
    public ShakeHandsMb getShakeHandsMb() {
        String appName = "";
        try {
            PackageManager packageManager = DataManager.this.getPackageManager();
            PackageInfo packageInfo = packageManager.getPackageInfo(DataManager.this.getPackageName(), 0);
            int labelRes = packageInfo.applicationInfo.labelRes;
            appName = DataManager.this.getResources().getString(labelRes);
        } catch (Exception e) {
            e.printStackTrace();
        }
        ShakeHandsMb shakeHandsMb = new ShakeHandsMb("", appName, DeviceUtils.getUniqueId(DataManager.this), System.currentTimeMillis());
        return shakeHandsMb;
    }

    @Override
    public ISendable getUploadDatas(Long taskId) {

        //查询任务
        TaskEntity taskEntity = MyApp.getDaoInstant().getTaskEntityDao().load(taskId);
        List<TaskResEnity> taskResEnities = MyApp.getDaoInstant().getTaskResEnityDao().queryBuilder().where(TaskResEnityDao.Properties.TaskId.eq(taskId)).list();

        //实例对象，将数据中的数据转换为TfjJsonBean
        TfjJsonBean tfjJsonBean = new TfjJsonBean();
        tfjJsonBean.setAndroidName("检测一组-通风机");
        tfjJsonBean.setDeviceName("矿用通风机无线多参数测试仪");
        //硬件唯一识别号
        String uniqueId = DeviceUtils.getUniqueId(getApplicationContext());
        tfjJsonBean.setAndroidNum(uniqueId);
        tfjJsonBean.setMsg("datas");
        tfjJsonBean.setTime(System.currentTimeMillis());

        tfjJsonBean.setTaskInf(new TfjJsonBean.TaskInfBean(taskEntity.getUnitName(), taskEntity.getNumber(), taskEntity.getPeopleName(), taskEntity.getGreateTaskTime()));

        TfjJsonBean.TaskDetailInfBean taskDetailInfBean = new TfjJsonBean.TaskDetailInfBean();
        taskDetailInfBean.set_IsCompleteTask(taskEntity.get_IsCompleteTask());
        taskDetailInfBean.setTaskHaveGetData(taskEntity.getTaskHaveGetData());
        taskDetailInfBean.setCsff(taskEntity.getCsff());
        taskDetailInfBean.setTffs(taskEntity.getTffs());
        taskDetailInfBean.setCfmj(taskEntity.getCfmj());
        taskDetailInfBean.setKsqckml(taskEntity.getKsqckmj());
        taskDetailInfBean.setCymjx(taskEntity.getCymjx());
        taskDetailInfBean.setCymjd(taskEntity.getCymjd());
        taskDetailInfBean.setDlbb1(taskEntity.getDlbb1());
        taskDetailInfBean.setDlbb2(taskEntity.getDlbb2());
        taskDetailInfBean.setDybb1(taskEntity.getDybb1());
        taskDetailInfBean.setDybb2(taskEntity.getDybb2());
        //电机1参数
        taskDetailInfBean.setDjk1(taskEntity.getDjk1());
        taskDetailInfBean.setDjksfxz1(taskEntity.getDjksfxz1());
        taskDetailInfBean.setDjqblc1(taskEntity.getDjqblc1());
        taskDetailInfBean.setDjcsff1(taskEntity.getDjcsff1());
        taskDetailInfBean.setDjcdxl1(taskEntity.getDjcdxl1());
        taskDetailInfBean.setDjcdfs1(taskEntity.getDjcdfs1());
        taskDetailInfBean.setDjeddy1(taskEntity.getDjeddy1());
        taskDetailInfBean.setDjeddl1(taskEntity.getDjeddl1());
        taskDetailInfBean.setDjedgl1(taskEntity.getDjedgl1());
        taskDetailInfBean.setDjedxl1(taskEntity.getDjedxl1());
        taskDetailInfBean.setDjkzdl1(taskEntity.getDjkzdl1());
        taskDetailInfBean.setDjkzgl1(taskEntity.getDjkzgl1());
        taskDetailInfBean.setDjjs1(taskEntity.getDjjs1());
        taskDetailInfBean.setDjwgjjdl1(taskEntity.getDjwgjjdl1());
        //电机2参数
        taskDetailInfBean.setDjk2(taskEntity.getDjk2());
        taskDetailInfBean.setDjksfxz2(taskEntity.getDjksfxz2());
        taskDetailInfBean.setDjqblc2(taskEntity.getDjqblc2());
        taskDetailInfBean.setDjcsff2(taskEntity.getDjcsff2());
        taskDetailInfBean.setDjcdxl2(taskEntity.getDjcdxl2());
        taskDetailInfBean.setDjcdfs2(taskEntity.getDjcdfs2());
        taskDetailInfBean.setDjeddy2(taskEntity.getDjeddy2());
        taskDetailInfBean.setDjeddl2(taskEntity.getDjeddl2());
        taskDetailInfBean.setDjedgl2(taskEntity.getDjedgl2());
        taskDetailInfBean.setDjedxl2(taskEntity.getDjedxl2());
        taskDetailInfBean.setDjkzdl2(taskEntity.getDjkzdl2());
        taskDetailInfBean.setDjkzgl2(taskEntity.getDjkzgl2());
        taskDetailInfBean.setDjjs2(taskEntity.getDjjs2());
        taskDetailInfBean.setDjwgjjdl2(taskEntity.getDjwgjjdl2());

        taskDetailInfBean.setFs(taskEntity.getFs());
        taskDetailInfBean.setJy(taskEntity.getJy());
        taskDetailInfBean.setDy(taskEntity.getDy());
        taskDetailInfBean.setWd(taskEntity.getWd());
        taskDetailInfBean.setSd(taskEntity.getSd());
        taskDetailInfBean.setDqy(taskEntity.getDqy());
        taskDetailInfBean.setDj1gl(taskEntity.getDj1gl());
        taskDetailInfBean.setDj2gl(taskEntity.getDj2gl());
        taskDetailInfBean.setDj1xl(taskEntity.getDj1xl());
        taskDetailInfBean.setDj2xl(taskEntity.getDj2xl());
        taskDetailInfBean.setEdzs(taskEntity.getEdzs());
        taskDetailInfBean.setSczs(taskEntity.getSczs());
        taskDetailInfBean.setSdfs(taskEntity.getSdfs());
        taskDetailInfBean.setSdjy(taskEntity.getSdjy());
        taskDetailInfBean.setSdcy(taskEntity.getSdcy());
        taskDetailInfBean.setSdwd(taskEntity.getSdwd());
        taskDetailInfBean.setSdsd(taskEntity.getSdsd());
        taskDetailInfBean.setSddqy(taskEntity.getSddqy());
        taskDetailInfBean.setSddj1gl(taskEntity.getSddj1gl());
        taskDetailInfBean.setSddj2gl(taskEntity.getSddj2gl());
        taskDetailInfBean.setSddj1xl(taskEntity.getSddj1xl());
        taskDetailInfBean.setSddj2xl(taskEntity.getSddj2xl());
        taskDetailInfBean.setSdedzs(taskEntity.getSdedzs());
        taskDetailInfBean.setSdsczs(taskEntity.getSdsczs());
        //备用
        taskDetailInfBean.setBy1(taskEntity.getBy1());
        taskDetailInfBean.setBy2(taskEntity.getBy2());
        taskDetailInfBean.setBy3(taskEntity.getBy3());
        taskDetailInfBean.setBy4(taskEntity.getBy4());
        taskDetailInfBean.setBy5(taskEntity.getBy5());
        taskDetailInfBean.setBy6(taskEntity.getBy6());
        taskDetailInfBean.setBy7(taskEntity.getBy7());
        taskDetailInfBean.setBy8(taskEntity.getBy8());
        taskDetailInfBean.setBy9(taskEntity.getBy9());
        taskDetailInfBean.setBy10(taskEntity.getBy10());
        taskDetailInfBean.setBy11(taskEntity.getBy11());
        taskDetailInfBean.setBy12(taskEntity.getBy12());
        taskDetailInfBean.setPtgxs(taskEntity.getPtgxs());

        List<TfjJsonBean.DataArrayBean> dataArrayBeans = new ArrayList<>();
        for (int i = 0; i < taskResEnities.size(); i++) {
            TaskResEnity taskResEnity = taskResEnities.get(i);
            TfjJsonBean.DataArrayBean dataArrayBean = new TfjJsonBean.DataArrayBean();
            dataArrayBean.setCsff(taskResEnity.getCsff());
            dataArrayBean.setTffs(taskResEnity.getTffs());
            dataArrayBean.setSaveIndex(taskResEnity.getSaveIndex());
            dataArrayBean.setSaveTime(taskResEnity.getSaveTime());
            dataArrayBean.setmBhzqy(taskResEnity.getMBhzqy());
            dataArrayBean.setmWd(taskResEnity.getMWd());
            dataArrayBean.setmKqmd(taskResEnity.getMKqmd());
            dataArrayBean.setmDqy(taskResEnity.getMDqy());
            dataArrayBean.setmSd(taskResEnity.getMSd());
            dataArrayBean.setmCfddy(taskResEnity.getMCfddy());
            dataArrayBean.setmPjfs((float) taskResEnity.getMPjfs());
            dataArrayBean.setmFl((float) taskResEnity.getMFl());
            dataArrayBean.setmCfmj((float) taskResEnity.getMCfmj());
            dataArrayBean.setmDy(taskResEnity.getMDy());
            dataArrayBean.setmPtgxs((float) taskResEnity.getMPtgxs());
            dataArrayBean.setmCymjd(taskResEnity.getMCymjd());
            dataArrayBean.setmCymjx(taskResEnity.getMCymjx());
            dataArrayBean.setmJyc(taskResEnity.getMJyc());
            dataArrayBean.setmZgl1(taskResEnity.getMZgl1());
            dataArrayBean.setmDjgl(taskResEnity.getMDjgl());
            dataArrayBean.setmDjxl(taskResEnity.getMDjxl());
            dataArrayBean.setmCdxl(taskResEnity.getMCdxl());

            dataArrayBean.setmZgl2(taskResEnity.getMZgl2());
            dataArrayBean.setmDjgl2(taskResEnity.getMDjgl2());
            dataArrayBean.setmDjxl2(taskResEnity.getMDjxl2());
            dataArrayBean.setmCdxl2(taskResEnity.getMCdxl2());

            dataArrayBean.setmJygl(taskResEnity.getMJygl());
            dataArrayBean.setmFjjy((float) taskResEnity.getMFjjy());
            dataArrayBean.setmQygl(taskResEnity.getMQygl());
            dataArrayBean.setmFjqy((float) taskResEnity.getMFjqy());
            dataArrayBean.setmJyxl(taskResEnity.getMJyxl());
            dataArrayBean.setmQyxl(taskResEnity.getMQyxl());
            dataArrayBean.setmKsckmj(taskResEnity.getMKsckmj());
            dataArrayBean.setmJy(taskResEnity.getMJy());
            dataArrayBean.setmKsckdy(taskResEnity.getMKsckdy());
            dataArrayBean.setmFjyxxl(taskResEnity.getMFjyxxl());
            dataArrayBean.setmGxnh(taskResEnity.getMGxnh());
            dataArrayBean.setMcKqmdzhxs(taskResEnity.getMcKqmdzhxs());
            dataArrayBean.setmSczs(taskResEnity.getMSczs());
            dataArrayBean.setMcZszhxs(taskResEnity.getMcZszhxs());
            dataArrayBean.setmEdzs(taskResEnity.getMEdzs());
            dataArrayBean.setMgFl((float) taskResEnity.getMgFl());
            dataArrayBean.setMgZgl1(taskResEnity.getMgZgl1());
            dataArrayBean.setMgZgl2(taskResEnity.getMgZgl2());
            dataArrayBean.setMgFjjy(taskResEnity.getMgFjjy());
            dataArrayBean.setMgJygl(taskResEnity.getMgJygl());
            dataArrayBean.setMgFjqy(taskResEnity.getMgFjqy());
            dataArrayBean.setMgQygl(taskResEnity.getMgQygl());
            dataArrayBean.setMgJyxl(taskResEnity.getMgJyxl());
            dataArrayBean.setMgQyxl(taskResEnity.getMgQyxl());
            //电机1参数
            dataArrayBean.setUa(taskResEnity.getUa());
            dataArrayBean.setUb(taskResEnity.getUb());
            dataArrayBean.setUc(taskResEnity.getUc());
            dataArrayBean.setUab(taskResEnity.getUab());
            dataArrayBean.setIa(taskResEnity.getIa());
            dataArrayBean.setUbc(taskResEnity.getUbc());
            dataArrayBean.setIb(taskResEnity.getIb());
            dataArrayBean.setUca(taskResEnity.getUca());
            dataArrayBean.setIc(taskResEnity.getIc());
            dataArrayBean.setPjU(taskResEnity.getPjU());
            dataArrayBean.setPjI(taskResEnity.getPjI());
            dataArrayBean.setDjgl(taskResEnity.getDjgl());
            dataArrayBean.setZgl(taskResEnity.getZgl());
            dataArrayBean.setScgl(taskResEnity.getScgl());
            dataArrayBean.setDjxl(taskResEnity.getDjxl());
            dataArrayBean.setZhxl(taskResEnity.getZhxl());
            dataArrayBean.setGlys(taskResEnity.getGlys());
            dataArrayBean.setFzxs(taskResEnity.getFzxs());
            dataArrayBean.setYxzt(taskResEnity.getYxzt());
            dataArrayBean.setStryxzt(taskResEnity.getStryxzt());
            dataArrayBean.setDj1qblc(taskResEnity.getDj1qblc());
            dataArrayBean.setDj1csff(taskResEnity.getDj1csff());
            dataArrayBean.setDj1cdxl(taskResEnity.getDj1cdxl());
            //电机2参数
            dataArrayBean.setUa2(taskResEnity.getUa2());
            dataArrayBean.setUb2(taskResEnity.getUb2());
            dataArrayBean.setUc2(taskResEnity.getUc2());
            dataArrayBean.setUab2(taskResEnity.getUab2());
            dataArrayBean.setIa2(taskResEnity.getIa2());
            dataArrayBean.setUbc2(taskResEnity.getUbc2());
            dataArrayBean.setIb2(taskResEnity.getIb2());
            dataArrayBean.setUca2(taskResEnity.getUca2());
            dataArrayBean.setIc2(taskResEnity.getIc2());
            dataArrayBean.setPjU2(taskResEnity.getPjU2());
            dataArrayBean.setPjI2(taskResEnity.getPjI2());
            dataArrayBean.setDjgl2(taskResEnity.getDjgl2());
            dataArrayBean.setZgl2(taskResEnity.getZgl2());
            dataArrayBean.setScgl2(taskResEnity.getScgl2());
            dataArrayBean.setDjxl2(taskResEnity.getDjxl2());
            dataArrayBean.setZhxl2(taskResEnity.getZhxl2());
            dataArrayBean.setGlys2(taskResEnity.getGlys2());
            dataArrayBean.setFzxs2(taskResEnity.getFzxs2());
            dataArrayBean.setYxzt2(taskResEnity.getYxzt2());
            dataArrayBean.setStryxzt2(taskResEnity.getStryxzt2());
            dataArrayBean.setDj2qblc(taskResEnity.getDj2qblc());
            dataArrayBean.setDj2csff(taskResEnity.getDj2csff());
            dataArrayBean.setDj2cdxl(taskResEnity.getDj2cdxl());


            dataArrayBeans.add(dataArrayBean);
        }


        tfjJsonBean.setTaskDetailInf(taskDetailInfBean);
        tfjJsonBean.setDataArray(dataArrayBeans);


        return tfjJsonBean;
    }
}
