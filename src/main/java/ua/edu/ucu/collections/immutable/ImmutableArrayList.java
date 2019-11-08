package ua.edu.ucu.collections.immutable;

import java.util.Arrays;

public class ImmutableArrayList implements ImmutableList {
    Object[] els;   // package-private access needed

    public ImmutableArrayList() {
        this.els = new Object[0];
    }

    public ImmutableArrayList(Object[] els) {
        this.els = els;
    }

    @Override
    public ImmutableArrayList add(Object e) {
        Object[] temporary = Arrays.copyOf(this.els, this.els.length + 1);
        temporary[this.els.length] = e;
        return new ImmutableArrayList(temporary);
    }

    @Override
    public ImmutableArrayList add(int index, Object e)
            throws IndexOutOfBoundsException {
        // throw an exception if the index is invalid
        if (index < 0 || index > els.length) {
            throw new IndexOutOfBoundsException();
        }

        // create a new array with one more cell
        Object[] newArray = new Object[els.length + 1];

        // copy the first part of the source array (0-index)
        System.arraycopy(els, 0, newArray, 0, index);
        // paste the given Object
        newArray[index] = e;
        // copy the rest
        System.arraycopy(els, index, newArray, index + 1, newArray.length - index - 1);

        return new ImmutableArrayList(newArray);
    }

    @Override
    public ImmutableArrayList addAll(Object[] c) {
        // create new input array
        Object[] new_inp = new Object[els.length + c.length];

        // copy the contents to corresponding positions
        System.arraycopy(els, 0, new_inp, 0, els.length);
        System.arraycopy(c, 0, new_inp, els.length, c.length);

        // create IAL instance
        return new ImmutableArrayList(new_inp);
    }

    @Override
    public ImmutableArrayList addAll(int index, Object[] c) throws IndexOutOfBoundsException {
        // check if the index is valid
        if (index < 0 || index > els.length) throw new IndexOutOfBoundsException();

        // create new input array
        Object[] newArr = new Object[els.length + c.length];

        // copy the contents of the old array and the new on to it
        System.arraycopy(els, 0, newArr, 0, index);
        System.arraycopy(c, 0, newArr, index, c.length);
        System.arraycopy(els, index, newArr, index + c.length, els.length - index);

        return new ImmutableArrayList(newArr);
    }

    @Override
    public Object get(int index) {
        if (index < 0 || index >= els.length) throw new IndexOutOfBoundsException();

        return els[index];
    }

    @Override
    public ImmutableArrayList remove(int index) {
        if (index < 0 || index >= els.length) throw new IndexOutOfBoundsException();

        Object[] new_arr = new Object[els.length - 1];
        System.arraycopy(els, 0, new_arr, 0, index);
        System.arraycopy(els, index + 1, new_arr, index, new_arr.length - index);

        return new ImmutableArrayList(new_arr);
    }

    @Override
    public ImmutableArrayList set(int index, Object e) {
        if (index < 0 || index >= els.length) throw new IndexOutOfBoundsException();

        Object[] new_arr = Arrays.copyOf(els, els.length);
        new_arr[index] = e;

        return new ImmutableArrayList(new_arr);
    }

    @Override
    public int indexOf(Object e) {
        for (int i = 0; i < els.length; i ++) {
            if (els[i] == e) return i;
        }
        return -1;
    }

    @Override
    public int size() {
        return els.length;
    }

    @Override
    public ImmutableArrayList clear() {
        return new ImmutableArrayList();
    }

    @Override
    public boolean isEmpty() {
        return els.length == 0;
    }

    @Override
    public Object[] toArray() {
        return Arrays.copyOf(els, els.length);
    }

    @Override
    public String toString() {
        String result = els.length > 0 ? els[0].toString() : "";
        for (int i = 1; i < els.length; i++)
            result = result.concat(',' + els[i].toString());
        return result;
    }
}
