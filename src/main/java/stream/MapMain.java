package stream;

import java.util.Arrays;
import java.util.List;

/**
 * Created by yanglikun on 2017/3/5.
 */
public class MapMain {
    public static void main(String[] args) {
        //计算单词和单词的长度
        List<String> list = Arrays.asList("abc","abcd","ab","a");
        list.stream()
                .map(s -> s + "-" + s.length())
                .forEach(System.out::println);
    }
}
