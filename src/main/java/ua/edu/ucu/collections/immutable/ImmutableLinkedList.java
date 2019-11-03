package ua.edu.ucu.collections.immutable;

//import com.sun.java.util.jar.pack.ConstantPool;

import java.util.Arrays;

public class ImmutableLinkedList implements ImmutableList{
    private Node start;
    private int length;
    private Node end;


    public ImmutableLinkedList() {
        start = null;
        length = 0;
        end = null;
    }

    public ImmutableLinkedList(Object[] input) {
        if (input.length > 0) {
            end = new Node(input[0], null);
            start = end;
            length ++;
            for (int i = 1; i < input.length; i++) {
                end.next = new Node(input[i], null);
                end = end.next;
                length ++;
            }
        } else {
            start = null;
            length = 0;
            end = null;
        }
    }


    public ImmutableLinkedList addFirst(Object e) {
        return this.add(0, e);
    }

    public ImmutableLinkedList addLast(Object e) {
        return this.add(length, e);
    }

    @Override
    public ImmutableLinkedList add(Object e) {
        return this.addLast(e);
    }

    @Override
    public ImmutableLinkedList add(int index, Object e) throws IndexOutOfBoundsException {
        if (index < 0 || index > length) throw new IndexOutOfBoundsException();

        Object[] input = {e};

        return this.addAll(index, input);
    }

    @Override
    public ImmutableLinkedList addAll(Object[] c) {
        return this.addAll(length, c);
    }

    @Override
    public ImmutableLinkedList addAll(int index, Object[] c) {
        if (index < 0 || index > length) throw new IndexOutOfBoundsException();

        Object[] newArr = new Object[length + c.length];
        Object[] oldArr  = this.toArray();

        System.arraycopy(oldArr, 0, newArr, 0, index);
        System.arraycopy(c, 0, newArr, index, c.length);
        System.arraycopy(oldArr, index, newArr, index + c.length, length - index);

        return new ImmutableLinkedList(newArr);
    }

    @Override
    public Object get(int index) throws IndexOutOfBoundsException {
        if (index < 0 || index >= length) throw new IndexOutOfBoundsException();
        int i = 0;
        Node current = start;
        while (i != index) {
            i ++;
            current = current.next;
        }
        return current.value;
    }

    @Override
    public ImmutableList remove(int index) {
        if (index < 0 || index >= length) throw new IndexOutOfBoundsException();

        Object[] new_arr = new Object[length - 1];

        Node current = start;
        int i = 0;
        while (current != null) {
            if (i != index) new_arr[i] = current.value;
            current = current.next;
            i++;
        }

        return new ImmutableLinkedList(new_arr);
    }

    @Override
    public ImmutableList set(int index, Object e) throws IndexOutOfBoundsException {
        if (index < 0 || index >= length) throw new IndexOutOfBoundsException();

        Object[] new_arr = Arrays.copyOf(this.toArray(), length);

        new_arr[index] = e;

        return new ImmutableLinkedList(new_arr);
    }

    @Override
    public int indexOf(Object e) {
        Node current = start;
        int i = 0;
        while (current != null) {
            if (current.value == e) return i;
            current = current.next;
            i ++;
        }
        return -1;
    }

    @Override
    public int size() {
        return length;
    }

    @Override
    public ImmutableLinkedList clear() {
        return new ImmutableLinkedList();
    }

    @Override
    public boolean isEmpty() {
        return (length == 0);
    }

    @Override
    public Object[] toArray() {
        Object[] result = new Object[length];
        Node current = start;
        int i = 0;
        while (current != null) {
            result[i] = current.value;
            current = current.next;
            i ++;
        }
        return result;
    }

    @Override
    public String toString() {
        if (length == 0) return "";

        String result = start.value.toString();
        Node curnode = start.next;
        while (curnode != null) {
            result = result.concat(',' + curnode.value.toString());
            curnode = curnode.next;
        }
        return result;
    }

    private static class Node {
        Object value;
        Node next;

        public Node(Object value, Node next) {
            this.value = value;
            this.next = next;
        }
    }
}
