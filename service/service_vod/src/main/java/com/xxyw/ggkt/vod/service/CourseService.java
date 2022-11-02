package com.xxyw.ggkt.vod.service;


import com.atguigu.ggkt.model.vod.Course;
import com.atguigu.ggkt.vo.vod.CourseFormVo;
import com.atguigu.ggkt.vo.vod.CoursePublishVo;
import com.atguigu.ggkt.vo.vod.CourseQueryVo;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author xxyw
 * @since 2022-10-27
 */
public interface CourseService extends IService<Course> {

    Map<String, Object> getCourse(Page<Course> page, CourseQueryVo courseQueryVo);

    long saveCourseInfo(CourseFormVo courseFormVo);

    void updateCourseInfo(CourseFormVo courseFormVo);

    CourseFormVo getCourseInfo(Long id);

    CoursePublishVo getCoursePublishVo(Long id);

    void publishCourseById(Long id);

    void removeCourseById(Long id);
}
