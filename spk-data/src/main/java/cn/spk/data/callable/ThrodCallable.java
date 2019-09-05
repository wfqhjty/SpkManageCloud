package cn.spk.data.callable;

import java.util.concurrent.Callable;

public class ThrodCallable implements Callable<Object> {
    @Override
    public String call() throws Exception {
        Thread.sleep(5000);
        System.out.println("third");
        return "throdCallable is run";
    }
}
