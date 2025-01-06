package org.orange.utils;

import com.github.houbb.sensitive.word.core.SensitiveWordHelper;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @BelongsProject: Orange_Blog
 * @ClassName SensitiveWordUtil
 * @Description 敏感词工具类
 * @Author WangZJ0908
 * @Date 2025/1/6
 * @Version: 1.0
 */
@Component
public class SensitiveWordUtil {
    // 判断是否含有敏感词
    public boolean contains(String text) {
        return SensitiveWordHelper.contains(text);
    }

    // 使用默认替换符 * 进行替换敏感词
    public String replace(String text) {
        return SensitiveWordHelper.replace(text);
    }

    // 返回所有敏感词
    public List<String> findAll(String text) {
        return SensitiveWordHelper.findAll(text);
    }
}
