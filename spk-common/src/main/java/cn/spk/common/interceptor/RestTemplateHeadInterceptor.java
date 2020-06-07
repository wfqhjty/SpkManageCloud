package cn.spk.common.interceptor;

import cn.spk.common.dict.Constant;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;

import java.io.IOException;

public class RestTemplateHeadInterceptor implements ClientHttpRequestInterceptor {

    @Override
    public ClientHttpResponse intercept(HttpRequest httpRequest, byte[] bytes, ClientHttpRequestExecution clientHttpRequestExecution) throws IOException {
        HttpHeaders httpHeaders = httpRequest.getHeaders();
        httpHeaders.add(Constant.SOURCE, Constant.SOURCE);
        return clientHttpRequestExecution.execute(httpRequest, bytes);
    }
}
