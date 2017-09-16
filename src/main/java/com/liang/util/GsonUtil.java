package com.liang.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.beanutils.BeanUtils;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;

/**
 * Created by liang on 2017/7/11.
 */
public class GsonUtil {

    /**
     * object 转化成 json 字符串
     * @return
     * @param object
     */
    public static String objTOjson(Object object){
        ObjectMapper mapper = new ObjectMapper();
        String json = "";
        try {
            json = mapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return json;
    }

    /**
     * list 对账转化成json
     * @param objects
     * @return
     */
    public static String objTOjson(List<Object> objects){
        ObjectMapper mapper = new ObjectMapper();
        StringBuffer sjson = new StringBuffer();
        for (Object obj:objects) {
            String json = "";
            try {
                json = mapper.writeValueAsString(obj);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
            if(sjson.length() > 0){
                sjson.append(",").append(json);
            }else{
                sjson.append(json);
            }

        }
        return sjson.toString();
    }

    /**
     * 10001 成功
     * 10002 失败
     * @param code
     * @param message
     * @return
     */

    public static String responseStr(String code , String message)
    {
        HashMap<String,String> map = new HashMap<String,String>();
        map.put("code",code);
        map.put("message",message);

        ObjectMapper mapper = new ObjectMapper();
        String json = "";
        try {
            json = mapper.writeValueAsString(map);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return json;

    }

    /**
     * 10001 成功
     * 10002 失败
     * @param message
     * @return
     */

    public static String responseStrFailed( String message )
    {
        HashMap<String,String> map = new HashMap<String,String>();
        map.put("code",SystemConfig.mess_failed);
        map.put("message",message);

        ObjectMapper mapper = new ObjectMapper();
        String json = "";
        try {
            json = mapper.writeValueAsString(map);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return json;
    }



    /**
     * @param message
     * @return
     */
    public static String responseStrSuccess( String message )
    {
        HashMap<String,String> map = new HashMap<String,String>();
        map.put("code", SystemConfig.mess_succ);
        map.put("message",message);

        ObjectMapper mapper = new ObjectMapper();
        String json = "";
        try {
            json = mapper.writeValueAsString(map);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        System.out.println(json);
        return json;
    }


    /**
     *
     * @param code
     * @param message
     * @param object
     * @return
     */
    public static HashMap responseMap( String code ,String message , Object object )
    {
        HashMap<String,Object> map = new HashMap<String,Object>();
        map.put("code", code);
        map.put("message",message);
        map.put("content",object);
        return map;
    }


    public static HashMap<String,String> tomap(Object c){
        HashMap<String,String> map = new HashMap<String,String>();
        Field[] fields=c.getClass().getDeclaredFields();
        for(int i=0;i<fields.length;i++){
            String key =fields[i].getName()+"";

            try {
                String value = BeanUtils.getProperty(c, key);

                map.put(key,value);

            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            }
        }
        return map;
    }

    /**
     * 把对象处理成 JSON 的样式字符串 返回的字符串是：字段：值
     *
     {"id":1,
     "name":"C",
     "size":"",
     "date":"02/19/2010"}
     *
     * @param c
     *  这个字段数组是你需要的字段，（有些不必要的字段过滤掉）
     * @param ziduan
     * @return
     */
    public static String jsonString(Object c , String [] ziduan_array){
        if(c == null){
            return "";
        }
        StringBuffer sb = new StringBuffer();

        for(String ziduan : ziduan_array){
            try {

                String value = BeanUtils.getProperty(c, ziduan);
                if(ziduan.equals("subjectid") ){
                    ziduan = "id";
                }
                if(ziduan.equals("subjectname") ){
                    ziduan = "text";
                }
                sb.append("\""+ziduan+"\"").append(":").append("\""+value+"\"").append(",");
            } catch (IllegalAccessException | InvocationTargetException
                    | NoSuchMethodException e) {
                e.printStackTrace();
                Exception ex = new Exception("---字段必须是对象的属性----");
                ex.printStackTrace();
            }
        }
        //去掉最后一个逗号
        return sb.toString().substring(0,sb.toString().length()-1);
    }


}
