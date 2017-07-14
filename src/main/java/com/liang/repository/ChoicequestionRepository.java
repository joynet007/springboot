package com.liang.repository;

import com.liang.pojo.po.Choicequestion;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by liang on 2017/7/13.
 */
public interface ChoicequestionRepository extends CrudRepository<Choicequestion, Integer> {

    @Query(value = "select * from tb_choicequestion where questionid = ?1" ,nativeQuery=true)
    Choicequestion findMoniYear(String questionid);



}
