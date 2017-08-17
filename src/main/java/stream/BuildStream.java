package stream;

import java.util.Arrays;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * Created by yanglikun on 2017/3/5.
 */
public class BuildStream {
    public static void main(String[] args) {
        //from values
        Stream<Integer> integerStream = Stream.of(1, 3, 5, 7);
        //empty stream
        Stream<Object> empty = Stream.empty();
        //form arrays
        int[] numbers = {1, 3, 5, 7};
        IntStream stream = Arrays.stream(numbers);


    }
}
