package thread;

import java.util.concurrent.TimeUnit;

/**
 * <p>Description: </p>
 */
public class ThreadTest {
    volatile static int data = 0;
    static int flag = 0;

    public static void main(String[] args) throws Exception {

        new Thread(() -> {
            while (flag != 1) {
                continue;
            }
            System.out.println("flag==1:" + data);
        }).start();
        TimeUnit.SECONDS.sleep(1);
        new Thread(() -> {
            data = 1;
            flag = 1;
            double a = 3.0;
            double b = 4;
            System.out.println(a / b);
        }).start();
        TimeUnit.SECONDS.sleep(1);

        System.out.println("end...flag:"+flag);
        TimeUnit.HOURS.sleep(1);
    }

}
