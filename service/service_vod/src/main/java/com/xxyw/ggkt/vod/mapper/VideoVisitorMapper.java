package com.xxyw.ggkt.vod.mapper;


import com.atguigu.ggkt.model.vod.VideoVisitor;
import com.atguigu.ggkt.vo.vod.VideoVisitorCountVo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 视频来访者记录表 Mapper 接口
 * </p>
 *
 * @author xxyw
 * @since 2022-11-01
 */
public interface VideoVisitorMapper extends BaseMapper<VideoVisitor> {

    List<VideoVisitorCountVo> selectVideoVisitorCountVo(@Param("courseId") Long courseId, @Param("startDate") String startDate, @Param("endDate") String endDate);
}
