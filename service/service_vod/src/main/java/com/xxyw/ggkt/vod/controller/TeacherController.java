package com.xxyw.ggkt.vod.controller;


import com.atguigu.ggkt.model.vod.Teacher;
import com.atguigu.ggkt.vo.vod.TeacherQueryVo;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xxyw.ggkt.result.Result;
import com.xxyw.ggkt.vod.service.TeacherService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 讲师 前端控制器
 * </p>
 *
 * @author xxyw
 * @since 2022-10-13
 */

@Api(tags = "讲师管理接口")
@RestController
@RequestMapping(value = "/admin/vod/teacher")
//@CrossOrigin
public class TeacherController {

    @Autowired
    TeacherService teacherService;

    // 1. 查询所有讲师
    @GetMapping("findAll")
    public Result findAllTeacher() {

        //try {
        //    int i = 10 / 0;
        //} catch (Exception e) {
        //    throw new GgktException(201, "自定义GgktException异常");
        //}

        List<Teacher> list = teacherService.list();
        return Result.ok(list).message("查询所有讲师成功");
    }

    // 2. 逻辑删除讲师
    @ApiOperation("逻辑删除讲师")
    @DeleteMapping("remove/{id}")
    public Result deleteTeacher(@ApiParam(name = "id", value = "ID", required = true) @PathVariable("id") Long id) {
        boolean res = teacherService.removeById(id);
        if (res) {
            return Result.ok(null);
        } else {
            return Result.fail(null);
        }
    }

    // 3. 分页条件查询
    @ApiOperation("分页条件查询")
    @PostMapping("page/{current}/{limit}")
    public Result findPage(@PathVariable("current") long current,
                           @PathVariable("limit") int limit,
                           @RequestBody(required = false) TeacherQueryVo teacherQueryVo) {
        Page<Teacher> page = new Page<>(current, limit);
        if (teacherQueryVo == null) {
            teacherService.page(page);
            return Result.ok(page);
        } else {
            LambdaQueryWrapper<Teacher> wrapper = new LambdaQueryWrapper<>();
            String name = teacherQueryVo.getName();
            if (!StringUtils.isEmpty(name)) {
                wrapper.like(Teacher::getName, name);
            }
            Integer level = teacherQueryVo.getLevel();
            if (!StringUtils.isEmpty(level)) {
                wrapper.eq(Teacher::getLevel, level);
            }
            String joinDateBegin = teacherQueryVo.getJoinDateBegin();
            if (!StringUtils.isEmpty(joinDateBegin)) {
                wrapper.ge(Teacher::getJoinDate, joinDateBegin);
            }
            String joinDateEnd = teacherQueryVo.getJoinDateEnd();
            if (!StringUtils.isEmpty(joinDateEnd)) {
                wrapper.le(Teacher::getJoinDate, joinDateEnd);
            }
            teacherService.page(page, wrapper);
            return Result.ok(page);
        }
    }

    // 4 添加讲师
    @ApiOperation("添加讲师")
    @PostMapping("saveTeacher")
    public Result saveTeacher(@RequestBody Teacher teacher) {
        boolean result = teacherService.save(teacher);
        if (result) {
            return Result.ok(null);
        } else {
            return Result.fail(null);
        }
    }

    // 5 修改——根据 id 查询
    @ApiOperation("根据ID查询讲师")
    @GetMapping("findTeacher/{id}")
    public Result findTeacher(@PathVariable("id") Long id) {
        Teacher teacher = teacherService.getById(id);
        return Result.ok(teacher);
    }

    // 6 修改
    @ApiOperation("修改操作")
    @PostMapping("updateTeacher")
    public Result updateTeacher(@RequestBody Teacher teacher) {
        boolean result = teacherService.updateById(teacher);
        if (result) {
            return Result.ok(null);
        } else {
            return Result.fail(null);
        }
    }

    // 7 批量删除
    @ApiOperation("批量删除")
    @DeleteMapping("deleteBatch")
    public Result deleteBatch(@RequestBody List<Long> idList) {
        boolean result = teacherService.removeByIds(idList);
        if (result) {
            return Result.ok(null);
        } else {
            return Result.fail(null);
        }
    }

}

