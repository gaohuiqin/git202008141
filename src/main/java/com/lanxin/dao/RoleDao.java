package com.lanxin.dao;

import java.util.Set;

/**
 * Created by Administrator on 2020/8/11 0011.
 */
public interface RoleDao {


    public String selectPassByUsername(String username);


    public Set<String> selectPermsByUsername(String username);
}
