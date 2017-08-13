package com.liang.pojo.po;

/**
 * Created by liang on 2017/8/11.
 */
public class Missquestion {
    public long pkid;
    public String userid;
    public String questionid;
    public long createtime;

    public long getPkid() {
        return pkid;
    }

    public void setPkid(long pkid) {
        this.pkid = pkid;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getQuestionid() {
        return questionid;
    }

    public void setQuestionid(String questionid) {
        this.questionid = questionid;
    }

    public long getCreatetime() {
        return createtime;
    }

    public void setCreatetime(long createtime) {
        this.createtime = createtime;
    }
}
