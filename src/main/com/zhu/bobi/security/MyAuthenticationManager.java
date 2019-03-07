package com.zhu.bobi.security;

import com.zhu.bobi.entity.UserInfo;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;

@Service
public class MyAuthenticationManager implements AuthenticationManager {

  @Override
  public Authentication authenticate(Authentication authentication) throws AuthenticationException {
    UserInfo user = (UserInfo) authentication.getDetails();
    if (!user.getAuthentiaction()) {
      throw new AuthenticationServiceException("The account is locked!username:" + user.getUsername());
    }
    System.out.println("验证成功");
    return authentication;
  }
}
