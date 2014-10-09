package com.ibusiness.codegenerate.code.generate;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.ibusiness.codegenerate.code.Columnt;
import com.ibusiness.codegenerate.code.NonceUtils;
import com.ibusiness.codegenerate.code.DbEntity.DbFiledToJspUtil;
import com.ibusiness.codegenerate.code.window.CreateFileProperty;
import com.ibusiness.codegenerate.util.CodeDateUtils;
import com.ibusiness.codegenerate.util.CodeResourceUtil;
import com.ibusiness.codegenerate.util.def.FtlDef;
import com.ibusiness.common.service.CommonBusiness;
import com.ibusiness.component.form.entity.ConfForm;
import com.ibusiness.component.form.entity.ConfFormTableColumn;
import com.ibusiness.component.table.entity.ConfTableColumns;

/**
 * 一对一代码生成器
 * 
 * @author JiangBo
 * 
 */
public class CodeGenerate implements ICallBack {
    /**
     * log
     */
    private static final Log log = LogFactory.getLog(CodeGenerate.class);
    private static String entityPackage = "test";
    private static String entityName = "Company";
    private static String formName = "";
    private static String tableName = "person";
    private static String tableDescription = "分公司";
    private static String primaryKeyPolicy = "uuid";
    private static String sequenceCode = "";
    // 表字段List
    private List<Columnt> columns = new ArrayList<Columnt>();
    // 表字段(原值)List
    private List<Columnt> originalColumns = new ArrayList<Columnt>();
    public static int FIELD_ROW_NUM = 1;
    private static CreateFileProperty createFileProperty = new CreateFileProperty();
    
    // JSP通用数据文件实体
    private DbFiledToJspUtil dbFiledToJspUtil = new DbFiledToJspUtil();

    static {
        createFileProperty.setActionFlag(true);
        createFileProperty.setServiceIFlag(true);
        createFileProperty.setJspFlag(true);
        createFileProperty.setServiceImplFlag(true);
        createFileProperty.setJspMode("01");
        createFileProperty.setPageFlag(true);
        createFileProperty.setEntityFlag(true);
    }

    /**
     * 构造函数
     */
    public CodeGenerate() {
    }
    public CodeGenerate(String packageStr, String formName, String entityNameStr, String tableNameStr, String descriptionStr,
            CreateFileProperty paramCreateFileProperty, int rowNumInt, String pkStr, String sequenceStr) {
        CodeGenerate.entityName = entityNameStr;
        CodeGenerate.formName = formName;
        CodeGenerate.entityPackage = packageStr;
        CodeGenerate.tableName = tableNameStr;
        CodeGenerate.tableDescription = descriptionStr;
        CodeGenerate.createFileProperty = paramCreateFileProperty;
        CodeGenerate.FIELD_ROW_NUM = rowNumInt;
        CodeGenerate.primaryKeyPolicy = pkStr;
        CodeGenerate.sequenceCode = sequenceStr;
    }
    public CodeGenerate(String packageStr, String formName, String entityNameStr, String tableNameStr, String descriptionStr,
            CreateFileProperty paramCreateFileProperty, String pkStr, String sequenceStr) {
        CodeGenerate.entityName = entityNameStr;
        CodeGenerate.formName = formName;
        CodeGenerate.entityPackage = packageStr;
        CodeGenerate.tableName = tableNameStr;
        CodeGenerate.tableDescription = descriptionStr;
        CodeGenerate.createFileProperty = paramCreateFileProperty;
        CodeGenerate.primaryKeyPolicy = pkStr;
        CodeGenerate.sequenceCode = sequenceStr;
    }

    /**
     * 执行方法,此方法中返回模板用的Map数据
     */
    public Map<String, Object> execute() {
        HashMap<String, Object> localHashMap = new HashMap<String, Object>();
        localHashMap.put("bussiPackage", CodeResourceUtil.bussiPackage);
        // 包名
        localHashMap.put("entityPackage", entityPackage);
        // 实体名
        localHashMap.put("entityName", entityName);
        // 表名
        localHashMap.put("tableName", tableName);
        // 表描述
        localHashMap.put("ftl_description", tableDescription);
        localHashMap.put(FtlDef.JEECG_TABLE_ID, CodeResourceUtil.JEECG_GENERATE_TABLE_ID);
        // 主键
        localHashMap.put(FtlDef.JEECG_PRIMARY_KEY_POLICY, primaryKeyPolicy);
        // SEQUENCE
        localHashMap.put(FtlDef.JEECG_SEQUENCE_CODE, sequenceCode);
        // 创建时间
        localHashMap.put("ftl_create_time", CodeDateUtils.dateToString(new Date()));
        localHashMap.put(FtlDef.FIELD_REQUIRED_NAME, Integer.valueOf((StringUtils.isNotEmpty(CodeResourceUtil.JEECG_UI_FIELD_REQUIRED_NUM)) ? Integer
                .parseInt(CodeResourceUtil.JEECG_UI_FIELD_REQUIRED_NUM) : -1));
        localHashMap.put(FtlDef.SEARCH_FIELD_NUM, Integer.valueOf((StringUtils.isNotEmpty(CodeResourceUtil.JEECG_UI_FIELD_SEARCH_NUM)) ? Integer
                .parseInt(CodeResourceUtil.JEECG_UI_FIELD_SEARCH_NUM) : -1));
        // 行字段数目
        localHashMap.put(FtlDef.FIELD_ROW_NAME, Integer.valueOf(FIELD_ROW_NUM));
        try {
            createFileProperty.isEntityFlag();
            createFileProperty.isPageFlag();
        } catch (Exception e) {
            log.error("===================" + e.toString());
        }
        try {
            // 读取指定表名的表字段List
            this.columns = this.dbFiledToJspUtil.readTableColumn(tableName);
            // 取得表字段信息
            Map<String, ConfTableColumns> tableColumnsMap = CommonBusiness.getInstance().getTableColumnsMap(tableName);
            if (tableColumnsMap.size() > 0) {
                for (Columnt columnt : this.columns) {
                    String key = columnt.getFieldDbName();
                    if (tableColumnsMap.containsKey(key)) {
                        columnt.setFiledComment(tableColumnsMap.get(key).getColumnName());
                    }
                }
            }
            // 设置表字段信息
            localHashMap.put("columns", this.columns);
            
            // 取得表单信息
            List<ConfForm> formList = CommonBusiness.getInstance().getFormList(formName);
            // 表单信息
            localHashMap.put("confForm", formList.get(0));
            
            // 取得表单对应表管理表信息
            List<ConfFormTableColumn> formTableColumnList = CommonBusiness.getInstance().getFormTableColumnList(tableName, formName);
            for (ConfFormTableColumn formTableColumn : formTableColumnList) {
                // 转换小写
                formTableColumn.setTableColumnLower(formTableColumn.getTableColumn().toLowerCase());
            }
            // 表单字段信息
            localHashMap.put("formTableColumnList", formTableColumnList);
            
            // 读取指定表名的表字段(原值)List
            this.originalColumns = this.dbFiledToJspUtil.readOriginalTableColumn(tableName);
            localHashMap.put("originalColumns", this.originalColumns);
            // 设置值
            for (Columnt columnt : originalColumns) {
                if (!columnt.getFieldName().toLowerCase().equals(CodeResourceUtil.JEECG_GENERATE_TABLE_ID.toLowerCase())) {
                    continue;
                }
                localHashMap.put("primary_key_type", columnt.getFieldType());
            }
        } catch (Exception e) {
            log.error("===================" + e.toString());
            System.exit(-1);
        }
        long serialVersionUID = NonceUtils.randomLong() + NonceUtils.currentMills();
        localHashMap.put("serialVersionUID", String.valueOf(serialVersionUID));
        return localHashMap;
    }

    /**
     * 生成文件
     */
    public void generateToFile() {
        log.info("----Code----Generation----[单表模型:" + tableName + "]------- 生成中。。。");
        // 代码生成工厂类
        CodeFactory codeFactory = new CodeFactory();
        codeFactory.setCallBack(new CodeGenerate());
        // JSP文件
        if (createFileProperty.isJspFlag()) {
            if ("01".equals(createFileProperty.getJspMode())) {
                codeFactory.invoke("jspListTemplate.ftl", "jspList");
                codeFactory.invoke("jspInputTemplate.ftl", "jsp");
            }
            if ("02".equals(createFileProperty.getJspMode())) {
                codeFactory.invoke("jspRowEditTemplate.ftl", "jsp");
            }
        }
        // ServiceImpl文件
        if (createFileProperty.isServiceImplFlag()) {
            codeFactory.invoke("serviceImplTemplate.ftl", "serviceImpl");
        }
        // ServiceI文件
        if (createFileProperty.isServiceIFlag()) {
            codeFactory.invoke("serviceTemplate.ftl", "service");
        }
        // controller文件
        if (createFileProperty.isActionFlag()) {
            codeFactory.invoke("controllerTemplate.ftl", "controller");
        }
        // entity文件
        if (createFileProperty.isEntityFlag()) {
            codeFactory.invoke("entityTemplate.ftl", "entity");
        }
        // page文件
        if (createFileProperty.isPageFlag()) {
            codeFactory.invoke("pageEntityTemplate.ftl", "page");
        }
        log.info("----Code----Generation-----[单表模型：" + tableName + "]------ 生成完成。。。");
    }

    /**
     * main测试方法
     * @param arg
     */
    public static void main(String[] arg) {
        System.out.println("----Code------------- Generation -----[单表模型]------- 生成中。。。");
        new CodeGenerate().generateToFile();
        System.out.println("----Code------------- Generation -----[单表模型]------- 生成完成。。。");
    }
    // ===============================================
}
