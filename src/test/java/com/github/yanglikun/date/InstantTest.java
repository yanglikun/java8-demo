package com.github.yanglikun.date;

import org.junit.Test;

import java.time.Instant;

public class InstantTest {

    @Test
    public void test(){
        Instant instant = Instant.ofEpochSecond(3);
        System.out.println(instant.getEpochSecond());

        Instant now = Instant.now();
        System.out.println(now.getNano());
        System.out.println(System.currentTimeMillis());
    }

    
}
