package org.orange.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.orange.domain.dto.LinkDto;
import org.orange.domain.entity.Link;
import org.orange.domain.response.ResponseResult;

import java.util.List;

public interface LinkService extends IService<Link> {

    ResponseResult getAllLink();

    ResponseResult getAllLinks(Integer pageNum, Integer pageSize, String name, String status);

    ResponseResult addLink(LinkDto linkDto);

    ResponseResult getLink(Long id);

    ResponseResult updateLink(LinkDto linkDto);

    ResponseResult deleteLink(List<Long> id);
}
