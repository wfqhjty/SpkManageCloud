package cn.spk.data.serivce.impl;

import cn.spk.data.callable.CallableFactory;
import cn.spk.data.serivce.ICallableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

@Service
public class CallableServiceImpl implements ICallableService {

    private static ExecutorService pool;

    static {
        pool = Executors.newFixedThreadPool(2);
    }

    @Autowired
    private CallableFactory callableFactory;

    @Override
    public List<String> getCallableList() throws ExecutionException, InterruptedException {

        List<Callable> callableList = new ArrayList<>();
        Callable firstCallable = callableFactory.getCallable("firstCallable");
        callableList.add(firstCallable);
        Callable secondCallable = callableFactory.getCallable("secondCallable");
        callableList.add(secondCallable);
        Callable thirdCallable = callableFactory.getCallable("thirdCallable");
        callableList.add(thirdCallable);
        List<Future> futureList = new ArrayList<>();
        List<String> lists = new ArrayList<>();
        for (Callable callable : callableList) {
            Future future = pool.submit(callable);
            futureList.add(future);
        }
        for (Future future : futureList) {
            String message = (String) future.get();
            lists.add(message);
        }
        return lists;
    }
}
