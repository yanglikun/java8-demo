package com.github.yanglikun.stream.parallel.rejecttest;

import com.github.yanglikun.BaseTest;
import com.google.common.util.concurrent.ThreadFactoryBuilder;
import com.sun.xml.internal.ws.util.CompletedFuture;
import junit.runner.BaseTestRunner;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class RejectTest extends BaseTest {

    private static final Logger logger = LoggerFactory.getLogger(RejectTest.class);

    @Test
    public void test() {
        ThreadFactory threadFactory = new ThreadFactoryBuilder()
                .setDaemon(true)
                .setNameFormat("ylk-%d")
                .build();
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(
                1,
                1,
                0L,
                TimeUnit.DAYS,
                new ArrayBlockingQueue<Runnable>(1),
                threadFactory,
                (r, executor) -> {
                    logger.info("任务太多,忽略任务");
                }
        );


        List<CompletableFuture<String>> futureList = Stream.of(1,2,3,4).map(
                number -> CompletableFuture.supplyAsync(() -> {
                    sleep(1);
                    return number + ":aa";
                }, threadPoolExecutor)
        ).collect(Collectors.toList());

        List<String> resultList = futureList.stream().map(
                future -> {
                    String ret = "default";
                    try {
                        ret = future.get(5, TimeUnit.SECONDS);
                    } catch (Exception e) {
                        e.printStackTrace();
                        ret = "异常了" + e.getMessage();
                    }
                    return ret;
                }
        ).collect(Collectors.toList());

        logger.info("任务结束:" + resultList);
        sleep(50);

    }

}
