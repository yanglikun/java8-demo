package com.github.yanglikun.threadpool;

import ch.qos.logback.classic.Logger;
import com.github.yanglikun.BaseTest;
import com.google.common.util.concurrent.ThreadFactoryBuilder;
import org.junit.Test;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class ThreadPoolTest extends BaseTest {

    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(ThreadPoolTest.class);

    private ThreadFactory threadFactory = new ThreadFactoryBuilder()
            .setNameFormat("city-query-task-%d")
            .setDaemon(true)
            .build();


    @Test
    public void testCoreThreadSize() {

        ThreadPoolExecutor executor = new ThreadPoolExecutor(
                5,
                10,
                1,
                TimeUnit.HOURS,
                new ArrayBlockingQueue<>(3),
                threadFactory
        );

        sleep(10);

        for (int i = 0; i < 10; i++) {
            final int index = i;
            Future<String> future = executor.submit(new Callable<String>() {
                @Override
                public String call() throws Exception {
                    sleep(3);
                    return "task-" + index;
                }
            });
            try {
                logger.info("任务 " + index + " 执行完成." + future.get());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }

        sleep(100);
    }


    @Test
    public void testMonitor() {
        CustomThreadPoolExecutor executor = new CustomThreadPoolExecutor(
                3,
                3,
                1,
                TimeUnit.HOURS,
                new ArrayBlockingQueue<>(5),
                threadFactory,
                (r, executor1) -> {
                    logger.info("任务太多抛弃");
                }
        );

        for (int i = 0; i < 20; i++) {
            int task = i;
            executor.execute(() -> {
                sleep(3);
                logger.info("处理任务：" + task);
            });
        }

        sleep(100);
    }


    @Test
    public void testFuture() throws Exception {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(
                5,
                10,
                1,
                TimeUnit.HOURS,
                new ArrayBlockingQueue<>(3),
                threadFactory
        );
        Future<String> future = executor.submit(() -> {
            try {
                TimeUnit.MINUTES.sleep(1000L);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            return "done";
        });
        System.out.println(future.get());
    }

    @Test
    public void testKillThread() throws Exception {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(
                1,
                1,
                1,
                TimeUnit.HOURS,
                new ArrayBlockingQueue<>(3),
                threadFactory
        );
        Future<String> future = executor.submit(() -> {
            while (true) {
                try {
                    TimeUnit.SECONDS.sleep(5);
                    logger.info("next loop");
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        System.out.println(future.get());
    }

    static class CustomThreadPoolExecutor extends ThreadPoolExecutor {

        private AtomicInteger taskCount = new AtomicInteger();

        public CustomThreadPoolExecutor(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit,
                                        BlockingQueue<Runnable> workQueue, ThreadFactory threadFactory,
                                        RejectedExecutionHandler rejectedExecutionHandler) {
            super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, threadFactory,
                    rejectedExecutionHandler);
        }


        @Override
        protected void beforeExecute(Thread t, Runnable r) {
            int queueTaskCount = this.getQueue().size();
            logger.info("before.任务数量:" + queueTaskCount);
            super.beforeExecute(t, r);
        }

    }

}
