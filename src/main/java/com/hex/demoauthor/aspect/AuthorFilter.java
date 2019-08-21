package com.hex.demoauthor.aspect;


import com.hex.demoauthor.enums.ResultEnum;
import com.hex.demoauthor.util.JsonUtil;
import com.hex.demoauthor.util.JwtUtil;
import com.hex.demoauthor.util.ResultUtil;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

/**
 * 操作身份过滤器
 * User: hexuan
 * Date: 2019/8/21
 * Time: 1:39 PM
 */
public class AuthorFilter implements Filter {

    private static final PathMatcher pathMatcher = new AntPathMatcher();

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void destroy() {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        try {
            /**
             * 如果请求路径不在不保护的集合中
             */
            if (!isUnProtectedUrl(request)) {
                String token = request.getHeader("Authorization");
                //检查jwt令牌, 如果令牌不合法或者过期, 里面会直接抛出异常, 下面的catch部分会直接返回
                JwtUtil.validateToken(token);
            }
        } catch (Exception e) {
            response.setCharacterEncoding("utf-8");
            response.setContentType("text/html;charset=utf-8");
            PrintWriter out = response.getWriter();
            out.write(JsonUtil.obj2String(ResultUtil.error(ResultEnum.OPERATOR_INFO_ERROR)));
            out.close();
//            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, e.getMessage());
            return;
        }
        //如果jwt令牌通过了检测, 那么就把request传递给后面的RESTful api
        filterChain.doFilter(request, response);
    }

    /**
     * 不进行jwt身份拦截的路径
     *
     * @param request
     * @return
     */
    private boolean isUnProtectedUrl(HttpServletRequest request) {
        List<String> unProtectedUrlList = new ArrayList<>();

        /**
         * 此处添加不进行身份拦截的路径
         */
        unProtectedUrlList.add("/login");
        unProtectedUrlList.add("/register");
        unProtectedUrlList.add("/page/*");

        for (String url : unProtectedUrlList) {
            if (pathMatcher.match(url, request.getServletPath())) {
                return true;
            }
        }

        return false;
    }
}
