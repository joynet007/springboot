package com.liang.pojo.po;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by liang on 2017/6/9.
 *
 * 选择题
 */

@Entity
@Table(name="tb_choicequestion")
public class Choicequestion {

    @Id
    public String questionid;
    public String name;
    public String subleve1;
    public String subleve2;
    public String subleve3;
    public String answera;
    public String answerb;
    public String answerc;
    public String answerd;
    public String answere;
    public String realanswer;
    public long ctime;

    public String getQuestionid() {
        return questionid;
    }

    public void setQuestionid(String questionid) {
        this.questionid = questionid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSubleve1() {
        return subleve1;
    }

    public void setSubleve1(String subleve1) {
        this.subleve1 = subleve1;
    }

    public String getSubleve2() {
        return subleve2;
    }

    public void setSubleve2(String subleve2) {
        this.subleve2 = subleve2;
    }

    public String getSubleve3() {
        return subleve3;
    }

    public void setSubleve3(String subleve3) {
        this.subleve3 = subleve3;
    }

    public String getAnswera() {
        return answera;
    }

    public void setAnswera(String answera) {
        this.answera = answera;
    }

    public String getAnswerb() {
        return answerb;
    }

    public void setAnswerb(String answerb) {
        this.answerb = answerb;
    }

    public String getAnswerc() {
        return answerc;
    }

    public void setAnswerc(String answerc) {
        this.answerc = answerc;
    }

    public String getAnswerd() {
        return answerd;
    }

    public void setAnswerd(String answerd) {
        this.answerd = answerd;
    }

    public String getAnswere() {
        return answere;
    }

    public void setAnswere(String answere) {
        this.answere = answere;
    }

    public long getCtime() {
        return ctime;
    }

    public void setCtime(long ctime) {
        this.ctime = ctime;
    }

    public String getRealanswer() {
        return realanswer;
    }

    public void setRealanswer(String realanswer) {
        this.realanswer = realanswer;
    }
}
