package com.xxyw.ggkt.activity.controller;


import com.atguigu.ggkt.model.activity.CouponInfo;
import com.atguigu.ggkt.model.activity.CouponUse;
import com.atguigu.ggkt.vo.activity.CouponUseQueryVo;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xxyw.ggkt.activity.service.CouponInfoService;
import com.xxyw.ggkt.result.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 优惠券信息 前端控制器
 * </p>
 *
 * @author xxyw
 * @since 2022-11-03
 */
@RestController
@RequestMapping("/admin/activity/couponInfo")
public class CouponInfoController {

    @Autowired
    private CouponInfoService couponInfoService;

    // 添加
    @PostMapping("save")
    public Result save(@RequestBody CouponInfo couponInfo) {
        couponInfoService.save(couponInfo);
        return Result.ok(null);
    }

    // 根据 ID 查询
    @GetMapping("get/{id}")
    public Result get(@PathVariable String id) {
        CouponInfo couponInfo = couponInfoService.getById(id);
        return Result.ok(couponInfo);
    }

    // 修改
    @PutMapping("update")
    public Result update(@RequestBody CouponInfo couponInfo) {
        couponInfoService.updateById(couponInfo);
        return Result.ok(null);
    }

    // 删除
    @DeleteMapping("remove/{id}")
    public Result remove(@PathVariable String id) {
        couponInfoService.removeById(id);
        return Result.ok(null);
    }

    // 批量删除
    @DeleteMapping("removeBatch")
    public Result removeBatch(@RequestBody List<String> idList) {
        couponInfoService.removeByIds(idList);
        return Result.ok(null);
    }

    // 分页查询
    @GetMapping("{curPage}/{pageSize}")
    public Result index(@PathVariable Long curPage, @PathVariable Long pageSize) {
        Page<CouponInfo> page = new Page<>(curPage, pageSize);
        couponInfoService.page(page);
        return Result.ok(page);
    }

    // 分页条件查询
    @GetMapping("couponUse/{curPage}/{pageSize}")
    public Result index(@PathVariable Long curPage, @PathVariable Long pageSize, CouponUseQueryVo couponUseQueryVo) {
        Page<CouponUse> page = new Page<>(curPage, pageSize);
        couponInfoService.selectCouponUsePage(page, couponUseQueryVo);
        return Result.ok(page);
    }

}

