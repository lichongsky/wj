package com.lichong.wj.exception;

import com.lichong.wj.entity.response.AjaxResponse;
import com.lichong.wj.entity.response.BaseResponseCode;
import com.lichong.wj.exception.errorType.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.ui.Model;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;


/**
 * 异常处理类
* @ClassName: MyExceptionHandler 
* @Description: TODO
* @author pujiang
* @date 2018年6月8日 下午9:43:47 
*
 */
@RestControllerAdvice
public class MyExceptionHandler extends BaseExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(MyExceptionHandler.class);

	/**
     * 工具类 异常处理 
     */
    @ExceptionHandler(value = UtilsException.class)
    public AjaxResponse utilsExceptionHandler(UtilsException ex) {
        return new AjaxResponse(getCode(ex),getMessage(ex));
    }
	
	/**
     * controller层异常处理
     */
    @ExceptionHandler(value = ControllerException.class)
    public AjaxResponse controllerExceptionHandler(ControllerException ex) {
        return new AjaxResponse(getCode(ex),getMessage(ex));
    }
	
	/**
     * service层异常处理
     */
    @ExceptionHandler(value = ServiceException.class)
    public AjaxResponse serviceExceptionHandler(ServiceException ex) {
        return new AjaxResponse(getCode(ex),getMessage(ex));
    }
    
    /**
     * 登录验证拦截抛出的异常处理
     */
    @ExceptionHandler(value = LoginException.class)
    public AjaxResponse loginExceptionHandler(LoginException ex) {
        return new AjaxResponse(getCode(ex), getMessage(ex));
    }
    
    /**
     * TOKEN校验类异常
     */
    @ExceptionHandler(value = TokenException.class)
    public AjaxResponse tokenExceptionHandler(TokenException ex) {
        return new AjaxResponse(getCode(ex), getMessage(ex));
    }
	
	/**
     * 捕捉我们自己抛出的异常处理
     */
    @ExceptionHandler(value = BaseException.class)
    public AjaxResponse baseExceptionHandler(BaseException ex) {
        return new AjaxResponse(getCode(ex),getMessage(ex));
    }
	
	/**
     * 全局未知的异常捕捉处理
     */
    @ExceptionHandler(value = Exception.class)
    public AjaxResponse exceptionHandler(Exception ex) {
    	logger.error("全局异常：{}", ex);
        return new AjaxResponse(BaseResponseCode.WZ_ERROR,"系统异常");
    }
    
	 /**
     * 应用到所有@RequestMapping注解方法，在其执行之前初始化数据绑定器
     */
    @InitBinder
    public void initBinder(WebDataBinder binder) {
    	
    }

    /**
     * 把值绑定到Model中，使全局@RequestMapping可以获取到该值
     */
    @ModelAttribute
    public void addAttributes(Model model) {
        //model.addAttribute("author", "MagicalSam");
    }
    
    /**
     * 缺少参数
     * @param e 异常
     * @return
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    public AjaxResponse handleMethodArgumentNotValid(MethodArgumentNotValidException e) {
        return new AjaxResponse(BaseResponseCode.PARAMS_ERROR,e.getBindingResult().getFieldError().getDefaultMessage());
    }
    
    /**
     * 不支持此请求方式
     */
    @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
    @ExceptionHandler(value = HttpRequestMethodNotSupportedException.class)
    public AjaxResponse methodNotSupported(){
        return new AjaxResponse(HttpStatus.METHOD_NOT_ALLOWED.value()+"", HttpStatus.METHOD_NOT_ALLOWED.name());
    }
    
    /**
     * 缺少方法体
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public AjaxResponse messageNotReadable() {
        logger.warn("缺少POST方法体");
        return new AjaxResponse(HttpStatus.BAD_REQUEST.value()+"", HttpStatus.BAD_REQUEST.name());
        
    }
}
