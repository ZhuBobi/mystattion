package com.zhu.bobi.security;

import com.zhu.bobi.commonutil.MyPasswordEncoder;
import com.zhu.bobi.handler.SecuritySuccessHandler;
import com.zhu.bobi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;

/**
 * Created by Zhu波比 on 2018/3/31.
 */
@EnableWebSecurity
public class MySecurity extends WebSecurityConfigurerAdapter {

  @Autowired
  private UserService userService;

  @Autowired
  private MySecurityFilter mySecurityFilter;

  @Autowired
  private MyAuthenticationManager myAuthenticationManager;

  private MyPasswordEncoder getpasswordEncoder() {
    return new MyPasswordEncoder();
  }

  private SecuritySuccessHandler getsecuritySuccessHandler() {
    return new SecuritySuccessHandler();
  }

  @Override
  public void configure(AuthenticationManagerBuilder auth) throws Exception {
    auth.userDetailsService(userService).passwordEncoder(getpasswordEncoder());
    auth.eraseCredentials(false);
//    auth.parentAuthenticationManager(myAuthenticationManager);
  }

  @Override
  public AuthenticationManager authenticationManagerBean() {
    return myAuthenticationManager;
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
//    http.authorizeRequests()
//        .antMatchers("/login","/css/**","/image/*").permitAll()
    http.addFilterBefore(mySecurityFilter, FilterSecurityInterceptor.class)
        .authorizeRequests()
        .antMatchers("/login", "/dologin", "/register","/css/**","/loginMid",
            "/font/*", "/img/**", "/js/**", "/bootstrap/**","/index","/index/**").permitAll()
        .and().formLogin()
        .loginPage("/login").permitAll().successHandler(getsecuritySuccessHandler())
        .and().logout().logoutSuccessUrl("/login")
        .invalidateHttpSession(true)
        .and().rememberMe()//登录后记住用户，下次自动登录,数据库中必须存在名为persistent_logins的表
        .tokenValiditySeconds(1209600)
        .and().csrf().disable();
  }

//  @Override
//  public void configure(WebSecurity web) {
//    web.ignoring().anyRequest().antMatchers("/css/**", "/font/*", "/img/**", "/js/**", "/layui/**");
//  }
}
