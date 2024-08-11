package org.orange.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.orange.domain.dto.TagListDto;
import org.orange.domain.entity.Tag;
import org.orange.domain.response.ResponseResult;
import org.orange.domain.vo.PageVo;
import org.orange.domain.vo.TagVo;

import java.util.List;

public interface TagService extends IService<Tag> {

    ResponseResult<PageVo> getTagList(Integer pageNum, Integer pageSize, TagListDto tagListDto);

    ResponseResult addTag(TagListDto tagListDto);

    ResponseResult deleteTag(List<Long> id);

    ResponseResult getTag(Long id);

    ResponseResult updateTag(TagVo tagVo);

    ResponseResult getAllTag();
}
