package org.orange.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.orange.domain.entity.Tag;
import org.orange.mapper.TagMapper;
import org.orange.service.TagService;
import org.springframework.stereotype.Service;

/**
 * @BelongsProject: Orange_Blog
 * @ClassName TagServiceImpl
 * @Description TODO
 * @Author WangZJ0908
 * @Date 2024/8/8
 * @Version: 1.0
 */
@Service
public class TagServiceImpl extends ServiceImpl<TagMapper, Tag> implements TagService {
}
