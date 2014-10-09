package com.ibusiness.codegenerate.code.generate;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.ibusiness.codegenerate.code.CodeParamEntity;
import com.ibusiness.codegenerate.code.Columnt;
import com.ibusiness.codegenerate.code.NonceUtils;
import com.ibusiness.codegenerate.code.SubTableEntity;
import com.ibusiness.codegenerate.code.DbEntity.DbFiledToJspUtil;
import com.ibusiness.codegenerate.code.window.CreateFileProperty;
import com.ibusiness.codegenerate.util.CodeDateUtils;
import com.ibusiness.codegenerate.util.CodeResourceUtil;
import com.ibusiness.codegenerate.util.def.FtlDef;
import com.ibusiness.codegenerate.util.def.JeecgKey;

public class CodeGenerateOneToMany
  implements ICallBack
{
  private static final Log jdField_a_of_type_OrgApacheCommonsLoggingLog = LogFactory.getLog(CodeGenerateOneToMany.class);
  private static String jdField_a_of_type_JavaLangString = "test";
  private static String jdField_b_of_type_JavaLangString = "Person";
  private static String jdField_c_of_type_JavaLangString = "person";
  private static String jdField_d_of_type_JavaLangString = "用户";
  private static String e = "uuid";
  private static String f = "";
  private static String g;
  public static String FTL_MODE_A = "A";
  public static String FTL_MODE_B = "B";
  private static List jdField_a_of_type_JavaUtilList = new ArrayList();
  private static CreateFileProperty jdField_a_of_type_ComCodeGenerateWindowCreateFileProperty = new CreateFileProperty();
  public static int FIELD_ROW_NUM = 4;
  private List jdField_b_of_type_JavaUtilList = new ArrayList();
  private List jdField_c_of_type_JavaUtilList = new ArrayList();
  private List jdField_d_of_type_JavaUtilList = new ArrayList();
  private DbFiledToJspUtil jdField_a_of_type_ComCodeDbEntityDbFiledToJspUtil = new DbFiledToJspUtil();

  static
  {
    jdField_a_of_type_ComCodeGenerateWindowCreateFileProperty.setActionFlag(true);
    jdField_a_of_type_ComCodeGenerateWindowCreateFileProperty.setServiceIFlag(true);
    jdField_a_of_type_ComCodeGenerateWindowCreateFileProperty.setJspFlag(true);
    jdField_a_of_type_ComCodeGenerateWindowCreateFileProperty.setServiceImplFlag(true);
    jdField_a_of_type_ComCodeGenerateWindowCreateFileProperty.setPageFlag(true);
    jdField_a_of_type_ComCodeGenerateWindowCreateFileProperty.setEntityFlag(true);
  }

  public CodeGenerateOneToMany()
  {
  }

  public CodeGenerateOneToMany(String paramString1, String paramString2, String paramString3, List paramList, String paramString4, CreateFileProperty paramCreateFileProperty, String paramString5, String paramString6)
  {
    jdField_b_of_type_JavaLangString = paramString2;
    jdField_a_of_type_JavaLangString = paramString1;
    jdField_c_of_type_JavaLangString = paramString3;
    jdField_d_of_type_JavaLangString = paramString4;
    jdField_a_of_type_ComCodeGenerateWindowCreateFileProperty = paramCreateFileProperty;
    jdField_a_of_type_JavaUtilList = paramList;
    e = (StringUtils.isNotBlank(paramString5)) ? paramString5 : "uuid";
    f = paramString6;
  }

  public CodeGenerateOneToMany(CodeParamEntity paramCodeParamEntity)
  {
    jdField_b_of_type_JavaLangString = paramCodeParamEntity.getEntityName();
    jdField_a_of_type_JavaLangString = paramCodeParamEntity.getEntityPackage();
    jdField_c_of_type_JavaLangString = paramCodeParamEntity.getTableName();
    jdField_d_of_type_JavaLangString = paramCodeParamEntity.getFtlDescription();
    jdField_a_of_type_JavaUtilList = paramCodeParamEntity.getSubTabParam();
    g = paramCodeParamEntity.getFtl_mode();
    e = (StringUtils.isNotBlank(paramCodeParamEntity.getPrimaryKeyPolicy())) ? paramCodeParamEntity.getPrimaryKeyPolicy() : "uuid";
    f = paramCodeParamEntity.getSequenceCode();
  }

  public Map execute()
  {
    HashMap localHashMap = new HashMap();
    localHashMap.put("bussiPackage", CodeResourceUtil.bussiPackage);
    localHashMap.put("entityPackage", jdField_a_of_type_JavaLangString);
    localHashMap.put("entityName", jdField_b_of_type_JavaLangString);
    localHashMap.put("tableName", jdField_c_of_type_JavaLangString);
    localHashMap.put("ftl_description", jdField_d_of_type_JavaLangString);
    localHashMap.put("jeecg_table_id", CodeResourceUtil.JEECG_GENERATE_TABLE_ID);
    localHashMap.put(FtlDef.JEECG_PRIMARY_KEY_POLICY, e);
    localHashMap.put(FtlDef.JEECG_SEQUENCE_CODE, f);
    localHashMap.put("ftl_create_time", CodeDateUtils.dateToString(new Date()));
    localHashMap.put(FtlDef.FIELD_REQUIRED_NAME, Integer.valueOf((StringUtils.isNotEmpty(CodeResourceUtil.JEECG_UI_FIELD_REQUIRED_NUM)) ? Integer.parseInt(CodeResourceUtil.JEECG_UI_FIELD_REQUIRED_NUM) : -1));
    localHashMap.put(FtlDef.SEARCH_FIELD_NUM, Integer.valueOf((StringUtils.isNotEmpty(CodeResourceUtil.JEECG_UI_FIELD_SEARCH_NUM)) ? Integer.parseInt(CodeResourceUtil.JEECG_UI_FIELD_SEARCH_NUM) : -1));
    localHashMap.put(FtlDef.FIELD_ROW_NAME, Integer.valueOf(FIELD_ROW_NUM));
    try
    {
      jdField_a_of_type_ComCodeGenerateWindowCreateFileProperty.isEntityFlag();
      jdField_a_of_type_ComCodeGenerateWindowCreateFileProperty.isPageFlag();
    }
    catch (Exception localException1)
    {
      localException1.printStackTrace();
    }
    try
    {
      this.jdField_b_of_type_JavaUtilList = this.jdField_a_of_type_ComCodeDbEntityDbFiledToJspUtil.readTableColumn(jdField_c_of_type_JavaLangString);
      localHashMap.put("mainColums", this.jdField_b_of_type_JavaUtilList);
      localHashMap.put("columns", this.jdField_b_of_type_JavaUtilList);
      this.jdField_c_of_type_JavaUtilList = this.jdField_a_of_type_ComCodeDbEntityDbFiledToJspUtil.readOriginalTableColumn(jdField_c_of_type_JavaLangString);
      localHashMap.put("originalColumns", this.jdField_c_of_type_JavaUtilList);
      Iterator localIterator = this.jdField_c_of_type_JavaUtilList.iterator();
      Object localObject;
      while (localIterator.hasNext())
      {
        localObject = (Columnt)localIterator.next();
        if (!((Columnt)localObject).getFieldName().toLowerCase().equals(CodeResourceUtil.JEECG_GENERATE_TABLE_ID.toLowerCase()))
          continue;
        localHashMap.put("primary_key_type", ((Columnt)localObject).getFieldType());
      }
      this.jdField_d_of_type_JavaUtilList.clear();
      localIterator = jdField_a_of_type_JavaUtilList.iterator();
      while (localIterator.hasNext())
      {
        localObject = (SubTableEntity)localIterator.next();
        SubTableEntity localSubTableEntity = new SubTableEntity();
        List localList = this.jdField_a_of_type_ComCodeDbEntityDbFiledToJspUtil.readTableColumn(((SubTableEntity)localObject).getTableName());
        localSubTableEntity.setSubColums(localList);
        localSubTableEntity.setEntityName(((SubTableEntity)localObject).getEntityName());
        localSubTableEntity.setFtlDescription(((SubTableEntity)localObject).getFtlDescription());
        localSubTableEntity.setTableName(((SubTableEntity)localObject).getTableName());
        localSubTableEntity.setEntityPackage(((SubTableEntity)localObject).getEntityPackage());
        String[] arrayOfString1 = ((SubTableEntity)localObject).getForeignKeys();
        ArrayList localArrayList = new ArrayList();
        for (String str1 : arrayOfString1)
          if (CodeResourceUtil.JEECG_FILED_CONVERT)
          {
            localArrayList.add(DbFiledToJspUtil.formatFieldCapital(str1));
          }
          else
          {
            String str2 = str1.toLowerCase();
            String str3 = str2.substring(0, 1).toUpperCase() + str2.substring(1);
            localArrayList.add(str3);
          }
        localSubTableEntity.setForeignKeys((String[])localArrayList.toArray(new String[0]));
        this.jdField_d_of_type_JavaUtilList.add(localSubTableEntity);
      }
      localHashMap.put("subTab", this.jdField_d_of_type_JavaUtilList);
    }
    catch (Exception localException2)
    {
      localException2.printStackTrace();
      System.exit(-1);
    }
    long l = NonceUtils.randomLong() + NonceUtils.currentMills();
    localHashMap.put("serialVersionUID", String.valueOf(l));
    return (Map)localHashMap;
  }

  public void generateToFile()
  {
    CodeFactoryOneToMany localCodeFactoryOneToMany = new CodeFactoryOneToMany();
    localCodeFactoryOneToMany.setCallBack(new CodeGenerateOneToMany());
    if (jdField_a_of_type_ComCodeGenerateWindowCreateFileProperty.isJspFlag())
    {
      localCodeFactoryOneToMany.invoke("jspOneToManyTemplate.ftl", "jsp");
      if (FTL_MODE_A.equals(g))
      {
        localCodeFactoryOneToMany.invoke("jspOnetoMainAddTemplate.ftl", "jspAdd");
        localCodeFactoryOneToMany.invoke("jspOnetoMainEditTemplate.ftl", "jspEdit");
      }
      else if (FTL_MODE_B.equals(g))
      {
        localCodeFactoryOneToMany.invoke("jspOnetoMainAddTemplate_tab.ftl", "jspAdd");
        localCodeFactoryOneToMany.invoke("jspOnetoMainEditTemplate_tab.ftl", "jspEdit");
      }
    }
    if (jdField_a_of_type_ComCodeGenerateWindowCreateFileProperty.isServiceImplFlag())
      localCodeFactoryOneToMany.invoke("serviceImplOnetoMainTemplate.ftl", "serviceImpl");
    if (jdField_a_of_type_ComCodeGenerateWindowCreateFileProperty.isServiceIFlag())
      localCodeFactoryOneToMany.invoke("serviceIOnetoMainTemplate.ftl", "service");
    if (jdField_a_of_type_ComCodeGenerateWindowCreateFileProperty.isActionFlag())
      localCodeFactoryOneToMany.invoke("actionOnetoMainTemplate.ftl", "action");
    if (jdField_a_of_type_ComCodeGenerateWindowCreateFileProperty.isEntityFlag())
      localCodeFactoryOneToMany.invoke("entityTemplate.ftl", "entity");
    if (!jdField_a_of_type_ComCodeGenerateWindowCreateFileProperty.isPageFlag())
      return;
    localCodeFactoryOneToMany.invoke("pageEntityTemplate.ftl", "page");
  }

  public static void main(String[] paramArrayOfString)
  {
    List localArrayList = new ArrayList();
    SubTableEntity localSubTableEntity1 = new SubTableEntity();
    localSubTableEntity1.setTableName("t60_gbuy_order_custom");
    localSubTableEntity1.setEntityName("GbuyOrderCustom");
    localSubTableEntity1.setEntityPackage("order");
    localSubTableEntity1.setFtlDescription("订单客户明细");
    localSubTableEntity1.setPrimaryKeyPolicy(JeecgKey.UUID);
    localSubTableEntity1.setSequenceCode(null);
    localSubTableEntity1.setForeignKeys(new String[] { "GORDER_OBID", "GO_ORDER_CODE" });
    localArrayList.add(localSubTableEntity1);
    SubTableEntity localSubTableEntity2 = new SubTableEntity();
    localSubTableEntity2.setTableName("t60_gbuy_order_product");
    localSubTableEntity2.setEntityName("GbuyOrderProduct");
    localSubTableEntity2.setEntityPackage("order");
    localSubTableEntity2.setFtlDescription("订单产品明细");
    localSubTableEntity2.setForeignKeys(new String[] { "GORDER_OBID", "GO_ORDER_CODE" });
    localSubTableEntity2.setPrimaryKeyPolicy(JeecgKey.UUID);
    localSubTableEntity2.setSequenceCode(null);
    localArrayList.add(localSubTableEntity2);
    CodeParamEntity localCodeParamEntity = new CodeParamEntity();
    localCodeParamEntity.setTableName("t60_gbuy_order");
    localCodeParamEntity.setEntityName("GbuyOrder");
    localCodeParamEntity.setEntityPackage("order");
    localCodeParamEntity.setFtlDescription("订单抬头");
    localCodeParamEntity.setFtl_mode(FTL_MODE_B);
    localCodeParamEntity.setPrimaryKeyPolicy(JeecgKey.UUID);
    localCodeParamEntity.setSequenceCode(null);
    localCodeParamEntity.setSubTabParam(localArrayList);
    oneToManyCreate(localArrayList, localCodeParamEntity);
  }

  public static void oneToManyCreate(List paramList, CodeParamEntity paramCodeParamEntity)
  {
    jdField_a_of_type_OrgApacheCommonsLoggingLog.info("----Code-----Generation-----[一对多数据模型：" + paramCodeParamEntity.getTableName() + "]------- 生成中。。。");
    CreateFileProperty localCreateFileProperty = new CreateFileProperty();
    localCreateFileProperty.setActionFlag(false);
    localCreateFileProperty.setServiceIFlag(true);
    localCreateFileProperty.setJspFlag(false);
    localCreateFileProperty.setServiceImplFlag(true);
    localCreateFileProperty.setPageFlag(true);
    localCreateFileProperty.setEntityFlag(true);
    Iterator localIterator = paramList.iterator();
    while (localIterator.hasNext())
    {
      SubTableEntity localSubTableEntity = (SubTableEntity)localIterator.next();
      // TODO
      new CodeGenerate(localSubTableEntity.getEntityPackage(), "", localSubTableEntity.getEntityName(), localSubTableEntity.getTableName(), localSubTableEntity.getFtlDescription(), localCreateFileProperty, (StringUtils.isNotBlank(localSubTableEntity.getPrimaryKeyPolicy())) ? localSubTableEntity.getPrimaryKeyPolicy() : "uuid", localSubTableEntity.getSequenceCode()).generateToFile();
    }
    new CodeGenerateOneToMany(paramCodeParamEntity).generateToFile();
    jdField_a_of_type_OrgApacheCommonsLoggingLog.info("----Code----Generation------[一对多数据模型：" + paramCodeParamEntity.getTableName() + "]------ 生成完成。。。");
  }
}

/* Location:           C:\Users\Administrator\Desktop\commons-cg-2.1.jar
 * Qualified Name:     com.code.generate.CodeGenerateOneToMany
 * JD-Core Version:    0.5.4
 */