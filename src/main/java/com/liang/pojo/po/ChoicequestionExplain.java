package com.liang.pojo.po;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by liang on 2017/6/9.
 *
 * 选择题的解析
 */

@Entity
@Table(name="tb_choicequestion_explain")
public class ChoicequestionExplain {
    @Id
    public String questionid;
    public String mexplain;

    public String getQuestionid() {
        return questionid;
    }

    public void setQuestionid(String questionid) {
        this.questionid = questionid;
    }

    public String getMexplain() {
        return mexplain;
    }

    public void setMexplain(String mexplain) {
        this.mexplain = mexplain;
    }
}
