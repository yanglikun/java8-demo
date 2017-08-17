package com.github.yanglikun;

import com.alibaba.fastjson.JSON;

/**
 * Created by yanglikun on 2017/5/6.
 */
public class BaseTest {

    public void print(Object obj) {
        System.out.println(JSON.toJSONString(obj));
    }
}
