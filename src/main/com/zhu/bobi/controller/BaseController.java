package com.zhu.bobi.controller;

import com.zhu.bobi.commonutil.CommonUtil;
import com.zhu.bobi.entity.UserInfo;
import com.zhu.bobi.result.Msg;
import com.zhu.bobi.result.Result;
import com.zhu.bobi.token.PassCardAuthenticationToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.util.Assert;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class BaseController {

  protected final Logger logger = LoggerFactory.getLogger(this.getClass());

  @Autowired
  protected HttpServletRequest request;
  @Autowired
  protected HttpServletResponse response;


  public int getPage() {
    return Integer.valueOf(request.getParameter("page"));
  }

  public int getLimit() {
    return Integer.valueOf(request.getParameter("limit"));
  }

  public Msg resultMsg() {
    return new Msg();
  }

  /*
   * 获取session对象
   *
   * @return
   **/
  protected HttpSession getSession() {
    return request.getSession();
  }

  public UserDetails getUser() {
    PassCardAuthenticationToken authentication =
        (PassCardAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
    PassCardAuthenticationToken sessionAuthentication =
        (PassCardAuthenticationToken) request.getSession().getAttribute("SPRING_SECURITY_CONTEXT");
    return null == authentication
        ? sessionAuthentication.getUserDetails() : authentication.getUserDetails();
  }

  public void logOut() {
    Assert.notNull(request, "HttpServletRequest required!");
    try {
      HttpSession session = request.getSession(false);
      if (null != session) {
        session.invalidate();
      }
      request.logout();
      SecurityContextHolder.clearContext();
    } catch (ServletException e) {
      e.printStackTrace();
    } finally {
      SecurityContextHolder.clearContext();
    }
  }

  public void makeUser(HttpServletRequest request, UserInfo userDetails) {
    logger.info("开始记录登陆用户信息！用户名：{}",userDetails.getUsername());
    PassCardAuthenticationToken authentication =
        new PassCardAuthenticationToken(userDetails, userDetails.getPassword(), userDetails.getAuthorities());
    authentication.setUserId(userDetails.getId());
    authentication.setAuthenticated(userDetails.getAuthentiaction());
    authentication.setIp(CommonUtil.getIpAddress(request));
    authentication.setDetails(new WebAuthenticationDetails(request));
    SecurityContextHolder.getContext().setAuthentication(authentication);
    HttpSession session = request.getSession(true);
    session.setAttribute(userDetails.getUsername(), SecurityContextHolder.getContext());
    logger.info("记录登陆用户信息完毕！");
  }

  public boolean checkAuthentication() {
    SecurityContext securityContext = SecurityContextHolder.getContext();
    return null == securityContext || securityContext.getAuthentication() == null;
  }
}
