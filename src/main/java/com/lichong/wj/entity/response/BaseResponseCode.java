package com.lichong.wj.entity.response;

/**
 *  http状态码
 */
public interface BaseResponseCode {
    /**
     * 成功返回给前端
     */
    String API_SUCCESS = "0";

    /**
     * 失败返回给前端
     */
    String API_FAIL = "-1";

    /**
     * 未知异常
     */
    String WZ_ERROR = "9999";

    /**
     * 控制器异常
     */
    String CONTROLLER_ERROR = "1001";

    /**
     * 服务异常
     */
    String SERVICE_ERROR = "1002";

    /**
     * 工具类异常
     */
    String UTILS_ERROR = "1003";

    /**
     * 参数异常
     */
    String PARAMS_ERROR = "1004";

    /**
     * 登陆异常
     */
    String LOGIN_ERROR = "1005";

    /**
     * 令牌异常
     */
    String TOKEN_ERROR = "1006";

    /**
     * 请勿重复提交
     */
    String REPEAT_SUBMIT = "1007";
    
    /**
     * feign调用熔断异常
     */
    String FEIGN_FALLBACK = "1008";
    
}
