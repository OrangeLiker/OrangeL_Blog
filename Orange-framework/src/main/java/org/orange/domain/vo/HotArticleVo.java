package org.orange.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @BelongsProject: Orange_Blog
 * @ClassName HotArticleVo
 * @Description 响应封装VO
 * @Author WangZJ0908
 * @Date 2024/8/4
 * @Version: 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class HotArticleVo {
    private  Long id;
    private  String title;
    private  Long viewCount;
}
