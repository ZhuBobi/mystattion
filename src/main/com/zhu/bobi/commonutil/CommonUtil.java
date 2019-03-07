package com.zhu.bobi.commonutil;

import com.zhu.bobi.result.ResultEnum;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;
import java.util.UUID;

public class CommonUtil {

  public static String getIpAddress(HttpServletRequest request) {
    String ip = request.getHeader("x-forwarded-for");
    if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
      ip = request.getHeader("Proxy-Client-IP");
    }
    if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
      ip = request.getHeader("WL-Proxy-Client-IP");
    }
    if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
      ip = request.getHeader("HTTP_CLIENT_IP");
    }
    if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
      ip = request.getHeader("HTTP_X_FORWARDED_FOR");
    }
    if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
      ip = request.getRemoteAddr();
    }
    return ip;
  }

  public static String getUUID() {
    return UUID.randomUUID().toString().replace("-", "");
  }

  public static Map<String, Object> resultFiller(Map<String, Object> map, ResultEnum resultEnum) {
    map.put("msg", resultEnum.getMsg());
    map.put("status", resultEnum.getStatus());
    return map;
  }


}
