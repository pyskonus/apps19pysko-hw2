package ua.edu.ucu.collections.immutable;

import org.junit.Test;
import static org.junit.Assert.*;

public class ImmutableLinkedListTest {

    private Object[] input_arr = {0, true, 2.0, "three"};
    private ImmutableLinkedList empty = new ImmutableLinkedList();
    private ImmutableLinkedList nonempty = new ImmutableLinkedList(input_arr);

    @Test
    public void testAddFirst() {

    }

    @Test
    public void testGet() {
        assertEquals(true, nonempty.get(1));
    }

    @Test
    public void testSet() {
        Object[] expected = {3, true, 2.0, "three"};
        assertArrayEquals(expected, nonempty.set(0, 3).toArray());
    }
    
//    @Test
//    public void testRemove() {
//        Object[] expected = {0, 2.0, "three"};
//        assertArrayEquals(expected, nonempty.remove(1).toArray());
//    }

    @Test
    public void testet() {
    }

    @Test
    public void testToArray() {
        assertArrayEquals(input_arr, nonempty.toArray());
    }

    @Test
    public void testToString() {
        // set up
        String expected = input_arr[0].toString();
        for (int i = 1; i < input_arr.length; i++) expected = expected.concat(',' + input_arr[i].toString());
        // check
        assertEquals("", empty.toString());
        assertEquals(expected, nonempty.toString());
    }

    @Test
    public void testIndexOf() {
        assertEquals(-1, nonempty.indexOf(100500));
        assertEquals(3, nonempty.indexOf("three"));
    }
}
