package com.pan.base.datastructure.list;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class MyArrayList<T> implements MyList<T> {

    private Object[] elements;
    private int pos;

    public MyArrayList() {
        elements = new Object[10];
    }
    @Override
    public void add(T object) {

        if (pos == (size() - 1)) {
            int newCapaCity = elements.length + (elements.length >> 1);
            elements = Arrays.copyOf(elements, newCapaCity);
        }
        elements[pos++] = object;
    }

    @Override
    public void delete(int pos) {

    }

    @Override
    public T get(int pos) {
        return (T) elements[pos];
    }

    @Override
    public int size() {
        return elements.length;
    }

    @Override
    public Iterator iterator() {
        return new MyIterator();
    }

    private class MyIterator implements Iterator<T> {

        int cursor;

        @Override
        public boolean hasNext() {
            return cursor!= pos;
        }

        @Override
        public T next() {
            int i = cursor;
            if (i > MyArrayList.this.size()) {
                throw new NoSuchElementException();
            }
            Object[] elements = MyArrayList.this.elements;
            cursor = i + 1;
            return (T) elements[i];
        }

        @Override
        public void remove() {

        }

    }
}
