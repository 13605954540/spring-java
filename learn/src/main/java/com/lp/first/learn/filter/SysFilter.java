package com.lp.first.learn.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

/**
 * @author LP
 * @date 2018/11/2
 */
@WebFilter
public class SysFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
//        if(SecurityContextHolder.getContext() != null && StringUtil.isEmpty(RpcContext.getContext().getAttachment("current_user_id"))) {
//            RpcContext.getContext().setAttachment("current_user_id", SpringUtil.getUserDetail().getUserId());
//        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }
}
