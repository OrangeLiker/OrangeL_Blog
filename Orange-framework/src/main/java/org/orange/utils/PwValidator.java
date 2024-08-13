package org.orange.utils;

import org.springframework.stereotype.Component;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @BelongsProject: Orange_Blog
 * @ClassName PwValidator
 * @Description TODO
 * @Author WangZJ0908
 * @Date 2024/8/12
 * @Version: 1.0
 */
@Component
public class PwValidator {

    // 定义密码的正则表达式
    private static final String PASSWORD_PATTERN =
            "^[a-zA-Z0-9_]{6,16}$";

    // 创建 Pattern 对象
    private static final Pattern pattern = Pattern.compile(PASSWORD_PATTERN);

    // 验证密码的方法
    public boolean isValidPassword(String password) {
        // 创建 Matcher 对象
        Matcher matcher = pattern.matcher(password);
        return matcher.matches();
    }
}