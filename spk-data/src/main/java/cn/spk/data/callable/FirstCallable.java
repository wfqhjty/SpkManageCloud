package cn.spk.data.callable;

import java.util.concurrent.Callable;

public class FirstCallable implements Callable<Object> {

    @Override
    public String call() throws Exception {
        Thread.sleep(10000);
        System.out.println("first");
        return "firstCallable is run";
    }
}
