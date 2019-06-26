package generic.bridgemethod;

/**
 * @author yanglikun
 */
public class NodeObject {

    private Object data;

    public void setData(Object data) {
        System.out.println("Node.setData");
        this.data = data;
    }
}
