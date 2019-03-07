package com.zhu.bobi.security;

import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Collection;
import java.util.Iterator;

@Service
public class MyAccessDecisionManager implements AccessDecisionManager {

  @Override
  public void decide(Authentication authentication, Object object,
                     Collection<ConfigAttribute> configAttributes)
      throws AccessDeniedException, InsufficientAuthenticationException {
    HttpServletRequest request = ((FilterInvocation) object).getHttpRequest();
    String needRole;
    AntPathRequestMatcher matcher;
    String[] staticSources =
        new String[]{"/css/**", "/font/**", "/img/**", "/js/**", "/bootstrap/**", "/dologin", "/register","/index","/index/**","/loginMid"};//解决配置静态文件路径不生效导致的无法访问
    for (String stat : staticSources) {
      matcher = new AntPathRequestMatcher(stat, "");
      if (matcher.matches(request)) {
        return;
      }
    }
    ConfigAttribute configAttribute;
    Iterator<ConfigAttribute> iter = configAttributes.iterator();
    do {
      configAttribute = iter.next();
      needRole = configAttribute.getAttribute();
      for (GrantedAuthority ga : authentication.getAuthorities()) {
        if (needRole.trim().equals(ga.getAuthority())) {
          return;
        } else if ("ROLE_ANONYMOUS".equals(ga.getAuthority())) { //决解无权限时，循环跳转login导致循环重定向
          matcher = new AntPathRequestMatcher("/login", "");
          if (matcher.matches(request)) {
            return;
          }
        }
      }
    } while (iter.hasNext());
    throw new AccessDeniedException("no right");
  }

  @Override
  public boolean supports(ConfigAttribute attribute) {
    return true;
  }

  @Override
  public boolean supports(Class<?> clazz) {
    return true;
  }
}