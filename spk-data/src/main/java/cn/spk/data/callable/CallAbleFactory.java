package cn.spk.data.callable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.Callable;

@Component
public class CallAbleFactory {

    @Autowired
    private Map<String, Callable> callableMap;

    public Callable getCallable(String name) {
        return callableMap.get(name);
    }

}
