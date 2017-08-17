package stream;

import java.util.Arrays;
import java.util.List;

/**
 * Created by yanglikun on 2017/3/5.
 */
public class DistinctMain {
    public static void main(String[] args) {
        List<Integer> integers = Arrays.asList(1, 2, 1, 3, 2, 3, 4);
        integers.stream()
                .filter(i -> i % 2 == 0)
                .distinct()
                .forEach(System.out::println);
    }
}
