package com.liang.controller.app;

import com.liang.SystemConfig;
import com.liang.pojo.MessageObject;
import com.liang.pojo.po.Choicequestion;
import com.liang.pojo.po.ChoicequestionExplain;
import com.liang.pojo.po.UserInfo;
import com.liang.repository.ChoicequestionExplainRepository;
import com.liang.repository.ChoicequestionRepository;
import com.liang.repository.LearnCurrentRepository;
import com.liang.repository.UserRepository;
import com.liang.util.GsonUtil;
import com.liang.util.StringUtil;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by liang on 2017/7/11.
 */
@RestController
@RequestMapping("/app/question")
public class QuestionApp {
    private static Logger logger = LogManager.getLogger("HelloLog4j");
    @Autowired
    ChoicequestionRepository choicequestionRepository;

    @Autowired
    ChoicequestionExplainRepository choicequestionExplainRepository;

    @Autowired
    LearnCurrentRepository learnCurrentRepository;

    @Autowired
    UserRepository userRepository;

    MessageObject mo;

    @RequestMapping(value="/viewlist")
    @ResponseBody
    public MessageObject viewlist(){
        mo = new MessageObject();
        List<Choicequestion> list= (List<Choicequestion>) choicequestionRepository.findAll();
        if(list!=null){
            String content = GsonUtil.objTOjson(list);
//            System.out.println("****choicequestion***"+content);
            mo.setMcontent(content);
        }
        return mo;
    }

    /**
     *
     * @param subjectid
     * @param mcode 这个参数需要
     * @return
     */
    @RequestMapping(value="/nextquestion")
    @ResponseBody
    public MessageObject nextquestion( @RequestParam(required = false) String subjectid ,
                                       @RequestParam(required = false) long mcode ){
//        System.out.println(subjectid+"--"+mindex);
        mo = new MessageObject();
        //try catch 如果没有数据查询为null 报异常，如果是异常暂定为无数据
        long maxcode = 0;
        try{
            maxcode = choicequestionRepository.findSubjectMaxChoicequestion(subjectid);
        }catch (Exception ex){
            mo.setCode(SystemConfig.mess_failed);
            mo.setMdesc("暂时没有题目！");
            return mo;
        }

        if(maxcode < mcode ){
            mo.setCode(SystemConfig.mess_failed);
            mo.setMdesc("已经是最后一道题目！");
            return mo;
        }
        Choicequestion question= (Choicequestion) choicequestionRepository.findSubjectChoiceQuestion(subjectid,mcode);
        if(question!=null){
            String content = GsonUtil.objTOjson(question);
            mo.setMcontent(content);
        }else{
            mo.setCode(SystemConfig.mess_failed);
            mo.setMdesc("查不到问题！");
        }
        return mo;
    }

    /**
     * @param questionid
     * @return
     */
    @RequestMapping(value="/findquestionexplain")
    @ResponseBody
    public MessageObject findquestionexplain( @RequestParam(required = false) String questionid ){
//        System.out.println(questionid+"--");
        mo = new MessageObject();
        ChoicequestionExplain questionExplain= (ChoicequestionExplain) choicequestionExplainRepository.findChoiceQuestionExplain(questionid);
        if(questionExplain!=null){
            String content = GsonUtil.objTOjson(questionExplain);
//            System.out.println("****questionExplain***"+questionExplain);
            mo.setMcontent(content);
        }else{
            mo.setCode(SystemConfig.mess_failed);
            mo.setMcontent("查不到问题详解");
        }
        return mo;
    }


    /**
     * @param subjectid
     * @param moniname
     * @param userid
     * @return
     * 第一次从年份的列表进入页面。
     */

    @RequestMapping(value="/firstmoniquestion")
    @ResponseBody
    public MessageObject firstmoniquestion( @RequestParam(required = false) String subjectid ,
                                           @RequestParam(required = false) String moniname,
                                           @RequestParam(required = false) long userid
    ){
        mo = new MessageObject();
        UserInfo userInfo =  userRepository.findByUserid(userid);
        if(userInfo == null){
            mo.setCode(SystemConfig.mess_failed);
            mo.setMdesc("用户失效，校验失败！");
            return mo;
        }

        //根据 科目 、 模拟年份 查询最大的编码比较 是否是最后一题
        long maxmonicode = 0;
        //try catch 如果没有数据查询为null 报异常，如果是异常暂定为无数据
        try{
            maxmonicode =  choicequestionRepository.findMaxMoniChoicequestion(subjectid,moniname);
        }catch (Exception ex){
            mo.setCode(SystemConfig.mess_failed);
            mo.setMdesc("暂时没有题目！");
            return mo;
        }

        long currtyCode=0; //当前学习的code
        try{
            currtyCode = learnCurrentRepository.findLearnCurrentMcodeByMoniname(userid,subjectid,moniname);
        }catch (Exception ex){
            //用户暂时还没有学习
            currtyCode =0;
        }

        if(maxmonicode <= currtyCode ){
            mo.setCode(SystemConfig.mess_failed);
            mo.setMdesc("恭喜您，你很厉害，已经学习完了整个练习！");
            return mo;
        }

        Choicequestion question= (Choicequestion) choicequestionRepository.findMoniChoiceQuestion(subjectid,moniname,++currtyCode);
        if(question!=null){
            String content = GsonUtil.objTOjson(question);
            System.out.println("***="+content);
            mo.setMcontent(content);
        }else{
            mo.setCode(SystemConfig.mess_failed);
            mo.setMdesc("查不到你所要的题目！");
        }
        return mo;
    }

    /**
     *  进入到下一题
     * @param questionid
     * @param userid
     * @return
     */
    @RequestMapping(value="/nextmoniquestion")
    @ResponseBody
    public MessageObject nextmoniquestion( @RequestParam(required = false) String questionid ,
                                           @RequestParam(required = false) long userid
    ){
        mo = new MessageObject();
        UserInfo userInfo =  userRepository.findByUserid(userid);
        if(userInfo == null){
            mo.setCode(SystemConfig.mess_failed);
            mo.setMdesc("用户失效，校验失败！");
            return mo;
        }

        //根据 科目 、 模拟年份 查询最大的编码比较 是否是最后一题
        long maxmonicode = 0;
        Choicequestion question= (Choicequestion) choicequestionRepository.findChoiceQuestion(questionid);
        if(question!=null){
            //try catch 如果没有数据查询为null 报异常，如果是异常暂定为无数据
            try{
                maxmonicode =  choicequestionRepository.findMaxMoniChoicequestion(question.getSublevel1(),question.getMoniname());
            }catch (Exception ex){
                maxmonicode=0;
                mo.setCode(SystemConfig.mess_failed);
                mo.setMdesc("暂时没有题目！");
                return mo;
            }

            if(question.getMcode() >= maxmonicode){
                mo.setCode(SystemConfig.mess_failed);
                mo.setMdesc("已经是最后一题了！");
                return mo;
            }

            long currtyCode=0; //当前学习的code
            try{
                currtyCode = learnCurrentRepository.findLearnCurrentMcodeByMoniname(userid,question.getSublevel1(),question.getMoniname());
            }catch (Exception ex){
                //用户暂时还没有学习
                currtyCode =0;
            }

            if(question.getMcode() > currtyCode ){
                mo.setCode(SystemConfig.mess_failed);
                mo.setMdesc("请您选择一个答案后，在来点我！！");
                return mo;
            }else if(question.getMcode() <= currtyCode){
                question= (Choicequestion) choicequestionRepository.findMoniChoiceQuestion(question.getSublevel1(),question.getMoniname(),(question.getMcode()+1));

            }
        }else{
            mo.setCode(SystemConfig.mess_failed);
            mo.setMdesc("当前题目不存在！退出重来！");
            return mo;
        }
        if(question!=null){
            String content = GsonUtil.objTOjson(question);
            System.out.println("***="+content);
            mo.setMcontent(content);
        }else{
            mo.setCode(SystemConfig.mess_failed);
            mo.setMdesc("查不到你所要的题目！");
        }
        return mo;
    }

    /**
     * 上一题
     * @param questionid
     * @param userid
     * @return
     */
    @RequestMapping(value="/upmoniquestion")
    @ResponseBody
    public MessageObject upmoniquestion( @RequestParam(required = false) String questionid ,
                                           @RequestParam(required = false) long userid
    ){
        mo = new MessageObject();
        UserInfo userInfo =  userRepository.findByUserid(userid);
        if(userInfo == null){
            mo.setCode(SystemConfig.mess_failed);
            mo.setMdesc("用户失效，校验失败！");
            return mo;
        }

        Choicequestion question= (Choicequestion) choicequestionRepository.findChoiceQuestion(questionid);
        if(question!=null){
            if(question.getMcode() == 1){
                mo.setCode(SystemConfig.mess_failed);
                mo.setMdesc("已经是第一题了！");
                return mo;
            }else if(question.getMcode()>1){
                long kmcode = question.getMcode()-1;
                System.out.println(kmcode+"--"+question.getSublevel1()+"--"+question.getMoniname()+"--"+question.getMcode());
                question = (Choicequestion) choicequestionRepository.findMoniChoiceQuestion(question.getSublevel1(),question.getMoniname(),(question.getMcode()-1));
            }else{
                mo.setCode(SystemConfig.mess_failed);
                mo.setMdesc("题目的mcode不能小于1");
                return mo;
            }
        }else{
            mo.setCode(SystemConfig.mess_failed);
            mo.setMdesc("当前题目不存在！退出重来！");
            return mo;
        }
        if(question!=null){
            String content = GsonUtil.objTOjson(question);
            System.out.println("***="+content);
            mo.setMcontent(content);
        }else{
            mo.setCode(SystemConfig.mess_failed);
            mo.setMdesc("查不到问题！");
        }
        return mo;
    }

}
