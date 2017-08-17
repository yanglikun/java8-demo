package lambda.methodreference;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiPredicate;

/**
 * Created by yanglikun on 2017/2/26.
 */
public class Example {

    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.add("c");
        list.add("a");
        list.add("d");
        list.add("b");
//        list.sort(String::compareToIgnoreCase);
        list.sort((o1, o2) -> o1.compareToIgnoreCase(o2));
        System.out.println(list);

        BiPredicate<List<String>, String> contains = List::contains;


    }


}
