package generic.bridgemethod;

/**
 * @author yanglikun
 */
public class MyNode extends Node<Integer> {
    @Override
    public void setData(Integer data) {
        System.out.println("MyNode.setData");
    }
}
