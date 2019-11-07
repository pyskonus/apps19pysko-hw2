package ua.edu.ucu.collections;

import org.junit.Test;
import static org.junit.Assert.*;

public class StackTest {
    private Object[] input_arr = {1, true, "Tut buv Romaniuk", 'k'};

    @Test
    public void testPeek() {
        Stack s = new Stack();

        for (Object o: input_arr) {
            s.push(o);
            assertEquals(o, s.peek());
        }
    }

    @Test
    public void testPop() {
        Stack s = new Stack(input_arr);

        s.push(9);
        assertEquals(9, s.pop());
        assertArrayEquals(input_arr, s.elements.toArray());
        for (Object o : input_arr) {    // remember that the array goes into stack in reverse order
            assertEquals(o, s.pop());
        }
        assertTrue(s.elements.isEmpty());
    }

    @Test
    public void testPush() {
        Stack s = new Stack();
        for (int i = input_arr.length - 1; i >= 0; i--) {
            s.push(input_arr[i]);
        }
        assertArrayEquals(input_arr, s.elements.toArray());
    }
}
