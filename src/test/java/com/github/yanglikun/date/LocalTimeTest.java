package com.github.yanglikun.date;

import org.junit.Test;

import java.time.LocalDate;
import java.time.LocalTime;

public class LocalTimeTest {

    @Test
    public void test() {
        LocalTime now = LocalTime.now();
        System.out.println(now);
        System.out.println(now.getHour());
        System.out.println(now.getMinute());
        System.out.println(now.getSecond());
    }

    @Test
    public void parse() {
        LocalDate ld = LocalDate.parse("2014-03-18");
        System.out.println(ld);
        LocalTime lt = LocalTime.parse("09:05:20");
        System.out.println(lt);
    }

}
