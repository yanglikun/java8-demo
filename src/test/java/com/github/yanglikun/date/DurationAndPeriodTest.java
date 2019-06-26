package com.github.yanglikun.date;

import org.junit.Test;

import java.time.Duration;
import java.time.LocalTime;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class DurationAndPeriodTest {

    @Test
    public void testDuration() {
    }

    @Test
    public void testPeriod() throws InterruptedException {
        LocalTime now = LocalTime.now();
        TimeUnit.SECONDS.sleep(3);
        LocalTime now1 = LocalTime.now();
        Duration between = Duration.between(now, now1);
        System.out.println("sec:" + between.getSeconds());
    }
}
