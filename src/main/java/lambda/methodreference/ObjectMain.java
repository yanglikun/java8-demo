package lambda.methodreference;

/**
 * Created by yanglikun on 2017/2/26.
 */
public class ObjectMain {

    public static void main(String[] args) {
        Apple apple = new Apple("red");
        Runnable run = apple::toString;
        run.run();
        Apple appleGreen = new Apple("green");
        Runnable run1 = appleGreen::toString;
        run1.run();

        MyFunction fun = Apple::test;
        fun.fun(apple, "a", 3);
    }


    static class Apple {
        private String color;

        public Apple(String color) {
            this.color = color;
        }

        public void test(String str, Integer val) {
            System.out.println(str + ":" + val);
        }


        @Override
        public String toString() {
            final StringBuilder sb = new StringBuilder("Apple{");
            sb.append("color='").append(color).append('\'');
            sb.append('}');
            System.out.println(sb.toString());
            return sb.toString();
        }
    }


    public interface MyFunction {
        public void fun(Apple apple, String str, Integer val);
    }

}
