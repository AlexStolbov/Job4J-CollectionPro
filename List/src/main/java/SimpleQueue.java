public class SimpleQueue<T> {
    LinkedListContainer<T> elements = new LinkedListContainer<>();

    public T poll() {
        T res = null;
        if (elements.getSize() > 0) {
            res = this.elements.get(0);
            this.elements.delete(0);
        }
        return res;
    }

    public void push(T value) {
        this.elements.add(value);
    }
}
