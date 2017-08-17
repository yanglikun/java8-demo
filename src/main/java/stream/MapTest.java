package stream;

import java.util.Arrays;
import java.util.List;

/**
 * Created by yanglikun on 2017/3/5.
 */
public class MapTest {
    public static void main(String[] args) {
        List<Integer> integers = Arrays.asList(1, 2, 3, 4, 5);
        integers.stream()
                .map(i -> i * i)
                .forEach(System.out::println);

        List<Integer> number1 = Arrays.asList(1, 2);
        List<Integer> number2 = Arrays.asList(3, 4, 5);

        number1.stream()
                .flatMap(i -> number2.stream().map(j -> Arrays.asList(i, j)))
                .filter(list -> (list.get(0) + list.get(1)) % 3 == 0)
                .forEach(System.out::println);
    }
}
