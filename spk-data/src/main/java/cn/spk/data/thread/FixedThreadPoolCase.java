package cn.spk.data.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class FixedThreadPoolCase {
    public static void main(String[] args) {
        ExecutorService exec = Executors.newFixedThreadPool(3);
//        String[] arr = new String[]{"aaa", "bbb", "ccc", "ddd", "eee","fff","ggg","hhh"};
//        for (int i = 0; i < arr.length; i++) {
//            String str = arr[i];
//            exec.execute(new ThreadTask(str));
//        }
        exec.execute(new ThreadTaskOne("a"));
        exec.execute(new ThreadTaskTwo("b"));
        exec.execute(new ThreadTaskThree("c"));
        exec.shutdown();
    }
}
