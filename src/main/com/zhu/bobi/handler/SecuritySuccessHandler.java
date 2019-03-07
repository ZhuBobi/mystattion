package com.zhu.bobi.handler;

import com.zhu.bobi.entity.UserInfo;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.zhu.bobi.commonutil.CommonUtil.getIpAddress;

public class SecuritySuccessHandler extends
    SavedRequestAwareAuthenticationSuccessHandler {

  @Override
  public void onAuthenticationSuccess(HttpServletRequest request,
                                      HttpServletResponse response, Authentication authentication) throws IOException,
      ServletException {
    //获得授权后可得到用户信息   可使用SUserService进行数据库操作
    UserInfo userDetails = (UserInfo) authentication.getPrincipal();
    /* Set<SysRole> roles = userDetails.getSysRoles();*/
    //输出登录提示信息
    System.out.println("用户 " + userDetails.getUsername() + " 登录");

    System.out.println("IP :" + getIpAddress(request));

    super.onAuthenticationSuccess(request, response, authentication);
  }
}
