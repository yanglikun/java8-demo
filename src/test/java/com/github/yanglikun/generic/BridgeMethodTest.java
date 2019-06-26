package com.github.yanglikun.generic;

import generic.bridgemethod.MyNode;
import generic.bridgemethod.Node;
import org.junit.Test;

/**
 * @author yanglikun
 */
public class BridgeMethodTest {

    @Test
    public void test() {
        Node<Integer> myNode = new MyNode();
        myNode.setData(3); //输出 Node.setData 还是 Node.setData?
    }

}
