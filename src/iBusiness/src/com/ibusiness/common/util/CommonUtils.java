package com.ibusiness.common.util;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 共用方法类
 * 
 * @author JiangBo
 *
 */
public class CommonUtils {

    /**
     * 单例模式
     */
    private static CommonUtils instance = new CommonUtils();
    private CommonUtils() {}
    public static CommonUtils getInstance() {
        return instance;
    }
    
    /**
     * 字符串,日期类型转换用变量
     */
    private SimpleDateFormat ymdhms = new SimpleDateFormat(Constants.DATE_FORMAT_YYYY_MM_DD_HH_MM_SS);
    /**
     * Log4j
     */
    private static Logger logger = LoggerFactory.getLogger(CommonUtils.class);
	
    /**
     * 判断日期之间的天数
     * 
     * @return
     */
    @SuppressWarnings("deprecation")
    public boolean isDaysBetween(String startTime, String endTime, int number) {
        try {
            Date startDate = ymdhms.parse(startTime.replace("T", " "));
            startDate.setDate(startDate.getDate() + number);
            if (startDate.after(ymdhms.parse(endTime.replace("T", " ")))) {
                return true;
            }
        } catch (ParseException e) {
            logger.error("==========================导出Excel方法 ParseException:" + e.toString());
        }
        return false;
    }
    
    /**
     * 判断字符串是否为bull
     * @param str
     * @return true 为空 false 不为空
     */
    public static boolean isNull(String str) {
        return (null == str || "".equals(str) || "null".equals(str)) ? true : false;
    }
    
    /**
     * Double格式化,返回一位小数
     * @param temp
     * @return
     */
    public static String formatDoubleBy1Decimal(double temp) {
        return new DecimalFormat("0.0").format(temp);
    }
    /**
     * Double格式化,返回一位小数
     * @param temp
     * @return
     */
    public static String formatDoubleBy2Decimal(double temp) {
        return new DecimalFormat("0.00").format(temp);
    }
    /**
     * 链接JSON字符串，是否加逗号分隔
     * @return
     */
    public static String appendJsonString(String strOne, String strTwo, boolean flag) {
        if (flag) {
            strOne = strOne + ",";
        }
        strOne = strOne + strTwo;
        return strOne;
    }
}
