package com.lichong.wj.exception.errorType;

import com.lichong.wj.entity.response.BaseResponseCode;

/**
 * TOKEN校验异常
 * 
 * @ClassName: TokenException
 * @Description: TODO
 * @author pujiang
 * @date 2018年6月20日 下午11:16:23
 *
 */
public class TokenException extends BaseException {

    /**
     * 
     */
    private static final long serialVersionUID = 3202124849738374214L;

    public TokenException() {
        super(BaseResponseCode.TOKEN_ERROR);
    }

    public TokenException(String message) {
        super(BaseResponseCode.TOKEN_ERROR, message);
    }

    public TokenException(String code, String message) {
        super(code, message);
    }

    public TokenException(String message, Throwable throwable) {
        super(message, throwable);
    }

    public TokenException(Throwable throwable) {
        super(throwable);
    }


}
