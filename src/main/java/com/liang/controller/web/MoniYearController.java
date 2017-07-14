package com.liang.controller.web;

import com.liang.SystemConfig;
import com.liang.pojo.po.MoniYear;
import com.liang.pojo.po.Subject;
import com.liang.repository.MoniYearRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * Created by liang on 2017/7/11.
 */

@Controller
@RequestMapping("/web/moniyear")
public class MoniYearController {

    @Autowired
    MoniYearRepository moniYearRepository;

    /**
     * 查看某一个资源
     * @return
     */
    @RequestMapping(value="/viewlist")
    public List<MoniYear> viewlist(){

        List<MoniYear> list =(List<MoniYear>)moniYearRepository.findAll();

        return list;
    }



}
