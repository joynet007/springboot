package com.liang.controller.app;

import com.liang.util.SystemConfig;
import com.liang.pojo.MessageObject;
import com.liang.pojo.po.MoniYear;
import com.liang.repository.MoniYearRepository;
import com.liang.util.GsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by liang on 2017/7/11.
 */
@RestController
@RequestMapping("/app/moniyear")
public class MoniYearApp {
    @Autowired
    MoniYearRepository moniYearRepository;

    /**
     * 查看某一个资源
     * @return
     */
    @RequestMapping(value="/viewlist")
    @ResponseBody
    public MessageObject viewlist(){
        MessageObject mo = new MessageObject(SystemConfig.mess_succ,"登录成功");
        System.out.println("--查看历年的模拟题列表--");
        List<MoniYear> list =(List<MoniYear>)moniYearRepository.findAll();
        System.out.println("--查看历年的模拟题列表--"+list.size());
        if(list!=null){
            String content = GsonUtil.objTOjson(list);
            System.out.println("****content111***"+content);
            mo.setMcontent(content);
        }

        return mo;
    }


}
