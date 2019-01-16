package com.tyshawn.Test;

import org.apache.commons.lang3.StringUtils;

import java.text.ParseException;
import java.util.Date;

/**
 * @author litianxiang
 */
public class Test {
    public static void main(String[] args) {

        String web = "http://wting.info/asdb/fiction/wuxia/sunxx/uidb4i51.mp3";
        if (!web.contains("vb.wting.info")) {
            System.out.println("导入失败：收费书籍域名错误");
        }
        if (web.contains("vb.wting.info")) {
            System.out.println("导入失败：免费书籍域名错误");
        }



    }
}
