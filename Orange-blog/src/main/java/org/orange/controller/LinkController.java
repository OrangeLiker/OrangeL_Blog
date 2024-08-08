package org.orange.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.orange.annotation.SystemLog;
import org.orange.domain.response.ResponseResult;
import org.orange.service.LinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @BelongsProject: Orange_Blog
 * @ClassName LinkController
 * @Description TODO
 * @Author WangZJ0908
 * @Date 2024/8/5
 * @Version: 1.0
 */
@RestController
@RequestMapping("/link")
@Api(tags = "友情链接模块",description = "友情链接模块相关接口")
public class LinkController {
    @Resource
    private LinkService linkService;
    @GetMapping("/getAllLink")
    @SystemLog(businessName = "查询所有友情链接")
    @ApiOperation("查询所有友情链接")
    public ResponseResult getAllLink(){
        return linkService.getAllLink();
    }
}
