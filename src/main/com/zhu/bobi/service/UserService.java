package com.zhu.bobi.service;

import com.zhu.bobi.entity.UserRole;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.Date;

/**
 * Created by Zhu波比 on 2018/3/20.
 */
public interface UserService extends UserDetailsService {
  void addUser(String id, String username, String password, String sysrolename, Date createdate, String note, boolean isAuthentiaction);

  void addRole(String id, String rolename, String roleurl, Date createdate);

  void addStory(String id, String msg, String imgPath, String note, Date time);

  UserRole getRoleByRoleName(String rolename);
}
