package com.xxyw.ggkt.activity.service.impl;


import com.atguigu.ggkt.model.activity.CouponInfo;
import com.atguigu.ggkt.model.activity.CouponUse;
import com.atguigu.ggkt.model.user.UserInfo;
import com.atguigu.ggkt.vo.activity.CouponUseQueryVo;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xxyw.ggkt.activity.mapper.CouponInfoMapper;
import com.xxyw.ggkt.activity.service.CouponInfoService;
import com.xxyw.ggkt.activity.service.CouponUseService;
import com.xxyw.ggkt.client.user.UserInfoFeignClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * <p>
 * 优惠券信息 服务实现类
 * </p>
 *
 * @author xxyw
 * @since 2022-11-03
 */
@Service
public class CouponInfoServiceImpl extends ServiceImpl<CouponInfoMapper, CouponInfo> implements CouponInfoService {

    @Autowired
    private CouponUseService couponUseService;

    @Autowired
    private UserInfoFeignClient userInfoFeignClient;


    @Override
    public IPage<CouponUse> selectCouponUsePage(Page<CouponUse> page, CouponUseQueryVo couponUseQueryVo) {

        // 获取查询条件
        Long couponId = couponUseQueryVo.getCouponId();
        String couponStatus = couponUseQueryVo.getCouponStatus();
        String getTimeBegin = couponUseQueryVo.getGetTimeBegin();
        String getTimeEnd = couponUseQueryVo.getGetTimeEnd();

        // 判断是否为空 封装查询条件
        LambdaQueryWrapper<CouponUse> lqw = new LambdaQueryWrapper<>();
        if (!StringUtils.isEmpty(couponId)) {
            lqw.eq(CouponUse::getCouponId, couponId);
        }
        if (!StringUtils.isEmpty(couponStatus)) {
            lqw.eq(CouponUse::getCouponStatus, couponStatus);
        }
        if (!StringUtils.isEmpty(getTimeBegin)) {
            lqw.ge(CouponUse::getGetTime, getTimeBegin);
        }
        if (!StringUtils.isEmpty(getTimeEnd)) {
            lqw.le(CouponUse::getGetTime, getTimeEnd);
        }

        // 分页查询
        couponUseService.page(page, lqw);
        List<CouponUse> couponUseList = page.getRecords();

        // 远程调用获取其他信息
        couponUseList.forEach(couponUse -> {
            Long userId = couponUse.getUserId();
            UserInfo userInfo = userInfoFeignClient.getById(userId);
            couponUse.getParam().put("nickName", userInfo.getNickName());
            couponUse.getParam().put("phone", userInfo.getPhone());
        });

        return page;
    }
}
