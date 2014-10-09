package com.ibusiness.bpm.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ibusiness.bpm.dao.BpmFlowNodeDao;
import com.ibusiness.bpm.entity.BpmFlowNode;

/**
 * 流程节点配置
 * 
 * @author JiangBo
 * 
 */
@Controller
@RequestMapping("bpm-process")
public class BpmConfNodeController {
    private BpmFlowNodeDao bpmFlowNodeDao;

    /**
     * 配置列表
     * 
     * @param flowVersionId
     * @param model
     * @return
     */
    @SuppressWarnings("unchecked")
    @RequestMapping("bpm-conf-node-list")
    public String list(@RequestParam("flowVersionId") String flowVersionId, Model model) {
        String hql = "from BpmFlowNode where flowVersionId=? order by priority";
        List<BpmFlowNode> bpmFlowNodes = bpmFlowNodeDao.find(hql, flowVersionId);
        model.addAttribute("bpmFlowNodes", bpmFlowNodes);
        return "bpm/bpm-conf-node-list.jsp";
    }

    // ======================================================================
    @Resource
    public void setBpmFlowNodeDao(BpmFlowNodeDao bpmFlowNodeDao) {
        this.bpmFlowNodeDao = bpmFlowNodeDao;
    }
}
