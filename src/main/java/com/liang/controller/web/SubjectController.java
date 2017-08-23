package com.liang.controller.web;

import com.liang.util.SystemConfig;
import com.liang.pojo.MessageObject;
import com.liang.pojo.po.Subject;
import com.liang.repository.SubjectRepository;
import com.liang.service.subjectservice.SubjectTreeImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by liang on 2017/7/11.
 */
@Controller
@RequestMapping("/web/subject")
public class SubjectController {

    @Autowired
    SubjectRepository subjectRepository;
    @Autowired
    SubjectTreeImpl subjectTree;

    private MessageObject mo;

    /**
     * 初始的页面
     * @return
     */
    @RequestMapping(value="/subject-trees")
    @ResponseBody
    public String subject_tree(){
        System.out.println("controller:"+subjectTree.getTrees_json().toString());
        return subjectTree.getTrees_json().toString();
    }

    @RequestMapping(value="/startpage")
    public String startpage(){
        return "subject/subject-main";
    }

    /**
     * add 的页面
     * @return
     */
    @RequestMapping(value="/add/{parentid}")
    public String add(@PathVariable String parentid,Model model ){
        Subject parent = subjectRepository.findSubject(parentid);

        if(parent != null){
            model.addAttribute("parent",parent);
        }
        return "subject/subject-add";
    }

    @RequestMapping(value="/delete/{subjectid}")
    @ResponseBody
    public MessageObject del(@PathVariable String subjectid){
        mo = new MessageObject();
        Subject subject = subjectRepository.findSubject(subjectid);
        if(null != subject){
            subjectRepository.delete(subject);
            subjectTree.init();
        }else{
            mo.setCode(SystemConfig.mess_failed);
            mo.setMdesc("对象不存在！");
        }
        return mo;
    }

    /**
     * 查看某一个资源
     * @param model
     * @param subjectid
     * @return
     */
    @RequestMapping(value="/subject-view/{subjectid}")
    public String subject_view(Model model , @PathVariable String subjectid){
        Subject subject = subjectRepository.findSubject(subjectid);
        if(subject != null)
        {
            Subject parent =new Subject();
            if(SystemConfig.root.equals(subject.getParentid()))
            {
                parent.setParentid("root");
                parent.setSubjectid("root");
                parent.setSubjectname("根目录");
            }else{
                parent = subjectRepository.findSubject(subject.getParentid());
            }
            model.addAttribute("parent",parent);
        }

        model.addAttribute("subject",subject);
        return "subject/subject-view";
    }


    /**
     * 创建对象
     * @param subjectid
     * @param subjectname
     * @param parentid
     * @param mstatus
     * @param model
     * @return
     */
    @RequestMapping(value="/save" , method = RequestMethod.POST)
    @ResponseBody
    public MessageObject save(
                    @RequestParam(required = false) String subjectid ,
                    @RequestParam(required = false) String subjectname ,
                    @RequestParam(required = false) String parentid ,
                    @RequestParam(required = false) String mstatus ,
                    Model model){
        mo = new MessageObject(SystemConfig.mess_succ,"保存成功");

        Subject sub = subjectRepository.findSubject(parentid+"_"+subjectid);
        Subject parent = subjectRepository.findSubject(parentid);
        if(parent == null){
            mo.setCode(SystemConfig.mess_failed);
            mo.setMdesc("父类科目不能为空！");
            return mo;
        }
        if(sub != null){
            mo.setCode(SystemConfig.mess_failed);
            mo.setMdesc("科目编号已经存在！");
            return mo;
        }

        try{
            sub = new Subject();
            sub.setSubjectid(parentid+"_"+subjectid);
            sub.setSubjectname(subjectname);
            sub.setCtime(System.currentTimeMillis());
            sub.setMstatus(mstatus);
            sub.setParentid(parentid);
            sub.setMlevel(parent.getMlevel()+1);

            subjectRepository.save(sub);
            subjectTree.init();

        }catch (Exception ex){
            ex.printStackTrace();
            mo.setCode(SystemConfig.mess_failed);
            mo.setMdesc("系统异常！");
            return mo;
        }

        return mo;
    }


    @RequestMapping(value="/viewlist")
    @ResponseBody
    public List<Subject> viewlist(){
        List<Subject> list = (List<Subject>) subjectRepository.findAll();
        return list;
    }


    /**
     * 根据 mlevel 查询列表对象
     * @param mlevel
     * @return
     */
    @RequestMapping(value="/findSubjectByLevel")
    @ResponseBody
    public List<Subject> findSubjectByLevel(@RequestParam int mlevel){
        List<Subject> list = (List<Subject>) subjectRepository.findSubjectByLevel(mlevel);
        return list;
    }


    /**
     * 根据 parentid 查询列表对象
     * @param pid
     * @return
     */
    @RequestMapping(value="/findSubjectBypid/{pid}")
    @ResponseBody
    public List<Subject> findSubjectBypid(@PathVariable String pid){
        List<Subject> list = (List<Subject>) subjectRepository.findSubjectBypid(pid);
        System.out.println(list.size()+"--aa--");
        return list;
    }



}
