package com.xxyw.ggkt.vod.service.impl;


import com.atguigu.ggkt.model.vod.VideoVisitor;
import com.atguigu.ggkt.vo.vod.VideoVisitorCountVo;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xxyw.ggkt.vod.mapper.VideoVisitorMapper;
import com.xxyw.ggkt.vod.service.VideoVisitorService;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * <p>
 * 视频来访者记录表 服务实现类
 * </p>
 *
 * @author xxyw
 * @since 2022-11-01
 */
@Service
public class VideoVisitorServiceImpl extends ServiceImpl<VideoVisitorMapper, VideoVisitor> implements VideoVisitorService {

    @Override
    public Map<String, Object> findCount(Long courseId, String startDate, String endDate) {
        List<VideoVisitorCountVo> list = baseMapper.selectVideoVisitorCountVo(courseId, startDate, endDate);
        Map<String, Object> map = new HashMap<>();
        List<String> dateList = list.stream().map(VideoVisitorCountVo::getJoinTime).collect(Collectors.toList());
        List<Integer> countList = list.stream().map(VideoVisitorCountVo::getUserCount).collect(Collectors.toList());
        map.put("xData", dateList);
        map.put("yData", countList);
        return map;
    }
}
