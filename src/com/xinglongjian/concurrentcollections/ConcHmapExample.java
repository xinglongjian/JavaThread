/*
 * Copyright (C) 2018 Baidu, Inc. All Rights Reserved.
 */
package com.xinglongjian.concurrentcollections;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

/**
 *
 * @author zhengweiliang
 * @date 2018/2/12.
 */
public class ConcHmapExample {

    // 使用Long 多线程操作会不一致
//    static Map<String, Long> orders = new ConcurrentHashMap<>();
    static Map<String, AtomicLong> orders = new ConcurrentHashMap<>();

    static void processOrders() {
        for (String city: orders.keySet()) {
            for (int i = 0;i<50;i++) {
//                Long oldOrder = orders.get(city);
//                orders.put(city, oldOrder+1);
                orders.get(city).getAndIncrement();
            }
        }

    }

    public static void main(String[] args) throws InterruptedException {
        orders.put("Bombay", new AtomicLong());
        orders.put("Beijing", new AtomicLong());
        orders.put("London", new AtomicLong());
        orders.put("New York", new AtomicLong());

        ExecutorService service = Executors.newFixedThreadPool(2);
        service.submit(ConcHmapExample::processOrders);
        service.submit(ConcHmapExample::processOrders);

        service.awaitTermination(1, TimeUnit.SECONDS);
        service.shutdown();

        System.out.println(orders);

    }
}
