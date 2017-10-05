package com.liang.service.questionservice.impl;

import com.liang.helper.ReaderUrlHelper;
import com.liang.pojo.po.Choicequestion;
import com.liang.pojo.po.ChoicequestionExplain;
import com.liang.repository.ChoicequestionExplainRepository;
import com.liang.repository.ChoicequestionRepository;
import com.liang.repository.SubjectRepository;
import com.liang.service.questionservice.QuestionManager;
import com.liang.service.subjectservice.ChoiceQuestionManager;
import com.liang.util.GsonUtil;
import com.liang.util.IDmanager;
import com.liang.util.StringUtil;
import com.liang.util.SystemConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by liang on 2017/10/2.
 */

@Service
public class QuestionImpl implements QuestionManager {

    @Autowired
    ChoicequestionRepository choicequestionRepository;

    @Autowired
    ChoicequestionExplainRepository choicequestionExplainRepository;

    @Autowired
    ChoiceQuestionManager choiceQuestionManager;

    @Autowired
    SubjectRepository subjectRepository;


//    public static String urlPath = "http://www.cnitpm.com/pm/27850.html";
//    public static String urlPath = "http://www.cnitpm.com/pm/27849.html";
//    public static String urlPath = "http://www.cnitpm.com/pm/27848.html";

    public static String urlPath = "http://www.cnitpm.com/pm/27480.html";

    public static String startStr = "<!--试题开始-->";
    public static String endStr = "<!--试题结束-->";
//系统集成项目管理工程师真题
    public static String moniname = "2014年上半年";
//    public static String kemu = "1001";//系统集成项目管理工程师真题
    public static String kemu = "1002";//信息系统项目管理师


    /**
     * 系统启动的时候自动启动此方法
     */
//    @PostConstruct
//    public void run(){
//        String content_all = readUrlString();
//        String content = jiquStr(content_all);
//
//        createObjs(content);
//
//
//    }

    /**
     * 读取网页 原始内容
     * @return
     */
    public String readUrlString(){

        System.out.println(""+ReaderUrlHelper.class.getName());
        StringBuffer strb = new StringBuffer();

        try {
            URL url = new URL(urlPath);
            URLConnection conn = url.openConnection();// 打开链接
            InputStream is = conn.getInputStream();// 获取数据流
            Scanner sc = new Scanner(is, "UTF-8");// 扫描数据流并存放在sc里,编码方式 GBK
            while (sc.hasNextLine()) {// 判断是否存在下一行
                strb.append(sc.nextLine());
            }
            sc.close();
            is.close();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return strb.toString();
    }


    /**
     * 将原始的内容进行切割，按照 "试题开始 试题结束"  切割出来
     * @param contentStr
     * @return
     */

    public String jiquStr(String contentStr){
        String resultStr ="";
        int startIndex = contentStr.indexOf(startStr);
        String str001 = contentStr.substring(startIndex+11);
        int endIndex = str001.indexOf(endStr);
        resultStr = str001.substring(0,endIndex);

        System.out.println("截取的字符串"+resultStr.substring(3));
        System.out.println("---切分数据---");

        return resultStr;

    }



    public void createObjs(String resultStr){
        List<Choicequestion> list = new ArrayList<Choicequestion>();

        Choicequestion cq ;

        int allcount=0;

        String [] str = resultStr.split("</a><br />");

        int length = str.length;
        System.out.println("总的个数："+ length);
        for(int i=0;i<length;i++){
            String content =str[i];
            String [] cc = content.split("<br />");
            System.out.println("当前的长度=============》 "+cc.length);
            if(cc.length == 7){
                ++allcount;

            }else{
                System.out.println("当前的长度=============------------========AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA=========****************************************》 "+cc.length);
            }
            String questionID = IDmanager.createID();
            cq = new Choicequestion();



            for(int c=0;c<cc.length;c++){
//                System.out.println(cc[c]);
                if(i<9){
                    System.out.println(cc[c].trim().substring(2));
                    String s = cc[c].trim().substring(2);
                    if(c==0){
                        cq.setName(s);
                    }else if(c == 1){
                        cq.setAnswera(s);
                    }else if(c == 2){
                        cq.setAnswerb(s);
                    }else if(c == 3){
                        cq.setAnswerc(s);
                    }else if(c == 4){
                        cq.setAnswerd(s);
                    }else if(c == 5){
                        cq.setRealanswer(s.substring(6,7));
                    }



                }else {
                    if(c ==0){
                        System.out.println(cc[c].trim().substring(3));

                        String s = cc[c].trim().substring(3);
                        if(c==0){
                            cq.setName(s);
                        }else if(c == 1){
                            cq.setAnswera(s);
                        }else if(c == 2){
                            cq.setAnswerb(s);
                        }else if(c == 3){
                            cq.setAnswerc(s);
                        }else if(c == 4){
                            cq.setAnswerd(s);
                        }else if(c == 5){
                            cq.setRealanswer(s.substring(6,7));
                        }

//                        System.out.println(cc[c].replaceAll("\\s","").substring(3));
                    }else{
//                        System.out.println(cc[c].replaceAll("\\s","").substring(2));
                        System.out.println(cc[c].trim().substring(2));
                        String s = cc[c].trim().substring(2);
                        if(c==0){
                            cq.setName(s);
                        }else if(c == 1){
                            cq.setAnswera(s);
                        }else if(c == 2){
                            cq.setAnswerb(s);
                        }else if(c == 3){
                            cq.setAnswerc(s);
                        }else if(c == 4){
                            cq.setAnswerd(s);
                        }else if(c == 5){
                            if(s.length()>=7){
                                cq.setRealanswer(s.substring(6,7));
                            }else{
                                System.out.println("-------------------> 默认答案：");
                                cq.setRealanswer("A");
                            }
                        }

                    }
                }

            }




            cq.setQuestionid(questionID);
            cq.setCtime(System.currentTimeMillis());
//        int mindex = choicequestionRepository.findmaxIndex(sublevel3);
//        cq.setMindex(mindex+1);

            cq.setSublevel1(kemu);
            cq.setSublevel2("");
            cq.setSublevel3("");

            //如果用户选择了，年份那么就是查找年份试卷最大的一个题目
            long maxmcode=0 ;
            try {
                if(!StringUtil.isEmpty(moniname)){
                    maxmcode = choicequestionRepository.findMaxMoniChoicequestion(kemu,moniname);
                }else{
                    //如果用户没有选择，模拟年份，则必须选择章节
//                    maxmcode = choicequestionRepository.findSubjectMaxChoicequestion(sublevel3);
                }
            }catch (Exception ex){
//            ex.printStackTrace();
            }


            cq.setMoniname(moniname);
            cq.setMcode((maxmcode+1));
//            cq.setRealanswer(realanswer);


            System.out.println("--保存 cq ："+ GsonUtil.objTOjson(cq));

            System.out.println("---------------当前执行的个数：："+(i+1)+"------------------");

            list.add(cq);



            ChoicequestionExplain ce = new ChoicequestionExplain();

            ce.setMexplain("");
            ce.setQuestionid(questionID);

            try{
                choiceQuestionManager.docreate(cq,ce);
            }catch (Exception ex){
//            ex.printStackTrace();
            }

        }



        System.out.println("执行完成：总的个数---"+list.size());
        System.out.println("执行完成：6 的总的个数---"+allcount);



    }


    /**
     * 切割分块，让后再
     *
     * @param resultStr
     */
    public  void saveData(String resultStr){

        String [] str = resultStr.split("</a><br>");
        int length = str.length;
        System.out.println("总的个数："+ length);
        for(int i=0;i<length;i++){
            String content =str[i];
            String [] cc = content.split("<br />");
            System.out.println("当前的长度=============》 "+cc.length);
            for(int c=0;c<cc.length;c++){
//                System.out.println(cc[c]);
                if(i<9){
                    System.out.println(cc[c].trim().substring(2));
                }else {
                    if(c ==0){
                        System.out.println(cc[c].trim().substring(3));
//                        System.out.println(cc[c].replaceAll("\\s","").substring(3));
                    }else{
//                        System.out.println(cc[c].replaceAll("\\s","").substring(2));
                        System.out.println(cc[c].trim().substring(2));
                    }
                }

            }

            System.out.println("---------------------------------");

        }


    }

}
