package org.orange.aspect;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.orange.annotation.SystemLog;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * @BelongsProject: Orange_Blog
 * @ClassName LogAspect
 * @Description 用于记录日志的切面
 * @Author WangZJ0908
 * @Date 2024/8/7
 * @Version: 1.0
 */
@Component
@Aspect
@Slf4j
public class LogAspect {
    //确定切点
    @Pointcut("@annotation(org.orange.annotation.SystemLog)")//指定注解
    public void pt() {//切点方法

    }

    //通知方法
    @Around("pt()")//环绕通知,指定切点
    public Object printLog(ProceedingJoinPoint joinPoint) throws Throwable {
        Object res;//执行目标方法,并获取返回值
        try {
            //前置通知
            handleBefore(joinPoint);
            res = joinPoint.proceed();
            //后置通知
            handleAfter(res);
        } finally {//是否抛出异常,都要打印信息
            log.info("=============END=============" + System.lineSeparator());
        }


        return res;
    }

    private void handleBefore(ProceedingJoinPoint joinPoint) {

        // 获取 request 对象
        ServletRequestAttributes attributes= (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        //url
        String url=request.getRequestURL().toString();
        //http method
        String method = request.getMethod();
        //Ip
        String ip = request.getRemoteHost();
        //获取被增强方法上的注解对象
        SystemLog systemLog = getSystemLog(joinPoint);

        //

        log.info("=============START=============");
        // 打印请求 URL
        log.info("URL                       : {}", url);
        // 打印描述信息
        log.info("BusinessName              : {}", systemLog.businessName());
        // 打印 Http method
        log.info("HTTP Method               : {}", method);
        // 打印调用 controller 的全路径以及执行方法
        log.info("Class Method              : {}.{}",joinPoint.getSignature().getDeclaringTypeName(),joinPoint.getSignature().getName());
        // 打印请求的 IP
        log.info("IP                        : {}", ip);
        // 打印请求入参
        log.info("Request Args              : {}", JSON.toJSONString(joinPoint.getArgs()));
    }

    // 获取被增强方法上的注解对象
    private SystemLog getSystemLog(ProceedingJoinPoint joinPoint) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        SystemLog systemLog= signature.getMethod().getAnnotation(SystemLog.class);
        return systemLog;
    }

    private void handleAfter(Object res) {//方法执行后返回的是一个Object,只需要转为Json即可
        // 打印出参
        log.info("Response : {}",JSON.toJSONString(res));
    }
}
