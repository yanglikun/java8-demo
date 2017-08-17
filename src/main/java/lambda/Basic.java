package lambda;

/**
 * Created by yanglikun on 2017/2/23.
 */
public class Basic {
    public static void main(String[] args) {

        //lambda之前，其实我们是要传递一个行为(动作，任务)给thread
        //但是这里却要传递一个匿名内部类
        new Thread(new Runnable() {
            public void run() {
                System.out.println("执行任务..");
            }
        });

        //lambda的操作，传递一个行为
        new Thread(() -> System.out.println("执行任务"));

    }
}
