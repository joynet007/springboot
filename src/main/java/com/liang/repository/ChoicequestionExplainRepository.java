package com.liang.repository;

import com.liang.pojo.po.Choicequestion;
import com.liang.pojo.po.ChoicequestionExplain;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by liang on 2017/7/13.
 */
public interface ChoicequestionExplainRepository extends CrudRepository<ChoicequestionExplain, Integer> {

    @Query(value = "select * from tb_choicequestion where questionid = ?1" ,nativeQuery=true)
    Choicequestion findChoiceQuestion(String questionid);

}
