package com.atguigu.sys.service;

import com.atguigu.car.utils.DataGridView;
import com.atguigu.sys.bean.SysMenu;
import com.atguigu.sys.vo.MenuVo;

import java.util.List;

public interface MenuService {

    public List<SysMenu> getAllMenu(MenuVo menuVo);

    public List<SysMenu> getAllMenuById(MenuVo menuVo, Integer id);

    DataGridView queryAllMenu(MenuVo menuVo);

    DataGridView queryAllMenuById(Integer id);

    void addMenu(MenuVo menuVo);

    void updateMenu(MenuVo menuVo);

    Long checkMenuHasChildren(MenuVo menuVo);

    void delMenuById(MenuVo menuVo);
}
