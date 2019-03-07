package com.zhu.bobi.service.impl;

import com.zhu.bobi.dao.UserRoleDao;
import com.zhu.bobi.service.BaseService;
import com.zhu.bobi.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class RoleServiceImpl extends BaseService implements RoleService  {

  @Autowired
  private UserRoleDao roleDao;

  @Override
  public void addRole(String id, String rolename, String roleurl, Date createdate) {
    roleDao.addRole(id, rolename, roleurl, createdate);
  }
}
