package org.orange.handler.exception;

import lombok.extern.slf4j.Slf4j;
import org.orange.domain.enums.AppHttpCodeEnum;
import org.orange.domain.response.ResponseResult;
import org.orange.exception.SystemException;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.annotation.RequestScope;


/**
 * @BelongsProject: Orange_Blog
 * @ClassName GlobalExceptionHandler
 * @Description TODO
 * @Author WangZJ0908
 * @Date 2024/8/5
 * @Version: 1.0
 */
@RestControllerAdvice//表示这是一个全局异常处理类,相当于@ControllerAdvice+@ResponseBody
@Slf4j
public class GlobalExceptionHandler {

    //处理SystemException异常
    @ExceptionHandler(SystemException.class)
    public ResponseResult systemExceptionHandler(SystemException exception) {
        //打印异常信息
        log.error("出现异常！{}",exception);
        //从异常对象中获取提示信息进行返回
        return ResponseResult.errorResult(exception.getCode(), exception.getMsg());
    }
    //其他异常
    @ExceptionHandler(Exception.class)//表示这个方法是用来处理Exception异常的
    public ResponseResult exceptionHandler(Exception exception) {
        //打印异常信息
        log.error("出现异常！{}",exception);
        //返回异常信息
        return ResponseResult.errorResult(AppHttpCodeEnum.SYSTEM_ERROR.getCode(),exception.getMessage());
    }
}
