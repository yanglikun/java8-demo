package lambda;

/**
 * Created by yanglikun on 2017/2/23.
 */
public class EffectivelyFinal {

    public static void main(String[] args) {

        String outer = "外部变量不需要加final";

        Runnable runnable = () -> System.out.println("外部变量:" + outer);

//        outer = "这里不能再赋值，否则上面会报错";
    }

}
