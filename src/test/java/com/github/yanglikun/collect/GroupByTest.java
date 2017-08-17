package com.github.yanglikun.collect;

import com.github.yanglikun.BaseTest;
import domain.Apple;
import domain.Color;
import org.junit.Test;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static java.util.stream.Collectors.*;

/**
 * Created by yanglikun on 2017/3/8.
 */
public class GroupByTest extends BaseTest {


    @Test
    public void groupByTest() {
        Map<Color, List<Apple>> collect = Apple.buildList().stream().collect(groupingBy(Apple::getColor));
        print(collect);
    }

    /**
     * 普通的groupingBy(f)相当于grouping(f,toList())的简写
     */
    @Test
    public void multiGroupByTest() {
        Map<Color, Map<String, List<Apple>>> collect = Apple.buildList().stream()
                .collect(
                        groupingBy(Apple::getColor, groupingBy(Apple::getOrigin))
                );
        print(collect);
    }

    /**
     * groupingBy的第二个参数是一个收集器，除了是groupingby，还能是其它的.
     * 对groupingBy后的结果进一步进行规约
     */
    @Test
    public void collectorGroupByTest() {
        Map<String, Optional<Apple>> collect = Apple.buildList().stream()
                .collect(
                        groupingBy(Apple::getOrigin, maxBy(Comparator.comparing(Apple::getWeight)))
                );
        print(collect);
    }

    /**
     * 把收集器返回的结果转换为另外的类型
     */
    @Test
    public void collectingAndThentTest() {
        Map<String, Apple> map = Apple.buildList().stream()
                .collect(
                        groupingBy(
                                Apple::getOrigin,
                                collectingAndThen(maxBy(Comparator.comparing(Apple::getWeight)), Optional::get)
                        )
                );
        print(map);
    }
    //常用的还有suming、mapping

    /**
     * 转换
     */
    @Test
    public void mappingTest() {
        Map<String, List<Color>> collect = Apple.buildList().stream()
                .collect(
                        groupingBy(
                                Apple::getOrigin,
                                mapping(Apple::getColor, toList())
                        )
                );
        print(collect);
    }

    @Test
    public void mappingTest1() {
    }


}
