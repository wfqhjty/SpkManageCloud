package cn.spk.data.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

public class Util {
    public static String httpExchangeGet(String url, RestTemplate restTemplate, HttpHeaders requestHeaders) {
        //headers
        //HttpHeaders requestHeaders = new HttpHeaders();
        //requestHeaders.add("source", "source");

        //HttpEntity
        HttpEntity<Object> requestEntity = new HttpEntity<>(requestHeaders);
        ResponseEntity<String> result = restTemplate.exchange(url, HttpMethod.GET, requestEntity, String.class);
        return result.getBody();
    }

    public static String httpExchangePost(String url, RestTemplate restTemplate, HttpHeaders httpHeaders, Map param) {
        //HttpHeaders httpHeaders = new HttpHeaders();
        //httpHeaders.add("source", "source");
        //Map<String, String> param = new HashMap<>();
        HttpEntity<Object> request = new HttpEntity<>(param, httpHeaders);
        return restTemplate.postForObject(url, request, String.class);
    }


}
