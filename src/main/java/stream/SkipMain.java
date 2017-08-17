package stream;

import java.util.Arrays;
import java.util.List;

/**
 * Created by yanglikun on 2017/3/5.
 */
public class SkipMain {
    public static void main(String[] args) {
        List<Integer> integers = Arrays.asList(1, 10, 2, 6, 7, 8,4);
        integers.stream()
                .filter(i -> i > 3)
                .skip(2)
                .forEach(System.out::println);//7 8 4
    }
}
