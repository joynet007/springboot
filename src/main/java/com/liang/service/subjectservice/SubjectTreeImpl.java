package com.liang.service.subjectservice;

import com.liang.util.SystemConfig;
import com.liang.pojo.po.Subject;
import com.liang.repository.SubjectRepository;
import com.liang.util.GsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by liang on 2017/7/12.
 */

@Service
public class SubjectTreeImpl {


    @Autowired
    private SubjectRepository subjectRepository;

    public static List<Subject> all_list;
    public static StringBuffer trees_json;

    public static final String [] ziduan = {"subjectid","subjectname"};

    /**
     * 创建一个单例
     */
    public static SubjectTreeImpl instance;
    public static SubjectTreeImpl getInstance(){
        if(null == instance){
            return new SubjectTreeImpl();
        }
        return instance;
    }

    /**
     * 项目启动的时候初始化次方法
     */
    @PostConstruct
    public void init(){
        System.out.println("---初始化---");
        trees_json = new StringBuffer();

        all_list = (List<Subject>) subjectRepository.findAll();


        if(all_list!=null && all_list.size()>0){
            init_tree_json(all_list);
        }

        System.out.println("--"+trees_json);
    }


    /**
     * 将所有的对象列表转化为 json 字符串
     * @param all_list
     */

    public void init_tree_json(List<Subject> all_list ){
        trees_json.append("[{");
        List<Subject> first_childs = findChilds(SystemConfig.root, all_list);
        if(first_childs!=null && first_childs.size()>0)
        {
            int i = 1;
            for(Subject ao : first_childs)
            {
                trees_json.append(GsonUtil.jsonString(ao, ziduan));

                List<Subject> childs = findChilds(ao.getSubjectid(), all_list);

                if(childs!=null && childs.size()>0)
                {
//                    trees_json.append(",\"state\":\"closed\",\"children\":[{");
                    trees_json.append(",\"state\":\"open\",\"children\":[{");

                    findNextChilds(ao, childs,all_list);
                }

                if(i<first_childs.size()){
                    trees_json.append("},{");
                }
                if(i==first_childs.size()){
                    trees_json.append("}]");
                }

                i++;
            }
        }

//		System.out.println("树形结构：：=="+trees_json.toString());

    }


    /**
     *
     * @param parentObj
     * @param all_list
     */
    public void findNextChilds(Subject parentObj , List<Subject> childs , List<Subject> all_list ){

        int i =1;
        for(Subject ao :childs){

            trees_json.append(GsonUtil.jsonString(ao, ziduan));

            List<Subject> next_childs = findChilds(ao.getSubjectid(), all_list);

            if(next_childs!=null && next_childs.size()>0){
                trees_json.append(",\"state\":\"closed\",\"children\":[{");
                findNextChilds(ao, next_childs , all_list);
            }

            if(i<childs.size()){
                trees_json.append("},{");
            }else{
                trees_json.append("}]");
            }

            i++;
        }
//		AuResourceTrees_json.append("}]");
    }


    /**
     * 根据parentID 查找 下级节点 返回列表
     * @param parentID
     * @param all_list
     * @return
     */
    public List<Subject> findChilds(String parentID , List<Subject> all_list)
    {
        List<Subject> childs = new ArrayList<Subject>();

        for(Subject ao : all_list)
        {
            if(parentID.equals(ao.getParentid())){
                childs.add(ao);
            }
        }
        return childs;
    }



    public  List<Subject> getTrees() {
        return all_list;
    }



    public  StringBuffer getTrees_json() {
        return trees_json;
    }





}
