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
    public String questiontype;
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

    public String getQuestiontype() {
        return questiontype;
    }

    public void setQuestiontype(String questiontype) {
        this.questiontype = questiontype;
    }

    public long getMcode() {
        return mcode;
    }

    public void setMcode(long mcode) {
        this.mcode = mcode;
    }
}
