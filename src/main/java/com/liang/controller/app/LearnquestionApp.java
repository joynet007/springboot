package com.liang.controller.app;

/**
 * Created by liang on 2017/8/13.
 */

import com.liang.SystemConfig;
import com.liang.pojo.MessageObject;
import com.liang.pojo.po.Choicequestion;
import com.liang.pojo.po.LearnCurrent;
import com.liang.pojo.po.LearnQuestion;
import com.liang.pojo.po.UserInfo;
import com.liang.repository.ChoicequestionRepository;
import com.liang.repository.LearnCurrentRepository;
import com.liang.repository.LearnQuestionRepository;
import com.liang.repository.UserRepository;
import com.liang.service.learnservice.LearnQuestionManager;
import com.liang.util.GsonUtil;
import com.liang.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

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

    @Autowired
    LearnCurrentRepository learnCurrentRepository;

    @Autowired
    LearnQuestionManager learnQuestionManager;

    @Autowired
    UserRepository userRepository;


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

        /**
         * 创建当前模拟年份学习的对象，如果对象已经存在则修改mcode
         */
        LearnCurrent learnCurrent = learnCurrentRepository.findLearnCurrentObjByMoniname(userid,cq.getSublevel1(),cq.getMoniname());
        if(learnCurrent == null){
            System.out.println("---查出来的对象为空!!!!---");
            learnCurrent = new LearnCurrent();
            learnCurrent.setUserid(userid);
            learnCurrent.setSubjectid(cq.getSublevel1());
            learnCurrent.setMoniname(cq.getMoniname());
            learnCurrent.setCreatetime(System.currentTimeMillis());
            learnCurrent.setMcode(cq.getMcode());
        }else{
            //确保每次学习顺序正确
            if(cq.getMcode() > learnCurrent.getMcode()){
                learnCurrent.setMcode(cq.getMcode());
            }

        }

       mo =  learnQuestionManager.createLearnObj(learnCurrent,learnQuestion);
//        learnQuestionRepository.save(learnQuestion);

        return mo;

    }

    @RequestMapping(value="/learncount")
    @ResponseBody
    public MessageObject learncount(@RequestParam(required = false) long userid ,
                                    @RequestParam(required = false) String subjectid ){
        mo = new MessageObject();
        UserInfo userInfo =  userRepository.findByUserid(userid);
        if(userInfo == null)
        {
            mo.setCode(SystemConfig.mess_failed);
            mo.setMdesc("用户失效，校验失败！");
            return mo;
        }

        HashMap<String,String> map = new HashMap<String,String>();

        long allquestion =0;//科目下面的总条数
        long learnall = 0 ;//已经学习总条数
        long mistakeno = 0;//已经学会
        long mistakeyes=0;//我的错题

        try{
            allquestion = choicequestionRepository.findChoicequestionCount(subjectid);
        }catch (Exception ex){
            System.out.println(this.getClass().getName()+"--没有题目数据--");
        }

        try{
            mistakeno = learnQuestionRepository.findcountismistakeLearnquestion(userid,subjectid,SystemConfig.mistake_no);
        }catch (Exception ex){
            System.out.println(this.getClass().getName()+"--没有对的题目数据--");
        }

        try{
            mistakeyes = learnQuestionRepository.findcountismistakeLearnquestion(userid,subjectid,SystemConfig.mistake_yes);
        }catch (Exception ex){
            System.out.println(this.getClass().getName()+"--没有错误题目数据--");
        }

        learnall = mistakeno+mistakeyes;

        map.put("allquestion",allquestion+"");
        map.put("mistakeno",mistakeno+"");
        map.put("mistakeyes",mistakeyes+"");
        map.put("learnall",learnall+"");

        String content = GsonUtil.objTOjson(map);
        mo.setMcontent(content);
        return mo;

    }

}
