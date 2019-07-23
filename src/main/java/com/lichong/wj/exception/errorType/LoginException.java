package com.lichong.wj.exception.errorType;


import com.lichong.wj.entity.response.BaseResponseCode;

/**
 * 登录检测
 *
 * @author pujiang
 * @ClassName: LoginException
 * @Description: TODO
 * @date 2018年6月8日 下午10:15:49
 */
public class LoginException extends BaseException {

    /**
     *
     */
    private static final long serialVersionUID = -7061156901243015720L;

    public LoginException() {
        super(BaseResponseCode.LOGIN_ERROR);
    }

    public LoginException(String message) {
        super(message);
    }

    public LoginException(String code, String message) {
        super(code, message);
    }

    public LoginException(String message, Throwable throwable) {
        super(message, throwable);
    }

    public LoginException(Throwable throwable) {
        super(throwable);
    }


}
