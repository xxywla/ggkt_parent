package com.xxyw.ggkt.vod.controller;

import com.xxyw.ggkt.result.Result;
import com.xxyw.ggkt.vod.service.VodService;
import com.xxyw.ggkt.vod.utils.ConstantPropertiesUtil;
import com.xxyw.ggkt.vod.utils.Signature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Random;

@RestController
@RequestMapping("/admin/vod")
//@CrossOrigin
public class VodController {


    @Autowired
    private VodService vodService;

    // 上传视频到腾讯云
    @PostMapping("upload")
    public Result upload() {
        String fileId = vodService.uploadVideo();
        return Result.ok(fileId);
    }

    // 根据 ID 删除视频
    @DeleteMapping("remove/{fileId}")
    public Result remove(@PathVariable String fileId) {
        vodService.removeVideo(fileId);
        return Result.ok(null);
    }

    @GetMapping("sign")
    public Result sign() {
        Signature sign = new Signature();
        // 设置 App 的云 API 密钥
        sign.setSecretId(ConstantPropertiesUtil.ACCESS_KEY_ID);
        sign.setSecretKey(ConstantPropertiesUtil.ACCESS_KEY_SECRET);
        sign.setCurrentTime(System.currentTimeMillis() / 1000);
        sign.setRandom(new Random().nextInt(java.lang.Integer.MAX_VALUE));
        sign.setSignValidDuration(3600 * 24 * 2); // 签名有效期：2天
        try {
            String signature = sign.getUploadSignature();
            System.out.println("signature : " + signature);
            return Result.ok(signature);
        } catch (Exception e) {
            System.out.print("获取签名失败");
            e.printStackTrace();
            return Result.fail(null);
        }
    }

}
