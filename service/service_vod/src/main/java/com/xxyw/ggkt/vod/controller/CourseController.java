package com.xxyw.ggkt.vod.controller;


import com.atguigu.ggkt.model.vod.Course;
import com.atguigu.ggkt.vo.vod.CourseFormVo;
import com.atguigu.ggkt.vo.vod.CourseQueryVo;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xxyw.ggkt.result.Result;
import com.xxyw.ggkt.vod.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * <p>
 * 课程 前端控制器
 * </p>
 *
 * @author xxyw
 * @since 2022-10-27
 */
@RestController
@RequestMapping("/admin/vod/course")
@CrossOrigin
public class CourseController {

    @Autowired
    private CourseService courseService;

    // 修改课程信息
    @PostMapping("update")
    public Result updateCourseInfo(@RequestBody CourseFormVo courseFormVo) {

        courseService.updateCourseInfo(courseFormVo);

        return Result.ok(null);
    }

    // 根据 ID 获取课程信息
    @GetMapping("get/{id}")
    public Result getCourseInfo(@PathVariable("id") Long id) {
        CourseFormVo courseFormVo = courseService.getCourseInfo(id);
        return Result.ok(courseFormVo);
    }

    // 添加课程
    @PostMapping("save")
    public Result save(@RequestBody CourseFormVo courseFormVo) {

        long id = courseService.saveCourseInfo(courseFormVo);

        return Result.ok(id);
    }

    // 分页 条件 查询课程
    @GetMapping("/page/{curPage}/{limit}")
    public Result page(@PathVariable("curPage") Integer curPage,
                       @PathVariable("limit") Integer limit,
                       CourseQueryVo courseQueryVo) {
        Page<Course> page = new Page<>(curPage, limit);

        Map<String, Object> map = courseService.getCourse(page, courseQueryVo);

        return Result.ok(map);
    }
}

