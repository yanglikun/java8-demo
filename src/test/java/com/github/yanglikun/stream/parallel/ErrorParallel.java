package com.github.yanglikun.stream.parallel;

import org.junit.Test;

import java.util.stream.LongStream;

public class ErrorParallel {

    @Test
    public void test() {
        Accumulator accumulator = new Accumulator();
        LongStream.rangeClosed(0, 10).forEach(accumulator::add);
        System.out.println("correct result:" + accumulator.total);
    }

    @Test
    public void inCorrect() {
        for (int i = 0; i < 10; i++) {
            Accumulator errorUse = new Accumulator();
            LongStream.rangeClosed(0, 10).parallel().forEach(errorUse::add);
            System.out.println("incorrect result:" + errorUse.total);
        }
    }

    static class Accumulator {

        public long total = 0;

        public void add(long value) {
            total += value;
        }
    }

}

