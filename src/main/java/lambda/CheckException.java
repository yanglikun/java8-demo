package lambda;

import java.io.IOException;
import java.util.function.Consumer;

/**
 * Created by yanglikun on 2017/2/25.
 */
public class CheckException {

    public static void main(String[] args) {
        Consumer<MyFunction> con= myFunction -> {
            try {
                myFunction.test("aa");
            } catch (IOException e) {
                e.printStackTrace();
            }
        };
    }



    public static void save(MyFunction ce){
    };

    public interface MyFunction {
        void test(String s) throws IOException;
    }

}
