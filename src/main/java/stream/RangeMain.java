package stream;

import java.util.stream.IntStream;

/**
 * Created by yanglikun on 2017/3/5.
 */
public class RangeMain {
    public static void main(String[] args) {

        IntStream.range(1, 5).forEach(System.out::println);

        IntStream.rangeClosed(1, 5).forEach(System.out::println);

    }
}
