package com.ventilator.bean;

import com.google.gson.GsonBuilder;
import com.xuhao.didi.core.iocore.interfaces.ISendable;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.List;

public class TfjJsonBean implements ISendable {

    /**
     * androidName : mock
     * deviceName : mock
     * androidNum : mock
     * msg : mock
     * time : mock
     * taskInf : {"taskName":"mock","taskNum":"mock","people":"mock","createTime":"mock"}
     * taskDetailInf : {"_IsCompleteTask":true,"taskHaveGetData":1,"csff":"mock","tffs":"mock","cfmj":"mock","ksqckml":"mock","cymjx":"mock","cymjd":"mock","dlbb1":"mock","dlbb2":"mock","dybb1":"mock","dybb2":"mock","djk1":"mock","djksfxz1":true,"djqblc1":"mock","djcsff1":"mock","djcdxl1":"mock","djcdfs1":"mock","djeddy1":"mock","djeddl1":"mock","djedgl1":"mock","djedxl1":"mock","djkzdl1":"mock","djkzgl1":"mock","djjs1":"mock","djwgjjdl1":"mock","djk2":"mock","djksfxz2":true,"djqblc2":"mock","djcsff2":"mock","djcdxl2":"mock","djcdfs2":"mock","djeddy2":"mock","djeddl2":"mock","djedgl2":"mock","djedxl2":"mock","djkzdl2":"mock","djkzgl2":"mock","djjs2":"mock","djwgjjdl2":"mock","fs":"mock","jy":"mock","dy":"mock","cy":"mock","wd":"mock","sd":"mock","dqy":"mock","dj1gl":"mock","dj2gl":"mock","dj1xl":"mock","dj2xl":"mock","edzs":"mock","sczs":"mock","sdfs":true,"sdjy":true,"sdcy":true,"sdwd":true,"sdsd":true,"sddqy":true,"sddj1gl":true,"sddj2gl":true,"sddj1xl":true,"sddj2xl":true,"sdedzs":true,"sdsczs":true,"by1":"mock","by2":"mock","by3":"mock","by4":"mock","by5":"mock","by6":"mock","by7":"mock","by8":"mock","by9":"mock","by10":"mock","by11":"mock","by12":"mock","ptgxs":"mock"}
     * dataArray : [{"csff":"mock","tffs":"mock","saveIndex":1,"SaveTime":"mock","mBhzqy":1,"mWd":1,"mKqmd":1,"mDqy":1,"mSd":1,"mCfddy":1,"mPjfs":1,"mFl":1,"mCfmj":1,"mDy":1,"mPtgxs":1,"mCymjd":1,"mCymjx":1,"mJyc":1,"mZgl1":1,"mDjgl":1,"mDjxl":1,"mCdxl":1,"mZgl2":1,"mDjgl2":1,"mDjxl2":1,"mCdxl2":1,"mJygl":1,"mFjjy":1,"mQygl":1,"mFjqy":1,"mJyxl":1,"mQyxl":1,"mKsckmj":1,"mJy":1,"mKsckdy":1,"mFjyxxl":1,"mGxnh":1,"mcKqmdzhxs":1,"mSczs":1,"mcZszhxs":1,"mEdzs":1,"mgFl":1,"mgZgl1":1,"mgZgl2":1,"mgFjjy":1,"mgJygl":1,"mgFjqy":1,"mgQygl":1,"mgJyxl":1,"mgQyxl":1,"Ua":1,"Ub":1,"Uc":1,"Uab":1,"Ia":1,"Ubc":1,"Ib":1,"Uca":1,"Ic":1,"pjU":1,"pjI":1,"djgl":1,"zgl":1,"scgl":1,"djxl":1,"zhxl":1,"glys":1,"fzxs":1,"yxzt":1,"stryxzt":"mock","dj1qblc":"mock","dj1csff":"mock","dj1cdxl":1,"Ua2":1,"Ub2":1,"Uc2":1,"Uab2":1,"Ia2":1,"Ubc2":1,"Ib2":1,"Uca2":1,"Ic2":1,"pjU2":1,"pjI2":1,"djgl2":1,"zgl2":1,"scgl2":1,"djxl2":1,"zhxl2":1,"glys2":1,"fzxs2":1,"yxzt2":1,"stryxzt2":"mock","dj2qblc":"mock","dj2csff":"mock","dj2cdxl":1}]
     */

    private String androidName;
    private String deviceName;
    private String androidNum;
    private String msg;
    private long time;
    private TaskInfBean taskInf;
    private TaskDetailInfBean taskDetailInf;
    private List<DataArrayBean> dataArray;

    public String getAndroidName() {
        return androidName;
    }

    public void setAndroidName(String androidName) {
        this.androidName = androidName;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public String getAndroidNum() {
        return androidNum;
    }

    public void setAndroidNum(String androidNum) {
        this.androidNum = androidNum;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public TaskInfBean getTaskInf() {
        return taskInf;
    }

    public void setTaskInf(TaskInfBean taskInf) {
        this.taskInf = taskInf;
    }

    public TaskDetailInfBean getTaskDetailInf() {
        return taskDetailInf;
    }

    public void setTaskDetailInf(TaskDetailInfBean taskDetailInf) {
        this.taskDetailInf = taskDetailInf;
    }

    public List<DataArrayBean> getDataArray() {
        return dataArray;
    }

    public void setDataArray(List<DataArrayBean> dataArray) {
        this.dataArray = dataArray;
    }

    @Override
    public byte[] parse() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        String json = gsonBuilder.serializeNulls().create().toJson(TfjJsonBean.this);
        byte[] body = json.getBytes();
        ByteBuffer bb = ByteBuffer.allocate(4 + body.length);
        bb.order(ByteOrder.BIG_ENDIAN);
        bb.putInt(body.length);
        bb.put(body);
        return bb.array();
    }

    public static class TaskInfBean {
        public TaskInfBean(String taskName, String taskNum, String people, String createTime) {
            this.taskName = taskName;
            this.taskNum = taskNum;
            this.people = people;
            this.createTime = createTime;
        }

        /**
         * taskName : mock
         * taskNum : mock
         * people : mock
         * createTime : mock
         */



        private String taskName;
        private String taskNum;
        private String people;
        private String createTime;

        public String getTaskName() {
            return taskName;
        }

        public void setTaskName(String taskName) {
            this.taskName = taskName;
        }

        public String getTaskNum() {
            return taskNum;
        }

        public void setTaskNum(String taskNum) {
            this.taskNum = taskNum;
        }

        public String getPeople() {
            return people;
        }

        public void setPeople(String people) {
            this.people = people;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }
    }

    public static class TaskDetailInfBean {
        /**
         * _IsCompleteTask : true
         * taskHaveGetData : 1
         * csff : mock
         * tffs : mock
         * cfmj : mock
         * ksqckml : mock
         * cymjx : mock
         * cymjd : mock
         * dlbb1 : mock
         * dlbb2 : mock
         * dybb1 : mock
         * dybb2 : mock
         * djk1 : mock
         * djksfxz1 : true
         * djqblc1 : mock
         * djcsff1 : mock
         * djcdxl1 : mock
         * djcdfs1 : mock
         * djeddy1 : mock
         * djeddl1 : mock
         * djedgl1 : mock
         * djedxl1 : mock
         * djkzdl1 : mock
         * djkzgl1 : mock
         * djjs1 : mock
         * djwgjjdl1 : mock
         * djk2 : mock
         * djksfxz2 : true
         * djqblc2 : mock
         * djcsff2 : mock
         * djcdxl2 : mock
         * djcdfs2 : mock
         * djeddy2 : mock
         * djeddl2 : mock
         * djedgl2 : mock
         * djedxl2 : mock
         * djkzdl2 : mock
         * djkzgl2 : mock
         * djjs2 : mock
         * djwgjjdl2 : mock
         * fs : mock
         * jy : mock
         * dy : mock
         * cy : mock
         * wd : mock
         * sd : mock
         * dqy : mock
         * dj1gl : mock
         * dj2gl : mock
         * dj1xl : mock
         * dj2xl : mock
         * edzs : mock
         * sczs : mock
         * sdfs : true
         * sdjy : true
         * sdcy : true
         * sdwd : true
         * sdsd : true
         * sddqy : true
         * sddj1gl : true
         * sddj2gl : true
         * sddj1xl : true
         * sddj2xl : true
         * sdedzs : true
         * sdsczs : true
         * by1 : mock
         * by2 : mock
         * by3 : mock
         * by4 : mock
         * by5 : mock
         * by6 : mock
         * by7 : mock
         * by8 : mock
         * by9 : mock
         * by10 : mock
         * by11 : mock
         * by12 : mock
         * ptgxs : mock
         */

        private boolean _IsCompleteTask;
        private int taskHaveGetData;
        private String csff;
        private String tffs;
        private String cfmj;
        private String ksqckml;
        private String cymjx;
        private String cymjd;
        private String dlbb1;
        private String dlbb2;
        private String dybb1;
        private String dybb2;
        private String djk1;
        private boolean djksfxz1;
        private String djqblc1;
        private String djcsff1;
        private String djcdxl1;
        private String djcdfs1;
        private String djeddy1;
        private String djeddl1;
        private String djedgl1;
        private String djedxl1;
        private String djkzdl1;
        private String djkzgl1;
        private String djjs1;
        private String djwgjjdl1;
        private String djk2;
        private boolean djksfxz2;
        private String djqblc2;
        private String djcsff2;
        private String djcdxl2;
        private String djcdfs2;
        private String djeddy2;
        private String djeddl2;
        private String djedgl2;
        private String djedxl2;
        private String djkzdl2;
        private String djkzgl2;
        private String djjs2;
        private String djwgjjdl2;
        private String fs;
        private String jy;
        private String dy;
        private String cy;
        private String wd;
        private String sd;
        private String dqy;
        private String dj1gl;
        private String dj2gl;
        private String dj1xl;
        private String dj2xl;
        private String edzs;
        private String sczs;
        private boolean sdfs;
        private boolean sdjy;
        private boolean sdcy;
        private boolean sdwd;
        private boolean sdsd;
        private boolean sddqy;
        private boolean sddj1gl;
        private boolean sddj2gl;
        private boolean sddj1xl;
        private boolean sddj2xl;
        private boolean sdedzs;
        private boolean sdsczs;
        private String by1;
        private String by2;
        private String by3;
        private String by4;
        private String by5;
        private String by6;
        private String by7;
        private String by8;
        private String by9;
        private String by10;
        private String by11;
        private String by12;
        private String ptgxs;

        public boolean is_IsCompleteTask() {
            return _IsCompleteTask;
        }

        public void set_IsCompleteTask(boolean _IsCompleteTask) {
            this._IsCompleteTask = _IsCompleteTask;
        }

        public int getTaskHaveGetData() {
            return taskHaveGetData;
        }

        public void setTaskHaveGetData(int taskHaveGetData) {
            this.taskHaveGetData = taskHaveGetData;
        }

        public String getCsff() {
            return csff;
        }

        public void setCsff(String csff) {
            this.csff = csff;
        }

        public String getTffs() {
            return tffs;
        }

        public void setTffs(String tffs) {
            this.tffs = tffs;
        }

        public String getCfmj() {
            return cfmj;
        }

        public void setCfmj(String cfmj) {
            this.cfmj = cfmj;
        }

        public String getKsqckml() {
            return ksqckml;
        }

        public void setKsqckml(String ksqckml) {
            this.ksqckml = ksqckml;
        }

        public String getCymjx() {
            return cymjx;
        }

        public void setCymjx(String cymjx) {
            this.cymjx = cymjx;
        }

        public String getCymjd() {
            return cymjd;
        }

        public void setCymjd(String cymjd) {
            this.cymjd = cymjd;
        }

        public String getDlbb1() {
            return dlbb1;
        }

        public void setDlbb1(String dlbb1) {
            this.dlbb1 = dlbb1;
        }

        public String getDlbb2() {
            return dlbb2;
        }

        public void setDlbb2(String dlbb2) {
            this.dlbb2 = dlbb2;
        }

        public String getDybb1() {
            return dybb1;
        }

        public void setDybb1(String dybb1) {
            this.dybb1 = dybb1;
        }

        public String getDybb2() {
            return dybb2;
        }

        public void setDybb2(String dybb2) {
            this.dybb2 = dybb2;
        }

        public String getDjk1() {
            return djk1;
        }

        public void setDjk1(String djk1) {
            this.djk1 = djk1;
        }

        public boolean isDjksfxz1() {
            return djksfxz1;
        }

        public void setDjksfxz1(boolean djksfxz1) {
            this.djksfxz1 = djksfxz1;
        }

        public String getDjqblc1() {
            return djqblc1;
        }

        public void setDjqblc1(String djqblc1) {
            this.djqblc1 = djqblc1;
        }

        public String getDjcsff1() {
            return djcsff1;
        }

        public void setDjcsff1(String djcsff1) {
            this.djcsff1 = djcsff1;
        }

        public String getDjcdxl1() {
            return djcdxl1;
        }

        public void setDjcdxl1(String djcdxl1) {
            this.djcdxl1 = djcdxl1;
        }

        public String getDjcdfs1() {
            return djcdfs1;
        }

        public void setDjcdfs1(String djcdfs1) {
            this.djcdfs1 = djcdfs1;
        }

        public String getDjeddy1() {
            return djeddy1;
        }

        public void setDjeddy1(String djeddy1) {
            this.djeddy1 = djeddy1;
        }

        public String getDjeddl1() {
            return djeddl1;
        }

        public void setDjeddl1(String djeddl1) {
            this.djeddl1 = djeddl1;
        }

        public String getDjedgl1() {
            return djedgl1;
        }

        public void setDjedgl1(String djedgl1) {
            this.djedgl1 = djedgl1;
        }

        public String getDjedxl1() {
            return djedxl1;
        }

        public void setDjedxl1(String djedxl1) {
            this.djedxl1 = djedxl1;
        }

        public String getDjkzdl1() {
            return djkzdl1;
        }

        public void setDjkzdl1(String djkzdl1) {
            this.djkzdl1 = djkzdl1;
        }

        public String getDjkzgl1() {
            return djkzgl1;
        }

        public void setDjkzgl1(String djkzgl1) {
            this.djkzgl1 = djkzgl1;
        }

        public String getDjjs1() {
            return djjs1;
        }

        public void setDjjs1(String djjs1) {
            this.djjs1 = djjs1;
        }

        public String getDjwgjjdl1() {
            return djwgjjdl1;
        }

        public void setDjwgjjdl1(String djwgjjdl1) {
            this.djwgjjdl1 = djwgjjdl1;
        }

        public String getDjk2() {
            return djk2;
        }

        public void setDjk2(String djk2) {
            this.djk2 = djk2;
        }

        public boolean isDjksfxz2() {
            return djksfxz2;
        }

        public void setDjksfxz2(boolean djksfxz2) {
            this.djksfxz2 = djksfxz2;
        }

        public String getDjqblc2() {
            return djqblc2;
        }

        public void setDjqblc2(String djqblc2) {
            this.djqblc2 = djqblc2;
        }

        public String getDjcsff2() {
            return djcsff2;
        }

        public void setDjcsff2(String djcsff2) {
            this.djcsff2 = djcsff2;
        }

        public String getDjcdxl2() {
            return djcdxl2;
        }

        public void setDjcdxl2(String djcdxl2) {
            this.djcdxl2 = djcdxl2;
        }

        public String getDjcdfs2() {
            return djcdfs2;
        }

        public void setDjcdfs2(String djcdfs2) {
            this.djcdfs2 = djcdfs2;
        }

        public String getDjeddy2() {
            return djeddy2;
        }

        public void setDjeddy2(String djeddy2) {
            this.djeddy2 = djeddy2;
        }

        public String getDjeddl2() {
            return djeddl2;
        }

        public void setDjeddl2(String djeddl2) {
            this.djeddl2 = djeddl2;
        }

        public String getDjedgl2() {
            return djedgl2;
        }

        public void setDjedgl2(String djedgl2) {
            this.djedgl2 = djedgl2;
        }

        public String getDjedxl2() {
            return djedxl2;
        }

        public void setDjedxl2(String djedxl2) {
            this.djedxl2 = djedxl2;
        }

        public String getDjkzdl2() {
            return djkzdl2;
        }

        public void setDjkzdl2(String djkzdl2) {
            this.djkzdl2 = djkzdl2;
        }

        public String getDjkzgl2() {
            return djkzgl2;
        }

        public void setDjkzgl2(String djkzgl2) {
            this.djkzgl2 = djkzgl2;
        }

        public String getDjjs2() {
            return djjs2;
        }

        public void setDjjs2(String djjs2) {
            this.djjs2 = djjs2;
        }

        public String getDjwgjjdl2() {
            return djwgjjdl2;
        }

        public void setDjwgjjdl2(String djwgjjdl2) {
            this.djwgjjdl2 = djwgjjdl2;
        }

        public String getFs() {
            return fs;
        }

        public void setFs(String fs) {
            this.fs = fs;
        }

        public String getJy() {
            return jy;
        }

        public void setJy(String jy) {
            this.jy = jy;
        }

        public String getDy() {
            return dy;
        }

        public void setDy(String dy) {
            this.dy = dy;
        }

        public String getCy() {
            return cy;
        }

        public void setCy(String cy) {
            this.cy = cy;
        }

        public String getWd() {
            return wd;
        }

        public void setWd(String wd) {
            this.wd = wd;
        }

        public String getSd() {
            return sd;
        }

        public void setSd(String sd) {
            this.sd = sd;
        }

        public String getDqy() {
            return dqy;
        }

        public void setDqy(String dqy) {
            this.dqy = dqy;
        }

        public String getDj1gl() {
            return dj1gl;
        }

        public void setDj1gl(String dj1gl) {
            this.dj1gl = dj1gl;
        }

        public String getDj2gl() {
            return dj2gl;
        }

        public void setDj2gl(String dj2gl) {
            this.dj2gl = dj2gl;
        }

        public String getDj1xl() {
            return dj1xl;
        }

        public void setDj1xl(String dj1xl) {
            this.dj1xl = dj1xl;
        }

        public String getDj2xl() {
            return dj2xl;
        }

        public void setDj2xl(String dj2xl) {
            this.dj2xl = dj2xl;
        }

        public String getEdzs() {
            return edzs;
        }

        public void setEdzs(String edzs) {
            this.edzs = edzs;
        }

        public String getSczs() {
            return sczs;
        }

        public void setSczs(String sczs) {
            this.sczs = sczs;
        }

        public boolean isSdfs() {
            return sdfs;
        }

        public void setSdfs(boolean sdfs) {
            this.sdfs = sdfs;
        }

        public boolean isSdjy() {
            return sdjy;
        }

        public void setSdjy(boolean sdjy) {
            this.sdjy = sdjy;
        }

        public boolean isSdcy() {
            return sdcy;
        }

        public void setSdcy(boolean sdcy) {
            this.sdcy = sdcy;
        }

        public boolean isSdwd() {
            return sdwd;
        }

        public void setSdwd(boolean sdwd) {
            this.sdwd = sdwd;
        }

        public boolean isSdsd() {
            return sdsd;
        }

        public void setSdsd(boolean sdsd) {
            this.sdsd = sdsd;
        }

        public boolean isSddqy() {
            return sddqy;
        }

        public void setSddqy(boolean sddqy) {
            this.sddqy = sddqy;
        }

        public boolean isSddj1gl() {
            return sddj1gl;
        }

        public void setSddj1gl(boolean sddj1gl) {
            this.sddj1gl = sddj1gl;
        }

        public boolean isSddj2gl() {
            return sddj2gl;
        }

        public void setSddj2gl(boolean sddj2gl) {
            this.sddj2gl = sddj2gl;
        }

        public boolean isSddj1xl() {
            return sddj1xl;
        }

        public void setSddj1xl(boolean sddj1xl) {
            this.sddj1xl = sddj1xl;
        }

        public boolean isSddj2xl() {
            return sddj2xl;
        }

        public void setSddj2xl(boolean sddj2xl) {
            this.sddj2xl = sddj2xl;
        }

        public boolean isSdedzs() {
            return sdedzs;
        }

        public void setSdedzs(boolean sdedzs) {
            this.sdedzs = sdedzs;
        }

        public boolean isSdsczs() {
            return sdsczs;
        }

        public void setSdsczs(boolean sdsczs) {
            this.sdsczs = sdsczs;
        }

        public String getBy1() {
            return by1;
        }

        public void setBy1(String by1) {
            this.by1 = by1;
        }

        public String getBy2() {
            return by2;
        }

        public void setBy2(String by2) {
            this.by2 = by2;
        }

        public String getBy3() {
            return by3;
        }

        public void setBy3(String by3) {
            this.by3 = by3;
        }

        public String getBy4() {
            return by4;
        }

        public void setBy4(String by4) {
            this.by4 = by4;
        }

        public String getBy5() {
            return by5;
        }

        public void setBy5(String by5) {
            this.by5 = by5;
        }

        public String getBy6() {
            return by6;
        }

        public void setBy6(String by6) {
            this.by6 = by6;
        }

        public String getBy7() {
            return by7;
        }

        public void setBy7(String by7) {
            this.by7 = by7;
        }

        public String getBy8() {
            return by8;
        }

        public void setBy8(String by8) {
            this.by8 = by8;
        }

        public String getBy9() {
            return by9;
        }

        public void setBy9(String by9) {
            this.by9 = by9;
        }

        public String getBy10() {
            return by10;
        }

        public void setBy10(String by10) {
            this.by10 = by10;
        }

        public String getBy11() {
            return by11;
        }

        public void setBy11(String by11) {
            this.by11 = by11;
        }

        public String getBy12() {
            return by12;
        }

        public void setBy12(String by12) {
            this.by12 = by12;
        }

        public String getPtgxs() {
            return ptgxs;
        }

        public void setPtgxs(String ptgxs) {
            this.ptgxs = ptgxs;
        }
    }

    public static class DataArrayBean {
        /**
         * csff : mock
         * tffs : mock
         * saveIndex : 1
         * SaveTime : mock
         * mBhzqy : 1
         * mWd : 1
         * mKqmd : 1
         * mDqy : 1
         * mSd : 1
         * mCfddy : 1
         * mPjfs : 1
         * mFl : 1
         * mCfmj : 1
         * mDy : 1
         * mPtgxs : 1
         * mCymjd : 1
         * mCymjx : 1
         * mJyc : 1
         * mZgl1 : 1
         * mDjgl : 1
         * mDjxl : 1
         * mCdxl : 1
         * mZgl2 : 1
         * mDjgl2 : 1
         * mDjxl2 : 1
         * mCdxl2 : 1
         * mJygl : 1
         * mFjjy : 1
         * mQygl : 1
         * mFjqy : 1
         * mJyxl : 1
         * mQyxl : 1
         * mKsckmj : 1
         * mJy : 1
         * mKsckdy : 1
         * mFjyxxl : 1
         * mGxnh : 1
         * mcKqmdzhxs : 1
         * mSczs : 1
         * mcZszhxs : 1
         * mEdzs : 1
         * mgFl : 1
         * mgZgl1 : 1
         * mgZgl2 : 1
         * mgFjjy : 1
         * mgJygl : 1
         * mgFjqy : 1
         * mgQygl : 1
         * mgJyxl : 1
         * mgQyxl : 1
         * Ua : 1
         * Ub : 1
         * Uc : 1
         * Uab : 1
         * Ia : 1
         * Ubc : 1
         * Ib : 1
         * Uca : 1
         * Ic : 1
         * pjU : 1
         * pjI : 1
         * djgl : 1
         * zgl : 1
         * scgl : 1
         * djxl : 1
         * zhxl : 1
         * glys : 1
         * fzxs : 1
         * yxzt : 1
         * stryxzt : mock
         * dj1qblc : mock
         * dj1csff : mock
         * dj1cdxl : 1
         * Ua2 : 1
         * Ub2 : 1
         * Uc2 : 1
         * Uab2 : 1
         * Ia2 : 1
         * Ubc2 : 1
         * Ib2 : 1
         * Uca2 : 1
         * Ic2 : 1
         * pjU2 : 1
         * pjI2 : 1
         * djgl2 : 1
         * zgl2 : 1
         * scgl2 : 1
         * djxl2 : 1
         * zhxl2 : 1
         * glys2 : 1
         * fzxs2 : 1
         * yxzt2 : 1
         * stryxzt2 : mock
         * dj2qblc : mock
         * dj2csff : mock
         * dj2cdxl : 1
         */

        private String csff;
        private String tffs;
        private int saveIndex;
        private String SaveTime;
        private float mBhzqy;
        private float mWd;
        private float mKqmd;
        private float mDqy;
        private double mSd;
        private float mCfddy;
        private float mPjfs;
        private float mFl;
        private float mCfmj;
        private float mDy;
        private float mPtgxs;
        private float mCymjd;
        private float mCymjx;
        private float mJyc;
        private float mZgl1;
        private float mDjgl;
        private float mDjxl;
        private float mCdxl;
        private float mZgl2;
        private float mDjgl2;
        private float mDjxl2;
        private float mCdxl2;
        private float mJygl;
        private float mFjjy;
        private float mQygl;
        private float mFjqy;
        private float mJyxl;
        private float mQyxl;
        private float mKsckmj;
        private float mJy;
        private float mKsckdy;
        private float mFjyxxl;
        private float mGxnh;
        private float mcKqmdzhxs;
        private float mSczs;
        private float mcZszhxs;
        private float mEdzs;
        private float mgFl;
        private float mgZgl1;
        private float mgZgl2;
        private float mgFjjy;
        private float mgJygl;
        private float mgFjqy;
        private float mgQygl;
        private float mgJyxl;
        private float mgQyxl;
        private float Ua;
        private float Ub;
        private float Uc;
        private float Uab;
        private float Ia;
        private float Ubc;
        private float Ib;
        private float Uca;
        private float Ic;
        private float pjU;
        private float pjI;
        private float djgl;
        private float zgl;
        private float scgl;
        private float djxl;
        private float zhxl;
        private float glys;
        private float fzxs;
        private float yxzt;
        private String stryxzt;
        private String dj1qblc;
        private String dj1csff;
        private float dj1cdxl;
        private float Ua2;
        private float Ub2;
        private float Uc2;
        private float Uab2;
        private float Ia2;
        private float Ubc2;
        private float Ib2;
        private float Uca2;
        private float Ic2;
        private float pjU2;
        private float pjI2;
        private float djgl2;
        private float zgl2;
        private float scgl2;
        private float djxl2;
        private float zhxl2;
        private float glys2;
        private float fzxs2;
        private float yxzt2;
        private String stryxzt2;
        private String dj2qblc;
        private String dj2csff;
        private float dj2cdxl;

        public String getCsff() {
            return csff;
        }

        public void setCsff(String csff) {
            this.csff = csff;
        }

        public String getTffs() {
            return tffs;
        }

        public void setTffs(String tffs) {
            this.tffs = tffs;
        }

        public int getSaveIndex() {
            return saveIndex;
        }

        public void setSaveIndex(int saveIndex) {
            this.saveIndex = saveIndex;
        }

        public String getSaveTime() {
            return SaveTime;
        }

        public void setSaveTime(String saveTime) {
            SaveTime = saveTime;
        }

        public float getmBhzqy() {
            return mBhzqy;
        }

        public void setmBhzqy(float mBhzqy) {
            this.mBhzqy = mBhzqy;
        }

        public float getmWd() {
            return mWd;
        }

        public void setmWd(float mWd) {
            this.mWd = mWd;
        }

        public float getmKqmd() {
            return mKqmd;
        }

        public void setmKqmd(float mKqmd) {
            this.mKqmd = mKqmd;
        }

        public float getmDqy() {
            return mDqy;
        }

        public void setmDqy(float mDqy) {
            this.mDqy = mDqy;
        }

        public double getmSd() {
            return mSd;
        }

        public void setmSd(double mSd) {
            this.mSd = mSd;
        }

        public float getmCfddy() {
            return mCfddy;
        }

        public void setmCfddy(float mCfddy) {
            this.mCfddy = mCfddy;
        }

        public float getmPjfs() {
            return mPjfs;
        }

        public void setmPjfs(float mPjfs) {
            this.mPjfs = mPjfs;
        }

        public float getmFl() {
            return mFl;
        }

        public void setmFl(float mFl) {
            this.mFl = mFl;
        }

        public float getmCfmj() {
            return mCfmj;
        }

        public void setmCfmj(float mCfmj) {
            this.mCfmj = mCfmj;
        }

        public float getmDy() {
            return mDy;
        }

        public void setmDy(float mDy) {
            this.mDy = mDy;
        }

        public float getmPtgxs() {
            return mPtgxs;
        }

        public void setmPtgxs(float mPtgxs) {
            this.mPtgxs = mPtgxs;
        }

        public float getmCymjd() {
            return mCymjd;
        }

        public void setmCymjd(float mCymjd) {
            this.mCymjd = mCymjd;
        }

        public float getmCymjx() {
            return mCymjx;
        }

        public void setmCymjx(float mCymjx) {
            this.mCymjx = mCymjx;
        }

        public float getmJyc() {
            return mJyc;
        }

        public void setmJyc(float mJyc) {
            this.mJyc = mJyc;
        }

        public float getmZgl1() {
            return mZgl1;
        }

        public void setmZgl1(float mZgl1) {
            this.mZgl1 = mZgl1;
        }

        public float getmDjgl() {
            return mDjgl;
        }

        public void setmDjgl(float mDjgl) {
            this.mDjgl = mDjgl;
        }

        public float getmDjxl() {
            return mDjxl;
        }

        public void setmDjxl(float mDjxl) {
            this.mDjxl = mDjxl;
        }

        public float getmCdxl() {
            return mCdxl;
        }

        public void setmCdxl(float mCdxl) {
            this.mCdxl = mCdxl;
        }

        public float getmZgl2() {
            return mZgl2;
        }

        public void setmZgl2(float mZgl2) {
            this.mZgl2 = mZgl2;
        }

        public float getmDjgl2() {
            return mDjgl2;
        }

        public void setmDjgl2(float mDjgl2) {
            this.mDjgl2 = mDjgl2;
        }

        public float getmDjxl2() {
            return mDjxl2;
        }

        public void setmDjxl2(float mDjxl2) {
            this.mDjxl2 = mDjxl2;
        }

        public float getmCdxl2() {
            return mCdxl2;
        }

        public void setmCdxl2(float mCdxl2) {
            this.mCdxl2 = mCdxl2;
        }

        public float getmJygl() {
            return mJygl;
        }

        public void setmJygl(float mJygl) {
            this.mJygl = mJygl;
        }

        public float getmFjjy() {
            return mFjjy;
        }

        public void setmFjjy(float mFjjy) {
            this.mFjjy = mFjjy;
        }

        public float getmQygl() {
            return mQygl;
        }

        public void setmQygl(float mQygl) {
            this.mQygl = mQygl;
        }

        public float getmFjqy() {
            return mFjqy;
        }

        public void setmFjqy(float mFjqy) {
            this.mFjqy = mFjqy;
        }

        public float getmJyxl() {
            return mJyxl;
        }

        public void setmJyxl(float mJyxl) {
            this.mJyxl = mJyxl;
        }

        public float getmQyxl() {
            return mQyxl;
        }

        public void setmQyxl(float mQyxl) {
            this.mQyxl = mQyxl;
        }

        public float getmKsckmj() {
            return mKsckmj;
        }

        public void setmKsckmj(float mKsckmj) {
            this.mKsckmj = mKsckmj;
        }

        public float getmJy() {
            return mJy;
        }

        public void setmJy(float mJy) {
            this.mJy = mJy;
        }

        public float getmKsckdy() {
            return mKsckdy;
        }

        public void setmKsckdy(float mKsckdy) {
            this.mKsckdy = mKsckdy;
        }

        public float getmFjyxxl() {
            return mFjyxxl;
        }

        public void setmFjyxxl(float mFjyxxl) {
            this.mFjyxxl = mFjyxxl;
        }

        public float getmGxnh() {
            return mGxnh;
        }

        public void setmGxnh(float mGxnh) {
            this.mGxnh = mGxnh;
        }

        public float getMcKqmdzhxs() {
            return mcKqmdzhxs;
        }

        public void setMcKqmdzhxs(float mcKqmdzhxs) {
            this.mcKqmdzhxs = mcKqmdzhxs;
        }

        public float getmSczs() {
            return mSczs;
        }

        public void setmSczs(float mSczs) {
            this.mSczs = mSczs;
        }

        public float getMcZszhxs() {
            return mcZszhxs;
        }

        public void setMcZszhxs(float mcZszhxs) {
            this.mcZszhxs = mcZszhxs;
        }

        public float getmEdzs() {
            return mEdzs;
        }

        public void setmEdzs(float mEdzs) {
            this.mEdzs = mEdzs;
        }

        public float getMgFl() {
            return mgFl;
        }

        public void setMgFl(float mgFl) {
            this.mgFl = mgFl;
        }

        public float getMgZgl1() {
            return mgZgl1;
        }

        public void setMgZgl1(float mgZgl1) {
            this.mgZgl1 = mgZgl1;
        }

        public float getMgZgl2() {
            return mgZgl2;
        }

        public void setMgZgl2(float mgZgl2) {
            this.mgZgl2 = mgZgl2;
        }

        public float getMgFjjy() {
            return mgFjjy;
        }

        public void setMgFjjy(float mgFjjy) {
            this.mgFjjy = mgFjjy;
        }

        public float getMgJygl() {
            return mgJygl;
        }

        public void setMgJygl(float mgJygl) {
            this.mgJygl = mgJygl;
        }

        public float getMgFjqy() {
            return mgFjqy;
        }

        public void setMgFjqy(float mgFjqy) {
            this.mgFjqy = mgFjqy;
        }

        public float getMgQygl() {
            return mgQygl;
        }

        public void setMgQygl(float mgQygl) {
            this.mgQygl = mgQygl;
        }

        public float getMgJyxl() {
            return mgJyxl;
        }

        public void setMgJyxl(float mgJyxl) {
            this.mgJyxl = mgJyxl;
        }

        public float getMgQyxl() {
            return mgQyxl;
        }

        public void setMgQyxl(float mgQyxl) {
            this.mgQyxl = mgQyxl;
        }

        public float getUa() {
            return Ua;
        }

        public void setUa(float ua) {
            Ua = ua;
        }

        public float getUb() {
            return Ub;
        }

        public void setUb(float ub) {
            Ub = ub;
        }

        public float getUc() {
            return Uc;
        }

        public void setUc(float uc) {
            Uc = uc;
        }

        public float getUab() {
            return Uab;
        }

        public void setUab(float uab) {
            Uab = uab;
        }

        public float getIa() {
            return Ia;
        }

        public void setIa(float ia) {
            Ia = ia;
        }

        public float getUbc() {
            return Ubc;
        }

        public void setUbc(float ubc) {
            Ubc = ubc;
        }

        public float getIb() {
            return Ib;
        }

        public void setIb(float ib) {
            Ib = ib;
        }

        public float getUca() {
            return Uca;
        }

        public void setUca(float uca) {
            Uca = uca;
        }

        public float getIc() {
            return Ic;
        }

        public void setIc(float ic) {
            Ic = ic;
        }

        public float getPjU() {
            return pjU;
        }

        public void setPjU(float pjU) {
            this.pjU = pjU;
        }

        public float getPjI() {
            return pjI;
        }

        public void setPjI(float pjI) {
            this.pjI = pjI;
        }

        public float getDjgl() {
            return djgl;
        }

        public void setDjgl(float djgl) {
            this.djgl = djgl;
        }

        public float getZgl() {
            return zgl;
        }

        public void setZgl(float zgl) {
            this.zgl = zgl;
        }

        public float getScgl() {
            return scgl;
        }

        public void setScgl(float scgl) {
            this.scgl = scgl;
        }

        public float getDjxl() {
            return djxl;
        }

        public void setDjxl(float djxl) {
            this.djxl = djxl;
        }

        public float getZhxl() {
            return zhxl;
        }

        public void setZhxl(float zhxl) {
            this.zhxl = zhxl;
        }

        public float getGlys() {
            return glys;
        }

        public void setGlys(float glys) {
            this.glys = glys;
        }

        public float getFzxs() {
            return fzxs;
        }

        public void setFzxs(float fzxs) {
            this.fzxs = fzxs;
        }

        public float getYxzt() {
            return yxzt;
        }

        public void setYxzt(float yxzt) {
            this.yxzt = yxzt;
        }

        public String getStryxzt() {
            return stryxzt;
        }

        public void setStryxzt(String stryxzt) {
            this.stryxzt = stryxzt;
        }

        public String getDj1qblc() {
            return dj1qblc;
        }

        public void setDj1qblc(String dj1qblc) {
            this.dj1qblc = dj1qblc;
        }

        public String getDj1csff() {
            return dj1csff;
        }

        public void setDj1csff(String dj1csff) {
            this.dj1csff = dj1csff;
        }

        public float getDj1cdxl() {
            return dj1cdxl;
        }

        public void setDj1cdxl(float dj1cdxl) {
            this.dj1cdxl = dj1cdxl;
        }

        public float getUa2() {
            return Ua2;
        }

        public void setUa2(float ua2) {
            Ua2 = ua2;
        }

        public float getUb2() {
            return Ub2;
        }

        public void setUb2(float ub2) {
            Ub2 = ub2;
        }

        public float getUc2() {
            return Uc2;
        }

        public void setUc2(float uc2) {
            Uc2 = uc2;
        }

        public float getUab2() {
            return Uab2;
        }

        public void setUab2(float uab2) {
            Uab2 = uab2;
        }

        public float getIa2() {
            return Ia2;
        }

        public void setIa2(float ia2) {
            Ia2 = ia2;
        }

        public float getUbc2() {
            return Ubc2;
        }

        public void setUbc2(float ubc2) {
            Ubc2 = ubc2;
        }

        public float getIb2() {
            return Ib2;
        }

        public void setIb2(float ib2) {
            Ib2 = ib2;
        }

        public float getUca2() {
            return Uca2;
        }

        public void setUca2(float uca2) {
            Uca2 = uca2;
        }

        public float getIc2() {
            return Ic2;
        }

        public void setIc2(float ic2) {
            Ic2 = ic2;
        }

        public float getPjU2() {
            return pjU2;
        }

        public void setPjU2(float pjU2) {
            this.pjU2 = pjU2;
        }

        public float getPjI2() {
            return pjI2;
        }

        public void setPjI2(float pjI2) {
            this.pjI2 = pjI2;
        }

        public float getDjgl2() {
            return djgl2;
        }

        public void setDjgl2(float djgl2) {
            this.djgl2 = djgl2;
        }

        public float getZgl2() {
            return zgl2;
        }

        public void setZgl2(float zgl2) {
            this.zgl2 = zgl2;
        }

        public float getScgl2() {
            return scgl2;
        }

        public void setScgl2(float scgl2) {
            this.scgl2 = scgl2;
        }

        public float getDjxl2() {
            return djxl2;
        }

        public void setDjxl2(float djxl2) {
            this.djxl2 = djxl2;
        }

        public float getZhxl2() {
            return zhxl2;
        }

        public void setZhxl2(float zhxl2) {
            this.zhxl2 = zhxl2;
        }

        public float getGlys2() {
            return glys2;
        }

        public void setGlys2(float glys2) {
            this.glys2 = glys2;
        }

        public float getFzxs2() {
            return fzxs2;
        }

        public void setFzxs2(float fzxs2) {
            this.fzxs2 = fzxs2;
        }

        public float getYxzt2() {
            return yxzt2;
        }

        public void setYxzt2(float yxzt2) {
            this.yxzt2 = yxzt2;
        }

        public String getStryxzt2() {
            return stryxzt2;
        }

        public void setStryxzt2(String stryxzt2) {
            this.stryxzt2 = stryxzt2;
        }

        public String getDj2qblc() {
            return dj2qblc;
        }

        public void setDj2qblc(String dj2qblc) {
            this.dj2qblc = dj2qblc;
        }

        public String getDj2csff() {
            return dj2csff;
        }

        public void setDj2csff(String dj2csff) {
            this.dj2csff = dj2csff;
        }

        public float getDj2cdxl() {
            return dj2cdxl;
        }

        public void setDj2cdxl(float dj2cdxl) {
            this.dj2cdxl = dj2cdxl;
        }
    }
}
