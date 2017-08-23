package com.liang.service.learnservice.impl;

import com.liang.util.SystemConfig;
import com.liang.pojo.MessageObject;
import com.liang.pojo.po.LearnCurrent;
import com.liang.pojo.po.LearnQuestion;
import com.liang.repository.LearnCurrentRepository;
import com.liang.repository.LearnQuestionRepository;
import com.liang.service.learnservice.LearnQuestionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by liang on 2017/8/13.
 */
@Service
public class LearnQuestionImpl implements LearnQuestionManager {

    @Autowired
    LearnQuestionRepository learnQuestionRepository;
    @Autowired
    LearnCurrentRepository learnCurrentRepository;

    MessageObject mo;
    @Transactional
    public MessageObject createLearnObj(LearnCurrent lc , LearnQuestion lq){

        mo = new MessageObject();
        try{
            learnQuestionRepository.save(lq);
            learnCurrentRepository.save(lc);
        }catch (Exception e){
            mo.setCode(SystemConfig.mess_failed);
            mo.setMdesc("保存失败！");
            e.printStackTrace();
        }
        return mo;

    }

}
