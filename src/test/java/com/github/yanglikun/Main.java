package com.github.yanglikun;

import java.util.stream.Collectors;

/**
 * Created by yanglikun on 2017/8/18.
 */
public class Main {

    public static void main(String[] args) {
        String collect = Dish.menu.stream().collect(
                Collectors.collectingAndThen(
                        Collectors.groupingBy(dish -> {
                            if (dish.getCalories() > 1000) {
                                return "1";
                            } else {
                                return "2";
                            }
                        }),
                        list -> "a" + list.toString()
                )
        );
        System.out.println(collect);
    }

}
