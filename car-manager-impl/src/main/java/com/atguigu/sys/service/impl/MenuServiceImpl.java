package com.atguigu.sys.service.impl;

import com.atguigu.car.utils.DataGridView;
import com.atguigu.car.utils.StringUtil;
import com.atguigu.sys.bean.SysMenu;
import com.atguigu.sys.bean.SysMenuExample;
import com.atguigu.sys.mapper.SysMenuMapper;
import com.atguigu.sys.service.MenuService;
import com.atguigu.sys.vo.MenuVo;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MenuServiceImpl implements MenuService {

    @Autowired
    SysMenuMapper menuMapper;
    /**
     * 该方法用于返回所有的菜单数据
     * @return
     */
    @Override
    public List<SysMenu> getAllMenu(MenuVo menuVo) {
        // 如果有条件匹配才进行条件查询
        if (!StringUtil.isEmpty(menuVo.getTitle())) {
            SysMenuExample exa = new SysMenuExample();
            exa.createCriteria().andTitleLike("%" + menuVo.getTitle() + "%");
           return menuMapper.selectByExample(exa);
        }
        return menuMapper.selectByExample(null);
    }

    /**
     * 普通用户登录时，分别显示菜单
     * @param menuVo
     * @param userid
     * @return
     */
    @Override
    public List<SysMenu> getAllMenuById(MenuVo menuVo, Integer userid) {

        return menuMapper.getAllMenuById(menuVo.getAvailable(), userid);
    }

    /**
     * 该方法用于在表格中显示数据，用到分页
     * @param menuVo
     * @return
     */
    @Override
    public DataGridView queryAllMenu(MenuVo menuVo) {
        Page<Object> page = PageHelper.startPage(menuVo.getPage(), menuVo.getLimit());
        List<SysMenu> menus = this.getAllMenu(menuVo);
        return new DataGridView(page.getTotal(), menus);
    }

    /**
     * 该方法用于点击左侧数据树来显示右边对应的表格数据
     * @param id
     * @return
     */
    @Override
    public DataGridView queryAllMenuById(Integer id) {
        SysMenuExample exa = new SysMenuExample();
        exa.createCriteria().andIdEqualTo(id);
        exa.or().andPidEqualTo(id);
        List<SysMenu> menus = menuMapper.selectByExample(exa);
        return new DataGridView(menus);
    }

    /**
     * 该方法用于新增菜单
     * @param menuVo
     */
    @Override
    public void addMenu(MenuVo menuVo) {
        menuMapper.insertSelective(menuVo);
    }

    /**
     * 该方法用于修改菜单
     * @param menuVo
     */
    @Override
    public void updateMenu(MenuVo menuVo) {
        menuMapper.updateByPrimaryKeySelective(menuVo);
    }

    /**
     * 该方法用于查看当前菜单是否有子菜单
     * @param menuVo
     * @return
     */
    @Override
    public Long checkMenuHasChildren(MenuVo menuVo) {
        SysMenuExample exa = new SysMenuExample();
        exa.createCriteria().andPidEqualTo(menuVo.getId());
        return menuMapper.countByExample(exa);
    }

    /**
     * 该方法用于删除菜单
     * @param menuVo
     */
    @Override
    public void delMenuById(MenuVo menuVo) {
        menuMapper.deleteByPrimaryKey(menuVo.getId());
    }
}
