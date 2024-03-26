package com.bkhb.EchoSphere.config;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * 跨域
 *
 * @author bkhb
 * @version 1.0
 * @date 2024/3/26 22:44
 */

@Component
public class CorsFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        // 将 ServletResponse 转换为 HttpServletResponse
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        // 设置允许跨域请求的源地址
        httpResponse.setHeader("Access-Control-Allow-Origin", "*");

        // 设置允许的请求方法
        httpResponse.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE");

        // 设置允许的请求头
        httpResponse.setHeader("Access-Control-Allow-Headers", "*");

        // 继续执行 Filter 链
        chain.doFilter(request, response);
    }
}
