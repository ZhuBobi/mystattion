package com.zhu.bobi.commonutil;

import com.alibaba.fastjson.JSONObject;

public class JsonUtil {

  public static <T> T JsonToObjec(String var, Class<T> clazz) {
    JSONObject object = JSONObject.parseObject(var);
    T result = object.toJavaObject(clazz);
    return result;
  }
}
