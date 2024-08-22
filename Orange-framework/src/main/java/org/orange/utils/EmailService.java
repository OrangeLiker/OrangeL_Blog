package org.orange.utils;

import org.orange.constans.SystemConstants;
import org.orange.domain.response.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.cache.CacheProperties;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
 * @BelongsProject: Orange_Blog
 * @ClassName EmailService
 * @Description TODO
 * @Author WangZJ0908
 * @Date 2024/8/13
 * @Version: 1.0
 */
@Service
public class EmailService {
    @Resource
    private JavaMailSender mailSender;
    @Autowired
    private RedisCache redisCache;
    private static final String MyEmail="2814964382@qq.com";
    private String generateVerificationCode(){
        return String.valueOf((int) ((Math.random() * 9 + 1) * 100000));
    }
    public void sendVerificationEmail(String email, String type){
        String code = generateVerificationCode();
        String key = SystemConstants.VERIFY_EMAIL_DATA+email;
        //将验证码存入缓存
        redisCache.setCacheObject(key,code,3, TimeUnit.MINUTES);
        SimpleMailMessage message=new SimpleMailMessage();
        if(type.equals("register")){
            message.setSubject("欢迎注册OrangeLiker'sBlog🍊");
            message.setText("您的注册验证码为："+code+",有效期3分钟，请您趁热哦☕");
        }else if(type.equals("reset")){
            message.setSubject("重置密码🍊！");
            message.setText("您重置验证码为："+code+",有效期3分钟，别再忘记啦(●'◡'●)");
        }
        message.setTo(email);
        message.setFrom(MyEmail);
        mailSender.send(message);
    }
}
