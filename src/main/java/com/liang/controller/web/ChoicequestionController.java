package com.liang.controller.web;

import com.liang.util.SystemConfig;
import com.liang.pojo.MessageObject;
import com.liang.pojo.po.Choicequestion;
import com.liang.pojo.po.ChoicequestionExplain;
import com.liang.pojo.po.Subject;
import com.liang.repository.ChoicequestionExplainRepository;
import com.liang.repository.ChoicequestionRepository;
import com.liang.repository.SubjectRepository;
import com.liang.service.subjectservice.ChoiceQuestionManager;
import com.liang.util.GsonUtil;
import com.liang.util.IDmanager;
import com.liang.util.StringUtil;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by liang on 2017/7/11.
 */
@Controller
@RequestMapping("/web/choicequestion")
public class ChoicequestionController {

    private static Logger logger = LogManager.getLogger("HelloLog4j");

    @Autowired
    ChoicequestionRepository choicequestionRepository;

    @Autowired
    ChoicequestionExplainRepository choicequestionExplainRepository;

    @Autowired
    ChoiceQuestionManager choiceQuestionManager;

    @Autowired
    SubjectRepository subjectRepository;

    MessageObject mo;

    @RequestMapping(value="/startpage")
    public String startpage(){
        return "choicequestion/choicequestion-list";
    }


    @RequestMapping(value="/viewlist")
    @ResponseBody
    public List<Choicequestion> viewlist(){
        List<Choicequestion> list= (List<Choicequestion>) choicequestionRepository.findallobjes();
        return list;
    }


    @RequestMapping(value="/add")
    public String add(Model model){
        List<Subject> subjects = (List<Subject>)subjectRepository.findSubjectByLevel(1);
        if(subjects!=null){
            model.addAttribute("subjects",subjects);
        }
        return "choicequestion/choicequestion-add";
    }

    @RequestMapping(value="/save" , method = RequestMethod.POST)
    @ResponseBody
    public MessageObject save(@RequestParam(required = false) String choicequestionname ,
                              @RequestParam(required = false) String answera ,
                              @RequestParam(required = false) String answerb ,
                              @RequestParam(required = false) String answerc ,
                              @RequestParam(required = false) String answerd ,
                              @RequestParam(required = false) String sublevel1 ,
                              @RequestParam(required = false) String sublevel2 ,
                              @RequestParam(required = false) String sublevel3 ,
                              @RequestParam(required = false) String realanswer ,
                              @RequestParam(required = false) String moniname ,
                              @RequestParam(required = false) String explain ,
                              Model model){

        mo = new MessageObject();

        String questionID = IDmanager.createID();
        Choicequestion cq = new Choicequestion();
        cq.setQuestionid(questionID);
        cq.setName(choicequestionname.trim());
        cq.setCtime(System.currentTimeMillis());
        cq.setAnswera(answera.trim());
        cq.setAnswerb(answerb.trim());
        cq.setAnswerc(answerc.trim());
        cq.setAnswerd(answerd.trim());
//        int mindex = choicequestionRepository.findmaxIndex(sublevel3);
//        cq.setMindex(mindex+1);

        cq.setSublevel1(sublevel1);
        cq.setSublevel2(sublevel2);
        cq.setSublevel3(sublevel3);

        //如果用户选择了，年份那么就是查找年份试卷最大的一个题目
        long maxmcode=0 ;
        try {
            if(!StringUtil.isEmpty(moniname)){
                maxmcode = choicequestionRepository.findMaxMoniChoicequestion(sublevel1,moniname);
            }else{
                //如果用户没有选择，模拟年份，则必须选择章节
                maxmcode = choicequestionRepository.findSubjectMaxChoicequestion(sublevel3);
            }
        }catch (Exception ex){
//            ex.printStackTrace();
        }


        cq.setMoniname(moniname);
        cq.setMcode((maxmcode+1));
        cq.setRealanswer(realanswer);


        System.out.println("--保存 cq ："+ GsonUtil.objTOjson(cq));

        ChoicequestionExplain ce = new ChoicequestionExplain();

        ce.setMexplain(explain.trim());
        ce.setQuestionid(questionID);

        try{
            mo = choiceQuestionManager.docreate(cq,ce);
        }catch (Exception ex){
            mo.setCode(SystemConfig.mess_failed);
            mo.setMdesc("保存失败111！");
            logger.info(ex.getMessage());
//            ex.printStackTrace();
        }
        return mo;
    }

    @RequestMapping(value="/del/{choicequestionid}")
    @ResponseBody
    public MessageObject del(@PathVariable String choicequestionid){
        mo = new MessageObject();
        if(StringUtil.isEmpty(choicequestionid)){
            mo.setCode(SystemConfig.mess_failed);
            mo.setMdesc("要删除的选择题编码不能为空！");
            return mo;
        }

        Choicequestion cq = choicequestionRepository.findChoiceQuestion(choicequestionid);
        if(cq == null){
            mo.setCode(SystemConfig.mess_failed);
            mo.setMdesc("要删除的对象不存在！");
            return mo;
        }
        ChoicequestionExplain ce = choicequestionExplainRepository.findChoiceQuestionExplain(choicequestionid);

        choiceQuestionManager.dodel(cq,ce);
//        choicequestionRepository.delete(cq);

        return mo;
    }

    @RequestMapping(value="/view")
    public String view(){
        return "question/add";
    }
}
