package cn.spk.data.callable;

import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Map;
import java.util.concurrent.Callable;

@Component
public class CallableFactory {

    @Resource
    private Map<String, Callable> callableMap;

    public Callable getCallable(String name) {
        return callableMap.get(name);
    }

}
