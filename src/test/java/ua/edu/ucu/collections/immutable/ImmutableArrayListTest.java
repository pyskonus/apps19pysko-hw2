package ua.edu.ucu.collections.immutable;

import org.junit.Ignore;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;

public class ImmutableArrayListTest {

    private Object[] input_arr = {123, "Tut buv Romaniuk", true, 56.7};


    @Test
    public void testConstructor1() {
        // create a new instance via the simple constructor
        ImmutableArrayList newList = new ImmutableArrayList();

        // compare the expected length (0) and the actual one
        assertEquals(0, newList.els.length);
    }

    @Test
    public void testConstructor2() {
        // empty
        Object[] emptyArr = {};
        ImmutableArrayList emptyList = new ImmutableArrayList(emptyArr);
        assertArrayEquals(emptyArr, emptyList.els);

        // one element
        Object[] oneEl = {'k'};
        ImmutableArrayList oneElList = new ImmutableArrayList(oneEl);
        assertArrayEquals(oneEl, oneElList.els);

        // n elements
        ImmutableArrayList nElsList = new ImmutableArrayList(input_arr);
        assertArrayEquals(input_arr, nElsList.els);
    }


    @Test
    public void testAdd1() {
        // create IAL instance
        ImmutableArrayList initial = new ImmutableArrayList(input_arr);

        // add the new value
        Object added_val = "I love OOP";
        ImmutableArrayList after_adding = initial.add(added_val);

        // compare
        Object[] exp = Arrays.copyOf(input_arr, input_arr.length + 1);
        exp[4] = added_val;
        assertArrayEquals(exp, after_adding.els);

        // check if the immutability is intact
        assertNotSame(after_adding, initial);
    }


    @Test
    public void testAdd2() {
        // set up the changed array
        Object[] new_input = new Object[input_arr.length + 1];
        Object val = 900;
        int ind = 2;
        System.arraycopy(input_arr, 0, new_input, 0, ind);
        new_input[ind] = val;
        System.arraycopy(input_arr, ind, new_input, ind + 1, new_input.length - ind - 1);

        // create the instance of IAL
        ImmutableArrayList initial = new ImmutableArrayList(input_arr);

        // compare
        assertArrayEquals(new_input, initial.add(ind, val).els);
    }

    @Test
    public void testExceptionsInAdd2() {
        ImmutableArrayList lst = new ImmutableArrayList(input_arr);
        boolean exception_occurred = false;
        try {
            lst.add(input_arr.length + 1, "Tesseract");
        }
        catch (Exception e) {
            exception_occurred = true;
            assertTrue(e instanceof IndexOutOfBoundsException);
        }
        assertTrue(exception_occurred);
    }


    @Test
    public void testAddAll1() {
        // create added and expected arrays
        Object[] added = {false, "first", 2};
        Object[] expected = Arrays.copyOf(input_arr, input_arr.length + added.length);
        System.arraycopy(added, 0, expected, input_arr.length, added.length);

        // create IAL instance
        ImmutableArrayList sample = new ImmutableArrayList(input_arr);

        assertArrayEquals(expected, sample.addAll(added).els);
    }


    @Test
    public void testAddAll2() {
        // create inserted and expected arrays
        Object[] inserted = {"some", "values", "here"};
        Object[] expected = Arrays.copyOf(inserted, input_arr.length + inserted.length);
        System.arraycopy(input_arr, 0, expected, inserted.length, input_arr.length);

        // call the tested method and compare
        ImmutableArrayList initial = new ImmutableArrayList(input_arr);
        assertArrayEquals(expected, initial.addAll(0, inserted).els);
    }

    @Test
    public void testExceptionsInAddAll2() {
        // create IAL instance and sample inserted array
        ImmutableArrayList sample = new ImmutableArrayList(input_arr);
        Object[] sampleArr = {"some", "values", "here"};

        boolean exceptionsOccurred = false;

        try {
            sample.addAll(-2, sampleArr);
        } catch (Exception e1) {
            try {sample.addAll(input_arr.length + sampleArr.length + 1, sampleArr);}
            catch (Exception e2) {
                exceptionsOccurred = true;
                assertTrue((e1 instanceof IndexOutOfBoundsException) && (e2 instanceof IndexOutOfBoundsException));
            }
        }
        assertTrue(exceptionsOccurred);
    }


    @Test
    public void testGet() {
        // make some preparation
        ImmutableArrayList sample = new ImmutableArrayList(input_arr);
        int index = input_arr.length / 2;   // always integer

        // call the tested method and compare
        assertEquals(input_arr[index], sample.get(index));
    }

    @Test
    public void testExceptionsInGet() {
        ImmutableArrayList sample = new ImmutableArrayList(input_arr);
        boolean exceptionsOccurred = false;
        try {
            sample.get(-10);
        } catch (Exception e1) {
            try {
                sample.get(input_arr.length + 20);
            } catch (Exception e2) {
                assertTrue((e1 instanceof IndexOutOfBoundsException) && (e2 instanceof IndexOutOfBoundsException));
                exceptionsOccurred = true;
            }
        }
        assertTrue(exceptionsOccurred);
    }


    @Test
    public void testRemove() {
        // prepare
        ImmutableArrayList sample = new ImmutableArrayList(input_arr);
        int index = input_arr.length - 2;  // assume the array is long enough
        Object[] expected = new Object[input_arr.length - 1];
        System.arraycopy(sample.els, 0, expected, 0, index);
        System.arraycopy(sample.els, index + 1, expected, index, sample.els.length -1 - index);

        // compare
        System.out.println(sample);
        System.out.println(new ImmutableArrayList(expected));
        assertArrayEquals(expected, sample.remove(index).els);
    }

    @Test
    public void testExceptionsInRemove() {
        ImmutableArrayList sample = new ImmutableArrayList(input_arr);
        boolean exceptionsOccurred = false;
        try {
            sample.remove(-10);
        } catch (Exception e1) {
            try {
                sample.remove(input_arr.length + 20);
            } catch (Exception e2) {
                assertTrue((e1 instanceof IndexOutOfBoundsException) && (e2 instanceof IndexOutOfBoundsException));
                exceptionsOccurred = true;
            }
        }
        assertTrue(exceptionsOccurred);
    }


    @Test
    public void testSet() {
        //prepare
        ImmutableArrayList sample = new ImmutableArrayList(input_arr);
        int index = input_arr.length - 1;
        Object val = 9;
        Object[] expected = Arrays.copyOf(input_arr, input_arr.length);
        expected[index] = val;

        // compare
        assertArrayEquals(expected, sample.set(index, val).els);
    }

    @Test
    public void testExceptionsInSet() {
        ImmutableArrayList sample = new ImmutableArrayList(input_arr);
        boolean exceptionsOccurred = false;
        try {
            sample.set(-10, 'k');
        } catch (Exception e1) {
            try {
                sample.set(input_arr.length + 20, 'k');
            } catch (Exception e2) {
                assertTrue((e1 instanceof IndexOutOfBoundsException) && (e2 instanceof IndexOutOfBoundsException));
                exceptionsOccurred = true;
            }
        }
        assertTrue(exceptionsOccurred);
    }


    @Test
    public void testIndexOf() {
        ImmutableArrayList sample = new ImmutableArrayList(input_arr);
        int index = input_arr.length - 2;

        assertEquals(index, sample.indexOf(sample.els[index]));
        assertEquals(-1, sample.indexOf("Takoho nema v spysku"));
    }


    @Test
    public void testSize() {
        ImmutableArrayList empty = new ImmutableArrayList();
        ImmutableArrayList nonempty = new ImmutableArrayList(input_arr);

        assertEquals(0, empty.size());
        assertEquals(input_arr.length, nonempty.size());
    }


    @Test
    public void testClear() {
        ImmutableArrayList sample = new ImmutableArrayList(input_arr);
        assertEquals(0, sample.clear().size());
    }


    @Test
    public void testIsEmpty() {
        ImmutableArrayList empty = new ImmutableArrayList();
        ImmutableArrayList nonempty = new ImmutableArrayList(input_arr);

        assertTrue(empty.isEmpty());
        assertFalse(nonempty.isEmpty());
    }


    @Test
    public void testToArray() {
        ImmutableArrayList sample = new ImmutableArrayList(input_arr);

        assertArrayEquals(input_arr, sample.toArray());
    }


    @Test
    public void testToString() {
        ImmutableArrayList empty = new ImmutableArrayList();
        ImmutableArrayList nonempty = new ImmutableArrayList(input_arr);

        String expected = "";
        if (input_arr.length > 0) {
            expected = input_arr[0].toString();
            for (int i = 1; i < input_arr.length; i++) expected = expected.concat(',' + input_arr[i].toString());
        }

        assertEquals("", empty.toString());
        assertEquals(expected, nonempty.toString());
    }
}
