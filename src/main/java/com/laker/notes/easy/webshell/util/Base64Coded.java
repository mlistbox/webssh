package com.laker.notes.easy.webshell.util;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.stereotype.Component;

@Component
public class Base64Coded {
    public static String decode(byte[] bytes) {
        return new String(Base64.decodeBase64(bytes));
    }

    //base64 ����
    public static String encode(byte[] bytes) {
        return new String(Base64.encodeBase64(bytes));
    }


}