package com.xxyw.ggkt.wechat.service.impl;


import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.atguigu.ggkt.model.wechat.Menu;
import com.atguigu.ggkt.vo.wechat.MenuVo;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xxyw.ggkt.wechat.mapper.MenuMapper;
import com.xxyw.ggkt.wechat.service.MenuService;
import lombok.SneakyThrows;
import me.chanjar.weixin.mp.api.WxMpService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 订单明细 订单明细 服务实现类
 * </p>
 *
 * @author xxyw
 * @since 2022-11-05
 */
@Service
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements MenuService {

    @Autowired
    private WxMpService wxMpService;

    @SneakyThrows
    @Override
    public void syncMenu() {
        List<MenuVo> menuVoList = this.findMenuInfo();
        //菜单
        JSONArray buttonList = new JSONArray();
        for (MenuVo oneMenuVo : menuVoList) {
            JSONObject one = new JSONObject();
            one.put("name", oneMenuVo.getName());
            JSONArray subButton = new JSONArray();
            for (MenuVo twoMenuVo : oneMenuVo.getChildren()) {
                JSONObject view = new JSONObject();
                view.put("type", twoMenuVo.getType());
                if (twoMenuVo.getType().equals("view")) {
                    view.put("name", twoMenuVo.getName());
                    view.put("url", "http://ggkt2.vipgz1.91tunnel.com/#" + twoMenuVo.getUrl());
                } else {
                    view.put("name", twoMenuVo.getName());
                    view.put("key", twoMenuVo.getMeunKey());
                }
                subButton.add(view);
            }
            one.put("sub_button", subButton);
            buttonList.add(one);
        }
        //菜单
        JSONObject button = new JSONObject();
        button.put("button", buttonList);
        this.wxMpService.getMenuService().menuCreate(button.toJSONString());
    }

    @SneakyThrows
    @Override
    public void removeMenu() {
        wxMpService.getMenuService().menuDelete();
    }

    @Override
    public List<MenuVo> findMenuInfo() {

        // 1.创建 最终返回的 列表
        List<MenuVo> menuVoList = new ArrayList<>();

        // 2.获取所有的 Menu对象
        List<Menu> menuList = baseMapper.selectList(null);

        // 3. 得到 一级 Menu 对象
        List<Menu> oneMenuList = menuList.stream().filter(menu -> menu.getParentId().equals(0L)).collect(Collectors.toList());

        // 4. 封装 一级 Menu 对象
        for (Menu menu : oneMenuList) {
            MenuVo menuVo = new MenuVo();
            BeanUtils.copyProperties(menu, menuVo);

            // 5. 封装 二级 Menu 到 一级
            List<Menu> twoMenuList = menuList.stream().filter(menu1 -> menu1.getParentId().equals(menu.getId())).collect(Collectors.toList());

            List<MenuVo> children = new ArrayList<>(twoMenuList.size());

            for (Menu menu1 : twoMenuList) {
                MenuVo menuVo1 = new MenuVo();
                BeanUtils.copyProperties(menu1, menuVo1);
                children.add(menuVo1);
            }
            menuVo.setChildren(children);

            menuVoList.add(menuVo);
        }

        return menuVoList;
    }

    @Override
    public List<Menu> findMenuOneInfo() {

        LambdaQueryWrapper<Menu> lqw = new LambdaQueryWrapper<>();

        lqw.eq(Menu::getParentId, 0);

        return baseMapper.selectList(lqw);
    }
}
