package cn.spk.data.filter;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Value;

import javax.servlet.*;
import javax.servlet.FilterConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class JwtFilter implements Filter {

    @Value("${server.servlet.context-path}")
    private String contextPath;

    //不需要登录就可以访问的路径(比如:注册登录等)
    private List<Pattern> list = new ArrayList<>();

    @Override
    public void init(FilterConfig filterConfig) {
        Pattern patternContext = Pattern.compile(contextPath + "/*.*");
        list.add(patternContext);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "POST, GET, PUT, OPTIONS, DELETE");
        response.setHeader("Access-Control-Max-Age", "3600");
        response.setHeader("Access-Control-Allow-Headers", "x-requested-with, Content-Type");
        if (request.getMethod().equalsIgnoreCase("OPTIONS")) {
            response.setStatus(Response.SC_OK);
        }
        //是否需要过滤
        filterChain.doFilter(servletRequest, servletResponse);
    }

    /**
     * @param url
     * @return
     */
    private boolean isNoNeedFilter(String url) {
        for (Pattern p : list) {
            Matcher matcher = p.matcher(url);
            if (matcher.matches()) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void destroy() {

    }

}

