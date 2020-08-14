package com.lanxin;

import org.apache.shiro.crypto.hash.Md5Hash;

/**
 * Created by Administrator on 2020/8/11 0011.
 */
public class Test {

    public static void main(String[] args) {

        //Md5

        //1.原始密码，2.密码盐值,3.加密次数

        //1. gaoqin beans123    gaoqin2 beans123

        String text="1234abc!@#$%";
        String username="gaoqin1";
        Md5Hash md5Hash=new Md5Hash("beans123",text+username,10);

        System.out.println(md5Hash.toString());
    }
}
