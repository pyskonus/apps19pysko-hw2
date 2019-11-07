package ua.edu.ucu.collections.immutable;

import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;

public class ImmutableLinkedListTest {

    private Object[] input_arr = {0, true, 2.0, "three"};
    private ImmutableLinkedList empty = new ImmutableLinkedList();
    private ImmutableLinkedList nonempty = new ImmutableLinkedList(input_arr);


    @Test
    public void testCopy() {
        ImmutableLinkedList ecpy = empty.copy();
        ImmutableLinkedList necpy = nonempty.copy();

        assertNull(ecpy.start);
        assertEquals(0, ecpy.length);
        ImmutableLinkedList.Node cur1 = nonempty.start;
        ImmutableLinkedList.Node cur2 = necpy.start;

        while (cur1 != null) {
            assertEquals(cur1.value, cur2.value);
            cur1 = cur1.next;
            cur2 = cur2.next;
        }
        assertNull(cur2);
        assertEquals(nonempty.length, necpy.length);
    }

    @Test
    public void testAddFirst() {
        Object inserted = 9;
        Object[] exp_empty = {inserted};
        Object[] expected = new Object[input_arr.length + 1];
        expected[0] = inserted;
        System.arraycopy(input_arr, 0, expected, 1, input_arr.length);

        assertArrayEquals(exp_empty, empty.addFirst(inserted).toArray());
        assertArrayEquals(expected, nonempty.addFirst(inserted).toArray());
    }

    @Test
    public void testAddLast() {
        Object inserted = 9;
        Object[] exp_empty = {9};
        Object[] expected = Arrays.copyOf(input_arr, input_arr.length + 1);
        expected[expected.length - 1] = inserted;

        assertArrayEquals(exp_empty, empty.addLast(inserted).toArray());
        assertArrayEquals(expected, nonempty.addLast(inserted).toArray());
    }

    @Test
    public void testAdd1() {
        Object inserted = 9;
        Object[] exp_empty = {9};
        Object[] expected = Arrays.copyOf(input_arr, input_arr.length + 1);
        expected[expected.length - 1] = inserted;

        assertArrayEquals(exp_empty, empty.add(inserted).toArray());
        assertArrayEquals(expected, nonempty.add(inserted).toArray());
    }

    @Test
    public void testExceptionsInAdd2() {
        boolean bothExceptions = false;

        try {
            ImmutableLinkedList uncreatable = empty.add(-4, 7);
        } catch(Exception e1) {
            try {
                ImmutableLinkedList uninitializable = nonempty.add(nonempty.length + 1, 7);
            } catch(Exception e2) {
                assertTrue(e1 instanceof IndexOutOfBoundsException);
                assertTrue(e2 instanceof IndexOutOfBoundsException);
                bothExceptions = true;
            }
        }
        assertTrue(bothExceptions);
    }

    @Test
    public void testAddAll() {
        Object[] inserted = {1, 2, 3, 'f', 'f', 's', false, false, true};

        Object[] expected1 = {1, 2, 3, 'f', 'f', 's', false, false, true, 0, true, 2.0, "three"};
        Object[] expected2 = {0, true, 1, 2, 3, 'f', 'f', 's', false, false, true, 2.0, "three"};
        Object[] expected3 = {0, true, 2.0, "three", 1, 2, 3, 'f', 'f', 's', false, false, true};

        ImmutableLinkedList result0 = empty.addAll(0, inserted);
        ImmutableLinkedList result1 = nonempty.addAll(0, inserted);
        ImmutableLinkedList result2 = nonempty.addAll(2, inserted);
        ImmutableLinkedList result3 = nonempty.addAll(4, inserted);

        assertArrayEquals(inserted, result0.toArray());
        assertArrayEquals(expected1, result1.toArray());
        assertArrayEquals(expected2, result2.toArray());
        assertArrayEquals(expected3, result3.toArray());

        assertEquals(inserted.length, result0.length);
        int expected_l = inserted.length + nonempty.length;
        assertEquals(expected_l, result1.length);
        assertEquals(expected_l, result2.length);
        assertEquals(expected_l, result3.length);
    }

    @Test
    public void testGet() {
        assertEquals(input_arr[1], nonempty.get(1));
    }

    @Test
    public void testRemove() {
        Object[] expected = {0, 2.0, "three"};
        assertArrayEquals(expected, nonempty.remove(1).toArray());
    }

    @Test
    public void testSet() {
        Object[] expected = {3, true, 2.0, "three"};
        assertArrayEquals(expected, nonempty.set(0, 3).toArray());
    }

    @Test
    public void testIndexOf() {
        assertEquals(-1, nonempty.indexOf(100500));
        assertEquals(3, nonempty.indexOf("three"));
    }

    @Test
    public void testSize() {
        assertEquals(input_arr.length, nonempty.length);
    }

    @Test
    public void testClear() {
        ImmutableLinkedList obtained = nonempty.clear();
        assertNull(obtained.start);
        assertNull(obtained.end);
        assertEquals(0, obtained.length);
    }

    @Test
    public void testIsEmpty() {
        assertTrue(empty.isEmpty());
        assertFalse(nonempty.isEmpty());
    }

    @Test
    public void testToArray() {
        Object[] temp = {};
        assertArrayEquals(temp, empty.toArray());
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
}
