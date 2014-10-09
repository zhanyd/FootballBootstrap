package com.ibusiness.common.page;

/**
 * MatchType.查询条件枚举类
 * 
 * @author Lingo
 */
public enum MatchType {
    /** not equals !=. */
    NEQ,
    /** equals. */
    EQ,
    /** like. */
    LIKE,
    /** less than. */
    LT,
    /** greater than. */
    GT,
    /** less equals. */
    LE,
    /** greater equals. */
    GE,
    /** in. */
    IN,
    /** unknown. */
    UNKNOWN;
}
