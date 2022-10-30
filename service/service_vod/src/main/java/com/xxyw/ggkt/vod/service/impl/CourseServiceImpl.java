package com.xxyw.ggkt.vod.service.impl;


import com.atguigu.ggkt.model.vod.Course;
import com.atguigu.ggkt.model.vod.CourseDescription;
import com.atguigu.ggkt.model.vod.Subject;
import com.atguigu.ggkt.model.vod.Teacher;
import com.atguigu.ggkt.vo.vod.CourseFormVo;
import com.atguigu.ggkt.vo.vod.CourseQueryVo;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xxyw.ggkt.vod.mapper.CourseMapper;
import com.xxyw.ggkt.vod.mapper.SubjectMapper;
import com.xxyw.ggkt.vod.mapper.TeacherMapper;
import com.xxyw.ggkt.vod.service.CourseDescriptionService;
import com.xxyw.ggkt.vod.service.CourseService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 课程 服务实现类
 * </p>
 *
 * @author xxyw
 * @since 2022-10-27
 */
@Service
public class CourseServiceImpl extends ServiceImpl<CourseMapper, Course> implements CourseService {


    @Autowired
    private TeacherMapper teacherMapper;

    @Autowired
    private SubjectMapper subjectMapper;

    @Autowired
    private CourseDescriptionService courseDescriptionService;

    @Override
    public Map<String, Object> getCourse(Page<Course> page, CourseQueryVo courseQueryVo) {

        LambdaQueryWrapper<Course> lqw = new LambdaQueryWrapper<>();

        Long teacherId = courseQueryVo.getTeacherId();
        Long subjectId = courseQueryVo.getSubjectId();
        Long subjectParentId = courseQueryVo.getSubjectParentId();
        String title = courseQueryVo.getTitle();

        if (!StringUtils.isEmpty(title)) {
            lqw.like(Course::getTitle, title);
        }
        if (!StringUtils.isEmpty(teacherId)) {
            lqw.eq(Course::getTeacherId, teacherId);
        }
        if (!StringUtils.isEmpty(subjectId)) {
            lqw.eq(Course::getSubjectId, subjectId);
        }
        if (!StringUtils.isEmpty(subjectParentId)) {
            lqw.eq(Course::getSubjectParentId, subjectParentId);
        }

        baseMapper.selectPage(page, lqw);

        Map<String, Object> map = new HashMap<>();

        // 总记录数
        map.put("totalCount", page.getTotal());
        // 总页数
        map.put("totalPage", page.getPages());
        List<Course> records = page.getRecords();
        records.forEach(this::getName);
        map.put("records", records);

        return map;
    }

    @Override
    public long saveCourseInfo(CourseFormVo courseFormVo) {

        // 添加课程
        Course course = new Course();
        BeanUtils.copyProperties(courseFormVo, course);
        baseMapper.insert(course);

        // 添加课程描述
        CourseDescription courseDescription = new CourseDescription();
        courseDescription.setId(course.getId());
        courseDescription.setDescription(courseFormVo.getDescription());

        courseDescriptionService.save(courseDescription);

        return course.getId();
    }

    @Override
    public void updateCourseInfo(CourseFormVo courseFormVo) {

        Course course = new Course();
        BeanUtils.copyProperties(courseFormVo, course);

        baseMapper.updateById(course);

        CourseDescription description = new CourseDescription();
        description.setId(courseFormVo.getId());
        description.setDescription(courseFormVo.getDescription());

        courseDescriptionService.updateById(description);

    }

    @Override
    public CourseFormVo getCourseInfo(Long id) {

        CourseFormVo courseFormVo = new CourseFormVo();

        Course course = baseMapper.selectById(id);

        if (course != null) {
            BeanUtils.copyProperties(course, courseFormVo);
        }

        CourseDescription description = courseDescriptionService.getById(id);

        if (description != null) {
            courseFormVo.setDescription(description.getDescription());
        }

        return courseFormVo;
    }

    private void getName(Course course) {

        Map<String, Object> param = course.getParam();

        Teacher teacher = teacherMapper.selectById(course.getTeacherId());
        if (teacher != null) {
            param.put("teacherName", teacher.getName());
        }

        Subject subject = subjectMapper.selectById(course.getSubjectParentId());
        if (subject != null) {
            param.put("subjectParentTitle", subject.getTitle());
        }

        Subject subject1 = subjectMapper.selectById(course.getSubjectId());
        if (subject1 != null) {
            param.put("subjectTitle", subject1.getTitle());
        }

    }
}
