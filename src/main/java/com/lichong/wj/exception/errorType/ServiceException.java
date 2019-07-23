package com.lichong.wj.exception.errorType;

import com.lichong.wj.entity.response.BaseResponseCode;

/**
 * 如果在service层 需要抛出异常 使用这个异常
* @ClassName: ServiceException 
* @Description: TODO
* @author pujiang
* @date 2018年6月8日 下午10:16:05 
*
 */
public class ServiceException extends BaseException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1760277946273198000L;

	public ServiceException() {
		super(BaseResponseCode.SERVICE_ERROR);
	}

	public ServiceException(String message) {
		super(message);
	}
	
	public ServiceException(String code,String message) {
		super(code,message);
	}

	public ServiceException(String message, Throwable throwable) {
		super(message, throwable);
	}

	public ServiceException(Throwable throwable) {
		super(throwable);
	}
	
}
