package com.mobo.xddemo.filter;

import org.apache.commons.lang.StringUtils;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 登录判断过滤器
 *
 * @author Mobo
 * @create 2020-11-16-14:45
 */
/**
 * 使用这个注解时无法配置过滤器的执行顺序
 * @WebFilter(urlPatterns = "/api/v1/pri/order/*", filterName = "loginFilter")
 **/
public class LoginFilter implements Filter {

    /**
     * 容器加载时调用
     * @param filterConfig
     * @throws ServletException
     */
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("init LoginFilter======");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("doFilter LoginFilter======");
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        String token = request.getHeader("token");
        if (StringUtils.isBlank(token)){
            token = request.getParameter("token");
        }

        if (StringUtils.isBlank(token)){
            return;
        } else {
            // TODO 判断token是否合法
            filterChain.doFilter(request, response);
        }
    }

    /**
     * 输出返回值
     * @param response
     * @param json
     */
    private void renderJson(HttpServletResponse response, String json) {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");

        try(PrintWriter writer = response.getWriter()) {
            writer.println(json);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 容器销毁时被调用
     */
    @Override
    public void destroy() {
        System.out.println("destroy LoginFilter======");
    }
}
