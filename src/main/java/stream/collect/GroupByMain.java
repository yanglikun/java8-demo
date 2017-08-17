package stream.collect;

import com.alibaba.fastjson.JSON;
import domain.Apple;
import domain.Color;

import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.groupingBy;

/**
 * Created by yanglikun on 2017/3/8.
 */
public class GroupByMain {

    public static void main(String[] args) {
        Map<Color, List<Apple>> collect = Apple.buildList().stream().collect(groupingBy(Apple::getColor));
        System.out.println(JSON.toJSONString(collect));
    }

}
