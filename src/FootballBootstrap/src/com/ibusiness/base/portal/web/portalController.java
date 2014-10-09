package com.ibusiness.base.portal.web;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ibusiness.base.menu.dao.MenuDao;
import com.ibusiness.base.menu.entity.Menu;
import com.ibusiness.base.user.dao.UserBaseDao;
import com.ibusiness.base.user.entity.UserBase;
import com.ibusiness.security.util.SpringSecurityUtils;
/**
 * 登录后首页controller
 * 
 * @author JiangBo
 *
 */
@Controller
@RequestMapping("portal")
public class portalController {

    private UserBaseDao userBaseDao;
    private MenuDao menuDao;
    
    /**
     * 
     * @param model
     * @param session
     * @return
     */
    @RequestMapping("portal")
    public String list(Model model, HttpSession session) {
        // 当前用户ID
        String userId = SpringSecurityUtils.getCurrentUserId();
        UserBase userBase = userBaseDao.get(Long.parseLong(userId));
        // 设置当前用户CSS样式
        session.setAttribute("userCSS",userBase.getCss());
        // 设置菜单
        List<Menu> menus = createMenu(userId);
        session.setAttribute("menuItemList", menus);
        // 设置桌面IMCA菜单
        List<Menu> deskmenus = createDeskMenu(menus);
        session.setAttribute("deskMenuItems", deskmenus);
        
        // 返回JSP
        return "base/portal/portal.jsp";
    }

    /**
     * 设置桌面IMCA菜单
     * 
     * @param menus
     * @return
     */
    private List<Menu> createDeskMenu(List<Menu> menus) {
        List<Menu> deskmenus = new ArrayList<Menu>();
        for (Menu menu : menus) {
            if ("1".equals(menu.getDesktopIcon())) {
                deskmenus.add(menu);
            }
            for (Menu menu2 : menu.getChiledItems()) {
                if ("1".equals(menu2.getDesktopIcon())) {
                    deskmenus.add(menu2);
                }
                for (Menu menu3 : menu2.getChiledItems()) {
                    if ("1".equals(menu3.getDesktopIcon())) {
                        deskmenus.add(menu3);
                    }
                }
            }
        }
        return deskmenus;
    }

    /**
     * 设置菜单
     * 
     * @param userId
     * @return
     */
    @SuppressWarnings("unchecked")
    private List<Menu> createMenu(String userId) {
        // 菜单设置
        String hql = "select m from Menu m, MenuRoleDef mrd, UserBase ub where m.id=mrd.menuId AND mrd.roleDefId= ub.roleDef.id AND m.menuLevel='1' AND ub.id=? ORDER BY m.menuOrder";
        List<Menu> menus = menuDao.find(hql, Long.parseLong(userId));
        
        String hql2 = "select m from Menu m, MenuRoleDef mrd, UserBase ub where m.id=mrd.menuId AND mrd.roleDefId= ub.roleDef.id AND m.menuLevel='2' AND ub.id=? ORDER BY m.menuOrder";
        List<Menu> menus2 = menuDao.find(hql2, Long.parseLong(userId));
        
        String hql3 = "select m from Menu m, MenuRoleDef mrd, UserBase ub where m.id=mrd.menuId AND mrd.roleDefId= ub.roleDef.id AND m.menuLevel='3' AND ub.id=? ORDER BY m.menuOrder";
        List<Menu> menus3 = menuDao.find(hql3, Long.parseLong(userId));
        for (Menu menu1 : menus) {
            menu1.getChiledItems().clear();
            for (Menu menu2 : menus2) {
                if(menu1.getId().equals(menu2.getIbMenu().getId())) {
                    menu1.getChiledItems().add(menu2);
                }
                menu2.getChiledItems().clear();
                for (Menu menu3 : menus3) {
                    if (menu2.getId().equals(menu3.getIbMenu().getId())) {
                        menu2.getChiledItems().add(menu3);
                    }
                }
            }
        }
        return menus;
    }
    
    // ==================================================
    /**
     * @return the userBaseDao
     */
    @Resource
    public void setUserBaseDao(UserBaseDao userBaseDao) {
        this.userBaseDao = userBaseDao;
    }
    @Resource
    public void setMenuDao(MenuDao menuDao) {
        this.menuDao = menuDao;
    }
}
