package cn.spk.data.util;

import com.alibaba.fastjson.JSON;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class Util {
    public static String httpExchangeGet(String url, RestTemplate restTemplate) {
        //headers
        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.add("source", "source");

        //HttpEntity
        HttpEntity<Object> requestEntity = new HttpEntity<>(requestHeaders);
        ResponseEntity<String> result = restTemplate.exchange(url, HttpMethod.GET, requestEntity, String.class);
        return result.getBody();
    }


}
