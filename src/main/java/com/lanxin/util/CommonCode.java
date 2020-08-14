package com.lanxin.util;

/**
 * Created by Administrator on 2020/6/20 0020.
 */
public interface CommonCode {

    public static final Integer SUCCESS_CODE=200;
    public static final String SUCCESS_MESSAGE="操作成功";
    public static final Integer FAIL_CODE=500;
    public static final String FAIL_MESSAGE="程序内部异常";
    public static final Integer DATA_NOT_FOUND=10001;
    public static final String DATA_NOT_FOUND_MESSAGE="您查找的数据不存在";

    public static final Integer ACCOUNT_NOT_FOUND=10002;
    public static final String ACCOUNT_NOT_FOUND_MESSAGE="账户不存在";

    public static final Integer PASSWORD_ERROR=10003;
    public static final String PASSWORD_ERROR_MESSAGE="密码错误";

    public static final Integer NOT_LOGIN_ERROR=10004;
    public static final String  NOT_LOGIN_ERROR_MESSAGE="您未登录";

    public  static  final  Integer NO_PERMISSION=10005;
    public static final String NO_PERMISSION_MESSAGE="权限不足";
}
