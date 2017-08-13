package com.liang.service.subjectservice;

import com.liang.pojo.MessageObject;
import com.liang.pojo.po.Choicequestion;
import com.liang.pojo.po.ChoicequestionExplain;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by liang on 2017/7/14.
 */
public interface ChoiceQuestionManager {

    /**
     * 创建对象
     * @param cq
     * @param ce
     * @return
     */
    public MessageObject docreate(Choicequestion cq , ChoicequestionExplain ce);

    /**
     * 删除对象
     * @param cq
     * @param ce
     * @return
     */
    public MessageObject dodel(Choicequestion cq , ChoicequestionExplain ce);




}
