package generic;

import java.io.Closeable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Tony
 */
public class BoundedTypeParameterDemo<E extends Closeable> {

    private String s;

    private E ele;

    public void set(E e) {
        //do something
    }

    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        GenericReflectionTest.printClass(list.getClass());
    }

}
