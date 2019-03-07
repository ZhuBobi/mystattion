package com.zhu.bobi.service.impl;

import com.zhu.bobi.commonutil.MyException;
import com.zhu.bobi.dao.StoryDao;
import com.zhu.bobi.dao.UserInfoDao;
import com.zhu.bobi.dao.UserRoleDao;
import com.zhu.bobi.entity.UserInfo;
import com.zhu.bobi.entity.UserRole;
import com.zhu.bobi.result.ResultEnum;
import com.zhu.bobi.service.BaseService;
import com.zhu.bobi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserServiceImpl extends BaseService implements UserService {

  @Autowired
  private StoryDao storyDao;

  @Autowired
  private UserInfoDao userInfoDao;

  @Autowired
  private UserRoleDao userRoleDao;

  @Bean
  public SessionRegistry getSessionRegistry() {
    SessionRegistry sessionRegistry = new SessionRegistryImpl();
    return sessionRegistry;
  }
//    public UserDetails authenticate(Authentication authentication){
//        PassCardAuthenticationToken token=(PassCardAuthenticationToken)authentication;
//        /*
//         * 这里进行逻辑认证操作，可以获取token中的属性来自定义验证逻辑，代码验证逻辑可以不用管
//         * 如果使用UserDetailsService的实现类来验证，就只能获取userName，不够灵活
//         */
//        if(token.getUserID()!=null&&token.getPassword()!=null){
//            UserInfo user=userInfoDao.getUserByUserName(token.getUsername());
//
//            String password=token.getPassword();
//            if(!password.equalsIgnoreCase(user.getPassword())){
//                token.setErrorcode("2");
//                return null;
//            }
//
//            if( token.isEnablePasscard()){//token中激活密码卡且系统使用密码卡
//
//                return null;
//            }
//            user.setCreatedate(new Date());
//            userInfoDao.addUser(user.getUserID(),user.getUsername(),user.getPassword(),
//                user.getSysRoleName(),user.getCreatedate(),user.getNote(),user.getAuthentiaction());
//            user.setAuthentiaction(true);
//            /*
//             * 导入一次角色权限，并且把权限set到User中，用于spring验证用户权限(getAuthorities方法)
//             */
//            List<UserRole> userRoles=user.getRoles();
//            Set<GrantedAuthority> accesses=new HashSet<GrantedAuthority>();
//            for(UserRole ur:userRoles){
//                accesses.add(ur);
//            }
////            user.getOrg().getOrgName();
////            if(user.getOrg().getCertTypes()!=null) user.getOrg().getCertTypes().size();//延迟载入一下
//            user.setAccesses(accesses);
//            return user;
//        }
//        return null;
//    }

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    if (null == username || "".equals(username)) {
      throw new MyException(ResultEnum.EMPTYNAME);
    }
    UserInfo user = userInfoDao.getUserByUserName(username);
    if (null == user || user.getSysRoleName().isEmpty()) {
      return null;
    }
    UserRole role = userRoleDao.getRoleByRoleName(user.getSysRoleName());
//        List<UserRole> userRoles=user.getRoles();
    List<UserRole> accesses = new ArrayList<>();
//            for(UserRole ur:userRoles){
    accesses.add(role);
    user.setRoles(accesses);
//            }
    return user;
//        new org.springframework.security.core.userdetails.User(user.getUsername(),
//        user.getPassword(), accesses);
  }

  @Override
  public void addUser(String id, String username, String password,
                      String sysrolename, Date createdate,
                      String note, boolean isAuthentiaction) {
    userInfoDao.addUser(id, username, password, sysrolename, createdate, note, isAuthentiaction);
  }

  @Override
  public void addRole(String id, String rolename, String roleurl, Date createdate) {
    userRoleDao.addRole(id, rolename, roleurl, createdate);
  }

  @Override
  public void addStory(String id, String msg, String imgPath, String note, Date time) {
    storyDao.addStory(id, msg, imgPath, note, time);
  }

  @Override
  public UserRole getRoleByRoleName(String rolename) {
    return userRoleDao.getRoleByRoleName(rolename);
  }
}
