package com.lanxin.util;

import lombok.*;
import lombok.experimental.FieldNameConstants;
import sun.applet.Main;

/**
 * Created by Administrator on 2020/6/20 0020.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LanxinResult {

    private Integer code; //响应码   200  500 404 ,1001

    private String msg; //错误或成功的消息

    private Object data;//数据

    public static LanxinResult businessFail(Integer code,String msg){

         return new LanxinResult(code,msg,null);
    }
    //成功带数据给前端
    public static LanxinResult ok(Object data){

          return new LanxinResult(CommonCode.SUCCESS_CODE,CommonCode.SUCCESS_MESSAGE,data);
    }
    //成功不带数据给前端
    public static LanxinResult ok(){

        return new LanxinResult(CommonCode.SUCCESS_CODE,CommonCode.SUCCESS_MESSAGE,null);
    }
    //后台出现异常
    public static LanxinResult fail(){

        return new LanxinResult(CommonCode.FAIL_CODE,CommonCode.FAIL_MESSAGE,null);
    }

}
