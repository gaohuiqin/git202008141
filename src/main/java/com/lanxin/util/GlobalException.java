package com.lanxin.util;

import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by Administrator on 2020/8/12 0012.
 */
@ControllerAdvice
public class GlobalException {

    @ResponseBody
    @ExceptionHandler(value = AuthorizationException.class)
    public LanxinResult handleAuthrozation() {

        return LanxinResult.businessFail(CommonCode.NO_PERMISSION, CommonCode.NO_PERMISSION_MESSAGE);

    }

}
