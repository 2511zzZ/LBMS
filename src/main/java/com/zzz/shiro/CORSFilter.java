package com.zzz.shiro;

import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

@Component
public class CORSFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletResponse res = (HttpServletResponse) response;
        res.addHeader("Access-Control-Allow-Credentials", "true");
        String originHeader=((HttpServletRequest) request).getHeader("Origin");
        // 局域网和本机前端均可访问
        Set<String> originSet = new HashSet<>();
        originSet.add("http://127.0.0.1:8000");
        originSet.add("http://192.168.43.206:8000");
        originSet.add("http://127.0.0.1:8080");
        if(originHeader != null && originSet.contains(originHeader)){
            res.addHeader("Access-Control-Allow-Origin", originHeader);
        }
        res.addHeader("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT");
        res.addHeader("Access-Control-Allow-Headers", "Content-Type,X-CAF-Authorization-Token,sessionToken,X-TOKEN,SessionId");
        if (((HttpServletRequest) request).getMethod().equals("OPTIONS")) {
            response.getWriter().println("ok");
            return;
        }
        chain.doFilter(request, response);
    }
    @Override
    public void destroy() {
    }
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }
}

