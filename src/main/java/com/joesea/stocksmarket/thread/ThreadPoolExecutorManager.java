package com.joesea.stocksmarket.thread;

import com.joesea.stocksmarket.EnvConfig;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * <p>@author : Joesea Lea</p>
 * <p>@date : 2019/11/20</p>
 * <p>@description : </p>
 */
public class ThreadPoolExecutorManager {
    private static ExecutorService executorService = Executors.newFixedThreadPool(EnvConfig.MAX_QUEUE_TASK_SIZE);

    public static void execute(Runnable runnable) {
        while(getQueueTaskSize() >= EnvConfig.MAX_QUEUE_TASK_SIZE) {
            try {
                TimeUnit.MILLISECONDS.sleep(100);
            } catch (InterruptedException e) {}
        }

        executorService.execute(runnable);
    }

    public static int getQueueTaskSize() {
        return ((ThreadPoolExecutor)executorService).getQueue().size();
    }

    public static void createExecutorService() {
        executorService = Executors.newFixedThreadPool(EnvConfig.MAX_QUEUE_TASK_SIZE);
    }
}
