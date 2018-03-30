package ru.astolbov;

public class SimpleHashSet<E> {

    private Object[] container;
    private float loadFactor;
    private int countElements;

    public SimpleHashSet() {
        this.container = new Object[20];
        //т.к. коллизии не обрабатываем
        this.loadFactor = 1;
    }

    public boolean add(E e) {
        boolean res = false;
        if (!contains(e)) {
            if (countElements >= loadFactor * container.length) {
                growSize();
            }
            int newHash = getHash(e);
            this.container[newHash] = e;
            countElements++;
            res = true;
        }
        return res;
    }

    public boolean contains(E e) {
        return e.equals(this.container[getHash(e)]);
    }

    public void remove(E e) {
        if (contains(e)) {
             this.container[getHash(e)] = null;
             countElements--;
        }
    }

    private int getHash(E e) {
        int hash = (e.hashCode() & 0x7FFFFFFF) % container.length;
        return hash;
    }

    private void growSize() {
        Object[] oldContainer = this.container;
        int newSize = (int) (this.container.length * 1.5);
        this.container = new Object[newSize];
        for (int i = 0; i < oldContainer.length; i++) {
            E current = (E) oldContainer[i];
            if (current != null) {
                this.container[getHash(current)] = current;
            }
        }
    }

}
