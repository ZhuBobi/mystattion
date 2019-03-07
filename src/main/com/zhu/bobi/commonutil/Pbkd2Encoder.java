package com.zhu.bobi.commonutil;

import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class Pbkd2Encoder extends Pbkdf2PasswordEncoder {

    public Pbkd2Encoder() {
        super("MD5");
    }

    public String Pbkdf2Maker(String key, String username) {
        return this.encode(key);
    }
}
