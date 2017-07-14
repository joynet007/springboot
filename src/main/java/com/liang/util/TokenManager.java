package com.liang.util;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * Created by liang on 2017/7/11.
 */
@Component
public class TokenManager {

    public static  HashMap<String,String> map = new HashMap<String, String>();
    public static  HashMap<String,Long> mapTime = new HashMap<String, Long>();


    /**
     * 创建一个Token
     * @param usertel
     * @return
     */
    public String createToken(String usertel){
       return  dotoken(1,usertel);
    }

    /**
     * 更新一个token
     * @param usertel
     */
    public void updateToken(String usertel){
        dotoken(2,usertel);
    }

    /**
     * 统一的多线程处理
     * @param i
     * @param usertel
     * @return
     */
    public String dotoken(int i,String usertel){
        synchronized(this){
            if(i == 1){
                String tokenID = IDmanager.createID();
                map.put(usertel,tokenID);
                mapTime.put(usertel,System.currentTimeMillis());

                return tokenID;
            }else if(i == 2){
                mapTime.put(usertel,System.currentTimeMillis());
            }else if(i == 3){
                mapTime.remove(usertel);
                map.remove(usertel);
            }
            return  "";
        }

    }

    @PostConstruct
    public void startexe() throws Exception {
        System.out.println("--启动清扫空余用户tokenID线程--");
        new Thread(new TokenThread()).start();
    }

    /**
     * 线程启动
     */
    class TokenThread implements Runnable{
        @Override
        public void run() {
            while(true){
//                System.out.println("---线程启动---");
                Iterator iter = mapTime.entrySet().iterator();
                while (iter.hasNext()) {
                    Map.Entry entry = (Map.Entry) iter.next();
                    String key = (String)entry.getKey();
                    Long val = (Long)entry.getValue();
                    if(System.currentTimeMillis()-val > (30*60*1000)){
                        dotoken(3,key);
                    }
                }
                try {
                    Thread.sleep(20*60*1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }
    }

}
