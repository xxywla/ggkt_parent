package com.xxyw.ggkt.activity.service;


import com.atguigu.ggkt.model.activity.CouponInfo;
import com.atguigu.ggkt.model.activity.CouponUse;
import com.atguigu.ggkt.vo.activity.CouponUseQueryVo;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 优惠券信息 服务类
 * </p>
 *
 * @author xxyw
 * @since 2022-11-03
 */
public interface CouponInfoService extends IService<CouponInfo> {

    IPage<CouponUse> selectCouponUsePage(Page<CouponUse> page, CouponUseQueryVo couponUseQueryVo);
}
