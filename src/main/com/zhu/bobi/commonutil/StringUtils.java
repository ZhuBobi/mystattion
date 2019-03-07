package com.zhu.bobi.commonutil;

public class StringUtils {
  public static boolean isEmpty(String var) {
    return (null == var || var.length() == 0);
  }

  /**
   * 判断两个字符串是否相等，任意为空均不等
   */
  public static boolean isEquals(String var1, String var2) {
    return (null != var1 && null != var2 && var1.equals(var2));
  }
}
