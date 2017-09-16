package com.liang.repository;

import com.liang.pojo.po.LearnQuestion;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by liang on 2017/8/13.
 */
public interface LearnQuestionRepository extends CrudRepository<LearnQuestion, Integer> {

    /**
     * 查询这个用户着这个科目下总共学了多少题目
     * @param userid
     * @param subjectid
     * @return
     */
    @Query(value = "select count(*) from tb_learnquestion where userid = ?1 and subjectid = ?2 " ,nativeQuery=true)
    long findcountLearnquestion(long userid,String subjectid);

    /**
     * 查询这个用户着这个科目下总共错（对）了多少题目
     * @param userid
     * @param subjectid
     * @param ismistake
     * @return
     */
    @Query(value = "select count(*) from tb_learnquestion where userid = ?1 and subjectid = ?2 and ismistake = ?3 " ,nativeQuery=true)
    long findcountismistakeLearnquestion(long userid,String subjectid,String ismistake);


    /**
     * 根据用户编码和题目编码查询用户是否存在对象
     * @param userid
     * @param questionid
     * @return
     */
    @Query(value = "select * from tb_learnquestion where userid = ?1 and questionid = ?2 " ,nativeQuery=true)
    LearnQuestion findLearnquestion(long userid,String questionid);




//    select * from ruankao.tb_learnquestion where pkid = (select max(pkid)  FROM ruankao.tb_learnquestion where ismistake='mistake.yes');

    /**
     *  查询当前用户在此科目下的，最近的一个错题
     * @param userid
     * @param subjectid
     * @return
     */
    @Query(value = "select * from tb_learnquestion where pkid = " +
            "(select max(pkid)  FROM tb_learnquestion where ismistake='mistake.yes' and userid = ?1 and subjectid = ?2)" ,nativeQuery=true)
    LearnQuestion findLearnquestionMaxIsMitake(long userid,String subjectid);


    /**
     *  查询当前用户在此科目下的，最近的一个错题 的下一题目
     * @param userid
     * @param subjectid
     * @param pkid
     * @return
     */
    @Query(value = "select * from tb_learnquestion a where a.pkid = (select max(pkid)  FROM tb_learnquestion t where t.ismistake='mistake.yes' and t.userid = ?1 and t.subjectid = ?2 and t.pkid < ?3)" ,nativeQuery=true)
    LearnQuestion findNextLearnquestionMaxIsMitake(long userid,String subjectid,long pkid);
    /**
     *  查询当前用户在此科目下的，最近的一个错题 的上一题目
     * @param userid
     * @param subjectid
     * @param pkid
     * @return
     */
    @Query(value = "select * from tb_learnquestion a where a.pkid = (select min(pkid)  FROM tb_learnquestion t where t.ismistake='mistake.yes' and t.userid = ?1 and t.subjectid = ?2 and t.pkid > ?3)" ,nativeQuery=true)
    LearnQuestion findUpLearnquestionMaxIsMitake(long userid,String subjectid,long pkid);




}
