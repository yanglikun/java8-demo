package stream;

import domain.Apple;
import domain.Color;

import java.util.List;

/**
 * Created by yanglikun on 2017/3/5.
 */
public class PredicateMain {

    public static void main(String[] args) {
        List<Apple> apples = Apple.buildList();
        apples.stream()
                .filter(apple -> apple.getColor().equals(Color.GREEN))
                .forEach(System.out::println);
    }
}
