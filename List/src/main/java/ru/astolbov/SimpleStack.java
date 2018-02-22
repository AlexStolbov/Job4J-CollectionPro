package ru.astolbov;

public class SimpleStack<T> {
    LinkedListContainer<T> elements = new LinkedListContainer<>();
    int lastIndex = -1;

    public T poll() {
        T res = null;
        if (this.lastIndex > -1) {
            res = this.elements.get(this.lastIndex);
            this.elements.delete(this.lastIndex);
            this.lastIndex--;
        }
        return res;
    }

    public void push(T value) {
        this.elements.add(value);
        this.lastIndex++;
    }
}
