package ua.edu.ucu.collections;

import ua.edu.ucu.collections.immutable.ImmutableLinkedList;

import java.util.EmptyStackException;

public class Stack {
    ImmutableLinkedList elements;

    public Stack() {
        elements = new ImmutableLinkedList();
    }

    public Stack(Object[] arr) {
        elements = new ImmutableLinkedList(arr);
    }

    public Object peek() throws EmptyStackException {
        if (elements.isEmpty()) throw new EmptyStackException();

        return elements.get(0);
    }

    public Object pop() throws EmptyStackException {
        if (elements.isEmpty()) throw new EmptyStackException();

        Object retVal = elements.get(0);
        elements = elements.remove(0);
        return retVal;
    }

    public void push(Object val) {
        elements = elements.addFirst(val);
    }
}
