package com.xxyw.ggkt.vod.service;


import com.atguigu.ggkt.model.vod.VideoVisitor;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;


/**
 * <p>
 * 视频来访者记录表 服务类
 * </p>
 *
 * @author xxyw
 * @since 2022-11-01
 */
public interface VideoVisitorService extends IService<VideoVisitor> {

    Map<String, Object> findCount(Long courseId, String startDate, String endDate);
}
