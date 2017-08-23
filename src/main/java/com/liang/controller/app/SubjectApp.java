package com.liang.controller.app;

import com.liang.util.SystemConfig;
import com.liang.pojo.MessageObject;
import com.liang.pojo.po.Subject;
import com.liang.repository.SubjectRepository;
import com.liang.util.GsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by liang on 2017/7/11.
 */
@RestController
@RequestMapping("/app/subject")
public class SubjectApp {

    @Autowired
    SubjectRepository subjectRepository;


    @RequestMapping(value="/viewlist/{mlevel}")
    public MessageObject viewlist(@PathVariable int mlevel){
        MessageObject mo = new MessageObject(SystemConfig.mess_succ,"登录成功");
        List<Subject> list = (List<Subject>) subjectRepository.findSubjectByLevel(mlevel);
        if(list != null ){
            String content = GsonUtil.objTOjson(list);
            System.out.println("****content***"+content);
            mo.setMcontent(content);
        }
        return mo;
    }

    /**
     * 根据parentid 查找下面的所有 科目
     *
     * @param parentid
     * @return
     */
    @RequestMapping(value="/subjectlist/{parentid}")
    public MessageObject viewlist(@PathVariable String parentid){
        MessageObject mo = new MessageObject(SystemConfig.mess_succ,"登录成功");
        System.out.println(parentid+"---kkkkk---");
        List<Subject> list = (List<Subject>) subjectRepository.findSubjectBypid(parentid);
        if(list != null ){
            String content = GsonUtil.objTOjson(list);
            System.out.println("****content111***"+content);
            mo.setMcontent(content);
        }
        return mo;
    }


    /**
     * 查询具体的科目
     * @param subjectid
     * @return
     */
    @RequestMapping(value="/findsubject/{subjectid}")
    public MessageObject findsubject(@PathVariable String subjectid){
        MessageObject mo = new MessageObject(SystemConfig.mess_succ,"登录成功");
        Subject subject =subjectRepository.findSubject(subjectid);
        if(subject != null ){
            String content = GsonUtil.objTOjson(subject);
            System.out.println("content:::"+content);
            mo.setMcontent(content);
        }
        return mo;
    }


}
