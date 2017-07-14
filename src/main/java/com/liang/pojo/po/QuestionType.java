package com.liang.pojo.po;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by liang on 2017/6/9.
 * 题目的类型
 *
 * 章节类型
 * 随机类型
 * 历年真题
 */


@Entity
@Table(name="tb_question_type")
public class QuestionType {
    @Id
    public long questiontypeid;
    public String questiontypename;

    public long getQuestiontypeid() {
        return questiontypeid;
    }

    public void setQuestiontypeid(long questiontypeid) {
        this.questiontypeid = questiontypeid;
    }

    public String getQuestiontypename() {
        return questiontypename;
    }

    public void setQuestiontypename(String questiontypename) {
        this.questiontypename = questiontypename;
    }
}
