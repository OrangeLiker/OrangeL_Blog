package org.orange.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.orange.domain.entity.Link;
import org.orange.domain.response.ResponseResult;

public interface LinkService extends IService<Link> {

    ResponseResult getAllLink();
}
