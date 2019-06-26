package com.github.yanglikun.generic;

import org.junit.Test;

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


}
