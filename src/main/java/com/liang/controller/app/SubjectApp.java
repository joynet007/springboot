package com.liang.controller.app;

import com.liang.SystemConfig;
import com.liang.pojo.MessageObject;
import com.liang.pojo.po.Subject;
import com.liang.repository.SubjectRepository;
import com.liang.util.GsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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

    private MessageObject mo = new MessageObject(SystemConfig.mess_succ,"登录成功");

    @RequestMapping(value="/viewlist")
    public MessageObject viewlist(){
        List<Subject> list = (List<Subject>) subjectRepository.findAll();
        if(list != null ){
            String content = GsonUtil.objTOjson(list);
            System.out.println("****content***"+content);
            mo.setMcontent(content);
        }
        return mo;
    }



}
