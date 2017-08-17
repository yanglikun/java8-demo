package stream;

import java.util.stream.Stream;

/**
 * Created by yanglikun on 2017/3/5.
 */
public class StreamFromFunctionMain {
    public static void main(String[] args) {
        //iterate 依次对每个新生成的值应用函数(UnaryOperator)
        Stream.iterate(0, n -> n + 2)
                .limit(10)
                .forEach(System.out::println);
        //generate 不是依次对每个新生成的值应用函数(Supplier)
        Stream.generate(Math::random)
                .limit(5)
                .forEach(System.out::println);
    }
}
