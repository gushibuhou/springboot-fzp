package com.jyp.springboot.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.util.*;

/**
 * @title: MutableHttpServletRequest类
 * @Author jiangyp
 * @Date: 2021/8/24 9:48
 * @Version 1.0
 */
public class MutableHttpServletRequest extends HttpServletRequestWrapper {

    private final Map<String, String> customHeaders;

    public MutableHttpServletRequest(HttpServletRequest request) {

        super(request);

        this.customHeaders = new HashMap<>();

    }

    /**

     * 往header中添加参数

     *

     * @param key  键

     * @param value 值

     */

    public void putHeader(String key, String value) {

        this.customHeaders.put(key, value);

    }

    @Override

    public String getHeader(String key) {

        String headerValue = customHeaders.get(key);

        if (headerValue != null) {

            return headerValue;

        }

        return ((HttpServletRequest) getRequest()).getHeader(key);

    }

    @Override

    public Enumeration<String> getHeaderNames() {

        Set<String> set = new HashSet<>(customHeaders.keySet());

        @SuppressWarnings("unchecked")

        Enumeration<String> e = ((HttpServletRequest) getRequest()).getHeaderNames();

        while (e.hasMoreElements()) {

            String n = e.nextElement();

            set.add(n);

        }

        return Collections.enumeration(set);

    }

}