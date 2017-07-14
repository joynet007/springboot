package com.liang.controller.web;

import com.liang.pojo.MessageObject;
import com.liang.pojo.po.Choicequestion;
import com.liang.pojo.po.ChoicequestionExplain;
import com.liang.pojo.po.UserInfo;
import com.liang.repository.ChoicequestionExplainRepository;
import com.liang.repository.ChoicequestionRepository;
import com.liang.util.IDmanager;
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

    @Autowired
    ChoicequestionRepository choicequestionRepository;

    @Autowired
    ChoicequestionExplainRepository choicequestionExplainRepository;

    @RequestMapping(value="/startpage")
    public String startpage(){
        return "/choicequestion/choicequestion-list";
    }


    @RequestMapping(value="/viewlist")
    @ResponseBody
    public List<Choicequestion> viewlist(){
        List<Choicequestion> list= (List<Choicequestion>) choicequestionRepository.findAll();
        return list;
    }

    @RequestMapping(value="/add")
    public String add(){
        return "/choicequestion/choicequestion-add";
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
                              @RequestParam(required = false) String explain ,
                              Model model){

        MessageObject mo = new MessageObject();

        String questionID = IDmanager.createID();
        Choicequestion cq = new Choicequestion();
        cq.setQuestionid(questionID);
        cq.setName(choicequestionname);
        cq.setCtime(System.currentTimeMillis());
        cq.setAnswera(answera);
        cq.setAnswerb(answerb);
        cq.setAnswerc(answerc);
        cq.setAnswerd(answerd);

        cq.setSubleve1(sublevel1);
        cq.setSubleve2(sublevel2);
        cq.setSubleve2(sublevel3);

        cq.setRealanswer(realanswer);

        choicequestionRepository.save(cq);


        ChoicequestionExplain ce = new ChoicequestionExplain();

        ce.setMexplain(explain);
        ce.setQuestionid(questionID);

        choicequestionExplainRepository.save(ce);


        return mo;
    }

    @RequestMapping(value="/del/{choicequestionid}")
    public String del(@PathVariable String choicequestionid){

        return "";

    }

    @RequestMapping(value="/view")
    public String view(){
        return "/question/add";
    }
}
