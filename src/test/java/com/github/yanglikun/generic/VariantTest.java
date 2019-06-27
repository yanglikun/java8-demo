package com.github.yanglikun.generic;

import generic.variant.SubType;
import generic.variant.SuperType;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yanglikun
 */
public class VariantTest {

    @Test
    public void test() {
        Object[] objects = null;
        String[] doubles = null;
        objects = doubles;

        List<Object> objectList = null;
        List<String> stringList = null;
        //objectList = stringList;//编译报错
    }


    /**
     * 数组是协变的
     */
    @Test
    public void testArray() {
        SuperType[] superArr = new SuperType[3];
        SubType[] subArr = new SubType[3];
        superArr = subArr;
    }

    /**
     * 泛型是不变的
     */
    @Test
    public void testGeneric() {
        List<Object> objectList = null;
        List<String> stringList = null;
        //objectList = stringList;//编译报错
    }

    /**
     * extend是协变的
     */
    @Test
    public void testGenericExtends() {
        List<? extends SuperType> superExtendList = new ArrayList<>();
        List<SubType> subList = new ArrayList<>();

        superExtendList = subList;
    }

    /**
     * super是逆变的
     */
    @Test
    public void testGenericSuper() {
        List<? super SubType> subSuperList = new ArrayList<>();
        List<SuperType> superList = new ArrayList<>();
        subSuperList = superList;
    }
}
