package org.orange.utils;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.orange.constans.SystemConstants;
import org.orange.domain.entity.User;
import org.orange.domain.enums.AppHttpCodeEnum;
import org.orange.domain.response.ResponseResult;
import org.orange.exception.SystemException;
import org.orange.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;
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
    @Autowired
    private UserMapper userMapper;
    private static final String MyEmail="2814964382@qq.com";
    private static final String Blog_Name="OrangeLiker";
    private String generateVerificationCode(){
        return String.valueOf((int) ((Math.random() * 9 + 1) * 100000));
    }
    public ResponseResult sendVerificationEmail(String email, String type) throws MessagingException, UnsupportedEncodingException {
        //如果是注册，需要对注册邮箱进行查重
        if(type.equals("register")){
            LambdaQueryWrapper<User> wrapper=new LambdaQueryWrapper<>();
            wrapper.eq(User::getEmail,email);
            User user = userMapper.selectOne(wrapper);
            if(user!=null){
                throw new SystemException(AppHttpCodeEnum.EMAIL_EXIST);
            }
        }
        String code = generateVerificationCode();
        String key = SystemConstants.VERIFY_EMAIL_DATA+email;
        //将验证码存入缓存
        redisCache.setCacheObject(key,code,3, TimeUnit.MINUTES);
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper=new MimeMessageHelper(message,true);
        if(type.equals("register")){
            helper.setSubject("欢迎注册OrangeLiker'sBlog🍊");
            helper.setText("您的注册验证码为："+code+",有效期3分钟，请您趁热哦☕");
        }else if(type.equals("reset")){
            helper.setSubject("重置密码🍊！");
            helper.setText("您重置验证码为："+code+",有效期3分钟，别再忘记啦(●'◡'●)");
        }
        //设置收件人，发件人
        helper.setTo(email);
        helper.setFrom(MyEmail,Blog_Name);
        //发送邮件
        mailSender.send(message);
        return new ResponseResult();
    }
}
