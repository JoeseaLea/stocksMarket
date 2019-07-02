package com.joesea.stocksmarket.scheduletask;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * <p>@author : Joesea Lea</p>
 * <p>@date : 2019/6/28</p>
 * <p>@description : </p>
 */
@Component
//@Configuration      //1.主要用于标记配置类，兼备Component的效果。
//@EnableScheduling   // 2.开启定时任务
public class SaticScheduleTask {
    @Autowired
    private Testservice testservice;
//    3.添加定时任务
//    @Scheduled(cron = "0/5 * * * * ?")
//    或直接指定时间间隔，例如：5秒
//    @Scheduled(fixedRate=5000)
    private void configureTasks() {
        testservice.printvalue();
        System.err.println("执行静态定时任务时间: " + LocalDateTime.now());
    }
}
