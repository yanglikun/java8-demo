package stream.practice;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * Created by yanglikun on 2017/3/5.
 */
public class PracticeMain {
    public static void main(String[] args) {
        Trader t1 = new Trader("交易员1", "北京");
        Trader t2 = new Trader("交易员2", "上海");
        Trader t3 = new Trader("交易员3", "北京");
        Trader t4 = new Trader("交易员4", "上海");
        List<Transaction> transactions = Arrays.asList(
                new Transaction(t4, 2011, 300),
                new Transaction(t1, 2012, 1000),
                new Transaction(t1, 2011, 400),
                new Transaction(t2, 2012, 710),
                new Transaction(t2, 2012, 700),
                new Transaction(t3, 2012, 950)
        );

        transactions.stream()
                .filter(trans -> trans.getYear() == 2011)
                .sorted(Comparator.comparing(Transaction::getValue))
                .forEach(System.out::println);

        transactions.stream()
                .map(trans -> trans.getTrader())
                .filter(trader -> trader.getCity().equals("北京"))
                .forEach(System.out::println);

        String str = transactions.stream()
                .map(trans -> trans.getTrader().getName())
                .distinct()
                .reduce("", (city1, city2) -> city1 + "-" + city2);
        System.out.println(str);

    }
}
