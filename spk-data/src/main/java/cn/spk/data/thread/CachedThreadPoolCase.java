package cn.spk.data.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CachedThreadPoolCase {
    public static void main(String[] args) throws InterruptedException {
        ExecutorService exec = Executors.newCachedThreadPool();
        String[] arr = new String[]{"aaa", "bbb", "ccc", "ddd", "eee"};
        for (int i = 0; i < arr.length; i++) {
            String str = arr[i];
            System.out.println("开始" + str);
            exec.execute(new ThreadTaskOne(str));
            Thread.sleep(30000);
            System.out.println("结束" + str);
        }
        exec.shutdown();
    }
}
