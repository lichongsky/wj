package com.lichong.wj.exception.errorType;

import com.lichong.wj.entity.response.BaseResponseCode;

/**
 * 如果在控制器 需要抛出异常 使用这个异常
 *
 * @author pujiang
 * @ClassName: ControllerException
 * @Description: TODO
 * @date 2018年6月8日 下午10:15:43
 */
public class ControllerException extends BaseException {

    /**
     *
     */
    private static final long serialVersionUID = 7093881376703451435L;

    public ControllerException() {
        super(BaseResponseCode.CONTROLLER_ERROR);
    }

    public ControllerException(String message) {
        super(message);
    }

    public ControllerException(String code, String message) {
        super(code, message);
    }

    public ControllerException(String message, Throwable throwable) {
        super(message, throwable);
    }

    public ControllerException(Throwable throwable) {
        super(throwable);
    }


}
