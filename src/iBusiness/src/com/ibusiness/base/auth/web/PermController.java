package com.ibusiness.base.auth.web;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ibusiness.base.auth.dao.PermDao;
import com.ibusiness.base.auth.dao.PermTypeDao;
import com.ibusiness.base.auth.entity.Perm;
import com.ibusiness.base.auth.entity.PermType;
import com.ibusiness.common.page.Page;
import com.ibusiness.common.page.PropertyFilter;
import com.ibusiness.core.mapper.BeanMapper;
import com.ibusiness.core.spring.MessageHelper;
import com.ibusiness.security.api.scope.ScopeHolder;

/**
 * 标签级权限管理
 * 
 * @author JiangBo
 * 
 */
@Controller
@RequestMapping("auth")
public class PermController {
    private PermDao permDao;
    private PermTypeDao permTypeDao;
    private MessageHelper messageHelper;
    private BeanMapper beanMapper = new BeanMapper();

    /**
     * 标签级权限管理类表
     * 
     * @param page
     * @param parameterMap
     * @param model
     * @return
     */
    @RequestMapping("perm-list")
    public String list(@ModelAttribute
    Page page, @RequestParam
    Map<String, Object> parameterMap, Model model) {
        List<PropertyFilter> propertyFilters = PropertyFilter.buildFromMap(parameterMap);
        propertyFilters.add(new PropertyFilter("EQS_scopeId", ScopeHolder.getScopeId()));
        propertyFilters.add(new PropertyFilter("NEQL_permType.id", "7"));
        page = permDao.pagedQuery(page, propertyFilters);

        model.addAttribute("page", page);

        return "common/auth/perm-list.jsp";
    }

    /**
     * 新建/更改
     * 
     * @param id
     * @param model
     * @return
     */
    @RequestMapping("perm-input")
    public String input(@RequestParam(value = "id", required = false) Long id, Model model) {
        if (id != null) {
            Perm perm = permDao.get(id);
            model.addAttribute("model", perm);
        }
        List<PermType> permTypes = permTypeDao.findBy("scopeId", ScopeHolder.getScopeId());
        model.addAttribute("permTypes", permTypes);
        return "common/auth/perm-input.jsp";
    }

    /**
     * 保存
     * @param perm
     * @param permTypeId
     * @param redirectAttributes
     * @return
     */
    @RequestMapping("perm-save")
    public String save(@ModelAttribute Perm perm, @RequestParam("permTypeId") Long permTypeId,
            RedirectAttributes redirectAttributes) {
        Perm dest = null;
        Long id = perm.getId();

        if (id != null) {
            dest = permDao.get(id);
            beanMapper.copy(perm, dest);
        } else {
            dest = perm;
        }

        if (id == null) {
            dest.setScopeId(ScopeHolder.getScopeId());
        }

        permDao.save(dest);

        messageHelper.addFlashMessage(redirectAttributes, "core.success.save", "保存成功");

        return "redirect:/auth/perm-list.do";
    }

    /**
     * 删除
     * @param selectedItem
     * @param redirectAttributes
     * @return
     */
    @RequestMapping("perm-remove")
    public String remove(@RequestParam("selectedItem")
    List<Long> selectedItem, RedirectAttributes redirectAttributes) {
        List<Perm> perms = permDao.findByIds(selectedItem);
        permDao.removeAll(perms);
        messageHelper.addFlashMessage(redirectAttributes, "core.delete.save", "删除成功");

        return "redirect:/auth/perm-list.do";
    }

    // ======================================================================
    @Resource
    public void setPermDao(PermDao permDao) {
        this.permDao = permDao;
    }
    @Resource
    public void setPermTypeDao(PermTypeDao permTypeDao) {
        this.permTypeDao = permTypeDao;
    }
    @Resource
    public void setMessageHelper(MessageHelper messageHelper) {
        this.messageHelper = messageHelper;
    }
}
