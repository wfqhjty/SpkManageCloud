package cn.spk.data.callable;

import org.springframework.stereotype.Component;

import java.util.concurrent.Callable;

@Component
public class SecondCallable implements Callable<Object> {

    @Override
    public String call() throws Exception {
        System.out.println("second");
        /*Thread.sleep(10000);*/
        return "secondCallable is run";
    }
}
