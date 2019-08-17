package cn.spk.user.service.impl;

import cn.spk.user.service.IRedisService;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Repository("redisServiceImpl")
public class RedisServiceImpl implements IRedisService {

    @Resource
    private RedisTemplate<String, String> redisTemplate;


    @Override
    public String get(String key) {
        return redisTemplate.opsForValue().get(key);
    }

    @Override
    public void set(String key, String value) {
        redisTemplate.opsForValue().set(key, value);
    }

    @Override
    public void setExpire(String key, String value) {
        redisTemplate.opsForValue().set(key, value, 2, TimeUnit.HOURS);
    }

    @Override
    public Map<Object, Object> hget(String key) {
        return redisTemplate.opsForHash().entries(key);
    }

    @Override
    public void hset(String key, Map<String, String> map) {
        redisTemplate.opsForHash().putAll(key, map);
    }
}
