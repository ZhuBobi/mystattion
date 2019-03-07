package com.zhu.bobi.token;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;


//实现AuthenticationProvider，自定义参数验证
//        这种验证以前项目用过，现在没有写示例代码，先写下大概流程和需要用到的类
//        这种验证的好处：可以在自定义登录界面添加登录时需要的参数，如多个验证码等、可以修改默认登录名称和密码的参数名
//
//        整体流程：
//        1.用户登录时，先经过自定义的passcard_filter过滤器，该过滤器继承了AbstractAuthenticationProcessingFilter，并且绑定了登录失败和成功时需要的处理器(跳转页面使用)
//        2.执行attemptAuthentication方法，可以通过request获取登录页面传递的参数，实现自己的逻辑，并且把对应参数set到AbstractAuthenticationToken的实现类中
//        3.验证逻辑走完后，调用 this.getAuthenticationManager().authenticate(token);方法，执行AuthenticationProvider的实现类的supports方法
//        4.如果返回true则继续执行authenticate方法
//        5.在authenticate方法中，首先可以根据用户名获取到用户信息，再者可以拿自定义参数和用户信息做逻辑验证，如密码的验证
//        6.自定义验证通过以后，获取用户权限set到User中，用于springSecurity做权限验证
//        7.this.getAuthenticationManager().authenticate(token)方法执行完后，会返回Authentication，如果不为空，则说明验证通过
//        8.验证通过后，可实现自定义逻辑操作，如记录cookie信息
//        9.attemptAuthentication方法执行完成后，由springSecuriy来进行对应权限验证，成功于否会跳转到相对应处理器设置的界面。

/**
 * Created by Zhu波比 on 2018/3/20.
 */
public class PassCardAuthenticationToken extends AbstractAuthenticationToken {
  private String userId;
  private UserDetails userDetails;
  private String password;
  private String errorcode;
  private String ip;
  private boolean isEnablePasscard;

  public PassCardAuthenticationToken(UserDetails userDetails, String password, Collection<? extends GrantedAuthority> authorities) {
    super(authorities);
    this.userDetails = userDetails;
    this.password = password;
  }

  /**
   * 凭证，用户密码
   */
  @Override
  public Object getCredentials() {
    return password;
  }

  /**
   * 当事人，登录名 用户Id
   */
  @Override
  public Object getPrincipal() {
    return userId;
  }

  public void setUserId(String userId) {
    this.userId = userId;
  }

  public String getUserId() {
    return this.userId;
  }

  public UserDetails getUserDetails() {
    return this.userDetails;
  }

  public String getPassword() {
    return this.password;
  }

  public void setUserDetails(UserDetails userDetails) {
    this.userDetails = userDetails;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getErrorcode() {
    return errorcode;
  }

  public void setErrorcode(String errorcode) {
    this.errorcode = errorcode;
  }

  public boolean isEnablePasscard() {
    return isEnablePasscard;
  }

  public void setEnablePasscard(boolean enablePasscard) {
    isEnablePasscard = enablePasscard;
  }

  public String getIp() {
    return ip;
  }

  public void setIp(String ip) {
    this.ip = ip;
  }
}
