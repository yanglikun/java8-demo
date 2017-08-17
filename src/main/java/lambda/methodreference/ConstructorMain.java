package lambda.methodreference;

import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * Created by yanglikun on 2017/2/26.
 */
public class ConstructorMain {

    public ConstructorMain() {
        System.out.println("没有参数");
    }

    public ConstructorMain(String oneArg) {
        System.out.println(oneArg);
    }

    public ConstructorMain(String oneArg, String twoArg) {
        System.out.println(oneArg + ":" + twoArg);
    }

    public static void main(String[] args) {
        Supplier<ConstructorMain> noArg = ConstructorMain::new;
        Function<String, ConstructorMain> oneArg = ConstructorMain::new;
        BiFunction<String, String, ConstructorMain> twoArg = ConstructorMain::new;
    }


}
