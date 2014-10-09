package com.ibusiness.base.group.web;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ibusiness.base.group.dao.OrgGroupDao;
import com.ibusiness.base.group.entity.OrgGroup;
import com.ibusiness.common.page.Page;
import com.ibusiness.common.page.PropertyFilter;
import com.ibusiness.core.mapper.BeanMapper;
import com.ibusiness.core.spring.MessageHelper;
import com.ibusiness.security.api.scope.ScopeHolder;
/**
 * 小组
 * 
 * @author JiangBo
 *
 */
@Controller
@RequestMapping("group")
public class OrgGroupController {
    private OrgGroupDao orgGroupDao;
    private MessageHelper messageHelper;
    private BeanMapper beanMapper = new BeanMapper();

    @RequestMapping("org-group-list")
    public String list(@ModelAttribute
    Page page, @RequestParam
    Map<String, Object> parameterMap, Model model) {
        List<PropertyFilter> propertyFilters = PropertyFilter.buildFromMap(parameterMap);
        propertyFilters.add(new PropertyFilter("EQS_scopeId", ScopeHolder.getScopeId()));
        page = orgGroupDao.pagedQuery(page, propertyFilters);

        model.addAttribute("page", page);

        return "common/group/org-group-list.jsp";
    }

    @RequestMapping("org-group-input")
    public String input(@RequestParam(value = "id", required = false) Long id, Model model) {
        if (id != null) {
            OrgGroup orgGroup = orgGroupDao.get(id);
            model.addAttribute("model", orgGroup);
        }

        return "common/group/org-group-input.jsp";
    }

    @RequestMapping("org-group-save")
    public String save(@ModelAttribute
    OrgGroup orgGroup, RedirectAttributes redirectAttributes) {
        OrgGroup dest = null;
        Long id = orgGroup.getId();

        if (id != null) {
            dest = orgGroupDao.get(id);
            beanMapper.copy(orgGroup, dest);
        } else {
            dest = orgGroup;
        }

        if (id == null) {
            dest.setScopeId(ScopeHolder.getScopeId());
        }

        orgGroupDao.save(dest);

        messageHelper.addFlashMessage(redirectAttributes, "core.success.save", "保存成功");

        return "redirect:/group/org-group-list.do";
    }

    @RequestMapping("org-group-remove")
    public String remove(@RequestParam("selectedItem")
    List<Long> selectedItem, RedirectAttributes redirectAttributes) {
        List<OrgGroup> orgGroups = orgGroupDao.findByIds(selectedItem);

        for (OrgGroup orgGroup : orgGroups) {
            orgGroupDao.remove(orgGroup);
        }

        messageHelper.addFlashMessage(redirectAttributes, "core.success.delete", "删除成功");

        return "redirect:/group/org-group-list.do";
    }

    // ~ ======================================================================
    @Resource
    public void setOrgGroupDao(OrgGroupDao orgGroupDao) {
        this.orgGroupDao = orgGroupDao;
    }

    @Resource
    public void setMessageHelper(MessageHelper messageHelper) {
        this.messageHelper = messageHelper;
    }
}
