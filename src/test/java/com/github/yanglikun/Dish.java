package com.github.yanglikun;
import java.util.*;

public class Dish {

    private final String name;
    private final boolean vegetarian;
    private final int calories;
    private final Type type;

    public Dish(String name, boolean vegetarian, int calories, Type type) {
        this.name = name;
        this.vegetarian = vegetarian;
        this.calories = calories;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public boolean isVegetarian() {
        return vegetarian;
    }

    public int getCalories() {
        return calories;
    }

    public Type getType() {
        return type;
    }

    public enum Type { MEAT, FISH, OTHER }

    @Override
    public String toString() {
        return name;
    }

    public static final List<Dish> menu =
            Arrays.asList( new Dish("猪肉", false, 800, Dish.Type.MEAT),
                           new Dish("牛肉", false, 700, Dish.Type.MEAT),
                           new Dish("鸡肉", false, 400, Dish.Type.MEAT),
                           new Dish("炸土豆片", true, 530, Dish.Type.OTHER),
                           new Dish("大米", true, 350, Dish.Type.OTHER),
                           new Dish("水果", true, 120, Dish.Type.OTHER),
                           new Dish("披萨", true, 550, Dish.Type.OTHER),
                           new Dish("虾", false, 400, Dish.Type.FISH),
                           new Dish("三文鱼", false, 450, Dish.Type.FISH));
}