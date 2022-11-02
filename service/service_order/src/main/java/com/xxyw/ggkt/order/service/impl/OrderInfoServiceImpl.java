package com.xxyw.ggkt.order.service.impl;


import com.atguigu.ggkt.model.order.OrderDetail;
import com.atguigu.ggkt.model.order.OrderInfo;
import com.atguigu.ggkt.vo.order.OrderInfoQueryVo;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xxyw.ggkt.order.mapper.OrderInfoMapper;
import com.xxyw.ggkt.order.service.OrderDetailService;
import com.xxyw.ggkt.order.service.OrderInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 订单表 订单表 服务实现类
 * </p>
 *
 * @author xxyw
 * @since 2022-11-02
 */
@Service
public class OrderInfoServiceImpl extends ServiceImpl<OrderInfoMapper, OrderInfo> implements OrderInfoService {

    @Autowired
    private OrderDetailService orderDetailService;

    @Override
    public Map<String, Object> selectOrderInfoByPage(Page<OrderInfo> page, OrderInfoQueryVo orderInfoQueryVo) {

        // 获取查询条件
        Long userId = orderInfoQueryVo.getUserId();
        String outTradeNo = orderInfoQueryVo.getOutTradeNo();
        String phone = orderInfoQueryVo.getPhone();
        String createTimeBegin = orderInfoQueryVo.getCreateTimeBegin();
        String createTimeEnd = orderInfoQueryVo.getCreateTimeEnd();
        Integer orderStatus = orderInfoQueryVo.getOrderStatus();

        // 判断查询条件是否为空
        LambdaQueryWrapper<OrderInfo> lqw = new LambdaQueryWrapper<>();

        if (!StringUtils.isEmpty(userId)) {
            lqw.eq(OrderInfo::getUserId, userId);
        }

        if (!StringUtils.isEmpty(outTradeNo)) {
            lqw.eq(OrderInfo::getOutTradeNo, outTradeNo);
        }

        if (!StringUtils.isEmpty(phone)) {
            lqw.eq(OrderInfo::getPhone, phone);
        }
        if (!StringUtils.isEmpty(createTimeBegin)) {
            lqw.ge(OrderInfo::getCreateTime, createTimeBegin);
        }
        if (!StringUtils.isEmpty(createTimeEnd)) {
            lqw.le(OrderInfo::getCreateTime, createTimeEnd);
        }

        if (!StringUtils.isEmpty(orderStatus)) {
            lqw.eq(OrderInfo::getOrderStatus, orderStatus);
        }


        // 查询出 订单
        baseMapper.selectPage(page, lqw);

        long pageCount = page.getPages();
        long total = page.getTotal();
        List<OrderInfo> records = page.getRecords();

        // 查询出 订单 详情
        records.forEach(orderInfo -> {
            OrderDetail orderDetail = orderDetailService.getById(orderInfo.getId());
            if (orderDetail != null) {
                orderInfo.getParam().put("courseName", orderDetail.getCourseName());
            }
        });

        Map<String, Object> map = new HashMap<>();
        map.put("pageCount", pageCount);
        map.put("total", total);
        map.put("records", records);

        return map;
    }
}
