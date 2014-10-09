package com.ibusiness.codegenerate.code.generate;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.ibusiness.codegenerate.code.NonceUtils;
import com.ibusiness.codegenerate.code.DbEntity.DbFiledToJspUtil;
import com.ibusiness.codegenerate.code.window.CreateFileProperty;
import com.ibusiness.codegenerate.util.CodeDateUtils;
import com.ibusiness.codegenerate.util.CodeResourceUtil;
import com.ibusiness.codegenerate.util.def.FtlDef;

public class CodeTreeGenerate
  implements ICallBack
{
  private static String jdField_a_of_type_JavaLangString = "auto";
  private static String jdField_b_of_type_JavaLangString = "AccountItem";
  private static String c = "t88_account_item";
  private static String d = "会计科目";
  public static int FIELD_ROW_NUM = 1;
  private static CreateFileProperty jdField_a_of_type_ComCodeGenerateWindowCreateFileProperty = new CreateFileProperty();
  private List jdField_a_of_type_JavaUtilList = new ArrayList();
  private List jdField_b_of_type_JavaUtilList = new ArrayList();
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

  public CodeTreeGenerate()
  {
  }

  public CodeTreeGenerate(String paramString1, String paramString2, String paramString3, String paramString4, CreateFileProperty paramCreateFileProperty, int paramInt)
  {
    jdField_b_of_type_JavaLangString = paramString2;
    jdField_a_of_type_JavaLangString = paramString1;
    c = paramString3;
    d = paramString4;
    jdField_a_of_type_ComCodeGenerateWindowCreateFileProperty = paramCreateFileProperty;
    FIELD_ROW_NUM = paramInt;
  }

  public CodeTreeGenerate(String paramString1, String paramString2, String paramString3, String paramString4, CreateFileProperty paramCreateFileProperty)
  {
    jdField_b_of_type_JavaLangString = paramString2;
    jdField_a_of_type_JavaLangString = paramString1;
    c = paramString3;
    d = paramString4;
    jdField_a_of_type_ComCodeGenerateWindowCreateFileProperty = paramCreateFileProperty;
  }

  public CodeTreeGenerate(String paramString1, String paramString2, String paramString3, String paramString4)
  {
    jdField_b_of_type_JavaLangString = paramString2;
    jdField_a_of_type_JavaLangString = paramString1;
    c = paramString3;
    d = paramString4;
  }

  public Map execute()
  {
    HashMap localHashMap = new HashMap();
    localHashMap.put("bussiPackage", CodeResourceUtil.bussiPackage);
    localHashMap.put("entityPackage", jdField_a_of_type_JavaLangString);
    localHashMap.put("entityName", jdField_b_of_type_JavaLangString);
    localHashMap.put("tableName", c);
    localHashMap.put("ftl_description", d);
    localHashMap.put(FtlDef.JEECG_TABLE_ID, CodeResourceUtil.JEECG_GENERATE_TABLE_ID);
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
      this.jdField_a_of_type_JavaUtilList = this.jdField_a_of_type_ComCodeDbEntityDbFiledToJspUtil.readTableColumn(c);
      localHashMap.put("columns", this.jdField_a_of_type_JavaUtilList);
      this.jdField_b_of_type_JavaUtilList = this.jdField_a_of_type_ComCodeDbEntityDbFiledToJspUtil.readOriginalTableColumn(c);
      localHashMap.put("originalColumns", this.jdField_b_of_type_JavaUtilList);
    }
    catch (Exception localException2)
    {
      localException2.printStackTrace();
    }
    long l = NonceUtils.randomLong() + NonceUtils.currentMills();
    localHashMap.put("serialVersionUID", String.valueOf(l));
    return localHashMap;
  }

  public void generateToFile()
  {
    CodeFactory localCodeFactory = new CodeFactory();
    localCodeFactory.setCallBack(new CodeTreeGenerate());
    if (jdField_a_of_type_ComCodeGenerateWindowCreateFileProperty.isJspFlag())
      localCodeFactory.invoke("tree/jspTreeTemplate.ftl", "jsp");
    if (jdField_a_of_type_ComCodeGenerateWindowCreateFileProperty.isServiceImplFlag())
      localCodeFactory.invoke("tree/serviceImplTreeTemplate.ftl", "serviceImpl");
    if (jdField_a_of_type_ComCodeGenerateWindowCreateFileProperty.isServiceIFlag())
      localCodeFactory.invoke("tree/serviceItreeTemplate.ftl", "service");
    if (jdField_a_of_type_ComCodeGenerateWindowCreateFileProperty.isActionFlag())
      localCodeFactory.invoke("tree/actionTreeTemplate.ftl", "action");
    if (jdField_a_of_type_ComCodeGenerateWindowCreateFileProperty.isPageFlag())
    {
      localCodeFactory.invoke("tree/pageTreeTemplate.ftl", "page");
      localCodeFactory.invoke("tree/comparatorTemplate.ftl", "comparator");
    }
    if (!jdField_a_of_type_ComCodeGenerateWindowCreateFileProperty.isEntityFlag())
      return;
    localCodeFactory.invoke("tree/entityTreeTemplate.ftl", "entity");
  }

  public static void main(String[] paramArrayOfString)
  {
    new CodeTreeGenerate().generateToFile();
    System.out.println("-- success --");
  }
}

/* Location:           C:\Users\Administrator\Desktop\commons-cg-2.1.jar
 * Qualified Name:     com.code.generate.CodeTreeGenerate
 * JD-Core Version:    0.5.4
 */