package com.lichong.wj.exception;

import com.lichong.wj.exception.errorType.BaseException;

/**
 * 异常处理基础类
 *
 * @author pujiang
 * @ClassName: BaseExceptionHandler
 * @Description: TODO
 * @date 2018年6月8日 下午9:39:57
 */
public class BaseExceptionHandler {

    protected String getMessage(BaseException ex) {
        ex.printStackTrace();
        String message = ex.getMessage();
        return message;
    }

    protected String getCode(BaseException ex) {
        ex.printStackTrace();
        String code = ex.getCode();
        return code;
    }
}
