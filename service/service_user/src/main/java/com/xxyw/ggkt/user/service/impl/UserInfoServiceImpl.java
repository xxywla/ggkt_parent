package com.xxyw.ggkt.user.service.impl;


import com.atguigu.ggkt.model.user.UserInfo;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xxyw.ggkt.user.mapper.UserInfoMapper;
import com.xxyw.ggkt.user.service.UserInfoService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author xxyw
 * @since 2022-11-03
 */
@Service
public class UserInfoServiceImpl extends ServiceImpl<UserInfoMapper, UserInfo> implements UserInfoService {

}
