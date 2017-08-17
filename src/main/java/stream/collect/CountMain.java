package stream.collect;

import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by yanglikun on 2017/3/8.
 */
public class CountMain {

    public static void main(String[] args) {
        long count = Stream.of(1, 3, 5, 7).count();
        System.out.println(count);
        Long collect = Stream.of(1, 3, 5, 7).collect(Collectors.counting());
        System.out.println(collect);
    }

}
