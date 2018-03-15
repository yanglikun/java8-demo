package com.github.yanglikun;

import org.junit.Test;

import java.util.concurrent.locks.LockSupport;

public class ThreadStateTest extends BaseTest {

    private Object lock1 = new Object();

    private Object lock2 = new Object();

    @Test
    public void test() {
        new Thread(() -> {
            for (; ; ) {
                int i = 1;
            }
        }, "ylk-running").start();

        new Thread(() -> {
            sleep(1000);
        }, "ylk-timedWaiting").start();

        new Thread(() -> {
            synchronized (lock1) {
                try {
                    lock1.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "ylk-waiting").start();

        new Thread(() -> {
            LockSupport.park();
        }, "ylk-park").start();

        synchronized (lock2) {
            new Thread(() -> {
                synchronized (lock2) {

                }
            }, "ylk-blocked").start();

            sleep(2000);
        }


    }

}
