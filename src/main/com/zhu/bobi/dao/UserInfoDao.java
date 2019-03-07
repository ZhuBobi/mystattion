package com.zhu.bobi.dao;

import com.zhu.bobi.entity.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * Created by Zhu波比 on 2018/3/20.
 */

public interface UserInfoDao extends JpaRepository<UserInfo, Long> {

  @Query(value = "SELECT * FROM sys_user  WHERE user_name = ?1 ", nativeQuery = true)
  UserInfo getUserByUserName(String username);

  @Query(value = "SELECT * FROM sys_user  WHERE id = ?1 ", nativeQuery = true)
  UserInfo getUserByID(String id);

  @Query(value = "select * from sys_user", nativeQuery = true)
  List<UserInfo> getAll();

  @Transactional
  @Modifying
  @Query(value = "INSERT INTO sys_user (id,user_name,password,sys_role_name,create_date,note,is_authentiaction) value(?1,?2,?3,?4,?5,?6,?7)", nativeQuery = true)
  void addUser(String id, String username, String password, String sysrolename, Date createdate, String note, boolean isAuthentiaction);

  @Transactional
  @Modifying
  @Query(value = "UPDATE  sys_user set is_authentiaction = ?1  WHERE user_name=?2", nativeQuery = true)
  void updateUser(boolean isAuthentiaction, String username);
}
