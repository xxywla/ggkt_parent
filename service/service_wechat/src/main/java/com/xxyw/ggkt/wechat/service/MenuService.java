package com.xxyw.ggkt.wechat.service;


import com.atguigu.ggkt.model.wechat.Menu;
import com.atguigu.ggkt.vo.wechat.MenuVo;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 订单明细 订单明细 服务类
 * </p>
 *
 * @author xxyw
 * @since 2022-11-05
 */
public interface MenuService extends IService<Menu> {

    void syncMenu();

    void removeMenu();

    List<MenuVo> findMenuInfo();

    List<Menu> findMenuOneInfo();
}
