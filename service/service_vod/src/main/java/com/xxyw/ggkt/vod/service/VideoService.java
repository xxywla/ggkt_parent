package com.xxyw.ggkt.vod.service;


import com.atguigu.ggkt.model.vod.Video;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 课程视频 服务类
 * </p>
 *
 * @author xxyw
 * @since 2022-10-27
 */
public interface VideoService extends IService<Video> {

    void removeByCourseId(Long id);

    void removeVideoById(Long id);
}
