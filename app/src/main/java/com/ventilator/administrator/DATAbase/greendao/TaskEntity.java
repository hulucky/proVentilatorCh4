package com.ventilator.administrator.DATAbase.greendao;

import com.xzkydz.function.search.module.ITaskRoot;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class TaskEntity implements ITaskRoot {

    @Id(autoincrement = true)
    private Long id;
    //受检单位名称
    private String unitName;
    //编号
    private String gasePumpNumber;
    //测试员
    private String peopleName;
    //测试任务的状态
    private boolean _IsCompleteTask;
    //测试任务已经测得测试数据条数
    private int taskHaveGetData;
    //测试任务的创建时间
    private String greateTaskTime;

    private String csff ; // 测试方法
    private String tffs ; // 通风方式
    private String cfmj ; // 测风面积
    private String ksqckmj ; // 扩撒器出口面积
    private String cymjx ; // 测压面积小
    private String cymjd ; // 测压面积大
    private String dlbb1 ; // 电流变比1
    private String dlbb2 ; // 电流变比2
    private String dybb1 ; // 电压变比1
    private String dybb2 ; // 电压变比2
    private String djk1 ; // 电机库1
    private boolean djksfxz1 ; // 电机库是否选择1
    private String djqblc1 ; // 电机钳表量程1
    private String djcsff1 ; // 电机测试方法1
    private String djcdxl1 ; // 电机传动效率1
    private String djcdfs1 ; // 电机传动方式1
    private String djeddy1 ; // 电机额定电压1
    private String djeddl1 ; // 电机额定电流1
    private String djedgl1 ; // 电机额定功率1
    private String djedxl1 ; // 电机额定效率1
    private String djkzdl1 ; // 电机空载电流1
    private String djkzgl1 ; // 电机空载功率1
    private String djjs1 ; // 电机级数1
    private String djwgjjdl1 ; // 电机无功经济当量1
    private String djk2 ; // 电机库2
    private boolean djksfxz2 ; // 电机库是否选择2
    private String djqblc2 ; // 电机钳表量程2
    private String djcsff2 ; // 电机测试方法2
    private String djcdxl2 ; // 电机传动效率2
    private String djcdfs2 ; // 电机传动方式2
    private String djeddy2 ; // 电机额定电压2
    private String djeddl2 ; // 电机额定电流2
    private String djedgl2 ; // 电机额定功率2
    private String djedxl2 ; // 电机额定效率2
    private String djkzdl2 ; // 电机空载电流2
    private String djkzgl2 ; // 电机空载功率2
    private String djjs2 ; // 电机级数2
    private String djwgjjdl2 ; // 电机无功经济当量2

    private String fs ; // 风速
    private String jy ; // 静压
    private String dy ; // 动压
    private String cy ; // 差压
    private String wd ; // 温度
    private String sd ; // 湿度
    private String dqy ; // 大气压
    private String dj1gl ; // 电机1功率
    private String dj2gl ; // 电机2功率
    private String dj1xl ; // 电机1效率
    private String dj2xl ; // 电机2效率
    private String edzs ; // 额定转速
    private String sczs ; // 实测转速
    private boolean sdfs ; // 设定风速
    private boolean sdjy ; // 设定静压
    private boolean sdcy ; // 设定差压
    private boolean sdwd ; // 设定温度
    private boolean sdsd ; // 设定湿度
    private boolean sddqy ; // 设定大气压
    private boolean sddj1gl ; // 设定电机1功率
    private boolean sddj2gl ; // 设定电机2功率
    private boolean sddj1xl ; // 设定电机1效率
    private boolean sddj2xl ; // 设定电机2效率
    private boolean sdedzs ; // 设定额定转速
    private boolean sdsczs ; // 设定实测转速
    private String by1 ; // 备用1
    private String by2 ; // 备用2
    private String by3 ; // 备用3
    private String by4 ; // 备用4
    private String by5 ; // 备用5
    private String by6 ; // 备用6
    private String by7 ; // 备用7
    private String by8 ; // 备用8
    private String by9 ; // 备用9
    private String by10 ; // 备用10
    private String by11 ; // 备用11
    private String by12 ; // 备用12
    private String ptgxs;//皮托管系数
    @Generated(hash = 1260898715)
    public TaskEntity(Long id, String unitName, String gasePumpNumber,
            String peopleName, boolean _IsCompleteTask, int taskHaveGetData,
            String greateTaskTime, String csff, String tffs, String cfmj,
            String ksqckmj, String cymjx, String cymjd, String dlbb1, String dlbb2,
            String dybb1, String dybb2, String djk1, boolean djksfxz1,
            String djqblc1, String djcsff1, String djcdxl1, String djcdfs1,
            String djeddy1, String djeddl1, String djedgl1, String djedxl1,
            String djkzdl1, String djkzgl1, String djjs1, String djwgjjdl1,
            String djk2, boolean djksfxz2, String djqblc2, String djcsff2,
            String djcdxl2, String djcdfs2, String djeddy2, String djeddl2,
            String djedgl2, String djedxl2, String djkzdl2, String djkzgl2,
            String djjs2, String djwgjjdl2, String fs, String jy, String dy,
            String cy, String wd, String sd, String dqy, String dj1gl, String dj2gl,
            String dj1xl, String dj2xl, String edzs, String sczs, boolean sdfs,
            boolean sdjy, boolean sdcy, boolean sdwd, boolean sdsd, boolean sddqy,
            boolean sddj1gl, boolean sddj2gl, boolean sddj1xl, boolean sddj2xl,
            boolean sdedzs, boolean sdsczs, String by1, String by2, String by3,
            String by4, String by5, String by6, String by7, String by8, String by9,
            String by10, String by11, String by12, String ptgxs) {
        this.id = id;
        this.unitName = unitName;
        this.gasePumpNumber = gasePumpNumber;
        this.peopleName = peopleName;
        this._IsCompleteTask = _IsCompleteTask;
        this.taskHaveGetData = taskHaveGetData;
        this.greateTaskTime = greateTaskTime;
        this.csff = csff;
        this.tffs = tffs;
        this.cfmj = cfmj;
        this.ksqckmj = ksqckmj;
        this.cymjx = cymjx;
        this.cymjd = cymjd;
        this.dlbb1 = dlbb1;
        this.dlbb2 = dlbb2;
        this.dybb1 = dybb1;
        this.dybb2 = dybb2;
        this.djk1 = djk1;
        this.djksfxz1 = djksfxz1;
        this.djqblc1 = djqblc1;
        this.djcsff1 = djcsff1;
        this.djcdxl1 = djcdxl1;
        this.djcdfs1 = djcdfs1;
        this.djeddy1 = djeddy1;
        this.djeddl1 = djeddl1;
        this.djedgl1 = djedgl1;
        this.djedxl1 = djedxl1;
        this.djkzdl1 = djkzdl1;
        this.djkzgl1 = djkzgl1;
        this.djjs1 = djjs1;
        this.djwgjjdl1 = djwgjjdl1;
        this.djk2 = djk2;
        this.djksfxz2 = djksfxz2;
        this.djqblc2 = djqblc2;
        this.djcsff2 = djcsff2;
        this.djcdxl2 = djcdxl2;
        this.djcdfs2 = djcdfs2;
        this.djeddy2 = djeddy2;
        this.djeddl2 = djeddl2;
        this.djedgl2 = djedgl2;
        this.djedxl2 = djedxl2;
        this.djkzdl2 = djkzdl2;
        this.djkzgl2 = djkzgl2;
        this.djjs2 = djjs2;
        this.djwgjjdl2 = djwgjjdl2;
        this.fs = fs;
        this.jy = jy;
        this.dy = dy;
        this.cy = cy;
        this.wd = wd;
        this.sd = sd;
        this.dqy = dqy;
        this.dj1gl = dj1gl;
        this.dj2gl = dj2gl;
        this.dj1xl = dj1xl;
        this.dj2xl = dj2xl;
        this.edzs = edzs;
        this.sczs = sczs;
        this.sdfs = sdfs;
        this.sdjy = sdjy;
        this.sdcy = sdcy;
        this.sdwd = sdwd;
        this.sdsd = sdsd;
        this.sddqy = sddqy;
        this.sddj1gl = sddj1gl;
        this.sddj2gl = sddj2gl;
        this.sddj1xl = sddj1xl;
        this.sddj2xl = sddj2xl;
        this.sdedzs = sdedzs;
        this.sdsczs = sdsczs;
        this.by1 = by1;
        this.by2 = by2;
        this.by3 = by3;
        this.by4 = by4;
        this.by5 = by5;
        this.by6 = by6;
        this.by7 = by7;
        this.by8 = by8;
        this.by9 = by9;
        this.by10 = by10;
        this.by11 = by11;
        this.by12 = by12;
        this.ptgxs = ptgxs;
    }
    @Generated(hash = 397975341)
    public TaskEntity() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getUnitName() {
        return this.unitName;
    }
    public void setUnitName(String unitName) {
        this.unitName = unitName;
    }
    public String getGasePumpNumber() {
        return this.gasePumpNumber;
    }
    public void setGasePumpNumber(String gasePumpNumber) {
        this.gasePumpNumber = gasePumpNumber;
    }
    public String getPeopleName() {
        return this.peopleName;
    }
    public void setPeopleName(String peopleName) {
        this.peopleName = peopleName;
    }
    public boolean get_IsCompleteTask() {
        return this._IsCompleteTask;
    }
    public void set_IsCompleteTask(boolean _IsCompleteTask) {
        this._IsCompleteTask = _IsCompleteTask;
    }
    public int getTaskHaveGetData() {
        return 0;
    }
    public void setTaskHaveGetData(int taskHaveGetData) {
        this.taskHaveGetData = taskHaveGetData;
    }
    public String getGreateTaskTime() {
        return this.greateTaskTime;
    }
    public void setGreateTaskTime(String greateTaskTime) {
        this.greateTaskTime = greateTaskTime;
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
    public String getCfmj() {
        return this.cfmj;
    }
    public void setCfmj(String cfmj) {
        this.cfmj = cfmj;
    }
    public String getKsqckmj() {
        return this.ksqckmj;
    }
    public void setKsqckmj(String ksqckmj) {
        this.ksqckmj = ksqckmj;
    }
    public String getCymjx() {
        return this.cymjx;
    }
    public void setCymjx(String cymjx) {
        this.cymjx = cymjx;
    }
    public String getCymjd() {
        return this.cymjd;
    }
    public void setCymjd(String cymjd) {
        this.cymjd = cymjd;
    }
    public String getDlbb1() {
        return this.dlbb1;
    }
    public void setDlbb1(String dlbb1) {
        this.dlbb1 = dlbb1;
    }
    public String getDlbb2() {
        return this.dlbb2;
    }
    public void setDlbb2(String dlbb2) {
        this.dlbb2 = dlbb2;
    }
    public String getDybb1() {
        return this.dybb1;
    }
    public void setDybb1(String dybb1) {
        this.dybb1 = dybb1;
    }
    public String getDybb2() {
        return this.dybb2;
    }
    public void setDybb2(String dybb2) {
        this.dybb2 = dybb2;
    }
    public String getDjk1() {
        return this.djk1;
    }
    public void setDjk1(String djk1) {
        this.djk1 = djk1;
    }
    public boolean getDjksfxz1() {
        return this.djksfxz1;
    }
    public void setDjksfxz1(boolean djksfxz1) {
        this.djksfxz1 = djksfxz1;
    }
    public String getDjqblc1() {
        return this.djqblc1;
    }
    public void setDjqblc1(String djqblc1) {
        this.djqblc1 = djqblc1;
    }
    public String getDjcsff1() {
        return this.djcsff1;
    }
    public void setDjcsff1(String djcsff1) {
        this.djcsff1 = djcsff1;
    }
    public String getDjcdxl1() {
        return this.djcdxl1;
    }
    public void setDjcdxl1(String djcdxl1) {
        this.djcdxl1 = djcdxl1;
    }
    public String getDjcdfs1() {
        return this.djcdfs1;
    }
    public void setDjcdfs1(String djcdfs1) {
        this.djcdfs1 = djcdfs1;
    }
    public String getDjeddy1() {
        return this.djeddy1;
    }
    public void setDjeddy1(String djeddy1) {
        this.djeddy1 = djeddy1;
    }
    public String getDjeddl1() {
        return this.djeddl1;
    }
    public void setDjeddl1(String djeddl1) {
        this.djeddl1 = djeddl1;
    }
    public String getDjedgl1() {
        return this.djedgl1;
    }
    public void setDjedgl1(String djedgl1) {
        this.djedgl1 = djedgl1;
    }
    public String getDjedxl1() {
        return this.djedxl1;
    }
    public void setDjedxl1(String djedxl1) {
        this.djedxl1 = djedxl1;
    }
    public String getDjkzdl1() {
        return this.djkzdl1;
    }
    public void setDjkzdl1(String djkzdl1) {
        this.djkzdl1 = djkzdl1;
    }
    public String getDjkzgl1() {
        return this.djkzgl1;
    }
    public void setDjkzgl1(String djkzgl1) {
        this.djkzgl1 = djkzgl1;
    }
    public String getDjjs1() {
        return this.djjs1;
    }
    public void setDjjs1(String djjs1) {
        this.djjs1 = djjs1;
    }
    public String getDjwgjjdl1() {
        return this.djwgjjdl1;
    }
    public void setDjwgjjdl1(String djwgjjdl1) {
        this.djwgjjdl1 = djwgjjdl1;
    }
    public String getDjk2() {
        return this.djk2;
    }
    public void setDjk2(String djk2) {
        this.djk2 = djk2;
    }
    public boolean getDjksfxz2() {
        return this.djksfxz2;
    }
    public void setDjksfxz2(boolean djksfxz2) {
        this.djksfxz2 = djksfxz2;
    }
    public String getDjqblc2() {
        return this.djqblc2;
    }
    public void setDjqblc2(String djqblc2) {
        this.djqblc2 = djqblc2;
    }
    public String getDjcsff2() {
        return this.djcsff2;
    }
    public void setDjcsff2(String djcsff2) {
        this.djcsff2 = djcsff2;
    }
    public String getDjcdxl2() {
        return this.djcdxl2;
    }
    public void setDjcdxl2(String djcdxl2) {
        this.djcdxl2 = djcdxl2;
    }
    public String getDjcdfs2() {
        return this.djcdfs2;
    }
    public void setDjcdfs2(String djcdfs2) {
        this.djcdfs2 = djcdfs2;
    }
    public String getDjeddy2() {
        return this.djeddy2;
    }
    public void setDjeddy2(String djeddy2) {
        this.djeddy2 = djeddy2;
    }
    public String getDjeddl2() {
        return this.djeddl2;
    }
    public void setDjeddl2(String djeddl2) {
        this.djeddl2 = djeddl2;
    }
    public String getDjedgl2() {
        return this.djedgl2;
    }
    public void setDjedgl2(String djedgl2) {
        this.djedgl2 = djedgl2;
    }
    public String getDjedxl2() {
        return this.djedxl2;
    }
    public void setDjedxl2(String djedxl2) {
        this.djedxl2 = djedxl2;
    }
    public String getDjkzdl2() {
        return this.djkzdl2;
    }
    public void setDjkzdl2(String djkzdl2) {
        this.djkzdl2 = djkzdl2;
    }
    public String getDjkzgl2() {
        return this.djkzgl2;
    }
    public void setDjkzgl2(String djkzgl2) {
        this.djkzgl2 = djkzgl2;
    }
    public String getDjjs2() {
        return this.djjs2;
    }
    public void setDjjs2(String djjs2) {
        this.djjs2 = djjs2;
    }
    public String getDjwgjjdl2() {
        return this.djwgjjdl2;
    }
    public void setDjwgjjdl2(String djwgjjdl2) {
        this.djwgjjdl2 = djwgjjdl2;
    }
    public String getFs() {
        return this.fs;
    }
    public void setFs(String fs) {
        this.fs = fs;
    }
    public String getJy() {
        return this.jy;
    }
    public void setJy(String jy) {
        this.jy = jy;
    }
    public String getDy() {
        return this.dy;
    }
    public void setDy(String dy) {
        this.dy = dy;
    }
    public String getCy() {
        return this.cy;
    }
    public void setCy(String cy) {
        this.cy = cy;
    }
    public String getWd() {
        return this.wd;
    }
    public void setWd(String wd) {
        this.wd = wd;
    }
    public String getSd() {
        return this.sd;
    }
    public void setSd(String sd) {
        this.sd = sd;
    }
    public String getDqy() {
        return this.dqy;
    }
    public void setDqy(String dqy) {
        this.dqy = dqy;
    }
    public String getDj1gl() {
        return this.dj1gl;
    }
    public void setDj1gl(String dj1gl) {
        this.dj1gl = dj1gl;
    }
    public String getDj2gl() {
        return this.dj2gl;
    }
    public void setDj2gl(String dj2gl) {
        this.dj2gl = dj2gl;
    }
    public String getDj1xl() {
        return this.dj1xl;
    }
    public void setDj1xl(String dj1xl) {
        this.dj1xl = dj1xl;
    }
    public String getDj2xl() {
        return this.dj2xl;
    }
    public void setDj2xl(String dj2xl) {
        this.dj2xl = dj2xl;
    }
    public String getEdzs() {
        return this.edzs;
    }
    public void setEdzs(String edzs) {
        this.edzs = edzs;
    }
    public String getSczs() {
        return this.sczs;
    }
    public void setSczs(String sczs) {
        this.sczs = sczs;
    }
    public boolean getSdfs() {
        return this.sdfs;
    }
    public void setSdfs(boolean sdfs) {
        this.sdfs = sdfs;
    }
    public boolean getSdjy() {
        return this.sdjy;
    }
    public void setSdjy(boolean sdjy) {
        this.sdjy = sdjy;
    }
    public boolean getSdcy() {
        return this.sdcy;
    }
    public void setSdcy(boolean sdcy) {
        this.sdcy = sdcy;
    }
    public boolean getSdwd() {
        return this.sdwd;
    }
    public void setSdwd(boolean sdwd) {
        this.sdwd = sdwd;
    }
    public boolean getSdsd() {
        return this.sdsd;
    }
    public void setSdsd(boolean sdsd) {
        this.sdsd = sdsd;
    }
    public boolean getSddqy() {
        return this.sddqy;
    }
    public void setSddqy(boolean sddqy) {
        this.sddqy = sddqy;
    }
    public boolean getSddj1gl() {
        return this.sddj1gl;
    }
    public void setSddj1gl(boolean sddj1gl) {
        this.sddj1gl = sddj1gl;
    }
    public boolean getSddj2gl() {
        return this.sddj2gl;
    }
    public void setSddj2gl(boolean sddj2gl) {
        this.sddj2gl = sddj2gl;
    }
    public boolean getSddj1xl() {
        return this.sddj1xl;
    }
    public void setSddj1xl(boolean sddj1xl) {
        this.sddj1xl = sddj1xl;
    }
    public boolean getSddj2xl() {
        return this.sddj2xl;
    }
    public void setSddj2xl(boolean sddj2xl) {
        this.sddj2xl = sddj2xl;
    }
    public boolean getSdedzs() {
        return this.sdedzs;
    }
    public void setSdedzs(boolean sdedzs) {
        this.sdedzs = sdedzs;
    }
    public boolean getSdsczs() {
        return this.sdsczs;
    }
    public void setSdsczs(boolean sdsczs) {
        this.sdsczs = sdsczs;
    }
    public String getBy1() {
        return this.by1;
    }
    public void setBy1(String by1) {
        this.by1 = by1;
    }
    public String getBy2() {
        return this.by2;
    }
    public void setBy2(String by2) {
        this.by2 = by2;
    }
    public String getBy3() {
        return this.by3;
    }
    public void setBy3(String by3) {
        this.by3 = by3;
    }
    public String getBy4() {
        return this.by4;
    }
    public void setBy4(String by4) {
        this.by4 = by4;
    }
    public String getBy5() {
        return this.by5;
    }
    public void setBy5(String by5) {
        this.by5 = by5;
    }
    public String getBy6() {
        return this.by6;
    }
    public void setBy6(String by6) {
        this.by6 = by6;
    }
    public String getBy7() {
        return this.by7;
    }
    public void setBy7(String by7) {
        this.by7 = by7;
    }
    public String getBy8() {
        return this.by8;
    }
    public void setBy8(String by8) {
        this.by8 = by8;
    }
    public String getBy9() {
        return this.by9;
    }
    public void setBy9(String by9) {
        this.by9 = by9;
    }
    public String getBy10() {
        return this.by10;
    }
    public void setBy10(String by10) {
        this.by10 = by10;
    }
    public String getBy11() {
        return this.by11;
    }
    public void setBy11(String by11) {
        this.by11 = by11;
    }
    public String getBy12() {
        return this.by12;
    }
    public void setBy12(String by12) {
        this.by12 = by12;
    }
    public String getPtgxs() {
        return this.ptgxs;
    }
    public void setPtgxs(String ptgxs) {
        this.ptgxs = ptgxs;
    }

    @Override
public Long getTaskId() {
    return id;
}

    @Override
    public String getTestFunction() {
        return csff;
    }
    @Override
    public String getNumber() {
        return gasePumpNumber;
    }
    @Override
    public boolean getTaskStatue() {
        return _IsCompleteTask;
    }
}
