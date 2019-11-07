package ua.edu.ucu.collections;

import org.junit.Test;
import static org.junit.Assert.*;

public class QueueTest {
    private Object[] input_arr = {"some", "values", "here"};

    @Test
    public void testPeek() {
        Queue tested = new Queue(input_arr);
        try {
            assertEquals(input_arr[0], tested.peek());
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void testDequeue() {
        Queue tested = new Queue(input_arr);

        try {
            tested.dequeue();
            tested.dequeue();
        } catch (Exception e) {
            fail();
        }
        Object[] expected = {"some"};
        System.out.print(tested.elements);
        assertArrayEquals(expected, tested.elements.toArray());
    }

    @Test
    public void testEnqueue() {
        Queue tested = new Queue();

        for (Object o: input_arr) {
            tested.enqueue(o);
        }

        assertArrayEquals(input_arr, tested.elements.toArray());
    }
}
