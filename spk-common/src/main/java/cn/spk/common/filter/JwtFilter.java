package cn.spk.common.filter;

import cn.spk.common.dict.Constant;
import cn.spk.common.util.CommonUtil;
import cn.spk.common.util.SpringContextUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.client.RestTemplate;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

public class JwtFilter implements Filter {

    private static Logger logger = LoggerFactory.getLogger(JwtFilter.class);

    @Autowired
    private RestTemplate restTemplate;

    /*
        不需要登录就可以访问的路径
     */
    @Value("${no.filter.urls}")
    private String no_filter_urls;

    /*
        验证登录token地址
     */
    @Value("${login.check.url}")
    private String login_check_url;


    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Headers", "Content-Type,Content-Length,Authorization,Accept,X-Requested-With");
        response.setHeader("Access-Control-Allow-Methods", "PUT,POST,GET,DELETE,OPTIONS");
        response.setHeader("Access-Control-MAX-Age", "3600");
        String url = request.getRequestURI();
        String applicationName = SpringContextUtils.getApplicationContext().getApplicationName();
        System.out.println("applicationName:" + applicationName);
        //每个请求都检测header
        String token = request.getHeader(Constant.AUTHORIZATION);
        String source = request.getHeader(Constant.SOURCE);
        //是否需要过滤
        boolean noNeedFilter = isNoNeedFilter(url);
        if (!noNeedFilter) {
            filterChain.doFilter(request, response);
        } else if (!CommonUtil.isNullOrEmpty(source) && Constant.SOURCE.equals(source)){
            filterChain.doFilter(request, response);
        }

        if(!this.checkToken(token, request)){
            logger.error("当前用户未登录");
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "当前用户未登录");
            return;
        }
        filterChain.doFilter(request, response);
    }

    /**
     * 不需要登录验证的地址
     *
     * @param url
     * @return
     */
    private boolean isNoNeedFilter(String url) {
        String[] noFilter = no_filter_urls.split(";");
        if (!CommonUtil.isNullOrEmpty(noFilter)) {
            for (String str : noFilter) {
                if (url.contains(str)) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 验证登录
     *
     * @param token
     * @return
     */
    public boolean checkToken(String token, HttpServletRequest request) {
        Map response = restTemplate.postForObject(login_check_url, token, Map.class);
        if (!CommonUtil.isNullOrEmpty(response)) {
            request.setAttribute(Constant.CURRENT_USER, request);
            return true;
        }
        return false;
    }

    @Override
    public void destroy() {

    }

}

