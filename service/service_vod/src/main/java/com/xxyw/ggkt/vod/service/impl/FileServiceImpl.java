package com.xxyw.ggkt.vod.service.impl;

import com.alibaba.fastjson.JSON;
import com.qcloud.cos.COSClient;
import com.qcloud.cos.ClientConfig;
import com.qcloud.cos.auth.BasicCOSCredentials;
import com.qcloud.cos.auth.COSCredentials;
import com.qcloud.cos.http.HttpProtocol;
import com.qcloud.cos.model.ObjectMetadata;
import com.qcloud.cos.model.PutObjectRequest;
import com.qcloud.cos.model.PutObjectResult;
import com.qcloud.cos.region.Region;
import com.xxyw.ggkt.vod.service.FileService;
import com.xxyw.ggkt.vod.utils.ConstantPropertiesUtil;
import org.joda.time.DateTime;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.UUID;

@Service
public class FileServiceImpl implements FileService {
    @Override
    public String uploadFile(MultipartFile file) {
        String secretId = ConstantPropertiesUtil.ACCESS_KEY_ID;
        String secretKey = ConstantPropertiesUtil.ACCESS_KEY_SECRET;
        COSCredentials cred = new BasicCOSCredentials(secretId, secretKey);
        Region region = new Region(ConstantPropertiesUtil.END_POINT);
        ClientConfig clientConfig = new ClientConfig(region);
        clientConfig.setHttpProtocol(HttpProtocol.https);
        COSClient cosClient = new COSClient(cred, clientConfig);

        String endpoint = ConstantPropertiesUtil.END_POINT;

        try {
            InputStream inputStream = file.getInputStream();
            String bucketName = ConstantPropertiesUtil.BUCKET_NAME;
            // 使用 UUID 防止重名覆盖
            String key = UUID.randomUUID().toString().replace("-", "") + file.getOriginalFilename();
            // 按照时间创建层级文件夹
            String dateUrl = new DateTime().toString("yyyy/MM/dd");
            key = dateUrl + "/" + key;
            ObjectMetadata objectMetadata = new ObjectMetadata();
            PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, key, inputStream, objectMetadata);
            PutObjectResult putObjectResult = cosClient.putObject(putObjectRequest);
            System.out.println(JSON.toJSONString(putObjectResult));
            return "https://" + bucketName + "." + "cos" + "." + endpoint + ".myqcloud.com" + "/" + key;
        } catch (Exception clientException) {
            clientException.printStackTrace();
            return null;
        }
    }
}
