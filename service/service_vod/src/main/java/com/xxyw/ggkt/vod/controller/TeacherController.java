package com.xxyw.ggkt.vod.controller;


import com.atguigu.ggkt.model.vod.Teacher;
import com.xxyw.ggkt.vod.service.TeacherService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
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
public class TeacherController {

    @Autowired
    TeacherService teacherService;

    // 1. 查询所有讲师
    @GetMapping("findAll")
    public List<Teacher> findAllTeacher() {
        List<Teacher> list = teacherService.list();
        return list;
    }

    // 2. 逻辑删除讲师
    @ApiOperation("逻辑删除讲师")
    @DeleteMapping("remove/{id}")
    public boolean deleteTeacher(@ApiParam(name = "id", value = "ID", required = true) @PathVariable("id") Long id) {
        boolean res = teacherService.removeById(id);
        return res;
    }

}

