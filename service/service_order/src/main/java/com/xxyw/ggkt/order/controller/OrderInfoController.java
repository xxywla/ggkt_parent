package com.xxyw.ggkt.order.controller;


import com.atguigu.ggkt.model.order.OrderInfo;
import com.atguigu.ggkt.vo.order.OrderInfoQueryVo;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xxyw.ggkt.order.service.OrderInfoService;
import com.xxyw.ggkt.result.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * <p>
 * 订单表 订单表 前端控制器
 * </p>
 *
 * @author xxyw
 * @since 2022-11-02
 */
@RestController
@RequestMapping(value = "/admin/order/orderInfo")
public class OrderInfoController {

    @Autowired
    private OrderInfoService orderInfoService;

    // 获取分页数据
    @GetMapping("{curPage}/{pageSize}")
    public Result page(@PathVariable Long curPage, @PathVariable Long pageSize, OrderInfoQueryVo orderInfoQueryVo) {

        Page<OrderInfo> page = new Page<>(curPage, pageSize);

        Map<String, Object> map = orderInfoService.selectOrderInfoByPage(page, orderInfoQueryVo);

        return Result.ok(map);
    }

}

