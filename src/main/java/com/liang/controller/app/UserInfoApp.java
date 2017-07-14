package com.liang.controller.app;

import com.liang.SystemConfig;
import com.liang.pojo.MessageObject;
import com.liang.pojo.po.UserInfo;
import com.liang.repository.UserRepository;
import com.liang.util.GsonUtil;
import com.liang.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Created by liang on 2017/7/11.
 */
@RestController
@RequestMapping("/app/userinfo")
public class UserInfoApp {


    @Autowired
    UserRepository userRepository;

    private MessageObject mo = new MessageObject(SystemConfig.mess_succ,"执行成功");

    @RequestMapping(value="/dologin")
    public MessageObject dologin(@RequestParam(required = false) String usertel,
                                 @RequestParam(required = false) String userpassword,
                                 HttpServletRequest request){

        System.out.println(usertel+"--"+userpassword);
        try {

            if(StringUtil.isEmpty(usertel) || StringUtil.isEmpty(userpassword)){
                mo.setMdesc("手机号，用户密码不能为空！");
                mo.setCode(SystemConfig.mess_succ);
                return mo;
            }

            UserInfo user = userRepository.findUser(usertel,userpassword);
            if(user==null){
                mo.setMdesc("手机号或者用户密码错误");
                mo.setCode(SystemConfig.mess_succ);
                return mo;
            }
            String content = GsonUtil.objTOjson(user);
            mo.setMcontent(content);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return mo;
    }

}
