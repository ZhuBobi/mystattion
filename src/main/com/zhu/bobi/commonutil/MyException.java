package com.zhu.bobi.commonutil;

import com.zhu.bobi.result.ResultEnum;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class MyException extends UsernameNotFoundException {

  private static final long serialVersionUID = 1L;
  /*
   * 错误编码
   **/
  private int status;

  /*
   * 消息是否为属性文件中的Key
   **/
  private boolean propertiesKey = true;

  public MyException(String message) {
    super(message);
  }

  public MyException(ResultEnum resultEnum) {
    super(resultEnum.getMsg());
    this.status = resultEnum.getStatus();
  }

  public MyException(String message, Throwable cause) {
    super(message, cause);
  }

  public int getStatus() {
    return status;
  }
}
