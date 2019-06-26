package com.github.yanglikun.date;

import org.junit.Test;

import java.time.LocalDate;
import java.time.temporal.ChronoField;

public class LocalDateTest {

    @Test
    public void LocalDateOf() {
        LocalDate localDate = LocalDate.of(2018, 10, 4);
        System.out.println(localDate.getYear());//2018
        System.out.println(localDate.getMonth());//OCTOBER
        System.out.println(localDate.getDayOfMonth());//4
        System.out.println(localDate.getDayOfWeek());//THURSDAY
        System.out.println(localDate.lengthOfMonth());//31
        System.out.println(localDate.isLeapYear());//false.

        //可以使用get方法和temporalField获取值
        System.out.println(localDate.get(ChronoField.YEAR));//2018
        System.out.println(localDate.get(ChronoField.MONTH_OF_YEAR));//10
        System.out.println(localDate.get(ChronoField.DAY_OF_MONTH));//4
    }

    @Test
    public void now() {
        LocalDate now = LocalDate.now();
        System.out.println(now);
    }

    @Test
    public void temporalField() {
        LocalDate now = LocalDate.now();
    }
}
