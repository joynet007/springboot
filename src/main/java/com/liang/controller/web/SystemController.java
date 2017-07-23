package com.liang.controller.web;

import com.liang.SystemConfig;
import com.liang.pojo.po.UserInfo;
import com.liang.repository.UserRepository;
import com.liang.util.MD5Util;
import com.liang.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Created by 袁亮
 * @data 2017年7月10日
 *
 */
@Controller
@RequestMapping(value="/sys")
public class SystemController {
    @Autowired
    UserRepository userRepository;

    @RequestMapping(value="/dologin")
    public String dologin(@RequestParam(required = false) String usertel ,
                          @RequestParam(required = false) String userpassword ,
                          Model model,HttpServletRequest request){
        try {
            HttpSession session = request.getSession();
            if(session.getAttribute(SystemConfig.Session_key) != null){
                return "/sys/main";
            }
            if(StringUtil.isEmpty(usertel) || StringUtil.isEmpty(userpassword))
            {
                return "/index";
            }
            UserInfo user =  userRepository.findByUsertel(usertel);
            if(null == user)
            {
                return "/index";
            }
            userpassword = MD5Util.getMD5Code(userpassword);
            user = userRepository.findUser(usertel,userpassword);
            if(user==null){
                return "/index";
            }
            session.setAttribute(SystemConfig.Session_key, user);
            return "/sys/main";

        } catch (Exception e) {
            e.printStackTrace();
            return "/index";
        }
    }



}