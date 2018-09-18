package com.pan.base.datastructure.list;

public interface MyList<T> extends Iterable{

    void add(T object);

    void delete(int pos);

    T get(int pos);

    int size();

}
