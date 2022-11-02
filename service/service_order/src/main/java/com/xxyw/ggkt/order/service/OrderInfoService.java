package com.xxyw.ggkt.order.service;


import com.atguigu.ggkt.model.order.OrderInfo;
import com.atguigu.ggkt.vo.order.OrderInfoQueryVo;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 * <p>
 * 订单表 订单表 服务类
 * </p>
 *
 * @author xxyw
 * @since 2022-11-02
 */
public interface OrderInfoService extends IService<OrderInfo> {

    Map<String, Object> selectOrderInfoByPage(Page<OrderInfo> page, OrderInfoQueryVo orderInfoQueryVo);
}
