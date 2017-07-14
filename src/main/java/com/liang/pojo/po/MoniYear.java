package com.liang.pojo.po;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by liang on 2017/7/11.
 *
 * 模拟题的年份
 */
@Entity
@Table(name="tb_moniyear")
public class MoniYear {
    @Id
    public long moniid;
    public String moniname;
    public String mstatus;
    public int mindex;

    public long getMoniid() {
        return moniid;
    }

    public void setMoniid(long moniid) {
        this.moniid = moniid;
    }

    public String getMoniname() {
        return moniname;
    }

    public void setMoniname(String moniname) {
        this.moniname = moniname;
    }

    public String getMstatus() {
        return mstatus;
    }

    public void setMstatus(String mstatus) {
        this.mstatus = mstatus;
    }

    public int getMindex() {
        return mindex;
    }

    public void setMindex(int mindex) {
        this.mindex = mindex;
    }

}
