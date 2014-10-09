package com.ibusiness.component.form.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ibusiness.common.page.Page;
import com.ibusiness.common.page.PropertyFilter;
import com.ibusiness.common.util.CommonUtils;
import com.ibusiness.component.form.dao.ConfFormDao;
import com.ibusiness.component.form.dao.ConfFormTableColumnDao;
import com.ibusiness.component.form.dao.ConfFormTableDao;
import com.ibusiness.component.form.entity.ConfForm;
import com.ibusiness.component.form.entity.ConfFormTable;
import com.ibusiness.component.form.entity.ConfFormTableColumn;
import com.ibusiness.component.table.dao.TableColumnsDao;
import com.ibusiness.component.table.dao.TableDao;
import com.ibusiness.component.table.entity.ConfTable;
import com.ibusiness.component.table.entity.ConfTableColumns;
import com.ibusiness.core.spring.MessageHelper;

/**
 * 表单管理
 * 
 * @author JiangBo
 * 
 */
@Controller
@RequestMapping("form")
public class FormController {

    private ConfFormDao confFormDao;
    private ConfFormTableDao confFormTableDao;
    private TableColumnsDao tableColumnsDao;
    private ConfFormTableColumnDao confFormTableColumnDao;
    private TableDao tableDao;
    private MessageHelper messageHelper;

    /**
     * 表单信息列表
     * 
     * @return
     */
    @RequestMapping("conf-form-list")
    public String formList(@ModelAttribute Page page, @RequestParam("packageName") String packageName, @RequestParam Map<String, Object> parameterMap, Model model) {
        // 取得表结构信息。
        List<PropertyFilter> propertyFilters = PropertyFilter.buildFromMap(parameterMap);
        propertyFilters.add(new PropertyFilter("EQS_packageName", packageName));
        propertyFilters.add(new PropertyFilter("NEQI_isBpmForm", "1"));
        page = confFormDao.pagedQuery(page, propertyFilters);
        model.addAttribute("page", page);
        model.addAttribute("packageName", packageName);
        
        return "component/form/conf-form-list.jsp"; 
    }
    /**
     * 流程表单信息列表
     * 
     * @return
     */
    @RequestMapping("conf-bpmForm-list")
    public String bpmFormList(@ModelAttribute Page page, @RequestParam("packageName") String packageName, @RequestParam Map<String, Object> parameterMap, Model model) {
        // 取得表结构信息。
        List<PropertyFilter> propertyFilters = PropertyFilter.buildFromMap(parameterMap);
        propertyFilters.add(new PropertyFilter("EQS_packageName", packageName));
        propertyFilters.add(new PropertyFilter("EQI_isBpmForm", "1"));
        page = confFormDao.pagedQuery(page, propertyFilters);
        model.addAttribute("page", page);
        model.addAttribute("packageName", packageName);
        
        return "component/form/conf-bpmform-list.jsp"; 
    }
    /**
     * 表单基础信息 插入页面
     * 
     * @param id
     * @param model
     * @return
     */
    @RequestMapping("conf-form-input")
    public String input(@RequestParam(value = "formId", required = false) String formId, @RequestParam("packageName") String packageName, @RequestParam("isBpmForm") String isBpmForm, Model model) {
        ConfForm confForm = null;
        // 实例化 表单操作配置信息对象
        List<String> selectedItems = new ArrayList<String>();
        if (!CommonUtils.isNull(formId)) {
            confForm = confFormDao.get(formId);
            // 编辑 表单操作配置信息
            selectedItems.add((null != confForm.getIsEdit() && 1 == confForm.getIsEdit()) ? "isEdit": "");
            selectedItems.add((null != confForm.getIsAdd() && 1 == confForm.getIsAdd()) ? "isAdd": "");
            selectedItems.add((null != confForm.getIsDelete() && 1 == confForm.getIsDelete()) ? "isDelete": "");
            selectedItems.add((null != confForm.getIsQuery() && 1 == confForm.getIsQuery()) ? "isQuery": "");
            selectedItems.add((null != confForm.getIsBpmForm() && 1 == confForm.getIsBpmForm()) ? "isBpmForm": "");
        } else {
            confForm = new ConfForm();
            confForm.setPackageName(packageName);
            if (!CommonUtils.isNull(isBpmForm)) {
                confForm.setIsBpmForm(Integer.valueOf(isBpmForm));
            }
        }
        // 设置 表单操作配置信息
        model.addAttribute("selectedItem", selectedItems);
        //
        model.addAttribute("model", confForm);
        model.addAttribute("formId", formId);
        // 控制tab标签显示属性
        model.addAttribute("tabType", "formBase");
        
        // 包名
        model.addAttribute("packageName", packageName);
        model.addAttribute("isBpmForm", isBpmForm);

        return "component/form/conf-form-input.jsp";
    }
    /**
     * 关联表设置
     * 
     * @param id
     * @param packageName
     * @param model
     * @return
     */
    @SuppressWarnings("unchecked")
    @RequestMapping("conf-formTables-input")
    public String formTablesInput(@RequestParam(value = "formId", required = false) String formId, @RequestParam("packageName") String packageName, Model model) {
        // 取得表单信息
        if (CommonUtils.isNull(formId)) {
            // TODO 
            return "component/form/conf-form-input.jsp";
        }
        ConfForm confForm = confFormDao.get(formId);
        // 主表/单表 关联表单
        String hql = "from ConfFormTable WHERE formName=? AND tableType='main' ";
        List<ConfFormTable> formTableList = confFormTableDao.find(hql, confForm.getFormName());
        for (ConfFormTable confFormTable : formTableList) {
            String tbleColumnsHql = "from ConfTableColumns WHERE tableName=?";
            List<ConfTableColumns> tableColumns = tableColumnsDao.find(tbleColumnsHql, confFormTable.getTableName());
            confFormTable.setTableColumns(tableColumns);
        }
        model.addAttribute("mainFormTable", formTableList);
        // 子表 关联表单
        String subHql = "from ConfFormTable WHERE formName=? AND tableType='sub' ";
        List<ConfFormTable> subFormTableList = confFormTableDao.find(subHql, confForm.getFormName());
        for (ConfFormTable confFormTable : subFormTableList) {
            String tbleColumnsHql = "from ConfTableColumns WHERE tableName=?";
            List<ConfTableColumns> tableColumns = tableColumnsDao.find(tbleColumnsHql, confFormTable.getTableName());
            confFormTable.setTableColumns(tableColumns);
        }
        model.addAttribute("subFormTables", subFormTableList);
        // 控制tab标签显示属性
        model.addAttribute("tabType", "formTables");
        // 包名
        model.addAttribute("packageName", packageName);
        model.addAttribute("isBpmForm", confForm.getIsBpmForm());
        model.addAttribute("formId", formId);
        model.addAttribute("formName", confForm.getFormName());
        // 主表列表下拉框
        String tableHql = "from ConfTable WHERE packageName=?";
        List<ConfTable> confTableList = tableDao.find(tableHql, packageName);
        model.addAttribute("mainTable", confTableList);
        // 子表下拉框
        model.addAttribute("subTable", confTableList);

        return "component/form/conf-form-input.jsp";
    }
    /**
     * 字段组件信息列表
     * 
     * @param id
     * @param packageName
     * @param model
     * @return
     */
    @SuppressWarnings("unchecked")
    @RequestMapping("conf-formLabel-list")
    public String formLabelList(@RequestParam(value = "formId", required = false) String formId, @RequestParam("packageName") String packageName, Model model) {
        ConfForm confForm = confFormDao.get(formId);
        // 表单对应字段组件管理, ID主键字段不显示
        String hql = "from ConfFormTableColumn WHERE formName=? AND tableColumn != 'ID'";
        List<ConfFormTableColumn> formTableColumnList = confFormTableColumnDao.find(hql, confForm.getFormName());
        model.addAttribute("formTableColumns", formTableColumnList);
        model.addAttribute("formId", formId);
        
        // 控制tab标签显示属性
        model.addAttribute("tabType", "formLabel");
        // 包名
        model.addAttribute("packageName", packageName);
        model.addAttribute("isBpmForm", confForm.getIsBpmForm());

        return "component/form/conf-form-input.jsp";
    }
    /**
     * 表单字段组件管理
     */
    @SuppressWarnings("unchecked")
    @RequestMapping("conf-formLabel-input")
    public String formLabelInput(@RequestParam(value = "formName", required = false) String formName, @RequestParam(value = "formColumn", required = false) String formColumn, @RequestParam("packageName") String packageName, Model model) {
        // 表单对应字段组件管理
        String hql = "from ConfFormTableColumn WHERE formName=? AND formColumn=? AND packageName=?";
        List<ConfFormTableColumn> formTableColumnList = confFormTableColumnDao.find(hql, formName, formColumn, packageName);
        if (null != formTableColumnList && formTableColumnList.size() > 0) {
            model.addAttribute("model", formTableColumnList.get(0));
        }
        return "component/form/conf-formLabel-input.jsp";
    }
    /**
     * 保存
     * 
     * @return
     * @throws Exception
     */
    @RequestMapping("conf-form-save")
    public String save(@ModelAttribute ConfForm confForm, @RequestParam(value = "selectedItem", required = false) List<String> selectedItems, RedirectAttributes redirectAttributes) throws Exception {
        String id = confForm.getId();
        // 设置 表单操作设置信息。
        confForm.setIsEdit(2);
        confForm.setIsAdd(2);
        confForm.setIsDelete(2);
        confForm.setIsQuery(2);
        if (null != selectedItems) {
            for (String selectedItem : selectedItems) {
                if ("isEdit".equals(selectedItem)) {
                    confForm.setIsEdit(1);
                } else if ("isAdd".equals(selectedItem)) {
                    confForm.setIsAdd(1);
                } else if ("isDelete".equals(selectedItem)) {
                    confForm.setIsDelete(1);
                } else if ("isQuery".equals(selectedItem)) {
                    confForm.setIsQuery(1);
                } else if ("isBpmForm".equals(selectedItem)) {
                    confForm.setIsBpmForm(1);
                }
            }
        }
        if (CommonUtils.isNull(id)) {
            confForm.setId(UUID.randomUUID().toString());
            // save
            confFormDao.saveInsert(confForm);
        } else {
            // save
            confFormDao.update(confForm);
        }
        messageHelper.addFlashMessage(redirectAttributes, "core.success.save", "保存成功");
        return "redirect:/form/conf-form-list.do?packageName=" + confForm.getPackageName();
    }
    /**
     * 保存 主表/单表/子表关联表单
     * 
     * @param confForm
     * @param parameterMap
     * @param redirectAttributes
     * @return
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    @RequestMapping("conf-formTables-save")
    public String formTablesSave(@RequestParam(value = "tableId", required = false) String tableId, 
            @RequestParam(value = "tableType", required = false) String tableType,
            @RequestParam(value = "formId", required = false) String formId,
            @RequestParam("packageName") String packageName,
            Model model, RedirectAttributes redirectAttributes) throws Exception {
        // 如果表ID为空返回
        if (CommonUtils.isNull(tableId)) {
            messageHelper.addFlashMessage(redirectAttributes, "core.failure.save", "请选择表~~!");
            return "redirect:/form/conf-formTables-input.do?packageName=" + packageName + "&formId="+formId;
        }
        // 取得表信息数据
        ConfTable confTable = tableDao.get(tableId);
        // confTable
        ConfFormTable confFormTable = new ConfFormTable();
        confFormTable.setPackageName(packageName);
        // 取得表单信息
        ConfForm confForm = confFormDao.get(formId);
        confFormTable.setFormName(confForm.getFormName());
        confFormTable.setTableName(confTable.getTableName());
        confFormTable.setTableType(tableType);
        confFormTableDao.saveInsert(confFormTable);
        
        // 表单对应字段管理表
        String tbleColumnsHql = "from ConfTableColumns WHERE tableName=?";
        List<ConfTableColumns> tbleColumns = tableColumnsDao.find(tbleColumnsHql, confFormTable.getTableName());
        for (ConfTableColumns tableColumn : tbleColumns) {
            ConfFormTableColumn formTableColumn = new ConfFormTableColumn();
            formTableColumn.setPackageName(packageName);
            formTableColumn.setFormName(confForm.getFormName());
            formTableColumn.setFormColumn(tableColumn.getTableName()+"."+tableColumn.getColumnValue());
            formTableColumn.setFormColumnTitle(confTable.getTableNameComment()+"."+tableColumn.getColumnName());
            formTableColumn.setTableName(confTable.getTableName());
            formTableColumn.setTableColumn(tableColumn.getColumnValue());
            formTableColumn.setFcEdit("1");
            formTableColumn.setFcDisplay("1");
            formTableColumn.setFcQuery("2");
            confFormTableColumnDao.saveInsert(formTableColumn);
        }
        
        messageHelper.addFlashMessage(redirectAttributes, "core.success.save", "保存成功");
        return "redirect:/form/conf-formTables-input.do?packageName=" + packageName + "&formId="+formId;
    }
    /**
     * 表单字段组件保存
     */
    @SuppressWarnings("unchecked")
    @RequestMapping("conf-formLabel-save")
    public String formLabelSave(ConfFormTableColumn model, @RequestParam("packageName") String packageName) {
        // 更新
        confFormTableColumnDao.update(model);
        
        // 查询表单编号
        String formHql="from ConfForm where formName=?";
        List<ConfForm> confForms = confFormDao.find(formHql, model.getFormName());
        String formId = null;
        if (null != confForms && confForms.size() > 0) {
            formId = confForms.get(0).getId();
        }
        return "redirect:/form/conf-formLabel-list.do?packageName=" + packageName + "&formId=" + formId;
    }
    /**
     * 删除
     * 
     * @param selectedItem
     * @param menuLevel
     * @param redirectAttributes
     * @return
     */
    @RequestMapping("conf-form-remove")
    public String remove(@RequestParam("selectedItem") List<String> selectedItem, RedirectAttributes redirectAttributes) {
        List<ConfForm> forms = confFormDao.findByIds(selectedItem);
        confFormDao.removeAll(forms);
        
        return "redirect:/form/conf-form-list.do?packageName=" + forms.get(0).getPackageName();
    }
    /**
     * 删除
     */
    @SuppressWarnings("unchecked")
    @RequestMapping("conf-formTables-remove")
    public String formTablesRemove(@RequestParam("packageName") String packageName, @RequestParam("formName") String formName, @RequestParam("tableName") String tableName, RedirectAttributes redirectAttributes) {
        String hql = "from ConfFormTable where tableName=? And formName=?";
        List<ConfFormTable> formTableList = confFormTableDao.find(hql, tableName, formName);
        confFormTableDao.removeAll(formTableList);
        // 
        String formTableColumnHql = "from ConfFormTableColumn where tableName=? And formName=?";
        List<ConfFormTableColumn> formTableColumns = confFormTableColumnDao.find(formTableColumnHql, tableName, formName);
        confFormTableDao.removeAll(formTableColumns);
        // 查询表单信息,显示用
        String formHql="from ConfForm where formName=?";
        List<ConfForm> confForms = confFormDao.find(formHql, formName);
        String formId = null;
        if (null != confForms && confForms.size() > 0) {
            formId = confForms.get(0).getId();
        }
        messageHelper.addFlashMessage(redirectAttributes, "core.success.remove", "删除成功");
        return "redirect:/form/conf-formTables-input.do?packageName=" + packageName + "&formId="+formId;
    }
    
    /**
     * 注入 
     */
    @Resource
    public void setConfFormDao(ConfFormDao confFormDao) {
        this.confFormDao = confFormDao;
    }
    @Resource
    public void setTableDao(TableDao tableDao) {
        this.tableDao = tableDao;
    }
    @Resource
    public void setConfFormTableDao(ConfFormTableDao confFormTableDao) {
        this.confFormTableDao = confFormTableDao;
    }
    @Resource
    public void setTableColumnsDao(TableColumnsDao tableColumnsDao) {
        this.tableColumnsDao = tableColumnsDao;
    }
    @Resource
    public void setConfFormTableColumnDao(ConfFormTableColumnDao confFormTableColumnDao) {
        this.confFormTableColumnDao = confFormTableColumnDao;
    }
    @Resource
    public void setMessageHelper(MessageHelper messageHelper) {
        this.messageHelper = messageHelper;
    }
}
