package com.hql.java.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.hql.java.entity.UserEntity;
import com.hql.java.runnable.OutputTask;
import com.hql.java.runnable.WriterQueue;
import com.hql.java.service.UserService;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;

@RestController
public class UserController {
    private static final Logger logger= LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService service;

    @Resource(name = "thread-pool-executor")
    private ThreadPoolExecutor executor;

    @Resource(name = "fixed-thread-pool")
    private ThreadPoolExecutor executorPool;

    @RequestMapping("/select")
    //@Async("thread-pool-executor")
    public void select() throws IOException {
        File file = new File("./");
        System.out.println(file.getCanonicalPath());
        long begin = System.currentTimeMillis();
        int total = service.size();
        int pageSize = 100;
        int pageNums = total % pageSize == 0 ? (total / pageSize) : (total / pageSize + 1);
        BlockingQueue queue = new ArrayBlockingQueue(1000);

        ThreadPoolExecutor executor = new ThreadPoolExecutor(10, 20, 2, TimeUnit.SECONDS, queue);
        ExecutorService executorPool = Executors.newFixedThreadPool(10);
        for (int i = 0; i < pageNums; i++) {
//            Runnable task = new UserRunnable(pageSize * i, pageSize, service);
//            executor.execute(task);
            int j = i;
            Runnable task = new Thread(() -> {
                List<UserEntity> userEntityList = service.select(pageSize * j, pageSize);
                userEntityList.forEach(user -> {
                    WriterQueue.getQueue().put(user.toString());
                });
            });
            executor.execute(task);
            OutputTask OutTask = new OutputTask("data.txt");
            executorPool.execute(OutTask);
        }
        while(executor.getActiveCount() > 0) {
            OutputTask OutTask = new OutputTask("data.txt");
            executorPool.execute(OutTask);
        }
        logger.info("优化SQL前数据导出耗时 : " + (System.currentTimeMillis() - begin));
        executor.shutdown();
        executorPool.shutdown();
    }

    @RequestMapping("/select2")
    public void select2() {
        ForkJoinPool pool = new ForkJoinPool();

    }

    @RequestMapping("/select3")
    public void select3() throws IOException {
        File file = new File("./");
        System.out.println(file.getCanonicalPath());
        long begin = System.currentTimeMillis();
        int total = service.size();
        int pageSize = 100;
        int pageNums = total % pageSize == 0 ? (total / pageSize) : (total / pageSize + 1);
        for (int i = 0; i < pageNums; i++) {
//            Runnable task = new UserRunnable(pageSize * i, pageSize, service);
//            executor.execute(task);
            int j = i;
            Runnable task = new Thread(() -> {
                List<UserEntity> userEntityList = service.select(pageSize * j, pageSize);
                userEntityList.forEach(user -> {
                    WriterQueue.getQueue().put(user.toString());
                });
            });
            executor.execute(task);
            OutputTask OutTask = new OutputTask("data.txt");
            executorPool.execute(OutTask);
        }
        while(executor.getActiveCount() > 0) {
            OutputTask OutTask = new OutputTask("data.txt");
            executorPool.execute(OutTask);
        }
        logger.info("select3-优化SQL前数据导出耗时 : " + (System.currentTimeMillis() - begin));
        executor.shutdown();
        executorPool.shutdown();
    }

    @RequestMapping("/insert")
    public String insert() {
        for (int i = 0; i < 1000; i++) {
            String name = getRandomString(12);
            String password = getRandomString(12);
            service.insert(name, password);
        }
        logger.info("finish insert");
        return "finish insert";
    }

    public String getRandomString(int length) {
        String str = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789$#";
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length; i++) {
            int number = random.nextInt(62);
            sb.append(str.charAt(number));
        }
        return sb.toString();
    }
}
