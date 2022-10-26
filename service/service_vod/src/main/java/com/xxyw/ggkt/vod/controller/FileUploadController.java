package com.xxyw.ggkt.vod.controller;

import com.xxyw.ggkt.result.Result;
import com.xxyw.ggkt.vod.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/admin/vod/file")
@CrossOrigin
public class FileUploadController {

    @Autowired
    private FileService fileService;

    @PostMapping("upload")
    public Result uploadFile(MultipartFile file) {
        String url = fileService.uploadFile(file);
        return Result.ok(url).message("文件上传成功");
    }
}
