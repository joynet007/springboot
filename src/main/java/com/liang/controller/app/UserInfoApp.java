package com.liang.controller.app;

import com.liang.util.SystemConfig;
import com.liang.pojo.MessageObject;
import com.liang.pojo.po.UserInfo;
import com.liang.repository.UserRepository;
import com.liang.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by liang on 2017/7/11.
 */
@RestController
@RequestMapping("/app/userinfo")
public class UserInfoApp {


    @Autowired
    UserRepository userRepository;

    private MessageObject mo = new MessageObject(SystemConfig.mess_succ,"执行成功");

    @RequestMapping(value="/dologin",method = RequestMethod.POST)
    public MessageObject dologin(@RequestParam(required = false) String usertel,
                                 @RequestParam(required = false) String userpassword,
                                 HttpServletRequest request){

        try {

//            System.out.println("---usertel---"+usertel+"---"+userpassword);
            if(StringUtil.isEmpty(usertel) || StringUtil.isEmpty(userpassword)){
                mo.setMdesc("手机号，用户密码不能为空！");
                mo.setCode(SystemConfig.mess_succ);
                return mo;
            }
            userpassword = MD5Util.getMD5Code(userpassword);
            UserInfo user = userRepository.findUser(usertel,userpassword);
            user.setTokenid(TokenManager.getInstance().createToken(user.getUsertel()));
            userRepository.save(user);
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

    /**
     * 创建用户信息
     * @param model
     * @return
     */
    @RequestMapping(value="/doregister", method = RequestMethod.POST)
    @ResponseBody
    public MessageObject doregister(@RequestParam(required = false) String usertel ,
                                        @RequestParam(required = false) String userpassword ,
                                        @RequestParam(required = false) String username ,
                                        Model model){
        UserInfo userInfo =  userRepository.findByUsertel(usertel);
        MessageObject messageObject = new MessageObject(SystemConfig.mess_succ,"执行成功！");
        try {
            if(userInfo == null){
                userInfo = new UserInfo();
                userpassword = MD5Util.getMD5Code(userpassword);
                userInfo.setUserpassword( userpassword );
                userInfo.setUsertel(usertel);
                userInfo.setCreatetime(System.currentTimeMillis());
                userInfo.setMstatus(SystemConfig.mstatus_normal);
                userInfo.setUsername(username);
                userInfo.setMsex(SystemConfig.sex_male);
                userRepository.save(userInfo);
            }else{
                messageObject.setCode(SystemConfig.mess_failed);
                messageObject.setMdesc("您已经完成注册,请直接去登录！");
                return messageObject;
            }
            return messageObject;
        } catch (Exception e) {
            e.printStackTrace();
            messageObject.setCode(SystemConfig.mess_failed);
            messageObject.setMdesc("创建失败，执行异常！");
            return messageObject;
        }
    }

}
