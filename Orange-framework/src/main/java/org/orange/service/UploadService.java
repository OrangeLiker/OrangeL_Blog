package org.orange.service;

import org.orange.domain.response.ResponseResult;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface UploadService {
    ResponseResult uploadImage(MultipartFile file) throws IOException;
}
