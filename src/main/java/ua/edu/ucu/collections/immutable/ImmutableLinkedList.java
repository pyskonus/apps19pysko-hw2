package ua.edu.ucu.collections.immutable;


import java.util.Arrays;

public class ImmutableLinkedList implements ImmutableList{
    Node start;
    int length;
    Node end;


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

    // helping function
    ImmutableLinkedList copy() {
        // returns a copy of self, using properties of linked list
        if (length == 0) return new ImmutableLinkedList();
        // initialise the return ILL
        ImmutableLinkedList result = new ImmutableLinkedList();
        // initialize the start and end nodes
        result.end = new Node(start.value, null);
        result.start = result.end;
        result.length++;
        Node current = start.next;
        // copy all the other nodes
        while (current != null) {
            result.end.next = new Node(current.value, null);
            result.end = result.end.next;
            result.length++;
            current = current.next;
        }
        return result;
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

        Node minusFirst = new Node(null, null);  // this is element №-1 of the returned list. Just for convenience
        Node curFinish = minusFirst;
        Node current = start;
        int i = 0;
        while (i != index) {
            curFinish.next = new Node(current.value, null);
            curFinish = curFinish.next;
            current = current.next;
            i++;
        }
        // at this point, current contains the node at position index, so it's time to insert elements from the array
        for (Object o : c) {
            curFinish.next = new Node(o, null);
            curFinish = curFinish.next;
        }
        // and here we continue inserting nodes from this
        while (current != null) {
            curFinish.next = new Node(current.value, null);
            curFinish = curFinish.next;
            current = current.next;
        }

        ImmutableLinkedList result = new ImmutableLinkedList();
        result.start = minusFirst.next;
        result.end = curFinish;
        result.length = length + c.length;
        return result;
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
    public ImmutableLinkedList remove(int index) {
        if (index < 0 || index >= length) throw new IndexOutOfBoundsException();

        // initialize the returned linked list, but not yet as ILL, just sequence of nodes
        Node new_start = new Node(null, null);
        Node new_end = new_start;
        // now we can easily add new nodes to it. The node, created above, will be deleted later.

        int i = 0;
        Node current = start;
        while (current != null) {
            if (i != index) {
                new_end.next = new Node(current.value, null);
                new_end = new_end.next;
            }
            i++;
            current = current.next;
        }

        ImmutableLinkedList result = new ImmutableLinkedList();
        result.start = new_start.next;  // because the first node was created just for convenience
        result.end = new_end;
        result.length = length - 1;
        return result;
    }

    @Override
    public ImmutableLinkedList set(int index, Object e) throws IndexOutOfBoundsException {
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

    static class Node {
        Object value;
        Node next;

        Node(Object value, Node next) {
            this.value = value;
            this.next = next;
        }
    }
}
