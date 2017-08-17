package stream;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

/**
 * Created by yanglikun on 2017/3/5.
 */
public class FlatMapMain {
    public static void main(String[] args) {
        List<String> list = Arrays.asList("hello", "world");
        Stream<Stream<String>> streamStream = list.stream().map(s -> s.split("")).map(Arrays::stream);
        Stream<String> stringStream = list.stream().map(s -> s.split("")).flatMap(Arrays::stream);

    }
}
