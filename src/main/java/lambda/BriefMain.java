package lambda;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * Created by yanglikun on 2017/2/26.
 */
public class BriefMain {


    public static void main(String[] args) {
        List<Apple> apples = new ArrayList<>();

        apples.add(new Apple(3, "d"));
        apples.add(new Apple(8, "y"));
        apples.add(new Apple(4, "b"));
        apples.add(new Apple(2, "a"));

        apples.sort(Comparator.comparing(Apple::getWeight));
        System.out.println(apples);
        apples.sort(Comparator.comparing(Apple::getColor).thenComparing(Apple::getWeight));
        apples.sort(Comparator.comparing(Apple::getColor).reversed());
        System.out.println(apples);

        Predicate<Apple> predicate = apple -> apple.getWeight() > 3;
        predicate.or(apple -> apple.getWeight() <= 3);
        List<Apple> collect = apples.stream().filter(predicate).collect(Collectors.toList());
        System.out.println(collect);

    }


    public static class Apple {
        private Integer weight;

        private String color;

        public Apple(Integer weight, String color) {
            this.weight = weight;
            this.color = color;
        }

        public Integer getWeight() {
            return weight;
        }

        public void setWeight(Integer weight) {
            this.weight = weight;
        }

        public String getColor() {
            return color;
        }

        public void setColor(String color) {
            this.color = color;
        }

        @Override
        public String toString() {
            final StringBuilder sb = new StringBuilder("Apple{");
            sb.append("weight=").append(weight);
            sb.append(", color='").append(color).append('\'');
            sb.append('}');
            return sb.toString();
        }
    }
}
