package com.github.yanglikun.generic;

import org.junit.Test;

import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.List;

/**
 * @author yanglikun
 */
public class GettingActualType {

    static class A<E> {
    }

    static class B extends A<String> {
        public Class getActualType() {
            ParameterizedType superClass = (ParameterizedType) this.getClass().getGenericSuperclass();
            return (Class) superClass.getActualTypeArguments()[0];
        }
    }

    @Test
    public void test() {
        Class actualType = (new B()).getActualType();
        //输出 class java.lang.String
        System.out.println(actualType);
    }

}
