package com.liang.repository;

import com.liang.pojo.po.Choicequestion;
import com.liang.pojo.po.LearnCurrent;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by liang on 2017/8/13.
 */
public interface LearnCurrentRepository extends CrudRepository<LearnCurrent, Integer> {

    /**
     * 查询用户在这个科目下，
     * @param userid
     * @param subjectid
     * @return
     */
    @Query(value = "select mcode from tb_learncurrent where userid = ?1 and subjectid = ?2 " ,nativeQuery=true)
    long findLearnCurrentMcodeBySubjectid(long userid,String subjectid);

    /**
     * 查询用户在这个科目下的当前对象，
     * @param userid
     * @param subjectid
     * @return
     */
    @Query(value = "select * from tb_learncurrent where userid = ?1 and subjectid = ?2 " ,nativeQuery=true)
    LearnCurrent findLearnCurrentObjBySubjectid(long userid,String subjectid);




    /**
     * 查询用户在这个类型模拟年份下的当前学习的题目编号，
     * @param userid
     * @param moniname
     * @return
     */
    @Query(value = "select mcode from tb_learncurrent where userid = ?1 and subjectid = ?2 and moniname = ?3 " ,nativeQuery=true)
    long findLearnCurrentMcodeByMoniname(long userid,String subjectid,String moniname);

    /**
     * 查询用户在这个类型模拟年份下的当前学习的 当前对象，
     * @param userid
     * @param moniname
     * @return
     */
    @Query(value = "select * from tb_learncurrent where userid = ?1 and subjectid = ?2 and moniname = ?3 " ,nativeQuery=true)
    LearnCurrent findLearnCurrentObjByMoniname(long userid,String subjectid,String moniname);


//    select * from ruankao.tb_learnquestion where pkid = (select max(pkid)  FROM ruankao.tb_learnquestion where ismistake='mistake.yes');



}
