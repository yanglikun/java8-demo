package generic.bridgemethod;

/**
 * @author yanglikun
 */
public class Node<E> {

    private E data;

    public void setData(E data) {
        System.out.println("Node.setData");
        this.data = data;
    }
}
