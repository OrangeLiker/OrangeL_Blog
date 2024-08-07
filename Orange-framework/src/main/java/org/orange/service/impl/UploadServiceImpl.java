package org.orange.service.impl;

import org.orange.domain.enums.AppHttpCodeEnum;
import org.orange.domain.response.ResponseResult;
import org.orange.exception.SystemException;
import org.orange.service.UploadService;
import org.orange.utils.OssUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * @BelongsProject: Orange_Blog
 * @ClassName UploadServiceImpl
 * @Description TODO
 * @Author WangZJ0908
 * @Date 2024/8/7
 * @Version: 1.0
 */
@Service
public class UploadServiceImpl implements UploadService {
    @Autowired
    private OssUtils ossUtils;
    @Override
    public ResponseResult uploadImage(MultipartFile file) throws IOException {
        //TODO 判断文件类型及文件大小
        String originalFilename=file.getOriginalFilename();
        if(!originalFilename.endsWith(".png")&&!originalFilename.endsWith(".jpg")){
            throw new SystemException(AppHttpCodeEnum.FILE_TYPE_ERROR);
        }
        //上传文件到OSS
        String url=ossUtils.uploadFile(file);
        return ResponseResult.okResult(url);
    }
}
