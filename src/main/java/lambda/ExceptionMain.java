package lambda;

/**
 * Created by yanglikun on 2017/3/25.
 */
public class ExceptionMain {

    public static void main(String[] args) {
        Runnable run = () -> {
            System.out.println("aa");
            System.out.println("aa");
            int i = 1 / 0;
            System.out.println("aa");
        };
        run.run();
    }


}
