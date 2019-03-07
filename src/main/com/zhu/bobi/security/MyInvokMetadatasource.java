package com.zhu.bobi.security;

import com.zhu.bobi.dao.UserInfoDao;
import com.zhu.bobi.dao.UserRoleDao;
import com.zhu.bobi.entity.UserInfo;
import com.zhu.bobi.entity.UserRole;
import com.zhu.bobi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

@Service
public class MyInvokMetadatasource implements FilterInvocationSecurityMetadataSource {

  @Autowired
  private UserService userService;

  @Autowired
  private UserInfoDao userInfoDao;

  private Map<String, Collection<ConfigAttribute>> map;

  /*
   *
   * 加载资源，初始化资源变量
   */
  private void loadResourceDefine() {
    map = new HashMap<>();
    Collection<ConfigAttribute> array;
    ConfigAttribute cfg;
    UserRole userRole;
    List<UserInfo> userInfos = userInfoDao.getAll();
    for (UserInfo userInfo : userInfos) {
      userInfo.setAuthentiaction(true);
      array = new ArrayList<>();
      cfg = new SecurityConfig(userInfo.getSysRoleName());
      userRole = userService.getRoleByRoleName(userInfo.getSysRoleName());
      array.add(cfg);
      map.put(userRole.getId(), array);
    }
  }

  @Override
  public Collection<ConfigAttribute> getAttributes(Object object)
      throws IllegalArgumentException {
    if (map == null) {
      loadResourceDefine();
    }
    List<ConfigAttribute> configAttributes = new ArrayList<>();
    Collection<Collection<ConfigAttribute>> configColAttributes = map.values();
    for (Collection<ConfigAttribute> configColAttribute:configColAttributes) {
      configAttributes.addAll(configColAttribute);
    }
    return configAttributes;
  }

  @Override
  public Collection<ConfigAttribute> getAllConfigAttributes() {
    if (map == null) {
      loadResourceDefine();
    }
    List<ConfigAttribute> configAttributes = new ArrayList<>();
    Iterator iter = map.values().iterator();
    while (iter.hasNext()) {
      configAttributes.addAll((List<ConfigAttribute>) iter.next());
    }
    return configAttributes;
  }

  @Override
  public boolean supports(Class<?> clazz) {
    return true;
  }
}