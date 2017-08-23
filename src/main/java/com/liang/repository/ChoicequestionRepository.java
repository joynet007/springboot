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
    Choicequestion findChoiceQuestion(String questionid);

    /**
     *
     * @param subjectid 这里一定是第三个 项目编号
     * @param mcode
     * 根据科目、题目编码查询题目
     * @return
     */
    @Query(value = "select * from tb_choicequestion where sublevel3 = ?1  and mcode =?2 " ,nativeQuery=true)
    Choicequestion findSubjectChoiceQuestion(String subjectid, long mcode);


    /**
     * 查询当前的科目、当前模拟题下的最大题目编码
     * 新增题目的时候题目编码自动增加一
     * @param subjectid
     * @return
     */
    @Query(value = "select max(mcode) from tb_choicequestion  where sublevel3= ?1 " ,nativeQuery=true)
    long findSubjectMaxChoicequestion(String subjectid);


    /**
     *
     * @param subjectid
     * @param moniname
     * @param mcode
     * 根据科目、模拟题、题目编码查询题目
     * @return
     */
    @Query(value = "select * from tb_choicequestion where sublevel1 = ?1 and  moniname=?2 and mcode =?3 " ,nativeQuery=true)
    Choicequestion findMoniChoiceQuestion(String subjectid,String moniname, long mcode);


    /**
     * 查询当前的科目、当前模拟题下的最大题目编码
     * 新增题目的时候题目编码自动增加一
     * @param subjectid
     * @param moniname
     * @return
     */
    @Query(value = "select max(mcode) from tb_choicequestion   where sublevel1 = ?1 and moniname=?2 " ,nativeQuery=true)
    long findMaxMoniChoicequestion(String subjectid,String moniname);


    /**
     * 查询当前的科目下的所有习题总数
     * 新增题目的时候题目编码自动增加一
     * @param subjectid
     * @return
     */
    @Query(value = "select count(*) from tb_choicequestion   where sublevel1 = ?1 " ,nativeQuery=true)
    long findChoicequestionCount(String subjectid);

    /**
     * 根据时间倒序排列
     * @return
     */
    @Query(value = "select * from tb_choicequestion  order by ctime desc" ,nativeQuery=true)
    List<Choicequestion> findallobjes();


}
