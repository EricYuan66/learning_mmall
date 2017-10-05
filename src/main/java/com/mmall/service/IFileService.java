package com.mmall.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * Created by yqn19 on 2017-10-05.
 */
public interface IFileService {
    String upload(MultipartFile file, String path);
}
