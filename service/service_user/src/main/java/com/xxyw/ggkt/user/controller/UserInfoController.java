package com.xxyw.ggkt.user.controller;


import com.atguigu.ggkt.model.user.UserInfo;
import com.xxyw.ggkt.user.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 用户表 前端控制器
 * </p>
 *
 * @author xxyw
 * @since 2022-11-03
 */
@RestController
@RequestMapping("/admin/user/userInfo")
public class UserInfoController {

    @Autowired
    private UserInfoService userInfoService;

    // 根据 ID 获取用户信息
    @GetMapping("inner/getById/{id}")
    public UserInfo getById(@PathVariable Long id) {
        return userInfoService.getById(id);
    }

}

