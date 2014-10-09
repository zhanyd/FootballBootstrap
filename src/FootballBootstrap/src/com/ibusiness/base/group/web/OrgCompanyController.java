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

import com.ibusiness.base.group.dao.OrgCompanyDao;
import com.ibusiness.base.group.entity.OrgCompany;
import com.ibusiness.common.page.Page;
import com.ibusiness.common.page.PropertyFilter;
import com.ibusiness.core.mapper.BeanMapper;
import com.ibusiness.core.spring.MessageHelper;
import com.ibusiness.security.api.scope.ScopeHolder;
/**
 * 公司
 * @author JiangBo
 *
 */
@Controller
@RequestMapping("group")
public class OrgCompanyController {
    private OrgCompanyDao orgCompanyDao;
    private MessageHelper messageHelper;
    private BeanMapper beanMapper = new BeanMapper();

    @RequestMapping("org-company-list")
    public String list(@ModelAttribute
    Page page, @RequestParam
    Map<String, Object> parameterMap, Model model) {
        List<PropertyFilter> propertyFilters = PropertyFilter.buildFromMap(parameterMap);
        propertyFilters.add(new PropertyFilter("EQS_scopeId", ScopeHolder.getScopeId()));
        page = orgCompanyDao.pagedQuery(page, propertyFilters);
        model.addAttribute("page", page);

        return "common/group/org-company-list.jsp";
    }

    @RequestMapping("org-company-input")
    public String input(@RequestParam(value = "id", required = false)
    Long id, Model model) {
        if (id != null) {
            OrgCompany orgCompany = orgCompanyDao.get(id);
            model.addAttribute("model", orgCompany);
        }

        return "common/group/org-company-input.jsp";
    }

    @RequestMapping("org-company-save")
    public String save(@ModelAttribute
    OrgCompany orgCompany, @RequestParam
    Map<String, Object> parameterMap, RedirectAttributes redirectAttributes) {
        OrgCompany dest = null;
        Long id = orgCompany.getId();

        if (id != null) {
            dest = orgCompanyDao.get(id);
            beanMapper.copy(orgCompany, dest);
        } else {
            dest = orgCompany;
        }

        if (id == null) {
            dest.setScopeId(ScopeHolder.getScopeId());
        }

        orgCompanyDao.save(dest);

        messageHelper.addFlashMessage(redirectAttributes, "core.success.save", "保存成功");

        return "redirect:/group/org-company-list.do";
    }

    @RequestMapping("org-company-remove")
    public String remove(@RequestParam("selectedItem")
    List<Long> selectedItem, RedirectAttributes redirectAttributes) {
        List<OrgCompany> orgCompanies = orgCompanyDao.findByIds(selectedItem);

        for (OrgCompany orgCompany : orgCompanies) {
            orgCompanyDao.remove(orgCompany);
        }

        messageHelper.addFlashMessage(redirectAttributes, "core.success.delete", "删除成功");

        return "redirect:/group/org-company-list.do";
    }

    // ~ ======================================================================
    @Resource
    public void setOrgCompanyDao(OrgCompanyDao orgCompanyDao) {
        this.orgCompanyDao = orgCompanyDao;
    }

    @Resource
    public void setMessageHelper(MessageHelper messageHelper) {
        this.messageHelper = messageHelper;
    }
}
