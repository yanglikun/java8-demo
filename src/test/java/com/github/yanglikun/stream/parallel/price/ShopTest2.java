package com.github.yanglikun.stream.parallel.price;

import static java.util.stream.Collectors.toList;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadFactory;
import java.util.stream.Stream;

public class ShopTest2 {

    private static final Logger logger = LoggerFactory.getLogger(ShopTest.class);

    private List<Shop> shops = new ArrayList<>();

    private Executor executor = null;

    public ShopTest2() {
        int shopSize = 5;
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

    /**
     * 顺序流
     */
    @Test
    public void testPrices() {
        long begin = System.currentTimeMillis();
        List<String> prices = shops.stream()
                .map(shop -> shop.getPriceWithDiscount("iphone6"))//远程交互
                .map(Quote::parse)
                .map(Discount::applyDisCount)//远程交互
                .collect(toList());
        long time = System.currentTimeMillis() - begin;
        System.out.println(time + ":" + prices);
    }

    /**
     * completablefuture
     */
    @Test
    public void testPricesWithCompletableFuture() {
        long begin = System.currentTimeMillis();
        List<CompletableFuture<String>> list = shops.stream()
                .map(shop -> CompletableFuture.supplyAsync(() -> shop.getPriceWithDiscount("iphone6"), executor))
                .map(future -> future.thenApply(Quote::parse))
                .map(future -> future.thenCompose(quote ->
                        CompletableFuture.supplyAsync(() -> Discount.applyDisCount(quote), executor)
                )).collect(toList());
        List<String> prices = list.stream().map(CompletableFuture::join).collect(toList());
        long time = System.currentTimeMillis() - begin;
        System.out.println(time + ":" + prices);
    }

    @Test
    public void testCombine() {
        List<CompletableFuture<Double>> list = shops.stream().map(
                shop -> {
                    return CompletableFuture.supplyAsync(() -> shop.getPrice("iphone6"))
                            .thenCombine(CompletableFuture.supplyAsync(() -> 1.3), (price, rate) -> {
                                return price * rate;
                            });
                }
        ).collect(toList());

        List<Double> collect = list.stream().map(CompletableFuture::join).collect(toList());
        System.out.println(collect);
    }


    @Test
    public void testThenApply() {
        CompletableFuture<Integer> first = CompletableFuture.supplyAsync(() -> 1);

        CompletableFuture<Integer> second = first.thenApply(x -> {
            logger.info("then Apply:" + x);
            return x + 1;
        });
        System.out.println("ret:" + second.join());
    }


}
