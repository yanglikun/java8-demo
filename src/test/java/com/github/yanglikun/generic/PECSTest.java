package com.github.yanglikun.generic;

import generic.variant.MyStack;
import generic.variant.SubType;
import generic.variant.SuperType;
import org.junit.Test;

import java.util.Collection;

/**
 * @author yanglikun
 */
public class PECSTest {

    @Test
    public void push() {
        MyStack<SuperType> myStack = new MyStack<>();
        SubType subType = new SubType();
        myStack.push(subType);//这个不报错

        Iterable<SubType> subTypeIterable = null;
        //incompatible types: java.lang.Iterable<generic.variant.SubType>
        // cannot be converted to java.lang.Iterable<generic.variant.SuperType>
        //myStack.pushAll(subTypeIterable); //!这个会报错

        myStack.pushAllWithExtends(subTypeIterable); //协变
    }

    @Test
    public void pop() {
        MyStack<SubType> myStack = new MyStack<>();
        Collection<SuperType> superTypeCollection = null;
        //incompatible types: java.util.Collection<generic.variant.SuperType> cannot be converted to
        // java.util.Collection<generic.variant.SubType>
        //myStack.popAll(superTypeCollection); //!这个会报错
        myStack.popAllWithSuper(superTypeCollection); //可以看作一个逆变

    }

}
