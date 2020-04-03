package com.ventilator.administrator.DATAbase.greendao;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Unique;

@Entity
public class TaskResEnity {

    //    @Entity：告诉GreenDao该对象为实体，只有被@Entity注释的Bean类才能被dao类操作
    //    @Id：对象的Id，使用Long类型作为EntityId，否则会报错。(autoincrement = true)表示主键会自增，如果false就会使用旧值
    //    @Property：可以自定义字段名，注意外键不能使用该属性
    //    @NotNull：属性不能为空
    //    @Transient：使用该注释的属性不会被存入数据库的字段中
    //    @Unique：该属性值必须在数据库中是唯一值
    //    @Generated：编译后自动生成的构造函数、方法等的注释，提示构造函数、方法等不能被修改

    //不能用int
    @Id(autoincrement = true)
    private Long id;

    private Long taskId;
    private String csff ; // 测试方法
    private String tffs ; // 通风方式
    private int saveIndex;
    private String SaveTime;

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
    private float Ua  ;
    private float Ub  ;
    private float Uc  ;
    private float Uab  ;
    private float Ia  ;
    private float Ubc  ;
    private float Ib  ;
    private float Uca  ;
    private float Ic  ;
    private float pjU  ;
    private float pjI  ;
    private float djgl  ;
    private float zgl  ;
    private float scgl  ;
    private float djxl  ;
    private float zhxl  ;
    private float glys  ;
    private float fzxs  ;
    private float yxzt  ;
    private String stryxzt;
    private String dj1qblc;
    private String dj1csff;
    private float dj1cdxl;
    // 2
    private float Ua2  ;
    private float Ub2  ;
    private float Uc2  ;
    private float Uab2  ;
    private float Ia2  ;
    private float Ubc2  ;
    private float Ib2  ;
    private float Uca2  ;
    private float Ic2  ;
    private float pjU2  ;
    private float pjI2  ;
    private float djgl2  ;
    private float zgl2  ;
    private float scgl2  ;
    private float djxl2  ;
    private float zhxl2  ;
    private float glys2  ;
    private float fzxs2  ;
    private float yxzt2  ;

    private String stryxzt2;
    private String dj2qblc;
    private String dj2csff;
    private float dj2cdxl;
    @Generated(hash = 442085777)
    public TaskResEnity(Long id, Long taskId, String csff, String tffs, int saveIndex,
            String SaveTime, float mBhzqy, float mWd, float mKqmd, float mDqy, double mSd,
            float mCfddy, double mPjfs, double mFl, double mCfmj, float mDy, double mPtgxs,
            float mCymjd, float mCymjx, float mJyc, float mZgl1, float mDjgl, float mDjxl,
            float mCdxl, float mZgl2, float mDjgl2, float mDjxl2, float mCdxl2, float mJygl,
            double mFjjy, float mQygl, double mFjqy, float mJyxl, float mQyxl, float mKsckmj,
            float mJy, float mKsckdy, float mFjyxxl, float mGxnh, float mcKqmdzhxs, int mSczs,
            float mcZszhxs, int mEdzs, double mgFl, float mgZgl1, float mgZgl2, float mgFjjy,
            float mgJygl, float mgFjqy, float mgQygl, float mgJyxl, float mgQyxl, float Ua,
            float Ub, float Uc, float Uab, float Ia, float Ubc, float Ib, float Uca, float Ic,
            float pjU, float pjI, float djgl, float zgl, float scgl, float djxl, float zhxl,
            float glys, float fzxs, float yxzt, String stryxzt, String dj1qblc,
            String dj1csff, float dj1cdxl, float Ua2, float Ub2, float Uc2, float Uab2,
            float Ia2, float Ubc2, float Ib2, float Uca2, float Ic2, float pjU2, float pjI2,
            float djgl2, float zgl2, float scgl2, float djxl2, float zhxl2, float glys2,
            float fzxs2, float yxzt2, String stryxzt2, String dj2qblc, String dj2csff,
            float dj2cdxl) {
        this.id = id;
        this.taskId = taskId;
        this.csff = csff;
        this.tffs = tffs;
        this.saveIndex = saveIndex;
        this.SaveTime = SaveTime;
        this.mBhzqy = mBhzqy;
        this.mWd = mWd;
        this.mKqmd = mKqmd;
        this.mDqy = mDqy;
        this.mSd = mSd;
        this.mCfddy = mCfddy;
        this.mPjfs = mPjfs;
        this.mFl = mFl;
        this.mCfmj = mCfmj;
        this.mDy = mDy;
        this.mPtgxs = mPtgxs;
        this.mCymjd = mCymjd;
        this.mCymjx = mCymjx;
        this.mJyc = mJyc;
        this.mZgl1 = mZgl1;
        this.mDjgl = mDjgl;
        this.mDjxl = mDjxl;
        this.mCdxl = mCdxl;
        this.mZgl2 = mZgl2;
        this.mDjgl2 = mDjgl2;
        this.mDjxl2 = mDjxl2;
        this.mCdxl2 = mCdxl2;
        this.mJygl = mJygl;
        this.mFjjy = mFjjy;
        this.mQygl = mQygl;
        this.mFjqy = mFjqy;
        this.mJyxl = mJyxl;
        this.mQyxl = mQyxl;
        this.mKsckmj = mKsckmj;
        this.mJy = mJy;
        this.mKsckdy = mKsckdy;
        this.mFjyxxl = mFjyxxl;
        this.mGxnh = mGxnh;
        this.mcKqmdzhxs = mcKqmdzhxs;
        this.mSczs = mSczs;
        this.mcZszhxs = mcZszhxs;
        this.mEdzs = mEdzs;
        this.mgFl = mgFl;
        this.mgZgl1 = mgZgl1;
        this.mgZgl2 = mgZgl2;
        this.mgFjjy = mgFjjy;
        this.mgJygl = mgJygl;
        this.mgFjqy = mgFjqy;
        this.mgQygl = mgQygl;
        this.mgJyxl = mgJyxl;
        this.mgQyxl = mgQyxl;
        this.Ua = Ua;
        this.Ub = Ub;
        this.Uc = Uc;
        this.Uab = Uab;
        this.Ia = Ia;
        this.Ubc = Ubc;
        this.Ib = Ib;
        this.Uca = Uca;
        this.Ic = Ic;
        this.pjU = pjU;
        this.pjI = pjI;
        this.djgl = djgl;
        this.zgl = zgl;
        this.scgl = scgl;
        this.djxl = djxl;
        this.zhxl = zhxl;
        this.glys = glys;
        this.fzxs = fzxs;
        this.yxzt = yxzt;
        this.stryxzt = stryxzt;
        this.dj1qblc = dj1qblc;
        this.dj1csff = dj1csff;
        this.dj1cdxl = dj1cdxl;
        this.Ua2 = Ua2;
        this.Ub2 = Ub2;
        this.Uc2 = Uc2;
        this.Uab2 = Uab2;
        this.Ia2 = Ia2;
        this.Ubc2 = Ubc2;
        this.Ib2 = Ib2;
        this.Uca2 = Uca2;
        this.Ic2 = Ic2;
        this.pjU2 = pjU2;
        this.pjI2 = pjI2;
        this.djgl2 = djgl2;
        this.zgl2 = zgl2;
        this.scgl2 = scgl2;
        this.djxl2 = djxl2;
        this.zhxl2 = zhxl2;
        this.glys2 = glys2;
        this.fzxs2 = fzxs2;
        this.yxzt2 = yxzt2;
        this.stryxzt2 = stryxzt2;
        this.dj2qblc = dj2qblc;
        this.dj2csff = dj2csff;
        this.dj2cdxl = dj2cdxl;
    }
    @Generated(hash = 131024579)
    public TaskResEnity() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public Long getTaskId() {
        return this.taskId;
    }
    public void setTaskId(Long taskId) {
        this.taskId = taskId;
    }
    public String getCsff() {
        return this.csff;
    }
    public void setCsff(String csff) {
        this.csff = csff;
    }
    public String getTffs() {

        return this.tffs;
    }
    public void setTffs(String tffs) {
        this.tffs = tffs;
    }
    public int getSaveIndex() {
        return this.saveIndex;
    }
    public void setSaveIndex(int saveIndex) {
        this.saveIndex = saveIndex;
    }
    public String getSaveTime() {
        return this.SaveTime;
    }
    public void setSaveTime(String SaveTime) {
        this.SaveTime = SaveTime;
    }
    public float getMBhzqy() {
        return this.mBhzqy;
    }
    public void setMBhzqy(float mBhzqy) {
        this.mBhzqy = mBhzqy;
    }
    public float getMWd() {
        return this.mWd;
    }
    public void setMWd(float mWd) {
        this.mWd = mWd;
    }
    public float getMKqmd() {
        return this.mKqmd;
    }
    public void setMKqmd(float mKqmd) {
        this.mKqmd = mKqmd;
    }
    public float getMDqy() {
        return this.mDqy;
    }
    public void setMDqy(float mDqy) {
        this.mDqy = mDqy;
    }
    public double getMSd() {
        return this.mSd;
    }
    public void setMSd(double mSd) {
        this.mSd = mSd;
    }
    public float getMCfddy() {
        return this.mCfddy;
    }
    public void setMCfddy(float mCfddy) {
        this.mCfddy = mCfddy;
    }
    public double getMPjfs() {
        return this.mPjfs;
    }
    public void setMPjfs(double mPjfs) {
        this.mPjfs = mPjfs;
    }
    public double getMFl() {
        return this.mFl;
    }
    public void setMFl(double mFl) {
        this.mFl = mFl;
    }
    public double getMCfmj() {
        return this.mCfmj;
    }
    public void setMCfmj(double mCfmj) {
        this.mCfmj = mCfmj;
    }
    public float getMDy() {
        return this.mDy;
    }
    public void setMDy(float mDy) {
        this.mDy = mDy;
    }
    public double getMPtgxs() {
        return this.mPtgxs;
    }
    public void setMPtgxs(double mPtgxs) {
        this.mPtgxs = mPtgxs;
    }
    public float getMCymjd() {
        return this.mCymjd;
    }
    public void setMCymjd(float mCymjd) {
        this.mCymjd = mCymjd;
    }
    public float getMCymjx() {
        return this.mCymjx;
    }
    public void setMCymjx(float mCymjx) {
        this.mCymjx = mCymjx;
    }
    public float getMJyc() {
        return this.mJyc;
    }
    public void setMJyc(float mJyc) {
        this.mJyc = mJyc;
    }
    public float getMZgl1() {
        return this.mZgl1;
    }
    public void setMZgl1(float mZgl1) {
        this.mZgl1 = mZgl1;
    }
    public float getMDjgl() {
        return this.mDjgl;
    }
    public void setMDjgl(float mDjgl) {
        this.mDjgl = mDjgl;
    }
    public float getMDjxl() {
        return this.mDjxl;
    }
    public void setMDjxl(float mDjxl) {
        this.mDjxl = mDjxl;
    }
    public float getMCdxl() {
        return this.mCdxl;
    }
    public void setMCdxl(float mCdxl) {
        this.mCdxl = mCdxl;
    }
    public float getMZgl2() {
        return this.mZgl2;
    }
    public void setMZgl2(float mZgl2) {
        this.mZgl2 = mZgl2;
    }
    public float getMDjgl2() {
        return this.mDjgl2;
    }
    public void setMDjgl2(float mDjgl2) {
        this.mDjgl2 = mDjgl2;
    }
    public float getMDjxl2() {
        return this.mDjxl2;
    }
    public void setMDjxl2(float mDjxl2) {
        this.mDjxl2 = mDjxl2;
    }
    public float getMCdxl2() {
        return this.mCdxl2;
    }
    public void setMCdxl2(float mCdxl2) {
        this.mCdxl2 = mCdxl2;
    }
    public float getMJygl() {
        return this.mJygl;
    }
    public void setMJygl(float mJygl) {
        this.mJygl = mJygl;
    }
    public double getMFjjy() {
        return this.mFjjy;
    }
    public void setMFjjy(double mFjjy) {
        this.mFjjy = mFjjy;
    }
    public float getMQygl() {
        return this.mQygl;
    }
    public void setMQygl(float mQygl) {
        this.mQygl = mQygl;
    }
    public double getMFjqy() {
        return this.mFjqy;
    }
    public void setMFjqy(double mFjqy) {
        this.mFjqy = mFjqy;
    }
    public float getMJyxl() {
        return this.mJyxl;
    }
    public void setMJyxl(float mJyxl) {
        this.mJyxl = mJyxl;
    }
    public float getMQyxl() {
        return this.mQyxl;
    }
    public void setMQyxl(float mQyxl) {
        this.mQyxl = mQyxl;
    }
    public float getMKsckmj() {
        return this.mKsckmj;
    }
    public void setMKsckmj(float mKsckmj) {
        this.mKsckmj = mKsckmj;
    }
    public float getMJy() {
        return this.mJy;
    }
    public void setMJy(float mJy) {
        this.mJy = mJy;
    }
    public float getMKsckdy() {
        return this.mKsckdy;
    }
    public void setMKsckdy(float mKsckdy) {
        this.mKsckdy = mKsckdy;
    }
    public float getMFjyxxl() {
        return this.mFjyxxl;
    }
    public void setMFjyxxl(float mFjyxxl) {
        this.mFjyxxl = mFjyxxl;
    }
    public float getMGxnh() {
        return this.mGxnh;
    }
    public void setMGxnh(float mGxnh) {
        this.mGxnh = mGxnh;
    }
    public float getMcKqmdzhxs() {
        return this.mcKqmdzhxs;
    }
    public void setMcKqmdzhxs(float mcKqmdzhxs) {
        this.mcKqmdzhxs = mcKqmdzhxs;
    }
    public int getMSczs() {
        return this.mSczs;
    }
    public void setMSczs(int mSczs) {
        this.mSczs = mSczs;
    }
    public float getMcZszhxs() {
        return this.mcZszhxs;
    }
    public void setMcZszhxs(float mcZszhxs) {
        this.mcZszhxs = mcZszhxs;
    }
    public int getMEdzs() {
        return this.mEdzs;
    }
    public void setMEdzs(int mEdzs) {
        this.mEdzs = mEdzs;
    }
    public double getMgFl() {
        return this.mgFl;
    }
    public void setMgFl(double mgFl) {
        this.mgFl = mgFl;
    }
    public float getMgZgl1() {
        return this.mgZgl1;
    }
    public void setMgZgl1(float mgZgl1) {
        this.mgZgl1 = mgZgl1;
    }
    public float getMgZgl2() {
        return this.mgZgl2;
    }
    public void setMgZgl2(float mgZgl2) {
        this.mgZgl2 = mgZgl2;
    }
    public float getMgFjjy() {
        return this.mgFjjy;
    }
    public void setMgFjjy(float mgFjjy) {
        this.mgFjjy = mgFjjy;
    }
    public float getMgJygl() {
        return this.mgJygl;
    }
    public void setMgJygl(float mgJygl) {
        this.mgJygl = mgJygl;
    }
    public float getMgFjqy() {
        return this.mgFjqy;
    }
    public void setMgFjqy(float mgFjqy) {
        this.mgFjqy = mgFjqy;
    }
    public float getMgQygl() {
        return this.mgQygl;
    }
    public void setMgQygl(float mgQygl) {
        this.mgQygl = mgQygl;
    }
    public float getMgJyxl() {
        return this.mgJyxl;
    }
    public void setMgJyxl(float mgJyxl) {
        this.mgJyxl = mgJyxl;
    }
    public float getMgQyxl() {
        return this.mgQyxl;
    }
    public void setMgQyxl(float mgQyxl) {
        this.mgQyxl = mgQyxl;
    }
    public float getUab() {
        return this.Uab;
    }
    public void setUab(float Uab) {
        this.Uab = Uab;
    }
    public float getIa() {
        return this.Ia;
    }
    public void setIa(float Ia) {
        this.Ia = Ia;
    }
    public float getUbc() {
        return this.Ubc;
    }
    public void setUbc(float Ubc) {
        this.Ubc = Ubc;
    }
    public float getIb() {
        return this.Ib;
    }
    public void setIb(float Ib) {
        this.Ib = Ib;
    }
    public float getUca() {
        return this.Uca;
    }
    public void setUca(float Uca) {
        this.Uca = Uca;
    }
    public float getIc() {
        return this.Ic;
    }
    public void setIc(float Ic) {
        this.Ic = Ic;
    }
    public float getPjU() {
        return this.pjU;
    }
    public void setPjU(float pjU) {
        this.pjU = pjU;
    }
    public float getPjI() {
        return this.pjI;
    }
    public void setPjI(float pjI) {
        this.pjI = pjI;
    }
    public float getDjgl() {
        return this.djgl;
    }
    public void setDjgl(float djgl) {
        this.djgl = djgl;
    }
    public float getZgl() {
        return this.zgl;
    }
    public void setZgl(float zgl) {
        this.zgl = zgl;
    }
    public float getScgl() {
        return this.scgl;
    }
    public void setScgl(float scgl) {
        this.scgl = scgl;
    }
    public float getDjxl() {
        return this.djxl;
    }
    public void setDjxl(float djxl) {
        this.djxl = djxl;
    }
    public float getZhxl() {
        return this.zhxl;
    }
    public void setZhxl(float zhxl) {
        this.zhxl = zhxl;
    }
    public float getGlys() {
        return this.glys;
    }
    public void setGlys(float glys) {
        this.glys = glys;
    }
    public float getFzxs() {
        return this.fzxs;
    }
    public void setFzxs(float fzxs) {
        this.fzxs = fzxs;
    }
    public float getYxzt() {
        return this.yxzt;
    }
    public void setYxzt(float yxzt) {
        this.yxzt = yxzt;
    }
    public String getStryxzt() {
        return this.stryxzt;
    }
    public void setStryxzt(String stryxzt) {
        this.stryxzt = stryxzt;
    }
    public String getDj1qblc() {
        return this.dj1qblc;
    }
    public void setDj1qblc(String dj1qblc) {
        this.dj1qblc = dj1qblc;
    }
    public String getDj1csff() {
        return this.dj1csff;
    }
    public void setDj1csff(String dj1csff) {
        this.dj1csff = dj1csff;
    }
    public float getDj1cdxl() {
        return this.dj1cdxl;
    }
    public void setDj1cdxl(float dj1cdxl) {
        this.dj1cdxl = dj1cdxl;
    }
    public float getUab2() {
        return this.Uab2;
    }
    public void setUab2(float Uab2) {
        this.Uab2 = Uab2;
    }
    public float getIa2() {
        return this.Ia2;
    }
    public void setIa2(float Ia2) {
        this.Ia2 = Ia2;
    }
    public float getUbc2() {
        return this.Ubc2;
    }
    public void setUbc2(float Ubc2) {
        this.Ubc2 = Ubc2;
    }
    public float getIb2() {
        return this.Ib2;
    }
    public void setIb2(float Ib2) {
        this.Ib2 = Ib2;
    }
    public float getUca2() {
        return this.Uca2;
    }
    public void setUca2(float Uca2) {
        this.Uca2 = Uca2;
    }
    public float getIc2() {
        return this.Ic2;
    }
    public void setIc2(float Ic2) {
        this.Ic2 = Ic2;
    }
    public float getPjU2() {
        return this.pjU2;
    }
    public void setPjU2(float pjU2) {
        this.pjU2 = pjU2;
    }
    public float getPjI2() {
        return this.pjI2;
    }
    public void setPjI2(float pjI2) {
        this.pjI2 = pjI2;
    }
    public float getDjgl2() {
        return this.djgl2;
    }
    public void setDjgl2(float djgl2) {
        this.djgl2 = djgl2;
    }
    public float getZgl2() {
        return this.zgl2;
    }
    public void setZgl2(float zgl2) {
        this.zgl2 = zgl2;
    }
    public float getScgl2() {
        return this.scgl2;
    }
    public void setScgl2(float scgl2) {
        this.scgl2 = scgl2;
    }
    public float getDjxl2() {
        return this.djxl2;
    }
    public void setDjxl2(float djxl2) {
        this.djxl2 = djxl2;
    }
    public float getZhxl2() {
        return this.zhxl2;
    }
    public void setZhxl2(float zhxl2) {
        this.zhxl2 = zhxl2;
    }
    public float getGlys2() {
        return this.glys2;
    }
    public void setGlys2(float glys2) {
        this.glys2 = glys2;
    }
    public float getFzxs2() {
        return this.fzxs2;
    }
    public void setFzxs2(float fzxs2) {
        this.fzxs2 = fzxs2;
    }
    public float getYxzt2() {
        return this.yxzt2;
    }
    public void setYxzt2(float yxzt2) {
        this.yxzt2 = yxzt2;
    }
    public String getStryxzt2() {
        return this.stryxzt2;
    }
    public void setStryxzt2(String stryxzt2) {
        this.stryxzt2 = stryxzt2;
    }
    public String getDj2qblc() {
        return this.dj2qblc;
    }
    public void setDj2qblc(String dj2qblc) {
        this.dj2qblc = dj2qblc;
    }
    public String getDj2csff() {
        return this.dj2csff;
    }
    public void setDj2csff(String dj2csff) {
        this.dj2csff = dj2csff;
    }
    public float getDj2cdxl() {
        return this.dj2cdxl;
    }
    public void setDj2cdxl(float dj2cdxl) {
        this.dj2cdxl = dj2cdxl;
    }
    public float getUa() {
        return this.Ua;
    }
    public void setUa(float Ua) {
        this.Ua = Ua;
    }
    public float getUb() {
        return this.Ub;
    }
    public void setUb(float Ub) {
        this.Ub = Ub;
    }
    public float getUc() {
        return this.Uc;
    }
    public void setUc(float Uc) {
        this.Uc = Uc;
    }
    public float getUa2() {
        return this.Ua2;
    }
    public void setUa2(float Ua2) {
        this.Ua2 = Ua2;
    }
    public float getUb2() {
        return this.Ub2;
    }
    public void setUb2(float Ub2) {
        this.Ub2 = Ub2;
    }
    public float getUc2() {
        return this.Uc2;
    }
    public void setUc2(float Uc2) {
        this.Uc2 = Uc2;
    }




}