package com.github.yanglikun.stream.parallel.price;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;

public class Discount {

    private static final Logger logger = LoggerFactory.getLogger(Discount.class);

    public static String applyDisCount(Quote quote) {

        return quote.getShopName() + " price is " + apply(quote.getPrice(), quote.getDiscountCode());
    }

    private static String apply(Double price, Discount.Code code) {
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            logger.error("休眠异常", e);
        }
        return String.format("%.2f", price * ((100 - code.percentage) / 100));
    }

    public enum Code {
        none(0), silver(5), gold(10), platinum(15), diamond(20);

        private final int percentage;

        Code(int percentage) {
            this.percentage = percentage;
        }
    }

}
