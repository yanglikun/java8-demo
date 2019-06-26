package generic.variant;

import java.util.Collection;

/**
 * @author yanglikun
 */
public class MyStack<E> {

    public void push(E e) {

    }

    public E pop() {
        return null;
    }

    public void pushAll(Iterable<E> src) {

    }

    public void popAll(Collection<E> dst) {
        while (isEmpty()) {
            dst.add(pop());
        }
    }

    public void pushAllWithExtends(Iterable<? extends E> src) {

    }

    public void popAllWithSuper(Collection<? super E> dst) {
        while (isEmpty()) {
            dst.add(pop());
        }
    }

    public Boolean isEmpty() {
        return true;
    }


}
