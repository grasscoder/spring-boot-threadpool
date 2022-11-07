package org.test.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Service;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

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
        ThreadFactory threadFactory = new CurrentThreadFactory();
        executor.setThreadFactory(threadFactory);
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.DiscardPolicy());
        //executor.getActiveCount();
        return executor;
    }

    @Bean("fixed-thread-pool")
    public ExecutorService init2(){
        return Executors.newFixedThreadPool(10);
    }

    public static class CurrentThreadFactory implements  ThreadFactory{

        private ThreadGroup group;
        private final AtomicInteger threadNumber = new AtomicInteger(1);
        private static final AtomicInteger poolNumber = new AtomicInteger(1);
        private final String namePrefix;
        CurrentThreadFactory() {
            SecurityManager s = System.getSecurityManager();
            group = (s != null) ? s.getThreadGroup() :
                    Thread.currentThread().getThreadGroup();
            namePrefix = "self-pool-" +
                    poolNumber.getAndIncrement() +
                    "-thread-";
        }

        @Override
        public Thread newThread(Runnable r) {
            Thread t = new Thread(group, r,
                    namePrefix + threadNumber.getAndIncrement(),
                    0);
            if (t.isDaemon())
                t.setDaemon(false);
            if (t.getPriority() != Thread.NORM_PRIORITY)
                t.setPriority(Thread.NORM_PRIORITY);
            return t;
        }
    }

}
