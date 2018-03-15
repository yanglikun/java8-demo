package com.github.yanglikun.stream.parallel.price;

import static java.util.stream.Collectors.toList;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadFactory;
import java.util.stream.Collectors;

public class ShopTest {

    private static final Logger logger = LoggerFactory.getLogger(ShopTest.class);

    private List<Shop> shops = new ArrayList<>();

    private Executor executor = null;

    public ShopTest() {
        int shopSize = 10;
        for (int i = 0; i < shopSize; i++) {
            shops.add(new Shop("shop-" + i));
        }

        executor = Executors.newFixedThreadPool(Math.min(shops.size(), 100), new ThreadFactory() {
            @Override
            public Thread newThread(Runnable r) {
                Thread thread = new Thread(r);
                thread.setDaemon(true);
                return thread;
            }
        });
    }

    @Test
    public void test() {
        Shop shop = new Shop("BestShop");
        String productName = "my favorite product";
        logger.info("开始获取价格.product:{}", productName);
        Future<Double> future = shop.getPriceAsync1("my favorite product");

        logger.info("开始获取价格");
        try {
            Double price = future.get();
            logger.info("获取到价格." + price);
        } catch (Exception e) {
            logger.error("outer:", e);
        }
    }

    /**
     * 顺序流
     */
    @Test
    public void testPrices() {
        long begin = System.currentTimeMillis();
        List<String> prices = findPrices("iphone6");
        long time = System.currentTimeMillis() - begin;
        System.out.println(time + ":" + prices);
    }

    private List<String> findPrices(String product) {
        return shops.stream()
                .map(shop -> shop.getName() + ":" + shop.getPrice(product))
                .collect(toList());
    }

    /**
     * 并行流
     */
    @Test
    public void testPricesParallel() {
        long begin = System.currentTimeMillis();
        List<String> prices = findPricesParallel("iphone6");
        long time = System.currentTimeMillis() - begin;
        System.out.println(time + ":" + prices);
    }

    private List<String> findPricesParallel(String product) {
        return shops.stream()
                .parallel()
                .map(shop -> shop.getName() + ":" + shop.getPrice(product))
                .collect(toList());
    }

    /**
     * completable
     */
    @Test
    public void testPricesCompletableFuture() {
        long begin = System.currentTimeMillis();
        List<String> prices = findPricesCompletableFuture("iphone6");
        long time = System.currentTimeMillis() - begin;
        System.out.println(time + ":" + prices);
    }

    private List<String> findPricesCompletableFuture(String product) {
        List<CompletableFuture<String>> futures = shops.stream()
                .map(shop -> CompletableFuture.supplyAsync(() -> shop.getName() + ":" + shop.getPrice(product)))
                .collect(toList());

        return futures.stream().map(CompletableFuture::join).collect(toList());
    }


    /**
     * completableFuture with custom executor
     */
    @Test
    public void testPricesCompletableFutureWithExecutor() {
        long begin = System.currentTimeMillis();
        List<String> prices = findPricesCompletableFutureWithExecutor("iphone6");
        long time = System.currentTimeMillis() - begin;
        System.out.println(time + ":" + prices);
    }

    private List<String> findPricesCompletableFutureWithExecutor(String product) {
        List<CompletableFuture<String>> futures = shops.stream()
                .map(shop -> CompletableFuture.supplyAsync(
                        () -> shop.getName() + ":" + shop.getPrice(product),
                        executor))
                .collect(toList());

        return futures.stream().map(CompletableFuture::join).collect(toList());
    }

}
