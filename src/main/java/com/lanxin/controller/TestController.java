package com.lanxin.controller;

import com.lanxin.util.CommonCode;
import com.lanxin.util.LanxinResult;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Administrator on 2020/8/12 0012.
 */
@RestController
public class TestController {


    @RequestMapping(value = "/login")
    public LanxinResult login(String username,String password){

        Subject subject=SecurityUtils.getSubject();

        UsernamePasswordToken usernamePasswordToken=new UsernamePasswordToken(username,password);

        try {
            subject.login(usernamePasswordToken);
            return LanxinResult.ok();
        } catch (IncorrectCredentialsException e1) {
            return LanxinResult.businessFail(CommonCode.PASSWORD_ERROR,CommonCode.PASSWORD_ERROR_MESSAGE);
        }catch (UnknownAccountException e2){
            return LanxinResult.businessFail(CommonCode.ACCOUNT_NOT_FOUND,CommonCode.ACCOUNT_NOT_FOUND_MESSAGE);
        }
    }

    //登出
    @RequestMapping(value = "/logout")
    public LanxinResult logout(){

        Subject subject=SecurityUtils.getSubject();

        subject.logout();

        return LanxinResult.ok();
    }

    //登出
    @RequestMapping(value = "/unlogin")
    public LanxinResult unlogin(){

        return LanxinResult.businessFail(CommonCode.NOT_LOGIN_ERROR,CommonCode.NOT_LOGIN_ERROR_MESSAGE);
    }

    @RequiresPermissions(value = "user:add")
    @RequestMapping(value = "/add")
    public LanxinResult add(){

        System.out.println("用户添加");
        return LanxinResult.ok();
    }

    @RequiresPermissions(value = "user:update")
    @RequestMapping(value = "/update")
    public LanxinResult update(){
        System.out.println("用户修改");
        return LanxinResult.ok();
    }

    @RequiresPermissions(value = "user:delete")
    @RequestMapping(value = "/delete")
    public LanxinResult delete(){

        System.out.println("用户删除");
        return LanxinResult.ok();
    }

    @RequiresPermissions(value = "user:select")
    @RequestMapping(value = "/select")
    public LanxinResult select(){
        System.out.println("用户查询");
        return LanxinResult.ok();
    }
}
