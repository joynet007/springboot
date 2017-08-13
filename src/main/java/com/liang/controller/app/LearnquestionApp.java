package com.liang.controller.app;

/**
 * Created by liang on 2017/8/13.
 */

import com.liang.SystemConfig;
import com.liang.pojo.MessageObject;
import com.liang.pojo.po.Choicequestion;
import com.liang.pojo.po.LearnQuestion;
import com.liang.repository.ChoicequestionRepository;
import com.liang.repository.LearnQuestionRepository;
import com.liang.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by liang on 2017/7/11.
 */
@RestController
@RequestMapping("/app/learnquestion")
public class LearnquestionApp {


    private MessageObject mo ;
    @Autowired
    ChoicequestionRepository choicequestionRepository;
    @Autowired
    LearnQuestionRepository learnQuestionRepository;

//    public long pkid;
//    public long userid;
//    public String questionid;
//    public long createtime;
//    public String subjectid;
//    public String ismistake;
//    public String moniname;

    @RequestMapping(value="/create")
    @ResponseBody
    public MessageObject createlearnquestion(@RequestParam(required = false) long userid ,
                                             @RequestParam(required = false) String questionid ,
                                             @RequestParam(required = false) String ismistake
                                             ){
        mo = new MessageObject();

        Choicequestion cq=null;
        if(StringUtil.isEmpty(questionid)){
            mo.setCode(SystemConfig.mess_failed);
            mo.setMdesc("你学习的题目编号不存在，请从头再来！");
            return mo;
        }

        cq = choicequestionRepository.findChoiceQuestion(questionid);

        if(cq == null){
            mo.setCode(SystemConfig.mess_failed);
            mo.setMdesc("你学习的题目不存在，请重新选择一题！");
            return mo;
        }

        LearnQuestion learnQuestion = learnQuestionRepository.findLearnquestion(userid,questionid);
        if(null == learnQuestion){
            learnQuestion = new LearnQuestion();
            learnQuestion.setCreatetime(System.currentTimeMillis());
            learnQuestion.setIsmistake(ismistake);
            learnQuestion.setMoniname(cq.getMoniname());
            learnQuestion.setQuestionid(questionid);
            learnQuestion.setSubjectid(cq.getSublevel1());
            learnQuestion.setUserid(userid);
        }else{
            learnQuestion.setIsmistake(ismistake);
        }



        learnQuestionRepository.save(learnQuestion);

        return mo;

    }


}
