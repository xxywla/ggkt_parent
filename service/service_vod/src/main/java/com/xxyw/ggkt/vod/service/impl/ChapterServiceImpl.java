package com.xxyw.ggkt.vod.service.impl;


import com.atguigu.ggkt.model.vod.Chapter;
import com.atguigu.ggkt.model.vod.Video;
import com.atguigu.ggkt.vo.vod.ChapterVo;
import com.atguigu.ggkt.vo.vod.VideoVo;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xxyw.ggkt.vod.mapper.ChapterMapper;
import com.xxyw.ggkt.vod.service.ChapterService;
import com.xxyw.ggkt.vod.service.VideoService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 课程 服务实现类
 * </p>
 *
 * @author xxyw
 * @since 2022-10-27
 */
@Service
public class ChapterServiceImpl extends ServiceImpl<ChapterMapper, Chapter> implements ChapterService {

    @Autowired
    private VideoService videoService;

    @Override
    public List<ChapterVo> getChapterList(Long courseId) {


        // 获取章节列表
        LambdaQueryWrapper<Chapter> chapterLQW = new LambdaQueryWrapper<>();
        chapterLQW.eq(Chapter::getCourseId, courseId);
        List<Chapter> chapterList = baseMapper.selectList(chapterLQW);


        // 获取小节列表
        LambdaQueryWrapper<Video> videoLQW = new LambdaQueryWrapper<>();
        videoLQW.eq(Video::getCourseId, courseId);
        List<Video> videoList = videoService.list(videoLQW);

        List<ChapterVo> chapterVoList = new ArrayList<>();

        for (Chapter chapter : chapterList) {
            ChapterVo chapterVo = new ChapterVo();
            BeanUtils.copyProperties(chapter, chapterVo);

            List<VideoVo> videoVoList = new ArrayList<>();

            for (Video video : videoList) {
                if (video.getChapterId().equals(chapterVo.getId())) {

                    VideoVo videoVo = new VideoVo();

                    BeanUtils.copyProperties(video, videoVo);

                    videoVoList.add(videoVo);
                }
            }

            chapterVo.setChildren(videoVoList);

            chapterVoList.add(chapterVo);
        }

        return chapterVoList;
    }
}
