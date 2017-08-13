package com.liang.service.learnservice;

import com.liang.pojo.MessageObject;
import com.liang.pojo.po.LearnCurrent;
import com.liang.pojo.po.LearnQuestion;

/**
 * Created by liang on 2017/8/13.
 */
public interface LearnQuestionManager {

    public MessageObject createLearnObj(LearnCurrent lc , LearnQuestion lq);

}
