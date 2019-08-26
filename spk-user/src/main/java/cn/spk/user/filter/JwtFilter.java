package cn.spk.user.filter;

import cn.spk.user.service.IRedisService;
import cn.spk.user.util.JwtUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;

import javax.annotation.Resource;
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

    private static Logger logger = LoggerFactory.getLogger(JwtFilter.class);
    //不需要登录就可以访问的路径(比如:注册登录等)
    private List<Pattern> list = new ArrayList<>();

    @Resource
    private IRedisService iRedisService;

    @Override
    public void init(FilterConfig filterConfig) {
        Pattern patternContext = Pattern.compile(contextPath + "/");
        list.add(patternContext);
        Pattern patternIndex = Pattern.compile(contextPath + "/index.*");
        list.add(patternIndex);
        Pattern patternLogin = Pattern.compile(contextPath + "/login.*");
        list.add(patternLogin);
        Pattern patternRegister = Pattern.compile(contextPath + "/register.*");
        list.add(patternRegister);
        Pattern favicon = Pattern.compile(contextPath + "/favicon.ico");
        list.add(favicon);
        Pattern patternBasic = Pattern.compile(contextPath + "/basic/.*");
        list.add(patternBasic);
        Pattern patternJs = Pattern.compile(contextPath + "/js/.*");
        list.add(patternJs);
        Pattern patternActuator = Pattern.compile(contextPath + "/actuator/*.*");
        list.add(patternActuator);
        Pattern patternUserLogin = Pattern.compile(contextPath + "/loginController/userLogin");
        list.add(patternUserLogin);
        Pattern patternUserRegister = Pattern.compile(contextPath + "/loginController/userRegister");
        list.add(patternUserRegister);

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        String url = request.getRequestURI();
        //每个请求都检测header
        String authorization = request.getHeader("Authorization");
        String source = request.getHeader("source");
        //是否需要过滤
        boolean noNeedFilter = isNoNeedFilter(url);
        if (noNeedFilter) { //不需要过滤直接传给下一个过滤器
            filterChain.doFilter(servletRequest, servletResponse);
        } else if ("source".equals(source))
            filterChain.doFilter(servletRequest, servletResponse);
        else { //需要过滤器
            if (StringUtils.isBlank(authorization)) {
                response.sendRedirect("login");
                //response.sendError(403, "Forbidden");
                return;
            }
            String uid = JwtUtil.getUid(authorization);
            if (StringUtils.isBlank(uid)) {
                response.sendRedirect("login");
                //response.sendError(403, "Forbidden");
                return;
            }
            String result = iRedisService.get(uid);
            if (StringUtils.isBlank(result)) {
                response.sendRedirect("login");
                //response.sendError(403, "Forbidden");
                return;
            } else if (!authorization.equals(result)) {
                response.sendRedirect("login");
                //response.sendError(403, "Forbidden");
                return;
            }
            iRedisService.setExpire(uid, authorization);
            filterChain.doFilter(servletRequest, servletResponse);
        }
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

