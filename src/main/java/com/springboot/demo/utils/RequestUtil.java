package com.springboot.demo.utils;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

public class RequestUtil {
    public static String getIpAddr(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }

    public static String getParamString(HttpServletRequest request) {
        Map<String, String[]> param = request.getParameterMap();
        StringBuilder paramSB = new StringBuilder();
        String paramString = null;
        try {
            if (param != null && param.size() != 0) {
                param.forEach((key, value) -> {
                    String[] val = new String[1];
                    String v = "";
                    for (int i = 0; i < val.length; i++) {
                        v += val[i];
                    }
                    paramSB.append(" (key:" + key + ",param:" + v + ") ");
                });
                paramString = paramSB.toString();
            }
        } catch (Exception e2) {
            paramString = null;
        }
        return paramString;
    }

    /**
     * 发送post请求
     *
     * @param url    请求url
     * @param header 请求头
     * @param body   请求内容
     */
    public static String doPost(String url, Map<String, String> header, String body) {
        String result = "";
        BufferedReader in = null;
        PrintWriter out = null;
        try {
            // 设置 url
            URL realUrl = new URL(url);
            URLConnection connection = realUrl.openConnection();
            // 设置 header
            for (String key : header.keySet()) {
                connection.setRequestProperty(key, header.get(key));
            }
            // 设置请求 body
            connection.setDoOutput(true);
            connection.setDoInput(true);
            out = new PrintWriter(connection.getOutputStream());
            // 保存body
            out.print(body);
            // 发送body
            out.flush();
            // 获取响应body
            in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            return null;
        }
        return result;
    }

    /**
     * 发送get请求
     *
     * @param url    请求url
     * @param header 请求头
     */
    public static String doGet(String url, Map<String, String> header) {
        StringBuilder result = new StringBuilder();
        BufferedReader in = null;
        try {
            // 设置 url
            URL realUrl = new URL(url);
            URLConnection connection = realUrl.openConnection();
            // 设置 header
            for (String key : header.keySet()) {
                connection.setRequestProperty(key, header.get(key));
            }
            // 设置请求 body
            in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result.append(line);
            }
        } catch (Exception e) {
            return null;
        }
        return result.toString();
    }

    public static String getRequestURL(Object object) {
        if (object != null) {
            StringBuilder result = new StringBuilder("");
            try {
                HashMap<String, Object> map = ObjectUtil.beanToMapExcludeNull(object);
                for (Map.Entry<String, Object> item : map.entrySet()) {
                    result.append(item.getKey());
                    result.append("=");
                    result.append(URLEncoder.encode(item.getValue().toString(), "utf-8"));
                    result.append("&");
                }
                result.deleteCharAt(result.lastIndexOf("&"));
                return result.toString();
            } catch (Exception e) {
                return null;
            }
        }
        return null;
    }
}
