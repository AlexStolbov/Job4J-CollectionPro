package ru.astolbov;

public interface SimpleContainer<E> extends Iterable<E> {

    void add(E value);

    E get(int index);

    int getSize();
}
