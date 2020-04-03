package com.greendao.manager;


import com.ventilator.administrator.DATAbase.greendao.TaskEntity;
import com.ventilator.administrator.DATAbase.greendao.TaskResEnity;
import com.ventilator.app.MyApp;

import org.greenrobot.greendao.annotation.Unique;

import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Handler;

public class DataTFJ {
    private TaskEntity mHisTask;
    private TaskResEnity mRes;
    private float fdjgl1;
    private float fdjgl2;
    private float fdjxl1;
    private float fdjxl2;
    private float mBhzqy;
    private float mWd;
    private float mKqmd;
    private float mDqy;
    private double mSd;

    private float mCfddy;
    private double mPjfs;
    private double mFl;
    private double mCfmj;
    private float mDy;
    private double mPtgxs;
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
    private double mFjjy;
    private float mQygl;
    private double mFjqy;
    private float mJyxl;
    private float mQyxl;
    private float mKsckmj;
    private float mJy;
    private float mKsckdy;
    private float mFjyxxl;
    private float mGxnh;
    private float mcKqmdzhxs;
    private int mSczs;
    private float mcZszhxs;
    private int mEdzs;
    private double mgFl;
    private float mgZgl1;
    private float mgZgl2;
    private float mgFjjy;
    private float mgJygl;
    private float mgFjqy;
    private float mgQygl;
    private float mgJyxl;
    private float mgQyxl;

    // 电参数
    @Unique
    private float Uab;
    private float Ia;
    private float Ubc;
    private float Ib;
    private float Uca;
    private float Ic;
    private float pjU;
    private float pjI;
    private float djgl;


    private float Ua;
    private float Ub;
    private float Uc;


    private float zgl;
    private float scgl;
    private float djxl;
    private float zhxl;
    private float glys;
    private float fzxs;
    private String yxzt = "未连接";
    private String dj1qblc;
    private String dj1csff;


    private float dj1cdxl;
    // 2
    @Unique
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
    private String yxzt2 = "未连接";
    private String dj2qblc;
    private String dj2csff;
    private float dj2cdxl;

    public float getP1() {
        return P1;
    }

    public void setP1(float p1) {
        P1 = p1;
    }

    public float getQ1() {
        return Q1;
    }

    public void setQ1(float q1) {
        Q1 = q1;
    }

    public float getCos1() {
        return cos1;
    }

    public void setCos1(float cos1) {
        this.cos1 = cos1;
    }

    public float getP2() {
        return P2;
    }

    public void setP2(float p2) {
        P2 = p2;
    }

    public float getQ2() {
        return Q2;
    }

    public void setQ2(float q2) {
        Q2 = q2;
    }

    public float getCos2() {
        return cos2;
    }

    public void setCos2(float cos2) {
        this.cos2 = cos2;
    }

    private float P1;
    private float Q1;
    private float cos1;
    private float P2;
    private float Q2;
    private float cos2;

    private String mff;


    private Boolean mventi;
    private Long mId;

    public double getCh4() {
        return ch4;
    }

    public void setCh4(double ch4) {
        this.ch4 = ch4;
    }

    private double ch4;

    public class sensor {


        public Integer getMpower() {
            return mpower;
        }

        public void setMpower(Integer mpower) {
            this.mpower = mpower;
        }

        public Integer getMsignal() {
            return msignal;
        }

        public void setMsignal(Integer msignal) {
            this.msignal = msignal;
        }

        public Integer getMstate() {
            return mstate;
        }

        public void setMstate(Integer mstate) {
            this.mstate = mstate;
        }

        Integer mpower = 100;
        Integer msignal = 100;
        Integer mstate = 0;
    }

    public sensor[] getSensors() {
        return sensors;
    }

    public void setSensors(sensor[] sensors) {
        this.sensors = sensors;
    }

    public void initSensors() {
        if (sensors == null) {
            sensors = new sensor[24];
            for (int i = 0; i < 24; i++) {
                sensors[i] = new sensor();
            }
        }
    }

    public void setSensor(Integer index, Integer power, Integer signal, Integer state) {

        sensor ms = new sensor();
        ms.setMpower(power);
        ms.setMsignal(signal);
        ms.setMstate(state);
        sensors[index] = ms;
        MyApp.getInstance().SetSensorConnectStateTrue(index);
    }

    private sensor[] sensors;


    DecimalFormat df1 = new DecimalFormat("#.0");
    DecimalFormat df2 = new DecimalFormat("#.00");
    DecimalFormat df3 = new DecimalFormat("#.000");

    public void SetHisTask(TaskEntity mtask) {
        this.mHisTask = mtask;
        mff = mHisTask.getCsff();
        mventi = mHisTask.getTffs().equals("抽出式") ? true : false;

        mSczs = Integer.parseInt(mHisTask.getSczs());
        mEdzs = Integer.parseInt(mHisTask.getEdzs());

        mKsckmj = Float.parseFloat(mHisTask.getKsqckmj());
        // mEdgl=mHisTask.getGbhd();
        if (mHisTask.getSddj1xl()) {
            mDjxl = Float.parseFloat(mHisTask.getDj1xl());
        }
        if (mHisTask.getSddj1gl()) {
            mDjgl = Float.parseFloat(mHisTask.getDj1gl());
        }
        mCdxl = Float.parseFloat(mHisTask.getDjcdxl1());
        mCfmj = Float.parseFloat(mHisTask.getCfmj());
        if (mff.equals("静压全压法")) {
            mPtgxs = Float.parseFloat(mHisTask.getPtgxs());
        } else if (mff.equals("静压差法")) {
            mCymjx = Float.parseFloat(mHisTask.getCymjx());
            mCymjd = Float.parseFloat(mHisTask.getCymjd());
        }
        if (mHisTask.getSddj2gl()) {
            mDjgl2 = Float.parseFloat(mHisTask.getDj2gl());
        }
        if (mHisTask.getSddj2xl()) {
            mDjxl2 = Float.parseFloat(mHisTask.getDj2xl());
        }
        mCdxl2 = Float.parseFloat(mHisTask.getDjcdxl2());
        dj1csff = mHisTask.getDjcsff1();
        dj2csff = mHisTask.getDjcsff2();
        dj1cdxl = Float.parseFloat(mHisTask.getDjcdxl1());
        dj2cdxl = Float.parseFloat(mHisTask.getDjcdxl2());
        dj1qblc = mHisTask.getDjqblc1();
        dj2qblc = mHisTask.getDjqblc2();

    }

    public TaskEntity GetHisTask() {
//		mHisTask.setFluid(mSczs);
//		mHisTask.setPsgd((float) mCfmj);
//
//		if (mff == 3) {
//			mHisTask.setJkyl((float) mPtgxs);
//		} else if (mff == 4) {
//			mHisTask.setJkyl(mCymjx);
//			mHisTask.setBwc(mCymjd);
//		}
        if (mHisTask != null) {
            return this.mHisTask;
        } else if (mRes != null) {
            return MyApp.getDaoInstant().getTaskEntityDao().queryBuilder().where(TaskEntityDao.Properties.Id.eq(mRes.getTaskId())).list().get(0);
        } else {
            return new TaskEntity();
        }
    }

    public void SetResOnly(TaskResEnity mres) {
        this.mRes = mres;
        List<TaskEntity> mlist = MyApp.getDaoInstant().getTaskEntityDao().queryBuilder().where(TaskEntityDao.Properties.Id.eq(mres.getTaskId())).list();
        if (mlist.size() > 0) {
            SetHisTask(mlist.get(0));
        }
        mId = mres.getId();
        if (mff == null) {
            mff = mres.getCsff();
        }
        try {
            mventi = mres.getTffs().equals("抽出式") ? true : false;
        } catch (Exception e) {
            e.printStackTrace();
        }
        mSczs = mres.getMSczs();
        mEdzs = mres.getMEdzs();
        mKsckmj = mres.getMKsckmj();
        mDjxl = mres.getMDjxl();
        mCdxl = mres.getMCdxl();
        mCfmj = mres.getMCfmj();
        if (mff.equals("静压全压法")) {
            mPtgxs = mres.getMPtgxs();
        } else if (mff.equals("静压差法")) {
            mCymjx = mres.getMCymjx();
            mCymjd = mres.getMCymjd();
        }
        mDjxl2 = mres.getMDjxl2();
        mCdxl2 = mres.getMCdxl2();
        dj1csff = mres.getDj1csff();
        dj2csff = mres.getDj2csff();
        dj1cdxl = mres.getDj1cdxl();
        dj2cdxl = mres.getDj2cdxl();
        dj1qblc = mres.getDj1qblc();
        dj2qblc = mres.getDj2qblc();
        mPjfs = mres.getMPjfs();
        mDqy = mres.getMDqy();
        mJy = mres.getMJy();
        mDy = mres.getMDy();
        mJyc = mres.getMJyc();
        mWd = mres.getMWd();
        mSd = mres.getMSd();
        mDjgl = mres.getMDjgl();
        mDjgl2 = mres.getMDjgl2();
        mZgl1 = mres.getMZgl1();
        mZgl2 = mres.getMZgl2();
        mKqmd = mres.getMKqmd();
        mFl = mres.getMFl();
        mBhzqy = mres.getMBhzqy();
        mCfddy = mres.getMCfddy();
        mKsckdy = mres.getMKsckdy();
        mFjjy = mres.getMFjjy();
        mFjqy = mres.getMFjqy();
        mJygl = mres.getMJygl();
        mJyxl = mres.getMJyxl();
        mQygl = mres.getMQygl();
        mQyxl = mres.getMQyxl();
        mFjyxxl = mres.getMFjyxxl();
        mGxnh = mres.getMGxnh();
        mcKqmdzhxs = mres.getMcKqmdzhxs();
        mcZszhxs = mres.getMcZszhxs();
        mgFl = mres.getMgFl();
        mgZgl1 = mres.getMgZgl1();
        mgZgl2 = mres.getMgZgl2();
        mgFjjy = mres.getMgFjjy();
        mgJygl = mres.getMgJygl();
        mgJyxl = mres.getMgJyxl();
        mgFjqy = mres.getMgFjqy();
        mgQygl = mres.getMgQygl();
        mgQyxl = mres.getMgQyxl();

        Ua = mres.getUa();
        Ub = mres.getUb();
        Uc = mres.getUc();
        Ua2 = mres.getUa2();
        Ub2 = mres.getUb2();
        Uc2 = mres.getUc2();
        Uab = mres.getUab();
        Ia = mres.getIa();
        Ubc = mres.getUbc();
        Ib = mres.getIb();
        Uca = mres.getUca();
        Ic = mres.getIc();
        pjU = mres.getPjU();
        pjI = mres.getPjI();
        djgl = mres.getDjgl();
        zgl = mres.getZgl();
        scgl = mres.getScgl();
        djxl = mres.getDjxl();
        zhxl = mres.getZhxl();
        glys = mres.getGlys();
        fzxs = mres.getFzxs();
        yxzt = mres.getStryxzt();

        Uab2 = mres.getUab2();
        Ia2 = mres.getIa2();
        Ubc2 = mres.getUbc2();
        Ib2 = mres.getIb2();
        Uca2 = mres.getUca2();
        Ic2 = mres.getIc2();
        pjU2 = mres.getPjU2();
        pjI2 = mres.getPjI2();
        djgl2 = mres.getDjgl2();
        zgl2 = mres.getZgl2();
        scgl2 = mres.getScgl2();
        djxl2 = mres.getDjxl2();
        zhxl2 = mres.getZhxl2();
        glys2 = mres.getGlys2();
        fzxs2 = mres.getFzxs2();
        yxzt2 = mres.getStryxzt2();
        dj1csff = mres.getDj1csff();
        dj2csff = mres.getDj2csff();
        dj1cdxl = mres.getDj1cdxl();
        dj2cdxl = mres.getDj2cdxl();
        dj1qblc = mres.getDj1qblc();
        dj2qblc = mres.getDj2qblc();
    }

    public void SetRes(TaskResEnity mres) {
        this.mRes = mres;
        mId = mres.getId();
        mPjfs = mres.getMPjfs();
        mDqy = mres.getMDqy();
        mJy = mres.getMJy();
        mDy = mres.getMDy();
        mJyc = mres.getMJyc();
        mWd = mres.getMWd();
        mSd = mres.getMSd();
        mDjgl = mres.getMDjgl();
        mDjgl2 = mres.getMDjgl2();
        mDjxl = mres.getMDjxl();
        mDjxl2 = mres.getMDjxl2();
        mZgl1 = mres.getMZgl1();
        mZgl2 = mres.getMZgl2();
        mKqmd = mres.getMKqmd();

        mFl = mres.getMFl();
        mBhzqy = mres.getMBhzqy();
        mCfddy = mres.getMCfddy();
        mKsckdy = mres.getMKsckdy();
        mFjjy = mres.getMFjjy();
        mFjqy = mres.getMFjqy();
        mJygl = mres.getMJygl();
        mJyxl = mres.getMJyxl();
        mQygl = mres.getMQygl();
        mQyxl = mres.getMQyxl();
        mFjyxxl = mres.getMFjyxxl();
        mGxnh = mres.getMGxnh();
        mcKqmdzhxs = mres.getMcKqmdzhxs();
        mcZszhxs = mres.getMcZszhxs();
        mgFl = mres.getMgFl();
        mgZgl1 = mres.getMgZgl1();
        mgZgl2 = mres.getMgZgl2();
        mgFjjy = mres.getMgFjjy();
        mgJygl = mres.getMgJygl();
        mgJyxl = mres.getMgJyxl();
        mgFjqy = mres.getMgFjqy();
        mgQygl = mres.getMgQygl();
        mgQyxl = mres.getMgQyxl();

        Ua = mres.getUa();
        Ub = mres.getUb();
        Uc = mres.getUc();
        Ua2 = mres.getUa2();
        Ub2 = mres.getUb2();
        Uc2 = mres.getUc2();
        Uab = mres.getUab();
        Ia = mres.getIa();
        Ubc = mres.getUbc();
        Ib = mres.getIb();
        Uca = mres.getUca();
        Ic = mres.getIc();
        pjU = mres.getPjU();
        pjI = mres.getPjI();
        djgl = mres.getDjgl();
        zgl = mres.getZgl();
        scgl = mres.getScgl();
        djxl = mres.getDjxl();
        zhxl = mres.getZhxl();
        glys = mres.getGlys();
        fzxs = mres.getFzxs();
//        yxzt = mres.getYxzt();

        Uab2 = mres.getUab2();
        Ia2 = mres.getIa2();
        Ubc2 = mres.getUbc2();
        Ib2 = mres.getIb2();
        Uca2 = mres.getUca2();
        Ic2 = mres.getIc2();
        pjU2 = mres.getPjU2();
        pjI2 = mres.getPjI2();
        djgl2 = mres.getDjgl2();
        zgl2 = mres.getZgl2();
        scgl2 = mres.getScgl2();
        djxl2 = mres.getDjxl2();
        zhxl2 = mres.getZhxl2();
        glys2 = mres.getGlys2();
        fzxs2 = mres.getFzxs2();
//        yxzt2 = mres.getYxzt2();
        dj1csff = mres.getDj1csff();
        dj2csff = mres.getDj2csff();
        dj1cdxl = mres.getDj1cdxl();
        dj2cdxl = mres.getDj2cdxl();
        dj1qblc = mres.getDj1qblc();
        dj2qblc = mres.getDj2qblc();

    }

    public void CopyRes(TaskResEnity mRes) {
        mRes.setTaskId(mHisTask.getId());
        mRes.setTffs(mventi ? "抽出式" : "压入式");
        mRes.setCsff(mff);
        mRes.setMCfmj(mCfmj);
        mRes.setMKsckmj(mKsckmj);
        mRes.setMCymjd(mCymjd);
        mRes.setMCymjx(mCymjx);
        mRes.setDj1cdxl(dj1cdxl);
        mRes.setDj2cdxl(dj2cdxl);
        mRes.setDj1csff(mHisTask.getDjcsff1());
        mRes.setDj2csff(mHisTask.getDjcsff2());
        mRes.setDj1qblc(mHisTask.getDjqblc1());
        mRes.setDj2qblc(mHisTask.getDjqblc2());
        mRes.setMPtgxs(mPtgxs);


        mRes.setMEdzs(mEdzs);
        mRes.setMSczs(mSczs);
        mRes.setMPjfs(mPjfs);
        mRes.setMDqy(mDqy);
        mJy = mHisTask.getSdjy() ? Float.parseFloat(mHisTask.getJy()) : mJy;
        mDy = mHisTask.getSdcy() ? Float.parseFloat(mHisTask.getCy()) : mDy;
        mJyc = mHisTask.getSdcy() ? Float.parseFloat(mHisTask.getCy()) : mJyc;
        mRes.setMJy(mJy);
        mRes.setMDy(mDy);
        mRes.setMJyc(mJyc);
        mRes.setMWd(mWd);
        mRes.setMSd(mSd);
        mRes.setMDjgl(mDjgl);
        mRes.setMDjgl2(mDjgl2);
        mRes.setMDjxl(mDjxl);
        mRes.setMDjxl2(mDjxl2);
        mRes.setMZgl1(mZgl1);
        mRes.setMZgl2(mZgl2);
        mRes.setMKqmd(mKqmd);
        mRes.setMFl(mFl);
        mRes.setMBhzqy(mBhzqy);
        mRes.setMCfddy(mCfddy);
        mRes.setMKsckdy(mKsckdy);
        mRes.setMFjjy(mFjjy);
        mRes.setMFjqy(mFjqy);
        mRes.setMJygl(mJygl);
        mRes.setMJyxl(mJyxl);
        mRes.setMQygl(mQygl);
        mRes.setMQyxl(mQyxl);
        mRes.setMFjyxxl(mFjyxxl);
        mRes.setMGxnh(mGxnh);
        mRes.setMcKqmdzhxs(mcKqmdzhxs);
        mRes.setMcZszhxs(mcZszhxs);
        mRes.setMgFl(mgFl);
        mRes.setMgZgl1(mgZgl1);
        mRes.setMgZgl2(mgZgl2);
        mRes.setMgFjjy(mgFjjy);
        mRes.setMgJygl(mgJygl);
        mRes.setMgJyxl(mgJyxl);
        mRes.setMgFjqy(mgFjqy);
        mRes.setMgQygl(mgQygl);
        mRes.setMgQyxl(mgQyxl);

        mRes.setUa(Ua);
        mRes.setUb(Ub);
        mRes.setUc(Uc);
        mRes.setUa2(Ua2);
        mRes.setUb2(Ub2);
        mRes.setUc2(Uc2);


        mRes.setUab(Uab);

        mRes.setIa(Ia);
        mRes.setUbc(Ubc);
        mRes.setIb(Ib);
        mRes.setUca(Uca);
        mRes.setIc(Ic);
        mRes.setPjU(pjU);
        mRes.setPjI(pjI);
        mRes.setDjgl(djgl);
        mRes.setZgl(zgl);
        mRes.setScgl(scgl);
        mRes.setDjxl(djxl);
        mRes.setZhxl(zhxl);

        mRes.setGlys(glys);
        if (glys == 0) {
            mRes.setGlys(1);
        }
        mRes.setFzxs(fzxs);
        mRes.setStryxzt(yxzt);
        mRes.setUab2(Uab2);
        mRes.setIa2(Ia2);
        mRes.setUbc2(Ubc2);
        mRes.setIb2(Ib2);
        mRes.setUca2(Uca2);
        mRes.setIc2(Ic2);
        mRes.setPjU2(pjU2);
        mRes.setPjI2(pjI2);
        mRes.setDjgl2(djgl2);
        mRes.setZgl2(zgl2);
        mRes.setScgl2(scgl2);
        mRes.setDjxl2(djxl2);
        mRes.setZhxl2(zhxl2);
        mRes.setGlys2(glys2);
        if (glys2 == 0) {
            mRes.setGlys2(1);
        }
        mRes.setFzxs2(fzxs2);
        mRes.setStryxzt2(yxzt2);
    }

    public TaskResEnity GetRes() {
        TaskResEnity mmRes = new TaskResEnity();
        mmRes.setId(mId);
        if (mHisTask != null) {
            mmRes.setTaskId(mHisTask.getId());
        } else {
            mmRes.setTaskId(mRes.getTaskId());
        }
        mmRes.setMCfmj(mCfmj);
        mmRes.setMKsckmj(mKsckmj);
        mmRes.setMCymjd(mCymjd);
        mmRes.setMCymjx(mCymjx);
        mmRes.setDj1cdxl(dj1cdxl);
        mmRes.setDj2cdxl(dj2cdxl);
        mmRes.setDj1csff(mHisTask.getDjcsff1());
        mmRes.setDj2csff(mHisTask.getDjcsff2());
        mmRes.setDj1qblc(mHisTask.getDjqblc1());
        mmRes.setDj2qblc(mHisTask.getDjqblc2());
        mmRes.setMPtgxs(mPtgxs);
        mmRes.setMPjfs(mPjfs);
        mmRes.setMEdzs(mEdzs);
        mmRes.setMSczs(mSczs);
        mmRes.setMDqy(mDqy);
        mmRes.setMJy(mJy);
        mmRes.setMDy(mDy);
        mmRes.setMJyc(mJyc);
        mmRes.setMWd(mWd);
        mmRes.setMSd(mSd);
        mmRes.setMDjgl(mDjgl);
        mmRes.setMDjgl2(mDjgl2);
        mmRes.setMZgl1(mZgl1);
        mmRes.setMZgl2(mZgl2);
        mmRes.setMKqmd(mKqmd);
        mmRes.setMFl(mFl);
        mmRes.setMBhzqy(mBhzqy);
        mmRes.setMCfddy(mCfddy);
        mmRes.setMKsckdy(mKsckdy);
        mmRes.setMFjjy(mFjjy);
        mmRes.setMFjqy(mFjqy);
        mmRes.setMJygl(mJygl);
        mmRes.setMJyxl(mJyxl);
        mmRes.setMQygl(mQygl);
        mmRes.setMQyxl(mQyxl);
        mmRes.setMFjyxxl(mFjyxxl);
        mmRes.setMGxnh(mGxnh);
        mmRes.setMcKqmdzhxs(mcKqmdzhxs);
        mmRes.setMcZszhxs(mcZszhxs);
        mmRes.setMgFl(mgFl);

        mmRes.setMgZgl1(mgZgl1);
        mmRes.setMgZgl2(mgZgl2);
        mmRes.setMgFjjy(mgFjjy);
        mmRes.setMgJygl(mgJygl);
        mmRes.setMgJyxl(mgJyxl);
        mmRes.setMgFjqy(mgFjqy);
        mmRes.setMgQygl(mgQygl);
        mmRes.setMgQyxl(mgQyxl);
        mmRes.setMDjxl(mDjxl);
        mmRes.setMDjxl2(mDjxl2);
        mmRes.setUa(Ua);
        mmRes.setUb(Ub);
        mmRes.setUc(Uc);
        mmRes.setUa2(Ua2);
        mmRes.setUb2(Ub2);
        mmRes.setUc2(Uc2);
        mmRes.setUab(Uab);
        mmRes.setIa(Ia);
        mmRes.setUbc(Ubc);
        mmRes.setIb(Ib);
        mmRes.setUca(Uca);
        mmRes.setIc(Ic);
        mmRes.setPjU(pjU);
        mmRes.setPjI(pjI);
        mmRes.setDjgl(djgl);
        mmRes.setZgl(zgl);
        mmRes.setScgl(scgl);
        mmRes.setDjxl(djxl);
        mmRes.setZhxl(zhxl);
        mmRes.setGlys(glys);
        mmRes.setFzxs(fzxs);
        mmRes.setStryxzt(yxzt);
        mmRes.setUab2(Uab2);
        mmRes.setIa2(Ia2);
        mmRes.setUbc2(Ubc2);
        mmRes.setIb2(Ib2);
        mmRes.setUca2(Uca2);
        mmRes.setIc2(Ic2);
        mmRes.setPjU2(pjU2);
        mmRes.setPjI2(pjI2);
        mmRes.setDjgl2(djgl2);
        mmRes.setZgl2(zgl2);
        mmRes.setScgl2(scgl2);
        mmRes.setDjxl2(djxl2);
        mmRes.setZhxl2(zhxl2);
        mmRes.setGlys2(glys2);
        mmRes.setFzxs2(fzxs2);
        mmRes.setStryxzt2(yxzt2);
        mmRes.setSaveTime(mRes.getSaveTime());

        return mmRes;
    }

    public boolean Refresh() {
        try {

            if (mHisTask != null) {
                fdjgl1 = Float.parseFloat(df2.format(mHisTask.getSddj1gl() ? mDjgl : djgl));
                fdjgl2 = Float.parseFloat(df2.format(mHisTask.getSddj2gl() ? mDjgl2 : djgl2));
                fdjxl1 = Float.parseFloat(df1.format(mHisTask.getSddj1xl() ? mDjxl : djxl));
                fdjxl2 = Float.parseFloat(df1.format(mHisTask.getSddj2xl() ? mDjxl2 : djxl2));
                mWd = Float.parseFloat(df2.format(mHisTask.getSdwd() ? Float.parseFloat(mHisTask.getWd()) : mWd));
                mSd = Float.parseFloat(df2.format(mHisTask.getSdsd() ? Float.parseFloat(mHisTask.getSd()) : mSd));
                mDqy = Float.parseFloat(df2.format(mHisTask.getSddqy() ? Float.parseFloat(mHisTask.getDqy()) : mDqy));
                mPjfs = Float.parseFloat(df1.format(mHisTask.getSdfs() ? Float.parseFloat(mHisTask.getFs()) : mPjfs));
                mJy = Float.parseFloat(df1.format(mHisTask.getSdjy() ? Float.parseFloat(mHisTask.getJy()) : mJy));
                mDy = Float.parseFloat(df1.format(mHisTask.getSdcy() ? Float.parseFloat(mHisTask.getCy()) : mDy));
                mJyc = Float.parseFloat(df1.format(mHisTask.getSdcy() ? Float.parseFloat(mHisTask.getCy()) : mJyc));


            }
            // ��������ѹ��lnP=12.062-4039.558/(�¶�+235.379)
            // P=e^(12.062-4039.558/(�¶�+235.379)) MPa(MPa=1000hPa=100000Pa)
            mBhzqy = Float.parseFloat(df1.format((float) Math.exp(12.062 - 4039.558 / (mWd + 235.379)) * 100 * 1000));
            // //0.00000001455208*t^4-0.00000151077638*t^3+0.00011599438358*t^2+0.00098088830675*t-0.17248452012436
            // mBhzqy= (float)
            // ((float)0.00000001455208*mWd*mWd*mWd*mWd-0.00000151077638*mWd*mWd*mWd+0.00011599438358*mWd*mWd+0.00098088830675*mWd-0.17248452012436);
            // //7.07406-��1657.46/(T+227.02)��
            // mBhzqy=(float) Math.exp(7.07406-(1657.46/(mWd+227.02)));
            // �����ܶ�=0.003484����������ѹ������-0.3779�����ʪ�ȡ���������ѹ(Pa)���£�273������¶ȣ� ǧ�� /������
            mKqmd = Float.parseFloat(df2.format((float) (0.003484 * (mDqy * 100 - 0.3779 * mSd / 100
                    * mBhzqy) / (273 + mWd))));

            switch (mff) {
                // �籭����
                // ����=ƽ�����١������� ������/��
                // ���㶯ѹ=0.5*�����ܶ�*ƽ������*ƽ������
                case "风杯法":// ���㷨

                    mCfddy = Float.parseFloat(df1.format((float) (0.5 * mKqmd * mPjfs * mPjfs)));
                    mFl = Float.parseFloat(df1.format(mPjfs * mCfmj));


                    break;
                case "静压全压法":// ��ѹȫѹ��
                    // ƽ������ =��(2*���㶯ѹ/�����ܶ�)*Ƥ�й�ϵ��
                    // ����=ƽ�����١������� ������/�루������=��ѹ�Ĳ�ѹ�����
                    mCfddy = mDy;
                    if (mKqmd != 0) {
                        mPjfs = Float.parseFloat(df1.format((float) (Math.sqrt(2 * mCfddy / mKqmd) * mPtgxs)));
                        mFl = Float.parseFloat(df1.format(mPjfs * mCfmj));
                    }
                    break;
                case "静压差法":// ��ѹ�
                    // ����=0.99*��ѹ���1*��ѹ���2*��((2*��ѹ��)/(�����ܶ�*������ѹ���2��^2-����ѹ���1��^2��))
                    // ���㶯ѹ=0.5*�����ܶ�*ƽ������*ƽ������
                    // ƽ������=����/������
                    if (mKqmd != 0 && mCymjd != mCymjx) {
                        mFl = Float.parseFloat(df1.format((float) (0.99 * mCymjx * mCymjd * Math.sqrt(2 * mJyc
                                / (mKqmd * (mCymjd * mCymjd - mCymjx * mCymjx))))));
                        mPjfs = Float.parseFloat(df1.format(mFl / mCfmj));
                        mCfddy = Float.parseFloat(df1.format((float) (0.5 * mKqmd * mPjfs * mPjfs)));
                    }
                    break;
                default:
                    break;

            }
            // �Ṧ��=������ʡ����Ч�ʡ�����Ч�� ǧ��

            mZgl1 = Float.parseFloat(df2.format(fdjgl1 * fdjxl1 / 100 * dj1cdxl));

            mZgl2 = Float.parseFloat(df2.format(fdjgl2 * fdjxl2 / 100 * dj2cdxl));
            // ��ɢ�����ڵĶ�ѹ=0.5*�����ܶ�*������/��ɢ���������*������/��ɢ���������
            if (mKsckmj != 0) {
                mKsckdy = Float.parseFloat(df1.format((float) (0.5 * mKqmd * (mFl / mKsckmj) * (mFl / mKsckmj))));
            }
            // ͨ�緽ʽ
            if (mventi) {
                // ���ʽͨ�����
                // �����ѹ=��Ծ�ѹ-���㶯ѹ.
                // ���ȫѹ=�����ѹ+��ɢ�����ڵĶ�ѹ. ��
                // �������Ч��=��ѹЧ��
                mFjjy = mJy - mCfddy;
                mFjqy = mFjjy + mKsckdy;
                // ��ѹ����=�����ѹ��������1000 ǧ��
                mJygl = Float.parseFloat(df2.format((float) (mFjjy * mFl / 1000)));
                // ȫѹ����=���ȫѹ��������1000 ǧ��
                mQygl = Float.parseFloat(df2.format((float) (mFjqy * mFl / 1000)));

                if ((mZgl1 + mZgl2) != 0) {
                    // ��ѹЧ��=��ѹ���ʡ£��Ṧ��1���Ṧ��2����100%
                    mJyxl = Float.parseFloat(df2.format(mJygl / (mZgl1 + mZgl2) * 100));

                    // ȫѹЧ��=ȫѹ���ʡ£��Ṧ��1���Ṧ��2����100%
                    mQyxl = Float.parseFloat(df2.format(mQygl / (mZgl1 + mZgl2) * 100));
                }

                mFjyxxl = mJyxl;
            } else {
                // ѹ��ʽͨ�����
                // �����ѹ=��Ծ�ѹ ��
                // ���ȫѹ=�����ѹ�����㶯ѹ
                // �������Ч��=ȫѹЧ��
                mFjjy = mJy;
                mFjqy = mFjjy + mCfddy;
                // ��ѹ����=�����ѹ��������1000 ǧ��
                mJygl = Float.parseFloat(df2.format((float) (mFjjy * mFl / 1000)));
                // ȫѹ����=���ȫѹ��������1000 ǧ��
                mQygl = Float.parseFloat(df2.format((float) (mFjqy * mFl / 1000)));

                if ((mZgl1 + mZgl2) != 0) {
                    // ��ѹЧ��=��ѹ���ʡ£��Ṧ��1���Ṧ��2����100%
                    mJyxl = Float.parseFloat(df2.format(mJygl / (mZgl1 + mZgl2) * 100));

                    // ȫѹЧ��=ȫѹ���ʡ£��Ṧ��1���Ṧ��2����100%
                    mQyxl = Float.parseFloat(df2.format(mQygl / (mZgl1 + mZgl2) * 100));
                }

                mFjyxxl = mQyxl;
            }

            // �����ܺ�=1�£�3.6���������Ч�ʣ� ǧ��ʱ/����������
            if (mFjyxxl != 0) {
                mGxnh = Float.parseFloat(df1.format((float) (1 / (3.6 * mFjyxxl / 100))));
            }
            // ת��ϵ��
            if (mKqmd != 0) {
                mcKqmdzhxs = Float.parseFloat(df2.format((float) (1.29 / mKqmd)));
            }
            //
            if (mSczs != 0) {
                mcZszhxs = Float.parseFloat(df1.format((float) mEdzs / mSczs));
            }
            // ת�������
            // ����
            mgFl = Float.parseFloat(df1.format((float) (mcZszhxs * mFl)));
            //
            mgZgl1 = Float.parseFloat(df2.format(mcKqmdzhxs * mcZszhxs * mcZszhxs * mcZszhxs * mZgl1));
            mgZgl2 = Float.parseFloat(df2.format(mcKqmdzhxs * mcZszhxs * mcZszhxs * mcZszhxs * mZgl2));
            mgFjjy = Float.parseFloat(df1.format((float) (mcKqmdzhxs * mcZszhxs * mcZszhxs * mFjjy)));
            mgJygl = Float.parseFloat(df2.format(mcKqmdzhxs * mcZszhxs * mcZszhxs * mcZszhxs * mJygl));

            mgFjqy = Float.parseFloat(df1.format((float) (mcKqmdzhxs * mcZszhxs * mcZszhxs * mFjqy)));
            mgQygl = Float.parseFloat(df2.format(mcKqmdzhxs * mcZszhxs * mcZszhxs * mcZszhxs * mQygl));
            if ((mgZgl1 + mgZgl2) != 0) {
                mgJyxl = Float.parseFloat(df2.format(mgJygl / (mgZgl1 + mgZgl2) * 100));
                mgQyxl = Float.parseFloat(df2.format(mgQygl / (mgZgl1 + mgZgl2) * 100));
            }

            return true;
        } catch (Exception e) {
            return false;
        }
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

    public double getmPjfs() {
        return mPjfs;
    }

    public void setmPjfs(double mPjfs) {
        this.mPjfs = mPjfs;
    }

    public double getmFl() {
        return mFl;
    }

    public void setmFl(double mFl) {
        this.mFl = mFl;
    }

    public double getmCfmj() {
        return mCfmj;
    }

    public void setmCfmj(double mCfmj) {
        this.mCfmj = mCfmj;
    }

    public float getmDy() {
        return mDy;
    }

    public void setmDy(float mDy) {
        this.mDy = mDy;
    }

    public double getmPtgxs() {
        return mPtgxs;
    }

    public void setmPtgxs(double mPtgxs) {
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

    public double getmFjjy() {
        return mFjjy;
    }

    public void setmFjjy(double mFjjy) {
        this.mFjjy = mFjjy;
    }

    public float getmQygl() {
        return mQygl;
    }

    public void setmQygl(float mQygl) {
        this.mQygl = mQygl;
    }

    public double getmFjqy() {
        return mFjqy;
    }

    public void setmFjqy(double mFjqy) {
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

    public int getmSczs() {
        return mSczs;
    }

    public void setmSczs(int mSczs) {
        this.mSczs = mSczs;
    }

    public float getMcZszhxs() {
        return mcZszhxs;
    }

    public void setMcZszhxs(float mcZszhxs) {
        this.mcZszhxs = mcZszhxs;
    }

    public int getmEdzs() {
        return mEdzs;
    }

    public void setmEdzs(int mEdzs) {
        this.mEdzs = mEdzs;
    }

    public double getMgFl() {
        return mgFl;
    }

    public void setMgFl(double mgFl) {
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

    public String getYxzt() {
        return yxzt;
    }

    public void setYxzt(String yxzt) {
        this.yxzt = yxzt;
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

    public void setDjgl2(float djgl2) {
        this.djgl2 = djgl2;
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

    public String getYxzt2() {
        return yxzt2;
    }

    public void setYxzt2(String yxzt2) {
        this.yxzt2 = yxzt2;
    }

    public String getMff() {
        return mff;
    }

    public void setMff(String mff) {
        this.mff = mff;
    }


    public float getDjgl() {
        return djgl;
    }

    public float getDjxl() {
        return djxl;
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

    public float getDjgl2() {
        return djgl2;
    }

    public float getZgl2() {
        return zgl2;
    }

    public Boolean getMventi() {
        return mventi;
    }

    public void setMventi(Boolean mventi) {
        this.mventi = mventi;
    }

    public float getDjxl2() {
        return djxl2;
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

    public long getMId() {
        return mId;
    }

    @Override
    public String toString() {
        return "DataTFJ{" +
                "mHisTask=" + mHisTask +
                ", mRes=" + mRes +
                ", fdjgl1=" + fdjgl1 +
                ", fdjgl2=" + fdjgl2 +
                ", fdjxl1=" + fdjxl1 +
                ", fdjxl2=" + fdjxl2 +
                ", mBhzqy=" + mBhzqy +
                ", mWd=" + mWd +
                ", mKqmd=" + mKqmd +
                ", mDqy=" + mDqy +
                ", mSd=" + mSd +
                ", mCfddy=" + mCfddy +
                ", mPjfs=" + mPjfs +
                ", mFl=" + mFl +
                ", mCfmj=" + mCfmj +
                ", mDy=" + mDy +
                ", mPtgxs=" + mPtgxs +
                ", mCymjd=" + mCymjd +
                ", mCymjx=" + mCymjx +
                ", mJyc=" + mJyc +
                ", mZgl1=" + mZgl1 +
                ", mDjgl=" + mDjgl +
                ", mDjxl=" + mDjxl +
                ", mCdxl=" + mCdxl +
                ", mZgl2=" + mZgl2 +
                ", mDjgl2=" + mDjgl2 +
                ", mDjxl2=" + mDjxl2 +
                ", mCdxl2=" + mCdxl2 +
                ", mJygl=" + mJygl +
                ", mFjjy=" + mFjjy +
                ", mQygl=" + mQygl +
                ", mFjqy=" + mFjqy +
                ", mJyxl=" + mJyxl +
                ", mQyxl=" + mQyxl +
                ", mKsckmj=" + mKsckmj +
                ", mJy=" + mJy +
                ", mKsckdy=" + mKsckdy +
                ", mFjyxxl=" + mFjyxxl +
                ", mGxnh=" + mGxnh +
                ", mcKqmdzhxs=" + mcKqmdzhxs +
                ", mSczs=" + mSczs +
                ", mcZszhxs=" + mcZszhxs +
                ", mEdzs=" + mEdzs +
                ", mgFl=" + mgFl +
                ", mgZgl1=" + mgZgl1 +
                ", mgZgl2=" + mgZgl2 +
                ", mgFjjy=" + mgFjjy +
                ", mgJygl=" + mgJygl +
                ", mgFjqy=" + mgFjqy +
                ", mgQygl=" + mgQygl +
                ", mgJyxl=" + mgJyxl +
                ", mgQyxl=" + mgQyxl +
                ", Uab=" + Uab +
                ", Ia=" + Ia +
                ", Ubc=" + Ubc +
                ", Ib=" + Ib +
                ", Uca=" + Uca +
                ", Ic=" + Ic +
                ", pjU=" + pjU +
                ", pjI=" + pjI +
                ", djgl=" + djgl +
                ", Ua=" + Ua +
                ", Ub=" + Ub +
                ", Uc=" + Uc +
                ", zgl=" + zgl +
                ", scgl=" + scgl +
                ", djxl=" + djxl +
                ", zhxl=" + zhxl +
                ", glys=" + glys +
                ", fzxs=" + fzxs +
                ", yxzt='" + yxzt + '\'' +
                ", dj1qblc='" + dj1qblc + '\'' +
                ", dj1csff='" + dj1csff + '\'' +
                ", dj1cdxl=" + dj1cdxl +
                ", Ua2=" + Ua2 +
                ", Ub2=" + Ub2 +
                ", Uc2=" + Uc2 +
                ", Uab2=" + Uab2 +
                ", Ia2=" + Ia2 +
                ", Ubc2=" + Ubc2 +
                ", Ib2=" + Ib2 +
                ", Uca2=" + Uca2 +
                ", Ic2=" + Ic2 +
                ", pjU2=" + pjU2 +
                ", pjI2=" + pjI2 +
                ", djgl2=" + djgl2 +
                ", zgl2=" + zgl2 +
                ", scgl2=" + scgl2 +
                ", djxl2=" + djxl2 +
                ", zhxl2=" + zhxl2 +
                ", glys2=" + glys2 +
                ", fzxs2=" + fzxs2 +
                ", yxzt2='" + yxzt2 + '\'' +
                ", dj2qblc='" + dj2qblc + '\'' +
                ", dj2csff='" + dj2csff + '\'' +
                ", dj2cdxl=" + dj2cdxl +
                ", P1=" + P1 +
                ", Q1=" + Q1 +
                ", cos1=" + cos1 +
                ", P2=" + P2 +
                ", Q2=" + Q2 +
                ", cos2=" + cos2 +
                ", mff='" + mff + '\'' +
                ", mventi=" + mventi +
                ", mId=" + mId +
                ", ch4=" + ch4 +
                ", sensors=" + Arrays.toString(sensors) +
                ", df1=" + df1 +
                ", df2=" + df2 +
                ", df3=" + df3 +
                '}';
    }
}
