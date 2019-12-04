package com.cornucopia.upload.service;

import com.cornucopia.common.enums.ExceptionEnum;
import com.cornucopia.common.exception.ResponseException;
import com.cornucopia.upload.config.UploadProperties;
import com.github.tobato.fastdfs.domain.StorePath;
import com.github.tobato.fastdfs.service.FastFileStorageClient;
import com.netflix.discovery.converters.Auto;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author cornucopia
 * @version 1.0
 * @since 2019-09-10
 */

@Service
@EnableConfigurationProperties({UploadProperties.class})
public class UploadService {

    @Autowired
    private FastFileStorageClient storageClient;

    @Autowired
    private UploadProperties prop;


    public String uploadImage(MultipartFile file) {
        try {
            //校验文件类型
            String contentType = file.getContentType();
            if (!prop.getAllowTypes().contains(contentType)) {
                throw new ResponseException(ExceptionEnum.INVALID_FILE_TYPE);
            }
            //校验文件内容
            BufferedImage image = ImageIO.read(file.getInputStream());
            if (image == null) {
                throw new ResponseException(ExceptionEnum.INVALID_FILE_TYPE);
            }
//            //准备目标路径
//            File dest = new File("/Users/ivan/Desktop/x",file.getOriginalFilename());
//            //保存文件到本地
//            file.transferTo(dest);
//            //返回路径
//            return "http://image.cornucopia.com/"+file.getOriginalFilename();
            String extension = StringUtils.substringAfterLast(file.getOriginalFilename(), ".");
            StorePath storePath =
                    storageClient.uploadFile(file.getInputStream(), file.getSize(), extension, null);
            return prop.getBaseUrl()+storePath.getFullPath();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("上传失败!");
            throw new ResponseException(ExceptionEnum.UPLOAD_FILE_ERROR);
        }
    }
}
