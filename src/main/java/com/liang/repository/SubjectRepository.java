package com.liang.repository;

import com.liang.pojo.po.Subject;
import com.liang.pojo.po.UserInfo;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by liang on 2017/7/11.
 */
public interface SubjectRepository extends CrudRepository<Subject, Integer> {

    @Query(value = "select * from tb_subject" ,nativeQuery=true)
    List<Subject> queryList();

    @Query(value = "select * from tb_subject where subjectid = ?1" ,nativeQuery=true)
    Subject findSubject(String subjectid);

    @Query(value = "select * from tb_subject where mlevel = ?1" ,nativeQuery=true)
    List<Subject> findSubjectByLevel(int mlevel);

}
