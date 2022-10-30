package com.xxyw.ggkt.vod.controller;


import com.atguigu.ggkt.model.vod.Chapter;
import com.atguigu.ggkt.vo.vod.ChapterVo;
import com.xxyw.ggkt.result.Result;
import com.xxyw.ggkt.vod.service.ChapterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 课程 前端控制器
 * </p>
 *
 * @author xxyw
 * @since 2022-10-27
 */
@RestController
@RequestMapping(value = "/admin/vod/chapter")
@CrossOrigin
public class ChapterController {

    @Autowired
    private ChapterService chapterService;

    // 1. 获取课程 的 章节 小节 列表
    @GetMapping("getNestedTreeList/{courseId}")
    public Result getChapterList(@PathVariable Long courseId) {

        List<ChapterVo> chapterVoList = chapterService.getChapterList(courseId);

        return Result.ok(chapterVoList);
    }

    // 2. 添加章节
    @PostMapping("save")
    public Result save(@RequestBody Chapter chapter) {

        chapterService.save(chapter);

        return Result.ok(null);
    }

    // 3. 修改 根据 ID 查询章节
    @GetMapping("get/{id}")
    public Result get(@PathVariable Long id) {

        Chapter chapter = chapterService.getById(id);

        return Result.ok(chapter);
    }

    // 4. 修改
    @PostMapping("update")
    public Result update(@RequestBody Chapter chapter) {

        chapterService.updateById(chapter);

        return Result.ok(null);
    }

    // 5. 删除章节
    @DeleteMapping("remove/{id}")
    public Result remove(@PathVariable("id") Long id) {

        chapterService.removeById(id);

        return Result.ok(null);
    }

}

