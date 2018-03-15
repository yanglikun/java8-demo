package com.github.yanglikun.stream.parallel;

import org.junit.Test;

import java.util.stream.LongStream;
import java.util.stream.Stream;

public class SimpleTest {

    int n = 100000;

    @Test
    public void availableProcessors() {
        int i = Runtime.getRuntime().availableProcessors();
        System.out.println(i);
    }

    @Test
    public void parallelTest() {
        long begin = System.currentTimeMillis();
        Long total = Stream.iterate(1L, i -> i + 1)
                .limit(n)
                .parallel()
                .reduce(0L, (a, b) -> {
                    return a + b;
                });
        Long end = System.currentTimeMillis();
        System.out.println("iterate(parallel) total:" + total + ",time:" + (end - begin));
    }

    @Test
    public void notParallelTest() {
        long begin = System.currentTimeMillis();
        Long total = Stream.iterate(1L, i -> i + 1)
                .limit(n)
                .reduce(0L, (a, b) -> {
                    return a + b;
                });
        Long end = System.currentTimeMillis();
        System.out.println("iterate total:" + total + ",time:" + (end - begin));
    }

    @Test
    public void LongStreamTest() {
        long begin = System.currentTimeMillis();
        long total = LongStream.rangeClosed(1, n).reduce(0L, (a, b) -> {
            return a + b;
        });
        Long end = System.currentTimeMillis();
        System.out.println("longStream total:" + total + ",time:" + (end - begin));
    }

    @Test
    public void parallelLongStreamTest() {
        long begin = System.currentTimeMillis();
        long total = LongStream.rangeClosed(1, n).parallel().reduce(0L, (a, b) -> {
            return a + b;
        });
        Long end = System.currentTimeMillis();
        System.out.println("longStream(parallel) longStream total:" + total + ",time:" + (end - begin));
    }


    public Long toSecond(Long milliseconds) {
        return milliseconds / 1000;
    }

}
