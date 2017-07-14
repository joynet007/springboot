package com.liang.service.subjectservice;

import com.liang.pojo.MessageObject;
import com.liang.pojo.po.Choicequestion;
import com.liang.pojo.po.ChoicequestionExplain;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by liang on 2017/7/14.
 */
public interface ChoiceQuestionManager {

    public MessageObject docreate(Choicequestion cq , ChoicequestionExplain ce);

}
