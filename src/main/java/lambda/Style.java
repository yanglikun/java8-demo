package lambda;


/**
 * Created by yanglikun on 2017/2/23.
 */
public class Style {
    public static void main(String[] args) {

        NoArgument noArgument = () -> System.out.println("没有参数的lambda");

        NoArgument noArgumentMultiLine = () -> {
            System.out.println("多行语句...");
            System.out.println("多行语句...");
            System.out.println("多行语句...");
        };

        OneArgument oneArgument = name -> System.out.println("一个参数的lambda:" + name);

        MoreThanOneArgument<Long> moreThanOneArgument = (value1, value2) -> value1 + value2;
        System.out.println("相加结果:" + moreThanOneArgument.add(3L, 4L));

        //显示指定参数类型
        MoreThanOneArgument<Long> moreThanOneArgument1 = (Long value1, Long value2) -> {
            System.out.println("两个数相加:value1:" + value1 + ",value2:" + value2);
            return value1 + value2;
        };
        System.out.println("相加结果:" + moreThanOneArgument.add(3L, 4L));
    }

    public interface NoArgument {
        public void test();
    }

    public interface OneArgument {
        public void test(String name);
    }

    public interface MoreThanOneArgument<T> {
        public T add(T value1, T value2);
    }
}
