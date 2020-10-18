package com.baizhi.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

/**
 * 解决Ajax的跨域问题
 * 为响应设置响应头
 * Access-Control-Allow-Origin ---> 设置访问白名单
 * urlPatterns 设置过滤范围
 * filterName 相当于过滤器的别名
 */
@WebFilter(urlPatterns = "/*",filterName = "corsFilter")
public class CorsFilter implements Filter {

    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        HttpServletResponse response = (HttpServletResponse) res;
        //获取前台传过来的的域名,   参数：Origin：是前台传递域名的key
        String originHeader=((HttpServletRequest) req).getHeader("Origin");
        ArrayList<String> domainList = new ArrayList<>();
        domainList.add("http://localhost:8989"); //添加允许访问的域名
        //判断该域名是否在白名单中
        if(domainList.contains(originHeader)){
            // 设置允许originHeader服务地址的请求访问
            response.setHeader("Access-Control-Allow-Origin", originHeader);
            // 设置允许什么样的请求方式
            response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
            // 设置允许使用cookie进行身份验证 或其他验证参数
            response.setHeader("Access-Control-Allow-Credentials", "true");
        }

        chain.doFilter(req, res);
    }
    public void init(FilterConfig filterConfig) {}
    public void destroy() {}
}