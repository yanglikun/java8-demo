package com.github.yanglikun.stream.parallel.price;

import ch.qos.logback.classic.Logger;
import org.slf4j.LoggerFactory;

public class Quote {

    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(Quote.class);

    private final String shopName;

    private final double price;

    private final Discount.Code discountCode;

    public Quote(String shopName, double price, Discount.Code discountCode) {
        this.shopName = shopName;
        this.price = price;
        this.discountCode = discountCode;
    }

    public static Quote parse(String s) {
        String[] split = s.split(":");
        Quote quote = new Quote(split[0], Double.parseDouble(split[1]), Discount.Code.valueOf(split[2]));
        logger.info("计算折扣完成:"+quote);
        return quote;
    }


    public String getShopName() {
        return shopName;
    }

    public double getPrice() {
        return price;
    }

    public Discount.Code getDiscountCode() {
        return discountCode;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Quote{");
        sb.append("shopName='").append(shopName).append('\'');
        sb.append(", price=").append(price);
        sb.append(", discountCode=").append(discountCode);
        sb.append('}');
        return sb.toString();
    }
}

