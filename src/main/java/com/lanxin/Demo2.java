package com.lanxin;

import com.alibaba.druid.pool.DruidDataSource;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.realm.jdbc.JdbcRealm;
import org.apache.shiro.subject.Subject;

/**
 * Created by Administrator on 2020/8/11 0011.
 */
public class Demo2 {

    public static void main(String[] args) {


        JdbcRealm jdbcRealm=new JdbcRealm();
        DruidDataSource druidDataSource=new DruidDataSource();
        druidDataSource.setUsername("root");
        druidDataSource.setPassword("beans123");
        druidDataSource.setUrl("jdbc:mysql://localhost:3306/shiro");
        jdbcRealm.setDataSource(druidDataSource);

        jdbcRealm.setPermissionsLookupEnabled(true);


        DefaultSecurityManager defaultSecurityManager=new DefaultSecurityManager();


        defaultSecurityManager.setRealm(jdbcRealm);

        SecurityUtils.setSecurityManager(defaultSecurityManager);


        Subject subject=SecurityUtils.getSubject();


        UsernamePasswordToken token=new UsernamePasswordToken("gaoqin","beans123");

        subject.login(token);



        //subject.checkRole("edit");


        subject.checkPermission("user:update");


    }
}
