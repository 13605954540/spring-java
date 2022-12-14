package com.lp.last.framework.util;

import org.springframework.http.*;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

/**
 * @author LP
 * @date 2018/7/29
 */
public class HttpClientUtil {

    /**
     * 执行http post请求
     *
     * @param url http请求地址
     * @param params 请求参数
     * @return post请求返回的body信息
     */
    public static String post(String url, MultiValueMap<String, Object> params){
        RestTemplate client = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<MultiValueMap<String, Object>>(params, headers);
        ResponseEntity<String> response = client.exchange(url, HttpMethod.POST, requestEntity, String.class);
        return response.getBody();
    }

    /**
     * 执行http get请求
     *
     * @param url http请求地址
     * @param params 请求参数
     * @return get请求返回的body信息
     */
    public static String get(String url, MultiValueMap<String, Object> params){
        RestTemplate client = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<MultiValueMap<String, Object>>(params, headers);
        ResponseEntity<String> response = client.exchange(url, HttpMethod.GET, requestEntity, String.class);
        return response.getBody();
    }

    /**
     * 发送http请求(可配置contentType,post or get)
     *
     * @param url 请求地址
     * @param mediaType contentType
     * @param httpMethod post or get
     * @param params 请求参数
     * @return http请求返回的body信息
     */
    public static String request(String url, MediaType mediaType,HttpMethod httpMethod,MultiValueMap<String, Object> params){
        RestTemplate client = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(mediaType);
        HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<MultiValueMap<String, Object>>(params, headers);
        ResponseEntity<String> response = client.exchange(url, httpMethod, requestEntity, String.class);
        return response.getBody();
    }
}