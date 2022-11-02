package com.xxyw.ggkt.vod.service.impl;


import com.atguigu.ggkt.model.vod.Video;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xxyw.ggkt.vod.mapper.VideoMapper;
import com.xxyw.ggkt.vod.service.VideoService;
import com.xxyw.ggkt.vod.service.VodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * <p>
 * 课程视频 服务实现类
 * </p>
 *
 * @author xxyw
 * @since 2022-10-27
 */
@Service
public class VideoServiceImpl extends ServiceImpl<VideoMapper, Video> implements VideoService {

    @Autowired
    private VodService vodService;

    @Override
    public void removeByCourseId(Long id) {

        // 获取课程 ID 对应的小节
        LambdaQueryWrapper<Video> lqw = new LambdaQueryWrapper<>();
        lqw.eq(Video::getCourseId, id);

        List<Video> videoList = baseMapper.selectList(lqw);

        for (Video video : videoList) {
            if (!StringUtils.isEmpty(video.getVideoSourceId())) {
                vodService.removeVideo(video.getVideoSourceId());
            }
        }

        baseMapper.delete(lqw);

    }

    @Override
    public void removeVideoById(Long id) {
        // 根据 id 查询出视频
        Video video = baseMapper.selectById(id);
        // 根据 id 删除腾讯云上的视频
        if (!StringUtils.isEmpty(video.getVideoSourceId())) {
            vodService.removeVideo(video.getVideoSourceId());
        }
        baseMapper.deleteById(id);
    }
}
