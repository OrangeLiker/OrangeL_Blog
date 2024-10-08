package org.orange.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.orange.annotation.SystemLog;
import org.orange.domain.enums.AppHttpCodeEnum;
import org.orange.domain.response.ResponseResult;
import org.orange.service.UploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * @BelongsProject: Orange_Blog
 * @ClassName UploadController
 * @Description 上传文件接口
 * @Author WangZJ0908
 * @Date 2024/8/7
 * @Version: 1.0
 */
@RestController
@Api(tags = "上传文件模块",description = "上传文件模块相关接口")
public class UploadController {

    @Autowired
    private UploadService uploadService;

    @PostMapping("/upload")
    public ResponseResult uploadFile(@RequestParam("img") MultipartFile file) throws IOException {
        if(file==null){
            return ResponseResult.errorResult(AppHttpCodeEnum.FILE_NOTEMPTY,"文件不能为空");
        }
        return uploadService.uploadImage(file);
    }
}
