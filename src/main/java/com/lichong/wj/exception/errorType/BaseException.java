package com.lichong.wj.exception.errorType;

import lombok.Getter;
import lombok.Setter;

/**
 * 添加异常基础异常信息，所有的异常都需要集成此类
 *
 * @author pujiang
 * @ClassName: BaseException
 * @Description: TODO
 * @date 2018年6月8日 下午10:15:36
 */
@Getter
@Setter
public class BaseException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    private String code;

    private String message;

    public BaseException(String code, String message) {
        super();
        this.code = code;
        this.message = message;
    }

    public BaseException(String message) {
        super(message);
        this.message = message;
    }

    public BaseException(String code, String message, Throwable throwable, boolean enableSuppression, boolean writableStackTrace) {
        super(message, throwable, enableSuppression, writableStackTrace);
        this.code = code;
        this.message = message;
    }

    public BaseException(String message, Throwable throwable) {
        super(message, throwable);
        this.message = message;
    }

    public BaseException(Throwable throwable) {
        super(throwable);
    }

}
