package stream;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * Created by yanglikun on 2017/3/5.
 */
public class ReduceMain {
    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(1, 3, 5, 7);
        //求和
        Integer sum = list.stream().reduce(0, (a, b) -> a + b);
        sum = list.stream().reduce(0, Integer::sum);
        //没有默认值，返回Optional
        Optional<Integer> reduce = list.stream().reduce(Integer::sum);
        //最大值
        list.stream().reduce(Integer::max);
        //最小值
        list.stream().reduce(Integer::min);
    }
}
