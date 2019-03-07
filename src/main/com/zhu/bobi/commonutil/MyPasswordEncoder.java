package com.zhu.bobi.commonutil;

import org.springframework.security.authentication.encoding.MessageDigestPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class MyPasswordEncoder extends MessageDigestPasswordEncoder {
  public MyPasswordEncoder() {
    super("MD5");
  }

  public String MD5Maker(String key, String username) {
    return this.encodePassword(key, username);
  }
}