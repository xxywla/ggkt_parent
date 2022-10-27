package com.xxyw.ggkt.vod.controller;


import com.atguigu.ggkt.model.vod.Subject;
import com.xxyw.ggkt.result.Result;
import com.xxyw.ggkt.vod.service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * <p>
 * 课程科目 前端控制器
 * </p>
 *
 * @author xxyw
 * @since 2022-10-26
 */
@RestController
@RequestMapping(value = "/admin/vod/subject")
@CrossOrigin
public class SubjectController {

    @Autowired
    private SubjectService subjectService;

    // 获取父节点是 ID 的子节点 课程
    @GetMapping("childSubject/{id}")
    public Result getChildSubject(@PathVariable("id") Integer id) {
        List<Subject> subjectList = subjectService.getChildSubject(id);
        return Result.ok(subjectList);
    }

    // 导出课程到 Excel表格
    @GetMapping("exportData")
    public void exportData(HttpServletResponse httpResponse) {
        subjectService.exportData(httpResponse);
    }

    // 把上传的 Excel表格的数据导入数据库
    @PostMapping("importData")
    public Result importData(MultipartFile file) {
        subjectService.importData(file);
        return Result.ok(null);
    }
}

