package com.github.yanglikun.stream.parallel.price;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class Shop {

    private static final Logger logger = LoggerFactory.getLogger(Shop.class);

    Random r = new Random();

    private String name;

    public Shop(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public Future<Double> getPriceAsync1(String product) {
        return CompletableFuture.supplyAsync(() -> calculatePrice(product));
    }


    public Future<Double> getPriceAsync(String product) {
        CompletableFuture<Double> futurePrice = new CompletableFuture<>();
        new Thread(() -> {
            try {
                double price = calculatePrice(product);
                futurePrice.complete(price);
            } catch (Exception e) {
                futurePrice.completeExceptionally(e);
            }
        }).start();
        return futurePrice;
    }

    public double getPrice(String product) {
        return calculatePrice(product);
    }

    private double calculatePrice(String product) {
        delay(1);
        double price = r.nextDouble() * product.charAt(0) + product.charAt(1);
        logger.info("计算价格完成:product:{},price:{}", product, price);
        return price;
    }


    public double getPriceRandomDelay(String product) {
        return calculatePrice(product);
    }

    private double calculatePriceRandomDelay(String product) {
        int delay = r.nextInt(2000);
        try {
            Thread.sleep(delay);
        } catch (InterruptedException e) {
            logger.error("sleep exception", e);
        }
        double price = r.nextDouble() * product.charAt(0) + product.charAt(1);
        logger.info("计算价格完成:product:{},price:{}", product, price);
        return price;
    }


    public String getPriceWithDiscount(String product) {
        double price = calculatePrice(product);
        Discount.Code discountCode = Discount.Code.values()[r.nextInt(Discount.Code.values().length)];
        return String.format("%s:%.2f:%s", product, price, discountCode);
    }

    public String getPriceWithDiscountAndDelay(String product) {
        double price = calculatePriceRandomDelay(product);
        Discount.Code discountCode = Discount.Code.values()[r.nextInt(Discount.Code.values().length)];
        return String.format("%s:%.2f:%s", product, price, discountCode);
    }


    private void delay(int second) {
        try {
            TimeUnit.SECONDS.sleep(second);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
