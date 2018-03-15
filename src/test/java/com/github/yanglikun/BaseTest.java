package com.github.yanglikun;

import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;

/**
 * Created by yanglikun on 2017/5/6.
 */
public class BaseTest {

    private static final Logger logger = LoggerFactory.getLogger(BaseTest.class);

    public void print(Object obj) {
        System.out.println(JSON.toJSONString(obj));
    }


    public void sleep(int second) {
        try {
            TimeUnit.SECONDS.sleep(second);
        } catch (InterruptedException e) {
            logger.error("休眠异常", e);
        }
    }
}
