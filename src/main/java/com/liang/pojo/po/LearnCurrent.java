package com.liang.pojo.po;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by liang on 2017/8/13.
 */
@Entity
@Table(name="tb_learncurrent")
public class LearnCurrent {

    @Id
    public long pkid;
    public long userid;
    public long createtime;
    public String subjectid;
    public String moniname;
    public long mcode;

    public long getPkid() {
        return pkid;
    }

    public void setPkid(long pkid) {
        this.pkid = pkid;
    }

    public long getUserid() {
        return userid;
    }

    public void setUserid(long userid) {
        this.userid = userid;
    }

    public long getCreatetime() {
        return createtime;
    }

    public void setCreatetime(long createtime) {
        this.createtime = createtime;
    }

    public long getMcode() {
        return mcode;
    }

    public void setMcode(long mcode) {
        this.mcode = mcode;
    }

    public String getSubjectid() {
        return subjectid;
    }

    public void setSubjectid(String subjectid) {
        this.subjectid = subjectid;
    }

    public String getMoniname() {
        return moniname;
    }

    public void setMoniname(String moniname) {
        this.moniname = moniname;
    }
}
