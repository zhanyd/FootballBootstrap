package com.ibusiness.component.bpm.controller;

import java.io.InputStream;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.impl.interceptor.Command;
import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ibusiness.bpm.cmd.ProcessDefinitionDiagramCmd;
import com.ibusiness.bpm.dao.BpmProcessVersionDao;
import com.ibusiness.bpm.entity.BpmProcess;
import com.ibusiness.bpm.entity.BpmProcessVersion;
import com.ibusiness.common.page.Page;
import com.ibusiness.common.page.PropertyFilter;
import com.ibusiness.common.util.CommonUtils;
import com.ibusiness.component.bpm.dao.BpmProcessDao;
import com.ibusiness.core.spring.MessageHelper;

/**
 * 流程管理控制器
 * 
 * @author JiangBo
 *
 */
@Controller
@RequestMapping("bpm-process")
public class BpmProcessController {

    private BpmProcessDao bpmProcessDao;
    private MessageHelper messageHelper;
    private BpmProcessVersionDao bpmProcessVersionDao;
    private ProcessEngine processEngine;
    
    /**
     * 流程列表
     * 
     * @param page
     * @param parameterMap
     * @param model
     * @return
     */
    @RequestMapping("bpm-process-list")
    public String bpmFlowList(@ModelAttribute Page page, @RequestParam("packageName") String packageName, @RequestParam Map<String, Object> parameterMap, Model model) {
        // 取得表结构信息。
        List<PropertyFilter> propertyFilters = PropertyFilter.buildFromMap(parameterMap);
        propertyFilters.add(new PropertyFilter("EQS_packageName", packageName));
        page = bpmProcessDao.pagedQuery(page, propertyFilters);
        // 
        model.addAttribute("page", page);
        model.addAttribute("packageName", packageName);
        
        return "bpm/bpm-process-list.jsp";
    }
    
    /**
     * 插入
     * @param id
     * @param model
     * @return
     */
    @RequestMapping("bpm-process-input")
    public String input(@RequestParam(value = "packageName", required = false) String packageName, @RequestParam(value = "id", required = false) String id, Model model) {
        BpmProcess entity = null;
        if (!CommonUtils.isNull(id)) {
            entity = bpmProcessDao.get(id);
        } else {
            entity = new BpmProcess();
            entity.setPackageName(packageName);
        }
        model.addAttribute("model", entity);
        
        return "bpm/bpm-process-input.jsp";
    }

    /**
     * 保存
     * 
     * @return
     * @throws Exception
     */
    @RequestMapping("bpm-process-save")
    public String save(@ModelAttribute BpmProcess entity, RedirectAttributes redirectAttributes) throws Exception {
        String id = entity.getId();
        if (CommonUtils.isNull(id)) {
            entity.setId(UUID.randomUUID().toString());
            bpmProcessDao.saveInsert(entity);
        } else {
            bpmProcessDao.update(entity);
        }
        messageHelper.addFlashMessage(redirectAttributes, "core.success.save", "保存成功");
        return "redirect:/bpm-process/bpm-process-list.do?packageName="+entity.getPackageName();
    }
   /**
     * 删除
     * @param selectedItem
     * @param redirectAttributes
     * @return
     */
    @RequestMapping("bpm-process-remove")
    public String remove(@RequestParam("selectedItem") List<String> selectedItem, RedirectAttributes redirectAttributes) {
        List<BpmProcess> entitys = bpmProcessDao.findByIds(selectedItem);
        for (BpmProcess entity : entitys) {
            bpmProcessDao.remove(entity);
        }
        messageHelper.addFlashMessage(redirectAttributes, "core.success.delete", "删除成功");

        return "redirect:/bpm-process/bpm-process-list.do?packageName="+entitys.get(0).getPackageName();
    }
    ////////////////////////////////////////////////////////////////////////////////////////
    /**
     * 流程定义图像 TODO
     * @param bpmProcessId
     * @param response
     * @throws Exception
     */
    @RequestMapping("bpm-process-graph")
    public void graphProcessDefinition(@RequestParam("bpmProcessId") String bpmProcessId, HttpServletResponse response) throws Exception {
        BpmProcess bpmProcess = bpmProcessDao.get(bpmProcessId);
        String versionId = bpmProcess.getVersionId();
        // 
        BpmProcessVersion bpmProcessVersion= bpmProcessVersionDao.get(versionId);
        String bpmProsessId = bpmProcessVersion.getBpmProsessId();
        Command<InputStream> cmd = new ProcessDefinitionDiagramCmd(bpmProsessId);

        InputStream is = processEngine.getManagementService().executeCommand(cmd);
        response.setContentType("image/png");

        IOUtils.copy(is, response.getOutputStream());
    }
    
    // ======================================================================
    @Resource
    public void setBpmProcessDao(BpmProcessDao bpmProcessDao) {
        this.bpmProcessDao = bpmProcessDao;
    }
    @Resource
    public void setMessageHelper(MessageHelper messageHelper) {
        this.messageHelper = messageHelper;
    }
    @Resource
    public void setBpmProcessVersionDao(BpmProcessVersionDao bpmProcessVersionDao) {
        this.bpmProcessVersionDao = bpmProcessVersionDao;
    }
    @Resource
    public void setProcessEngine(ProcessEngine processEngine) {
        this.processEngine = processEngine;
    }
}
