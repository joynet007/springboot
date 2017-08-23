package com.liang.service.subjectservice.impl;

import com.liang.util.SystemConfig;
import com.liang.pojo.MessageObject;
import com.liang.pojo.po.Choicequestion;
import com.liang.pojo.po.ChoicequestionExplain;
import com.liang.repository.ChoicequestionExplainRepository;
import com.liang.repository.ChoicequestionRepository;
import com.liang.service.subjectservice.ChoiceQuestionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by liang on 2017/7/14.
 */
@Service
public class ChoiceQuestionImpl implements ChoiceQuestionManager {

    @Autowired
    ChoicequestionRepository choicequestionRepository;

    @Autowired
    ChoicequestionExplainRepository choicequestionExplainRepository;

    MessageObject mo;

    /**
     * 启动事物
     * @param cq
     * @param ce
     */
    @Transactional
    @Override
    public MessageObject docreate(Choicequestion cq, ChoicequestionExplain ce) {
        mo = new MessageObject();
        try{
            choicequestionRepository.save(cq);

            choicequestionExplainRepository.save(ce);
        }catch (Exception e){
            mo.setCode(SystemConfig.mess_failed);
            mo.setMdesc("保存失败！");
            e.printStackTrace();
        }
        return mo;

    }

    @Transactional
    @Override
    public MessageObject dodel(Choicequestion cq, ChoicequestionExplain ce) {
        mo = new MessageObject();
        try{
            choicequestionRepository.delete(cq);

            choicequestionExplainRepository.delete(ce);
        }catch (Exception e){
            mo.setCode(SystemConfig.mess_failed);
            mo.setMdesc("删除失败！");
            e.printStackTrace();
        }
        return mo;

    }
}
