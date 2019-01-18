package com.tyshawn.Test;

import org.apache.commons.lang3.StringUtils;

import java.text.ParseException;
import java.util.Date;

/**
 * @author litianxiang
 */
public class Test {
    public static void main(String[] args) {

        String imei = "MDE0NzQ3QjktQzJGMi00QzhGLUFGMjAtNkJFMjRCQzRFNDYw";

        System.out.println(imei);
//        System.out.println(Base64.decode(imei));
        System.out.println(Base64.decode(imei).toString());
    }
}
