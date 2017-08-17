package lambda;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by yanglikun on 2017/3/25.
 */
public class PeekMain {
    public static void main(String[] args) {
        List<Integer> collect = Stream.of(1, 7, 3, 9)
                .peek(i -> System.out.println("before map:" + i))
                .map(i -> i + 3)
                .peek(i -> System.out.println("after map:" + i))
                .filter(i -> i > 3)
                .peek(i -> System.out.println("after filter:" + i))
                .limit(2)
                .peek(i -> System.out.println("after limit:" + i)).collect(Collectors.toList());

        System.out.println("collect:" + collect);

    }
}
