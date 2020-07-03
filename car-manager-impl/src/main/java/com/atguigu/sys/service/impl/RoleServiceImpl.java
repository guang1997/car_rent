package com.atguigu.sys.service.impl;

import com.atguigu.car.utils.DataGridView;
import com.atguigu.car.utils.StringUtil;
import com.atguigu.car.utils.SysConstast;
import com.atguigu.car.utils.TreeNode;
import com.atguigu.sys.bean.*;
import com.atguigu.sys.mapper.SysMenuMapper;
import com.atguigu.sys.mapper.SysRoleMapper;
import com.atguigu.sys.mapper.SysRoleMenuMapper;
import com.atguigu.sys.mapper.SysRoleUserMapper;
import com.atguigu.sys.service.RoleService;
import com.atguigu.sys.vo.MenuVo;
import com.atguigu.sys.vo.RoleVo;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    SysRoleMapper roleMapper;
    @Autowired
    SysRoleMenuMapper roleMenuMapper;
    @Autowired
    SysRoleUserMapper roleUserMapper;

    @Autowired
    SysMenuMapper menuMapper;
    /**
     * 用于模糊查询所有的角色
     *
     * @param roleVo
     * @return
     */
    @Override
    public List<SysRole> getAllRole(RoleVo roleVo) {
        SysRoleExample exa = new SysRoleExample();
        SysRoleExample.Criteria criteria = exa.createCriteria();
        if (!StringUtil.isEmpty(roleVo.getRolename())) {
            criteria.andRolenameLike("%" + roleVo.getRolename() + "%");
        }
        if (!StringUtil.isEmpty(roleVo.getRoledesc())) {
            criteria.andRoledescLike("%" + roleVo.getRoledesc() + "%");
        }
        if (roleVo.getAvailable() != null && roleVo.getAvailable() == 1) {
            criteria.andAvailableEqualTo(1);
        }
        if (roleVo.getAvailable() != null && roleVo.getAvailable() == 0) {
            criteria.andAvailableEqualTo(0);
        }
        return roleMapper.selectByExample(exa);
    }

    /**
     * 根据用户id查询用户可用的角色
     *
     * @param id
     * @return
     */
    @Override
    public List<SysRole> getAllRoleById(RoleVo roleVo, Integer id) {
        return null;
    }

    /**
     * 用于在页面上显示所有的角色，使用了分页
     *
     * @param roleVo
     * @return
     */
    @Override
    public DataGridView loadAllRole(RoleVo roleVo) {
        Page<Object> page = PageHelper.startPage(roleVo.getPage(), roleVo.getLimit());
        List<SysRole> allRole = this.getAllRole(roleVo);
        return new DataGridView(page.getTotal(), allRole);
    }

    /**
     * 用于添加角色
     *
     * @param roleVo
     */
    @Override
    public void addRole(RoleVo roleVo) {
        roleMapper.insertSelective(roleVo);
    }

    /**
     * 用于删除角色
     * 删除角色时，相关联的表中的角色也要删除
     *
     * @param roleVo
     */
    @Override
    public void delRole(RoleVo roleVo) {
        // 删除role表中的数据
        roleMapper.deleteByPrimaryKey(roleVo.getRoleid());
        // 删除role_menu表中的数据
        SysRoleMenuExample exa = new SysRoleMenuExample();
        exa.createCriteria().andRidEqualTo(roleVo.getRoleid());
        roleMenuMapper.deleteByExample(exa);
        // 删除role_user表中的数据
        SysRoleUserExample exa1 = new SysRoleUserExample();
        exa1.createCriteria().andRidEqualTo(roleVo.getRoleid());
        roleUserMapper.deleteByExample(exa1);
    }

    /**
     * 用于批量删除角色
     *
     * @param roleVo
     */
    @Override
    public void deleteBatchRole(RoleVo roleVo) {
        // 删除role表中的数据
        SysRoleExample exa = new SysRoleExample();
        exa.createCriteria().andRoleidIn(roleVo.getIds());
        roleMapper.deleteByExample(exa);
        // 删除role_menu表中的数据
        SysRoleMenuExample exa1 = new SysRoleMenuExample();
        exa1.createCriteria().andRidIn(roleVo.getIds());
        roleMenuMapper.deleteByExample(exa1);
        // 删除role_user表中的数据
        SysRoleUserExample exa2 = new SysRoleUserExample();
        exa2.createCriteria().andRidIn(roleVo.getIds());
        roleUserMapper.deleteByExample(exa2);
    }

    /**
     * 用于更改角色
     *
     * @param roleVo
     */
    @Override
    public void updateRole(RoleVo roleVo) {
        roleMapper.updateByPrimaryKeySelective(roleVo);
    }

    /**
     * 用于查询角色权限树
     * @param roleid
     * @return
     */
    @Override
    public DataGridView initRoleMenuTreeJson(Integer roleid) {
        // 查询所有的可用菜单
        SysMenuExample menusExa = new SysMenuExample();
        menusExa.createCriteria().andAvailableEqualTo(SysConstast.AVAIABLE_TRUE);
        List<SysMenu> addMenus = menuMapper.selectByExample(menusExa);
        // 根据角色id查询当前角色所拥有的菜单id
        SysRoleMenuExample roleMenuExa = new SysRoleMenuExample();
        roleMenuExa.createCriteria().andRidEqualTo(roleid);
        // 获取到角色id对应的结果集对象
        List<SysMenu> roleMenus = menuMapper.selectMenuByRoleId(roleid);

        ArrayList<TreeNode> treeNodes = new ArrayList<>();
        // 对全部Menu集合进行遍历
        for (SysMenu m1 : addMenus) {
            String checkArr = SysConstast.CODE_ZERO + "";
            // 如果角色id对应的Menu集合中包含正在遍历的Menu，那么将其数据保存到treeNodes中，并且设置checkArr为1
            for (SysMenu m2 : roleMenus) {
                if (m1.getId()==m2.getId()) {
                    checkArr = SysConstast.CODE_ONE + "";
                    break;
                }
            }
            // 将数据保存到treeNodes中
            Boolean spread = m1.getSpread() == SysConstast.SPREAD_TRUE;
            treeNodes.add(new TreeNode(m1.getId(), m1.getPid(), m1.getTitle(), spread, checkArr));
        }
        return new DataGridView(treeNodes);
    }

    /**
     * 用于保存角色权限
     * @param roleVo
     */
    @Override
    public void saveRoleMenu(RoleVo roleVo) {
        // 先将角色所有的权限删除
        SysRoleMenuExample roleMenuExa = new SysRoleMenuExample();
        roleMenuExa.createCriteria().andRidEqualTo(roleVo.getRoleid());
        roleMenuMapper.deleteByExample(roleMenuExa);
        // 然后对其进行批量插入
        roleMenuMapper.batchInsertByRoleIdAndMid(roleVo.getRoleid(), roleVo.getIds());
    }
}
