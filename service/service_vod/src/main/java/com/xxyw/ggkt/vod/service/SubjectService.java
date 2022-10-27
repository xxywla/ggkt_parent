package com.xxyw.ggkt.vod.service;


import com.atguigu.ggkt.model.vod.Subject;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * <p>
 * 课程科目 服务类
 * </p>
 *
 * @author xxyw
 * @since 2022-10-26
 */
public interface SubjectService extends IService<Subject> {

    List<Subject> getChildSubject(Integer id);

    void exportData(HttpServletResponse httpResponse);

    void importData(MultipartFile file);
}
