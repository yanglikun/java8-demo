package com.github.yanglikun.stream.parallel;

import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.junit.Test;

import java.io.IOException;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.atomic.AtomicInteger;

public class FutureTest {

    private static final org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(FutureTest.class);

    public static String sendHttp() {
        CloseableHttpClient client = HttpClientBuilder.create().build();
        RequestConfig config = RequestConfig.custom().setConnectionRequestTimeout(5)
                .setConnectTimeout(50_000)
                .setSocketTimeout(60_000).build();
        HttpGet httpGet = new HttpGet("http://127.0.0.1:8099/sleep");
        httpGet.setConfig(config);
        String respStr = null;
        try {
            logger.info("http请求开始");
            CloseableHttpResponse resp = client.execute(httpGet);
            respStr = EntityUtils.toString(resp.getEntity());
            logger.info("http请求结束:" + respStr);
        } catch (IOException e) {
            logger.error("http请求异常", e);
        }
        return respStr;
    }

    @Test
    public void test() {
        ExecutorService executorService = Executors.newFixedThreadPool(5, new DefaultThreadFactory());
        for (int i = 0; i < 10; i++) {
            executorService.submit(() -> {

            });
        }


        Future<String> future = executorService.submit(new Callable<String>() {
            public String call() throws Exception {
                int i = 1 / 0;
                return null;
            }
        });

        try {
            logger.info("开始获取结果");
            String ret = future.get();
            logger.error("返回结果:" + ret);
        } catch (InterruptedException e) {
            logger.error("", e);
        } catch (ExecutionException e) {
            logger.error("", e);
        }

        logger.info("执行完成");
        sleep(200);
    }

    public void sleep(int sec) {
        try {
            TimeUnit.SECONDS.sleep(sec);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    static class DefaultThreadFactory implements ThreadFactory {

        private static final AtomicInteger poolNumber = new AtomicInteger(1);

        private final ThreadGroup group;

        private final AtomicInteger threadNumber = new AtomicInteger(1);

        private final String namePrefix;

        DefaultThreadFactory() {
            SecurityManager s = System.getSecurityManager();
            group = (s != null) ? s.getThreadGroup() :
                    Thread.currentThread().getThreadGroup();
            namePrefix = "ylk-pool-" +
                    poolNumber.getAndIncrement() +
                    "-thread-";
        }

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
