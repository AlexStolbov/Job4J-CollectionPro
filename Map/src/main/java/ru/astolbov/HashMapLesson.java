package ru.astolbov;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class HashMapLesson<K, V> implements Iterable<V>{

    private Node<K, V>[] table;
    int count;

    public HashMapLesson() {
        this.table = (Node<K, V>[]) new Node[1 << 2];
    }

    public boolean insert(K key, V value) {
        if (count >= this.table.length) {
            growSize();
        }
        int address = getAddress(key);
        Node<K, V> element = this.table[address];
        boolean res = false;
        if (element == null) {
            this.table[address] = new Node(key, value);
            count++;
            res = true;
        }
        return res;
    }

    public V get(K key) {
        Node<K, V> element = this.table[getAddress(key)];
        V res = null;
        if (element != null) {
            res = element.value;
        }
        return res;
    }

    public boolean delete(K key) {
        int address = getAddress(key);
        Node<K, V> element = this.table[address];
        boolean res = false;
        if (element != null) {
            this.table[address] = null;
            res = true;
        }
        return res;
    }

    private int getAddress(K e) {
        return e.hashCode() & (this.table.length - 1);
    }

    private void growSize() {
        Node<K, V>[] oldContainer = this.table;
        int newSize = this.table.length << 1;
        this.table = (Node<K, V>[]) new Node[newSize];
        for (int i = 0; i < oldContainer.length; i++) {
            Node<K, V> current = oldContainer[i];
            if (current != null) {
                this.table[getAddress(current.key)] = current;
            }
        }
    }

    static class Node<K, V> {
        final K key;
        V value;

        Node(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    @Override
    public Iterator<V> iterator() {
        return new IteratorLesson();
    }

    private class IteratorLesson implements Iterator {
        boolean doNext;
        int pos;
        int lastPos;

        public IteratorLesson() {
            this.pos = 0;
            this.lastPos = -1;
        }

        @Override
        public boolean hasNext() {
            while (pos < table.length && table[pos] == null) {
                pos++;
            }
            return pos < table.length;
        }

        @Override
        public Object next() {
            if (hasNext()) {
                doNext = true;
                this.lastPos = this.pos;
                return table[this.pos++].value;
            } else {
                throw new NoSuchElementException();
            }
        }

        @Override
        public void remove() {
            if (lastPos == -1) {
                throw new IllegalStateException();
            } else {
                table[lastPos] = null;
                lastPos = -1;
            }
        }
    }

}
