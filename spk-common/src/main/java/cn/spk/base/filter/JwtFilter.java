package cn.spk.base.filter;


import cn.spk.common.dict.Constant;
import cn.spk.common.util.CommonUtil;
import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.client.RestTemplate;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

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
        String url = request.getRequestURI();
        //每个请求都检测header
        String token = request.getHeader(Constant.AUTHORIZATION);
        String source = request.getHeader(Constant.SOURCE);
        //是否需要过滤
        boolean noNeedFilter = isNoNeedFilter(url);
        filterChain.doFilter(servletRequest, servletResponse);
        if (!noNeedFilter && !Constant.SOURCE.equals(source)) {
            if (!this.checkToken(token, request)) {
                logger.error("当前用户未登录");
                response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "当前用户未登录");
                return;
            } else filterChain.doFilter(request, response);
        }
        filterChain.doFilter(request, response);
    }

    /**
     * @param url
     * @return
     */
    private boolean isNoNeedFilter(String url) {
        String[] noFilter = no_filter_urls.split(",");
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
        String response = restTemplate.postForObject(login_check_url, token, String.class);
        JSONObject dataMap = JSONObject.fromObject(response);
        if (dataMap != null) return true;
        return false;
    }

    @Override
    public void destroy() {

    }

}

