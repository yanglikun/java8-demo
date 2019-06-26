package com.github.yanglikun.generic;

import generic.GenericReflectionTest;
import org.junit.Test;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * @author Tony
 */
public class ErasureTest {

    /**
     * 打印list类
     */
    @Test
    public void test() {
        List<String> list = new ArrayList<>();
        Class<? extends List> listClass = list.getClass();
        //输出class java.util.ArrayList<E>
        // extends java.util.AbstractList<E> implements java.util.List<E>,
        // java.util.RandomAccess, java.lang.Cloneable, java.io.Serializable
        GenericReflectionTest.printClass(listClass);
    }

    @Test
    public void printMethod() throws Exception {
        //方法源码:public static <T extends Object & Comparable<? super T>> T
        // max(Collection<? extends T> coll) {
        Method max = Collections.class.getMethod("max", Collection.class);
        //输出：public static <T extends java.lang.Object & java.lang.Comparable<? super T>> T
        // max(java.util.Collection<? extends T>)
        GenericReflectionTest.printMethod(max);
    }

}
