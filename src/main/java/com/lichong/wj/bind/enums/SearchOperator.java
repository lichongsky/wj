package com.lichong.wj.bind.enums;

import com.lichong.wj.exception.errorType.SearchException;
import com.lichong.wj.util.StringUtil;

/**
 * 搜索操作符
 * 2018年1月2日
 * @author cyg
 */
public enum SearchOperator {
	/**
	 * 搜索操作枚举
	 */
    eq("等于", "="), ne("不等于", "!="),
    gt("大于", ">"), gte("大于等于", ">="), lt("小于", "<"), lte("小于等于", "<="),
    like("模糊匹配", "like"), notLike("不匹配", "not like"),
    isNull("空", "is null"), isNotNull("非空", "is not null"),
    in("包含", "in"), notIn("不包含", "not in"), custom("自定义默认的", null);

    private final String info;
    private final String operator;

    SearchOperator(final String info, String operator) {
        this.info = info;
        this.operator = operator;
    }

    public String getInfo() {
        return info;
    }

    public String getOperator() {
        return operator;
    }

   //操作符是否允许为空
    public static boolean isAllowBlankValue(final SearchOperator operator) {
        return operator == SearchOperator.isNotNull || operator == SearchOperator.isNull;
    }

    //获取枚举
    public static SearchOperator valueBySymbol(String operator) throws SearchException {
    	operator = formatSymbol(operator);
        for (SearchOperator op : values()) {
            if (op.name().equals(operator)) {
                return op;
            }
        }
        throw new SearchException("不支持的查询操作符号: " + operator);
    }
    
    //格式化操作符
    private static String formatSymbol(String symbol) {
        if (StringUtil.isBlank(symbol)) {
            return symbol;
        }
        return symbol.trim().toLowerCase().replace("  ", " ");
    }
    
}
