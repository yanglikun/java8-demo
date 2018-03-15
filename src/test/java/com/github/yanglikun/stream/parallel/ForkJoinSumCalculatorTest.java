package com.github.yanglikun.stream.parallel;

import org.junit.Test;

import java.util.concurrent.ForkJoinPool;
import java.util.stream.LongStream;

public class ForkJoinSumCalculatorTest {

    int n = 1000;

    @Test
    public void test() {
        long[] numbers = LongStream.rangeClosed(1, n).toArray();
        ForkJoinSumCalculator task = new ForkJoinSumCalculator(numbers);
        Long ret = new ForkJoinPool().invoke(task);
        System.out.println(ret);
        System.out.println("...");
    }

}
