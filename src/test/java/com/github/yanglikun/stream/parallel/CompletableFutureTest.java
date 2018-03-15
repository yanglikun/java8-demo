package com.github.yanglikun.stream.parallel;

import com.github.yanglikun.BaseTest;
import com.google.common.util.concurrent.ThreadFactoryBuilder;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CompletableFutureTest extends BaseTest {

    private static final Logger logger = LoggerFactory.getLogger(CompletableFutureTest.class);

    private Executor executor;

    public CompletableFutureTest() {
        ThreadFactory threadFactory = new ThreadFactoryBuilder()
                .setNameFormat("city-query-task-%d")
                .setDaemon(true)
                .build();

        executor = new ThreadPoolExecutor(
                1,
                1,
                1,
                TimeUnit.HOURS,
                new ArrayBlockingQueue<>(3),
                threadFactory
        );
    }

    /**
     * thenApply是那个线程
     */
    @Test
    public void test() {
        List<CompletableFuture<Integer>> futures = Stream.of(3, 7).map(i ->
                CompletableFuture.supplyAsync(() -> {
                    logger.info("执行任务:" + i);
                    return i;
                })
        ).map(future ->
                {
                    sleep(3);
                    return future.thenApply(i -> {
                        logger.info("执行thenApply:" + i);
                        return i;
                    });
                }
        ).collect(Collectors.toList());

        sleep(100);
    }

    @Test
    public void testException() {
        List<CompletableFuture<Integer>> collect = Stream.of(3, 7, 8, 1, 3).map(i ->
                CompletableFuture.supplyAsync(() -> {
                    if (i == 8) {
                        int a = 3 / 0;
                    }
                    logger.info("执行任务:" + i);
                    return i;
                }).exceptionally(throwable -> {
                    logger.error("出错了~~", throwable);
                    return 88;
                })
        ).collect(Collectors.toList());

        for (CompletableFuture<Integer> future : collect) {
            try {
                logger.info("结果:" + future.get());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }
        sleep(200);
    }

    @Test
    public void testExceptionWithGuava() {

        ThreadFactory threadFactory = new ThreadFactoryBuilder()
                .setDaemon(true)
                .setNameFormat("ylk-test-%d")
                .setUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {
                    @Override
                    public void uncaughtException(Thread t, Throwable e) {
                        logger.info("未捕获的异常...");
                    }
                })
                .build();

        ThreadPoolExecutor executor = new ThreadPoolExecutor(
                5,
                10,
                1,
                TimeUnit.HOURS,
                new ArrayBlockingQueue<>(3),
                threadFactory
        );

        List<CompletableFuture<Integer>> collect = Stream.of(3, 7, 8, 1, 3).map(i ->
                CompletableFuture.supplyAsync(() -> {
                    if (i == 8) {
                        int a = 3 / 0;
                    }
                    logger.info("执行任务:" + i);
                    return i;
                },executor)
        ).collect(Collectors.toList());

        for (CompletableFuture<Integer> future : collect) {
            try {
                logger.info("结果:" + future.get());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }
        sleep(200);
    }


}
