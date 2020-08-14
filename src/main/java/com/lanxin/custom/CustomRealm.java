package com.lanxin.custom;

import com.lanxin.dao.RoleDao;
import com.lanxin.util.SqlSessionUtil;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Set;

/**
 * Created by Administrator on 2020/8/11 0011.
 */
@Component
public class CustomRealm extends AuthorizingRealm {

    @Autowired
    private RoleDao roleDao;
    //封装授权信息（权限）
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {


        String username=principalCollection.getPrimaryPrincipal().toString();//拿到用户名

        SimpleAuthorizationInfo simpleAuthorizationInfo=new SimpleAuthorizationInfo();

        //根据用户名查找他的权限，set集合

        Set<String> perms=roleDao.selectPermsByUsername(username);

        simpleAuthorizationInfo.setStringPermissions(perms);


        return simpleAuthorizationInfo;
    }

   //封装认证信息
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {

        //用户界面上填写的用户名
        String username=authenticationToken.getPrincipal().toString();

        //根据用户名查询数据库中的密码

        String password=roleDao.selectPassByUsername(username);

        if(password!=null){

            SimpleAuthenticationInfo simpleAuthenticationInfo=new SimpleAuthenticationInfo(username,password,"customRealm");

            String salt="12345abc!@#$%"+username;

            simpleAuthenticationInfo.setCredentialsSalt(ByteSource.Util.bytes(salt));

            return simpleAuthenticationInfo;
        }

        return null;//unkownaccountexception
    }

}
