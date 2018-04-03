package ru.astolbov;

public class HashMapLesson<K, V> {

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

}
