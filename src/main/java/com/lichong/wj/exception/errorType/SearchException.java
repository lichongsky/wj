package com.lichong.wj.exception.errorType;

import com.lichong.wj.entity.response.BaseResponseCode;

/**
 * 绑定参数时 报错
* @ClassName: SearchException 
* @Description: TODO
* @author pujiang
* @date 2018年6月8日 下午10:15:58 
*
 */
public class SearchException extends BaseException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 154877165008205121L;

	public SearchException() {
		super(BaseResponseCode.PARAMS_ERROR);
	}

	public SearchException(String message) {
		super(message);
	}

	public SearchException(String code,String message) {
		super(code,message);
	}

	public SearchException(String message, Throwable throwable) {
		super(message, throwable);
	}

	public SearchException(Throwable throwable) {
		super(throwable);
	}

	
}
