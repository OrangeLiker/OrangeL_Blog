package org.orange.utils;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.model.PutObjectRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 * @BelongsProject: Orange_Blog
 * @ClassName OssUtils
 * @Description TODO
 * @Author WangZJ0908
 * @Date 2024/8/7
 * @Version: 1.0
 */
@Component
public class OssUtils {

    private static final String endpoint = "oss-cn-beijing.aliyuncs.com";
    private static final String accessKeyId = "LTAI5tFxFDP3RMvGa9iYZtz7";
    private static final String accessKeySecret = "Ylofh4WURkWJZWXoDMiW2sWySWVEoo";
    private static final String bucketName = "study-note908";

    public String uploadFile(MultipartFile multipartFile) throws IOException {
        // 创建OSSClient实例
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);

        try {
            // 获取当前日期，创建对应的文件夹路径
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM");
            String datePath = dateFormat.format(new Date());

            if (multipartFile == null || multipartFile.isEmpty()) {
                throw new RuntimeException("文件为空");
            }

            // 获取文件名
            String originalFileName = multipartFile.getOriginalFilename();
            String fileExtension = "";
            if (originalFileName != null && originalFileName.contains(".")) {
                fileExtension = originalFileName.substring(originalFileName.lastIndexOf("."));
            }

            // 生成唯一文件名
            String uniqueFileName = UUID.randomUUID().toString() + fileExtension;

            // 拼接文件路径
            String objectName = "OrangeBlog/images/" + datePath + "/" + uniqueFileName;

            // 上传文件到OSS
            ossClient.putObject(new PutObjectRequest(
                    bucketName, objectName, multipartFile.getInputStream()));

            // 返回文件的URL
            return "https://" + bucketName + "." + endpoint + "/" + objectName;
        } finally {
            // 关闭OSSClient
            ossClient.shutdown();
        }
    }
}
