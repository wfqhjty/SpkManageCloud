package cn.spk.data.callable;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class ExecutorTest {

    private static ExecutorService pool;
    static{
        pool = Executors.newFixedThreadPool(2);
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        /**
         * 调用后打印
         * second
         * third
         * first
         * secondCallable is run
         * firstCallable is run
         * throdCallable is run
         * 线程按照抢占CPU的先后顺序进行执行
         * 但返回list输出是还是按照进入的先后顺序打印返回值
         */
        List<Callable> callableList = new ArrayList<>();
        SecondCallable secondCallable = new SecondCallable();
        callableList.add(secondCallable);
        FirstCallable firstCallable = new FirstCallable();
        callableList.add(firstCallable);
        ThrodCallable throdCallable = new ThrodCallable();
        callableList.add(throdCallable);
        List<String> lists = runCallable(callableList);
        for (String list : lists){
            System.out.println(list.toString());
        }


    }
    public static List<String> runCallable(List<Callable> callableList) throws ExecutionException, InterruptedException {
        List<Future> futureList =new ArrayList<>();
        List<String> lists =new ArrayList<>();
        for(Callable callable : callableList){
            Future future = pool.submit(callable);
            futureList.add(future);
        }

        for(Future future : futureList){
            String message = (String)future.get();
            lists.add(message);
        }
        return lists;
    }
}
