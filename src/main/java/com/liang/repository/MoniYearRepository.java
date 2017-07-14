package com.liang.repository;

import com.liang.pojo.po.MoniYear;
import com.liang.pojo.po.Subject;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * Created by liang on 2017/7/13.
 */


public interface MoniYearRepository  extends CrudRepository<MoniYear, Integer> {

    @Query(value = "select * from tb_moniyear " ,nativeQuery=true)
    List<MoniYear> queryList();

    @Query(value = "select * from tb_moniyear where moniid = ?1" ,nativeQuery=true)
    Subject findMoniYear(String moniid);


}
