package com.lanxin;

import com.lanxin.custom.CustomRealm;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.subject.Subject;
/**
 * Created by Administrator on 2020/8/11 0011.
 */
public class Demo3 {

    public static void main(String[] args) {


        CustomRealm customRealm=new CustomRealm();

        DefaultSecurityManager securityManager=new DefaultSecurityManager();

        HashedCredentialsMatcher hashedCredentialsMatcher=new HashedCredentialsMatcher();
        hashedCredentialsMatcher.setHashAlgorithmName("md5");
        hashedCredentialsMatcher.setHashIterations(10);

        customRealm.setCredentialsMatcher(hashedCredentialsMatcher);

        securityManager.setRealm(customRealm);

        SecurityUtils.setSecurityManager(securityManager);

        Subject subject=SecurityUtils.getSubject();

        UsernamePasswordToken usernamePasswordToken=new UsernamePasswordToken("gaoqin2","beans123");

        subject.login(usernamePasswordToken);

    }
}
