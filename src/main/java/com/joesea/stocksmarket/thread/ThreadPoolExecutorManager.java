package com.joesea.stocksmarket.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * <p>@author : Joesea Lea</p>
 * <p>@date : 2019/11/20</p>
 * <p>@description : </p>
 */
public class ThreadPoolExecutorManager {
    private static ExecutorService executorService = Executors.newCachedThreadPool();

    public static void execute(Runnable runnable) {
        executorService.execute(runnable);
    }
}
