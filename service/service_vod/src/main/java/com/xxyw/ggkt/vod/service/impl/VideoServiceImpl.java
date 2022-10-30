package com.xxyw.ggkt.vod.service.impl;


import com.atguigu.ggkt.model.vod.Video;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xxyw.ggkt.vod.mapper.VideoMapper;
import com.xxyw.ggkt.vod.service.VideoService;
import org.springframework.stereotype.Service;

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

}
