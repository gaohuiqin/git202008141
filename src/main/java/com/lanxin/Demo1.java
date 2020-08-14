package com.lanxin;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.realm.SimpleAccountRealm;
import org.apache.shiro.subject.Subject;

/**
 * Created by Administrator on 2020/8/11 0011.
 */
public class Demo1 {

    public static void main(String[] args) {


        SimpleAccountRealm  simpleAccountRealm=new SimpleAccountRealm();

        simpleAccountRealm.addAccount("gaoqin","beans123","管理员");//数据库

        simpleAccountRealm.addAccount("chy","666","普通用户");



        DefaultSecurityManager defaultSecurityManager=new DefaultSecurityManager();


        defaultSecurityManager.setRealm(simpleAccountRealm);


        SecurityUtils.setSecurityManager(defaultSecurityManager);


        Subject subject=SecurityUtils.getSubject();


        //认证，登录,用户在界面上填写用户和密码

        UsernamePasswordToken token=new UsernamePasswordToken("gaoqin","beans123");


        //发起认证请求
        try {

            subject.login(token);

        } catch (IncorrectCredentialsException e) {
            System.out.println("密码错误");
        }catch(UnknownAccountException un){
            System.out.println("账户不正确");
        }

        System.out.println("是否登录成功:"+subject.isAuthenticated());


        //发起授权请求
        System.out.println(subject.hasRole("普通用户"));

        System.out.println(subject.hasRole("管理员"));

        subject.checkRole("管理员");
    }
}
