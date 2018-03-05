package ru.astolbov;

public class SimpleHashSet<E> {

    private ArrayContainer<E> container = new ArrayContainer<>();

    public void add(E e) {
        if (!contains(e)) {
            int hash = getHash(e);
            this.container.add(e, hash);
        }
    }

    public boolean contains(E e) {
        return this.container.contains(e);
    }

    public void remove(E e) {
        if (container.contains(e)) {
            int hash = getHash(e);
            if (e.equals(container.get(hash))) {
                container.remove(hash);
            }
        }
    }

    public int getSize() {
        return container.getSize();
    }

    private int getHash(E e) {
        return e.hashCode() - 1;
    }

}
