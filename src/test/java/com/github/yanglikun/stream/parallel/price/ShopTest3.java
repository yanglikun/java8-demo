package com.github.yanglikun.stream.parallel.price;

import com.sun.org.apache.xpath.internal.SourceTree;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ShopTest3 {

    private static final Logger logger = LoggerFactory.getLogger(ShopTest3.class);

    private List<Shop> shops = new ArrayList<>();

    private Executor executor = null;

    public ShopTest3() {
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

    @Test
    public void test() {
        Stream<CompletableFuture<String>> priceStream = findPricesStream("iphone6");

        CompletableFuture[] completableFutures = priceStream.map(
                f -> f.thenAccept(ret ->
                        logger.info("ret:" + ret)
                )
        ).toArray(size -> new CompletableFuture[size]);
        CompletableFuture.allOf(completableFutures).join();
    }

    @Test
    public void testAllOf() {
        Stream<CompletableFuture<String>> priceStream = findPricesStream("iphone6");

        long begin = System.nanoTime();
        CompletableFuture[] completableFutures = priceStream.map(
                f -> f.thenAccept(ret ->
                        logger.info("ret(" + ((System.nanoTime() - begin) / 1_000_000) + "):" + ret)
                )
        ).toArray(size -> new CompletableFuture[size]);
        CompletableFuture.allOf(completableFutures).join();
    }


    @Test
    public void testAnyOf() {
        Stream<CompletableFuture<String>> priceStream = findPricesStream("iphone6");

        long begin = System.nanoTime();
        CompletableFuture[] completableFutures = priceStream.map(
                f -> f.thenAccept(ret ->
                        logger.info("ret(" + ((System.nanoTime() - begin) / 1_000_000) + "):" + ret)
                )
        ).toArray(size -> new CompletableFuture[size]);
        CompletableFuture.anyOf(completableFutures).join();
    }


    private Stream<CompletableFuture<String>> findPricesStream(String product) {
        return shops.stream()
                .map(shop ->
                        CompletableFuture.supplyAsync(() -> shop.getPriceWithDiscountAndDelay(product))
                )
                .map(future -> future.thenApply(Quote::parse))
                .map(future -> future.thenCompose(quote ->
                        CompletableFuture.supplyAsync(() -> Discount.applyDisCount(quote))
                ));
    }

}
