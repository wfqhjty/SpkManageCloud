package cn.spk.data.thread;

import java.io.FileWriter;
import java.io.IOException;

public class ThreadTaskTwo implements Runnable {

    private String str;

    public ThreadTaskTwo() {

    }

    public ThreadTaskTwo(String str) {
        this.str = str;
    }

    @Override
    public void run() {

        FileWriter writer;
        try {
            writer = new FileWriter("E:/test.txt");
            while (true) {
                writer.write(str);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
