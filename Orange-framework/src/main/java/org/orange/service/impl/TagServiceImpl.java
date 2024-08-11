package org.orange.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.orange.domain.dto.TagListDto;
import org.orange.domain.entity.Tag;
import org.orange.domain.enums.AppHttpCodeEnum;
import org.orange.domain.response.ResponseResult;
import org.orange.domain.vo.PageVo;
import org.orange.domain.vo.TagVo;
import org.orange.exception.SystemException;
import org.orange.mapper.TagMapper;
import org.orange.service.TagService;
import org.orange.utils.BeanCopyUtils;
import org.orange.utils.SecurityUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.Security;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

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
    @Autowired
    private TagMapper tagMapper;
    @Override
    public ResponseResult<PageVo> getTagList(Integer pageNum, Integer pageSize, TagListDto tagListDto) {
        //分页查询
        LambdaQueryWrapper<Tag> queryWrapper=new LambdaQueryWrapper();
        if (tagListDto.getName() != null && !tagListDto.getName().trim().isEmpty()) {
            queryWrapper.like(Tag::getName, tagListDto.getName().toLowerCase());
        }

        // 检查 remark 是否为空，如果不为空则添加模糊查询条件
        if (tagListDto.getRemark() != null && !tagListDto.getRemark().trim().isEmpty()) {
            queryWrapper.like(Tag::getRemark, tagListDto.getRemark().toLowerCase());
        }
        queryWrapper.eq(Tag::getDelFlag,0);
        String name=tagListDto.getName();
        String remark=tagListDto.getRemark();
        Page<Tag> page=new Page<>();
        page.setCurrent(pageNum);
        page.setSize(pageSize);
        page(page, queryWrapper);
        //封装
        PageVo pageVo=new PageVo(page.getRecords(),page.getTotal());
        return ResponseResult.okResult(pageVo);
    }
    //新增标签
    @Override
    public ResponseResult addTag(TagListDto tagListDto) {
        Tag tag = BeanCopyUtils.copyBean(tagListDto, Tag.class);
        tag.setCreateBy(SecurityUtils.getUserId());
        tag.setCreateTime(new Date());
        tag.setUpdateBy(SecurityUtils.getUserId());
        tag.setUpdateTime(new Date());
        tag.setDelFlag(0);
        tagMapper.insert(tag);
        return ResponseResult.okResult();
    }

    @Override
    public ResponseResult deleteTag(List<Long> id) {
        for (Long tagId : id) {
            Tag tag = tagMapper.selectById(tagId);
            if (tag == null) {
                throw new SystemException(AppHttpCodeEnum.TAG_NOT_FOUND);
            }
            tag.setDelFlag(1);
            tag.setUpdateBy(SecurityUtils.getUserId());
            tag.setUpdateTime(new Date());
            tagMapper.updateById(tag);
        }
        return ResponseResult.okResult();
    }

    @Override
    public ResponseResult getTag(Long id) {
        Tag tag = tagMapper.selectById(id);
        TagVo tagVo = BeanCopyUtils.copyBean(tag, TagVo.class);
        return ResponseResult.okResult(tagVo);
    }

    @Override
    public ResponseResult updateTag(TagVo tagVo) {
        Tag tag = tagMapper.selectById(tagVo.getId());
        tag.setName(tagVo.getName());
        tag.setRemark(tagVo.getRemark());
        tag.setUpdateBy(SecurityUtils.getUserId());
        tag.setUpdateTime(new Date());
        tagMapper.updateById(tag);
        return ResponseResult.okResult();
    }

    @Override
    public ResponseResult getAllTag() {
        LambdaQueryWrapper<Tag> queryWrapper=new LambdaQueryWrapper();
        queryWrapper.eq(Tag::getDelFlag,0);
        List<Tag> tags = tagMapper.selectList(queryWrapper);
        List<TagVo> tagVos = BeanCopyUtils.copyBeanList(tags, TagVo.class);
        return ResponseResult.okResult(tagVos);
    }


}
