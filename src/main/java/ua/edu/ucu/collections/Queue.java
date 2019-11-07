package ua.edu.ucu.collections;

import ua.edu.ucu.collections.immutable.ImmutableLinkedList;

public class Queue {
    ImmutableLinkedList elements;

    public Queue() {
        elements = new ImmutableLinkedList();
    }

    public Queue(Object[] arr) {
        elements = new ImmutableLinkedList(arr);
    }

    public Object peek() throws Exception {
        if (elements.isEmpty()) throw new Exception();

        return elements.get(0);
    }

    public Object dequeue() throws Exception {
        if (elements.isEmpty()) throw new Exception();

        Object retVal = elements.get(elements.size() - 1);
        elements = elements.remove(elements.size() - 1);
        return retVal;
    }

    public void enqueue(Object e) {
        elements = elements.addLast(e);
    }
}
