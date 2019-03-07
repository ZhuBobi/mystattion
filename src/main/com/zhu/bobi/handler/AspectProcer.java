package com.zhu.bobi.handler;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * AOP的使用
 * 可以用来处理对数据库操作之前的角色权限认证
 */
@Component
@Aspect
public class AspectProcer {

  Logger logger = LoggerFactory.getLogger(this.getClass());

  @Pointcut(value = "execution(public * com.zhu.bobi.service.impl.*.*(..))")
  private void point() {
  }

  @Before("point()")
  public void beforePoint() {
    logger.info("开始执行切点");
    //权限认证
  }

  @After("point()")
  public void afterPoint() {
    logger.info("执行切点结束");
    //日志记录操作人员
  }
}
