package com.mmall.service.Impl;


import com.google.common.collect.Lists;
import com.mmall.service.IFileService;
import com.mmall.util.FtpUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

/**
 * Created by yqn19 on 2017-10-05.
 */
@Service("iFileService")
public class FileServiceImpl implements IFileService{

    private Logger logger = LoggerFactory.getLogger(FileServiceImpl.class);

    public String upload(MultipartFile file, String path) {
        String filename = file.getOriginalFilename();
        //扩展名
        String fileExtensionName = filename.substring(filename.lastIndexOf(".") + 1);
        //文件名
        String uploadFilename = UUID.randomUUID().toString() + "." + fileExtensionName;
        logger.info("开始上传文件，上传的文件名：{},上传的路径：{}，新文件名：{}", filename, path, uploadFilename);

        File fileDir = new File(path);
        if (!fileDir.exists()) {
            fileDir.setWritable(true);
            fileDir.mkdirs();
        }
        File targetFile = new File(path, uploadFilename);

        try {
            //此处上传文件
            file.transferTo(targetFile);
            //将targetFile上传到我们的FTP服务器
            FtpUtil.uploadFile(Lists.newArrayList(targetFile));
            //上传完之后，删除upload下面的文件，因为该文件存在于tomcat服务器之下，太多的文件会导致服务器变大变慢
            targetFile.delete();
        } catch (IOException e) {
            logger.error("上传文件异常", e);
            return null;
        }
        return targetFile.getName();
    }
}
