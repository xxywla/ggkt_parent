package com.xxyw.ggkt.vod.service.impl;


import com.alibaba.excel.EasyExcel;
import com.atguigu.ggkt.model.vod.Subject;
import com.atguigu.ggkt.vo.vod.SubjectEeVo;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xxyw.ggkt.exception.GgktException;
import com.xxyw.ggkt.vod.listener.SubjectListener;
import com.xxyw.ggkt.vod.mapper.SubjectMapper;
import com.xxyw.ggkt.vod.service.SubjectService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 课程科目 服务实现类
 * </p>
 *
 * @author xxyw
 * @since 2022-10-26
 */
@Service
public class SubjectServiceImpl extends ServiceImpl<SubjectMapper, Subject> implements SubjectService {


    @Autowired
    private SubjectListener subjectListener;

    @Override
    public List<Subject> getChildSubject(Integer id) {
        QueryWrapper<Subject> qw = new QueryWrapper<>();
        qw.eq("parent_id", id);
        List<Subject> subjectList = baseMapper.selectList(qw);
        for (Subject subject : subjectList) {
            QueryWrapper<Subject> qw2 = new QueryWrapper<>();
            qw2.eq("parent_id", subject.getId());
            subject.setHasChildren(baseMapper.selectCount(qw2) > 0);
        }
        return subjectList;
    }

    @Override
    public void exportData(HttpServletResponse response) {

        try {

            response.setContentType("application/vnd.ms-excel");
            response.setCharacterEncoding("utf-8");
            // 这里URLEncoder.encode可以防止中文乱码 当然和easyexcel没有关系
            String fileName = URLEncoder.encode("课程分类", "UTF-8");
            response.setHeader("Content-disposition", "attachment;filename=" + fileName + ".xlsx");

            // 查询出所有的课程
            List<Subject> subjectList = baseMapper.selectList(null);

            // 赋值
            List<SubjectEeVo> data = new ArrayList<>(subjectList.size());

            for (Subject subject : subjectList) {
                SubjectEeVo subjectEeVo = new SubjectEeVo();
                BeanUtils.copyProperties(subject, subjectEeVo);

                data.add(subjectEeVo);
            }

            // 使用 EasyExcel生成 Excel文件
            EasyExcel.write(response.getOutputStream(), SubjectEeVo.class).sheet("课程分类").doWrite(data);

        } catch (Exception e) {
            throw new GgktException(20001, "导出失败");
        }


    }

    @Override
    public void importData(MultipartFile file) {

        try {

            EasyExcel.read(file.getInputStream(), SubjectEeVo.class, subjectListener).sheet().doRead();
        } catch (Exception e) {
            throw new GgktException(20001, "上传课程数据异常");
        }

    }
}
