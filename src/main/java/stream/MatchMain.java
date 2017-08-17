package stream;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * Created by yanglikun on 2017/3/5.
 */
public class MatchMain {
    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7);

        boolean ret = list.stream().allMatch(i -> i > 3);
        System.out.println(ret);

        boolean ret1 = list.stream().anyMatch(i -> i > 3);
        System.out.println(ret1);

        boolean ret2 = list.stream().noneMatch(i -> i > 3);
        System.out.println(ret2);

        Optional<Integer> opt = list.stream().filter(i -> i > 3).findAny();
        opt.isPresent();//是否存在值
        opt.get();//获取值，如果值不存在就抛出异常
        opt.ifPresent(i -> System.out.println(i));//存在执行操作
        opt.orElse(8);//值存在时返回值，不存在返回默认值
    }
}
