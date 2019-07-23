package com.lichong.wj.util;

import okhttp3.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.ConnectException;
import java.util.concurrent.TimeUnit;

public class OkHttpUtil {
    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
    private static final Logger logger = LoggerFactory.getLogger(OkHttpUtil.class);
    /**
     * 连接超时时间,单位秒
     */
    private static long CONNECT_TIMEOUT = 10;
    /**
     * 读取超时时间，单位秒
     */
    private static long READ_TIMEOUT = 10;

    /**
     * post 表格请求
     *
     * @param url
     * @param formBody
     * @return
     * @throws IOException
     */
    public static String post(String url, FormBody formBody) {
        Request request = new Request.Builder().url(url).post(formBody).build();
        Response response = execute(request);
        String result = handleResponse(response);
        logger.info("Post 请求地址：{}， 请求参数：{}，返回结果: {}", url, formBody.toString(), result);
        return result;
    }

    /**
     * GET请求
     *
     * @param url
     * @return
     * @throws IOException
     */
    public static String get(String url) {
        Request request = new Request.Builder().url(url).build();
        Response response = execute(request);
        String result = handleResponse(response);
        logger.info("Get 请求地址: {}, 返回结果: {}", url, result);
        return result;
    }

    /**
     * 处理请求返回，转换返回体为字符串
     * @param response
     * @return
     */
    private static String handleResponse(Response response){
        String result = null;
        if (response != null && response.code() == 200) {
            try {
                result = response.body().string();
            }catch (IOException e){
                logger.error("请求返回字符串转换失败!",e);
            }
        }
        return result;
    }

    /**
     * 添加读取时间和超时时间
     *
     * @param request
     * @return
     * @throws IOException
     */
    private static Response execute(Request request) {
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS)
                .readTimeout(READ_TIMEOUT, TimeUnit.SECONDS).build();
        Response response = null;
        try {
            response = okHttpClient.newCall(request).execute();
        } catch (ConnectException e) {
            logger.error("请求链接被拒绝，url:"+request.url());
        } catch (Exception e) {
            logger.error("请求链接失败，请联系管理员!", e);
        }
        return response;
    }
}
