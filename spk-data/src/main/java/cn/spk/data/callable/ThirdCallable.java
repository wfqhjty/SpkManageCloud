package cn.spk.data.callable;

import org.springframework.stereotype.Component;

import java.util.concurrent.Callable;

@Component
public class ThirdCallable implements Callable<Object> {
    @Override
    public String call() throws Exception {
        Thread.sleep(5000);
        System.out.println("third");
        return "throdCallable is run";
    }
}
