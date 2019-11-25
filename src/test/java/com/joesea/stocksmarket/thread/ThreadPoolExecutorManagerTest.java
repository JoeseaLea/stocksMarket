package com.joesea.stocksmarket.thread;

import org.junit.Test;

import java.util.concurrent.TimeUnit;

import static org.junit.Assert.*;

public class ThreadPoolExecutorManagerTest {

    @Test
    public void execute() {
        for (int i = 0 ; i < 10000; i ++) {
            final int temp = i;
            ThreadPoolExecutorManager.execute(new Runnable() {
                @Override
                public void run() {
//                    System.out.println(temp);
                    try {
                        TimeUnit.SECONDS.sleep(2);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
        }

        while (true) {
            System.out.println(ThreadPoolExecutorManager.getQueueTaskSize() + "-------");
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}