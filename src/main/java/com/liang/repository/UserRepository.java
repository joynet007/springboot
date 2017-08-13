package com.liang.repository;

import com.liang.pojo.po.UserInfo;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by liang on 2017/3/25.
 */


@Transactional
public interface UserRepository extends CrudRepository<UserInfo, Integer> {

    @Query(value="select * from tb_userinfo where usertel = ?1",nativeQuery=true)
    UserInfo findByUsertel(String usertel);

    @Query(value="select * from tb_userinfo where usertel = ?1 and userpassword = ?2",nativeQuery=true)
    UserInfo findUser(String usertel ,String userPassword);

    @Query(value = "select * from tb_userinfo limit ?1,?2" ,nativeQuery=true)
    List<UserInfo> queryList(int pageNumber , int pageSize);

    @Query(value="select * from tb_userinfo where userid = ?1",nativeQuery=true)
    UserInfo findByUserid(long userid);

}
