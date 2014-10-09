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

import com.ibusiness.base.group.dao.JobTypeDao;
import com.ibusiness.base.group.entity.JobType;
import com.ibusiness.common.page.Page;
import com.ibusiness.common.page.PropertyFilter;
import com.ibusiness.core.mapper.BeanMapper;
import com.ibusiness.core.spring.MessageHelper;
import com.ibusiness.security.api.scope.ScopeHolder;
/**
 * 职务类型
 * 
 * @author JiangBo
 *
 */
@Controller
@RequestMapping("group")
public class JobTypeController {
    private JobTypeDao jobTypeDao;
    private MessageHelper messageHelper;
    private BeanMapper beanMapper = new BeanMapper();

    @RequestMapping("job-type-list")
    public String list(@ModelAttribute
    Page page, @RequestParam
    Map<String, Object> parameterMap, Model model) {
        List<PropertyFilter> propertyFilters = PropertyFilter.buildFromMap(parameterMap);
        propertyFilters.add(new PropertyFilter("EQS_scopeId", ScopeHolder.getScopeId()));
        page = jobTypeDao.pagedQuery(page, propertyFilters);

        model.addAttribute("page", page);

        return "common/group/job-type-list.jsp";
    }

    @RequestMapping("job-type-input")
    public String input(@RequestParam(value = "id", required = false)
    Long id, Model model) {
        if (id != null) {
            JobType jobType = jobTypeDao.get(id);
            model.addAttribute("model", jobType);
        }

        model.addAttribute("jobTypes", jobTypeDao.getAll());

        return "common/group/job-type-input.jsp";
    }

    @RequestMapping("job-type-save")
    public String save(@ModelAttribute
    JobType jobType, @RequestParam(value = "jobTypeId", required = false)
    Long jobTypeId, RedirectAttributes redirectAttributes) {
        JobType dest = null;
        Long id = jobType.getId();

        if (id != null) {
            dest = jobTypeDao.get(id);
            beanMapper.copy(jobType, dest);
        } else {
            dest = jobType;
        }

        if (jobTypeId != null) {
            dest.setJobType(jobTypeDao.get(jobTypeId));
        } else {
            dest.setJobType(null);
        }

        if (id == null) {
            dest.setScopeId(ScopeHolder.getScopeId());
        }

        jobTypeDao.save(dest);

        messageHelper.addFlashMessage(redirectAttributes, "core.success.save", "保存成功");

        return "redirect:/group/job-type-list.do";
    }

    @RequestMapping("job-type-remove")
    public String remove(@RequestParam("selectedItem")
    List<Long> selectedItem, RedirectAttributes redirectAttributes) {
        List<JobType> jobTypes = jobTypeDao.findByIds(selectedItem);

        for (JobType jobType : jobTypes) {
            jobTypeDao.remove(jobType);
        }

        messageHelper.addFlashMessage(redirectAttributes, "core.success.delete", "删除成功");

        return "redirect:/group/job-type-list.do";
    }

    // ~ ======================================================================
    @Resource
    public void setJobTypeDao(JobTypeDao jobTypeDao) {
        this.jobTypeDao = jobTypeDao;
    }

    @Resource
    public void setMessageHelper(MessageHelper messageHelper) {
        this.messageHelper = messageHelper;
    }
}
