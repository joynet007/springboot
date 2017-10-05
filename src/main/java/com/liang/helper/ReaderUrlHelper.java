package com.liang.helper;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Scanner;

/**
 * Created by liang on 2017/10/1.
 */
public class ReaderUrlHelper {



    public static String urlPath = "http://www.cnitpm.com/pm/27850.html";

    public static String startStr = "<!--试题开始-->";
    public static String endStr = "<!--试题结束-->";



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


//    public String jiquStr(String contentStr){
//        int startIndex = contentStr.indexOf(startStr);
//        String str001 = contentStr.substring(startIndex+11);
//
//        int endIndex = str001.indexOf(endStr);
//
//        resultStr = str001.substring(0,endIndex);
//
//        System.out.println("截取的字符串"+resultStr);
//        System.out.println("---切分数据---");
//
//        return resultStr;
//
//    }


    public  static String resultStr ="";
    public static void main(String args[]){

        ReaderUrlHelper readerUrlHelper = new ReaderUrlHelper();


        String contentStr = readerUrlHelper.readUrlString();
        int startIndex = contentStr.indexOf(startStr);


//        System.out.println(startIndex+"---"+endIndex);

        String str001 = contentStr.substring(startIndex+11);

        int endIndex = str001.indexOf(endStr);

        resultStr = str001.substring(0,endIndex);
        System.out.println("截取的字符串"+resultStr);
        System.out.println("---切分数据---");

        readerUrlHelper.saveData();

    }


    public  void saveData(){

        String [] str = resultStr.split("</a><br>");
        int length = str.length;
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
