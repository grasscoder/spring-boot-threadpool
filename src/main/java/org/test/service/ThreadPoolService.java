package org.test.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Service;

import java.util.concurrent.*;

@Service
@EnableAsync
public class ThreadPoolService {

    private static final Logger logger= LoggerFactory.getLogger(ThreadPoolService.class);

    @Bean("thread-pool-executor")
    public ThreadPoolExecutor init(){
        BlockingQueue queue = new ArrayBlockingQueue(1000);
        ThreadPoolExecutor executor = new ThreadPoolExecutor(10,
                20, 2,
                TimeUnit.SECONDS,
                queue);
        ThreadFactory threadFactory = Executors.defaultThreadFactory();
        executor.setThreadFactory(threadFactory);
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.DiscardPolicy());
        //executor.getActiveCount();
        return executor;
    }

    @Bean("fixed-thread-pool")
    public ExecutorService init2(){
        return Executors.newFixedThreadPool(10);
    }

}
