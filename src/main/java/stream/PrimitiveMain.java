package stream;

import java.util.Arrays;
import java.util.List;

/**
 * Created by yanglikun on 2017/3/5.
 */
public class PrimitiveMain {
    public static void main(String[] args) {
        List<Integer> integers = Arrays.asList(1, 3, 5, 7);
        //这个规约操作会把包装类型转换为原始类型，消耗性能
        integers.stream().reduce(0, (a, b) -> a + b);
    }
}
