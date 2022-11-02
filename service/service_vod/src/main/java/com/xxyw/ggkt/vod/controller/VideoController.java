package com.xxyw.ggkt.vod.controller;


import com.atguigu.ggkt.model.vod.Video;
import com.xxyw.ggkt.result.Result;
import com.xxyw.ggkt.vod.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 课程视频 前端控制器
 * </p>
 *
 * @author xxyw
 * @since 2022-10-27
 */
@RestController
@RequestMapping(value = "/admin/vod/video")
//@CrossOrigin
public class VideoController {

    @Autowired
    private VideoService videoService;

    // 1. 添加一个小节
    @PostMapping("save")
    public Result save(@RequestBody Video video) {

        videoService.save(video);

        return Result.ok(null);

    }

    // 2. 修改 根据 ID 查询
    @GetMapping("get/{id}")
    public Result get(@PathVariable Long id) {
        Video video = videoService.getById(id);
        return Result.ok(video);
    }

    // 3. 修改
    @PutMapping("update")
    public Result update(@RequestBody Video video) {
        videoService.updateById(video);
        return Result.ok(null);
    }


    // 4. 删除 根据 ID
    @DeleteMapping("remove/{id}")
    public Result remove(@PathVariable Long id) {
        videoService.removeVideoById(id);
        return Result.ok(null);
    }

}

