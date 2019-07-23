package com.lichong.wj.entity.response;

import lombok.Data;

import java.io.Serializable;

/**
 * 前端请求返回数据包装对象
 *
 * @author ：lichongsky
 * @date ：Created in 2019/7/23 14:36
 */

@Data
public class AjaxResponse implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     * 编码
     */
    String code;

    /**
     * 数据
     */
    Object data;

    /**
     * 提示信息
     */
    String message;

    public AjaxResponse() {
    }

    public AjaxResponse(String code) {
        this.code = code;
    }

    public AjaxResponse(Object data) {
        //默认成功
        this.code = BaseResponseCode.API_SUCCESS;
        this.data = data;
    }

    public AjaxResponse(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public AjaxResponse(String code, Object data) {
        this.code = code;
        this.data = data;
    }

    public AjaxResponse(Object data, String message) {
        //默认成功
        this.code = BaseResponseCode.API_SUCCESS;
        this.data = data;
        this.message = message;
    }

    public AjaxResponse(String code, Object data, String message) {
        this.code = code;
        this.data = data;
        this.message = message;
    }

}
