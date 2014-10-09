package com.ibusiness.codegenerate.code;

import java.util.List;

public class CodeParamEntity
{
  private String jdField_a_of_type_JavaLangString;
  private String b;
  private String c;
  private String d;
  private String e;
  private String f;
  private String g = "A";
  List jdField_a_of_type_JavaUtilList;

  public List getSubTabParam()
  {
    return this.jdField_a_of_type_JavaUtilList;
  }

  public void setSubTabParam(List paramList)
  {
    this.jdField_a_of_type_JavaUtilList = paramList;
  }

  public String getEntityPackage()
  {
    return this.jdField_a_of_type_JavaLangString;
  }

  public String getTableName()
  {
    return this.b;
  }

  public String getEntityName()
  {
    return this.c;
  }

  public String getFtlDescription()
  {
    return this.d;
  }

  public void setEntityPackage(String paramString)
  {
    this.jdField_a_of_type_JavaLangString = paramString;
  }

  public void setTableName(String paramString)
  {
    this.b = paramString;
  }

  public void setEntityName(String paramString)
  {
    this.c = paramString;
  }

  public void setFtlDescription(String paramString)
  {
    this.d = paramString;
  }

  public String getFtl_mode()
  {
    return this.g;
  }

  public void setFtl_mode(String paramString)
  {
    this.g = paramString;
  }

  public String getPrimaryKeyPolicy()
  {
    return this.e;
  }

  public String getSequenceCode()
  {
    return this.f;
  }

  public void setPrimaryKeyPolicy(String paramString)
  {
    this.e = paramString;
  }

  public void setSequenceCode(String paramString)
  {
    this.f = paramString;
  }
}

/* Location:           C:\Users\Administrator\Desktop\commons-cg-2.1.jar
 * Qualified Name:     com.code.CodeParamEntity
 * JD-Core Version:    0.5.4
 */