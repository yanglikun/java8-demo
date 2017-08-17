package domain;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yanglikun on 2017/3/5.
 */
public class Apple {

    private Color color;

    private Integer weight;

    private String origin;

    public Apple(Color color, Integer weight) {
        this.color = color;
        this.weight = weight;
    }

    public Apple(Color color, Integer weight, String origin) {
        this.color = color;
        this.origin = origin;
        this.weight = weight;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Apple{");
        sb.append("color=").append(color);
        sb.append(", weight=").append(weight);
        sb.append(", origin='").append(origin).append('\'');
        sb.append('}');
        return sb.toString();
    }

    public static List<Apple> buildList() {
        List<Apple> list = new ArrayList<>();
        list.add(new Apple(Color.RED, 1, "北京"));
        list.add(new Apple(Color.GREEN, 2, "上海"));
        list.add(new Apple(Color.RED, 3, "深圳"));
        list.add(new Apple(Color.GREEN, 4, "北京"));
        list.add(new Apple(Color.GREEN, 5, "上海"));
        return list;
    }
}
