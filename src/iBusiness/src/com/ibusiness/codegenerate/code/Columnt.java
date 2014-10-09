package com.ibusiness.codegenerate.code;

/**
 * 表列字段Bean
 * 
 * @author JiangBo
 *
 */
public class Columnt
{
  public static final String OPTION_REQUIRED = "required:true";
  public static final String OPTION_NUMBER_INSEX = "precision:2,groupSeparator:','";
  private String fieldDbName;
  private String fieldName;
  private String filedComment = "";
  private String fieldType = "";
  private String classType = "";
  private String classType_row = "";
  private String optionType = "";
  private String charmaxLength = "";
  private String precision;
  private String scale;
  private String nullable;

  public String getNullable()
  {
    return this.nullable;
  }

  public void setNullable(String paramString)
  {
    this.nullable = paramString;
  }

  public String getPrecision()
  {
    return this.precision;
  }

  public String getScale()
  {
    return this.scale;
  }

  public void setPrecision(String paramString)
  {
    this.precision = paramString;
  }

  public void setScale(String paramString)
  {
    this.scale = paramString;
  }

  public String getOptionType()
  {
    return this.optionType;
  }

  public void setOptionType(String paramString)
  {
    this.optionType = paramString;
  }

  public String getClassType()
  {
    return this.classType;
  }

  public void setClassType(String paramString)
  {
    this.classType = paramString;
  }

  public String getFieldType()
  {
    return this.fieldType;
  }

  public void setFieldType(String paramString)
  {
    this.fieldType = paramString;
  }

  public String getFieldName()
  {
    return this.fieldName;
  }

  public void setFieldName(String paramString)
  {
    this.fieldName = paramString;
  }

  public String getFiledComment()
  {
    return this.filedComment;
  }

  public void setFiledComment(String paramString)
  {
    this.filedComment = paramString;
  }

  public String getClassType_row()
  {
    if ((this.classType != null) && (this.classType.indexOf("easyui-") >= 0))
      return this.classType.replaceAll("easyui-", "");
    return this.classType_row;
  }

  public void setClassType_row(String paramString)
  {
    this.classType_row = paramString;
  }

  public String getCharmaxLength()
  {
    if ((this.charmaxLength == null) || ("0".equals(this.charmaxLength)))
      return "";
    return this.charmaxLength;
  }

  public void setCharmaxLength(String paramString)
  {
    this.charmaxLength = paramString;
  }

  public String getFieldDbName()
  {
    return this.fieldDbName;
  }

  public void setFieldDbName(String paramString)
  {
    this.fieldDbName = paramString;
  }
}
