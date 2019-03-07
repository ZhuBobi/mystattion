package com.zhu.bobi.dao;

import com.zhu.bobi.entity.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

public interface UserRoleDao extends JpaRepository<UserRole, Long> {

  @Query(value = "SELECT * FROM sys_role t WHERE t.ROLE_NAME = ?1 ", nativeQuery = true)
  UserRole getRoleByRoleName(String rolename);

  @Query(value = "SELECT * FROM sys_role t WHERE t.ID = ?1 ", nativeQuery = true)
  UserRole getRoleById(String id);

  @Transactional
  @Modifying
  @Query(value = "INSERT INTO sys_role ( id, role_name,role_url,create_date) value( ?1, ?2, ?3, ?4)", nativeQuery = true)
  void addRole(@Param("id") String id,
               @Param("role_name") String rolename,
               @Param("role_url") String roleurl,
               @Param("create_date") Date createdate);
}
