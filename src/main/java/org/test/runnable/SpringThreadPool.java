package org.test.runnable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;
@EnableAsync
@Component
public class SpringThreadPool {
    private static final Logger logger= LoggerFactory.getLogger(SpringThreadPool.class);

    public TaskExecutor taskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        // 设置核心线程数
        executor.setCorePoolSize(8);
        // 设置最大线程数
        executor.setMaxPoolSize(16);
        // 设置队列容量
        executor.setQueueCapacity(50);
        // 设置线程活跃时间（秒）
        executor.setKeepAliveSeconds(60);
        //设置线程池中任务的等待时间，如果超过这个时候还没有销毁就强制销毁，以确保应用最后能够被关闭，而不是阻塞住
        executor.setAwaitTerminationSeconds(60);
        // 设置默认线程名称
        executor.setThreadNamePrefix("DemoThreadPool-");
        // 设置拒绝策略
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        // 等待所有任务结束后再关闭线程池
        executor.setWaitForTasksToCompleteOnShutdown(true);
        executor.initialize();
        return executor;
    }
    public Executor getAsyncExecutor() {
        return taskExecutor();
    }
    public AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler() {
        return new MyAsyncExceptionHandler();
    }
    /**
     * 自定义异常处理类
     */
    class MyAsyncExceptionHandler implements AsyncUncaughtExceptionHandler {

        @Override
        public void handleUncaughtException(Throwable throwable, Method method, Object... objects) {
            logger.info("Exception message - " + throwable.getMessage());
            logger.info("Method name - " + method.getName());
            for (Object param : objects) {
                logger.info("Parameter value - " + param);
            }
        }
    }

}
