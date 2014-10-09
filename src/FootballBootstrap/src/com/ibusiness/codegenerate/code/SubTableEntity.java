package com.ibusiness.codegenerate.code;

import java.util.List;

public class SubTableEntity
{
  private String jdField_a_of_type_JavaLangString;
  private String b;
  private String c;
  private String d;
  private String e;
  private String f;
  private String[] jdField_a_of_type_ArrayOfJavaLangString;
  private List jdField_a_of_type_JavaUtilList;

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
    return this.f;
  }

  public List getSubColums()
  {
    return this.jdField_a_of_type_JavaUtilList;
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
    this.f = paramString;
  }

  public void setSubColums(List paramList)
  {
    this.jdField_a_of_type_JavaUtilList = paramList;
  }

  public String[] getForeignKeys()
  {
    return this.jdField_a_of_type_ArrayOfJavaLangString;
  }

  public void setForeignKeys(String[] paramArrayOfString)
  {
    this.jdField_a_of_type_ArrayOfJavaLangString = paramArrayOfString;
  }

  public String getPrimaryKeyPolicy()
  {
    return this.d;
  }

  public String getSequenceCode()
  {
    return this.e;
  }

  public void setPrimaryKeyPolicy(String paramString)
  {
    this.d = paramString;
  }

  public void setSequenceCode(String paramString)
  {
    this.e = paramString;
  }
}

/* Location:           C:\Users\Administrator\Desktop\commons-cg-2.1.jar
 * Qualified Name:     com.code.SubTableEntity
 * JD-Core Version:    0.5.4
 */