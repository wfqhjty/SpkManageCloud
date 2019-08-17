package cn.spk.user.service;

import java.util.Map;

public interface IRedisService {
    String get(String key);

    void set(String key, String value);

    void setExpire(String key, String value);

    Map<Object, Object> hget(String key);

    void hset(String key, Map<String, String> map);
}
