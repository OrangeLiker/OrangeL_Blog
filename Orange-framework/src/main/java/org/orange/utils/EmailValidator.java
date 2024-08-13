package org.orange.utils;

import org.springframework.stereotype.Component;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @BelongsProject: Orange_Blog
 * @ClassName EmailValidator
 * @Description TODO
 * @Author WangZJ0908
 * @Date 2024/8/12
 * @Version: 1.0
 */
@Component
public class EmailValidator {

    // 定义邮箱的正则表达式
    private static final String EMAIL_PATTERN =
            "^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+$";

    // 创建 Pattern 对象
    private static final Pattern pattern = Pattern.compile(EMAIL_PATTERN);

    // 验证邮箱的方法
    public static boolean isValidEmail(String email) {
        // 创建 Matcher 对象
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }


}