package com.liang.pojo;

import com.liang.SystemConfig;
import com.liang.pojo.po.Subject;

import java.util.List;

/**
 * Created by liang on 2017/7/11.
 */
public class MessageObject {
    public MessageObject(String code, String mdesc) {
        this.code = code;
        this.mdesc = mdesc;
    }
    public MessageObject() {
        this.code = SystemConfig.mess_succ;
        this.mdesc = "执行成功";
    }

    public String code;//编码
    public String mdesc;//描述
    public String mcontent;//内容

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMdesc() {
        return mdesc;
    }

    public void setMdesc(String mdesc) {
        this.mdesc = mdesc;
    }

    public String getMcontent() {
        return mcontent;
    }

    public void setMcontent(String mcontent) {
        this.mcontent = mcontent;
    }
}
