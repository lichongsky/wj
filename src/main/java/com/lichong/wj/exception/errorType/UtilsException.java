package com.lichong.wj.exception.errorType;

import com.lichong.wj.entity.response.BaseResponseCode;

/**
 * 工具类 需要抛出异常 使用这个异常
* @ClassName: UtilsException 
* @Description: TODO
* @author pujiang
* @date 2018年6月8日 下午10:16:13 
*
 */
public class UtilsException extends BaseException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 679840367662207578L;

	public UtilsException() {
		super(BaseResponseCode.UTILS_ERROR);
	}
	
	public UtilsException(String message) {
		super(message);
	}

	public UtilsException(String code,String message) {
		super(code,message);
	}

	public UtilsException(String message, Throwable throwable) {
		super(message, throwable);
	}

	public UtilsException(Throwable throwable) {
		super(throwable);
	}

	
}
