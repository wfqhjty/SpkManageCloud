package cn.spk.data.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SingleThreadCase {
    public static void main(String[] args) {
        ExecutorService exec = Executors.newSingleThreadExecutor();
        String[] arr = new String[]{"aaa", "bbb", "ccc", "ddd", "eee"};
        for (int i = 0; i < arr.length; i++) {
            String str = arr[i];
            exec.execute(new ThreadTaskOne(str));
        }
        exec.shutdown();
    }
}
