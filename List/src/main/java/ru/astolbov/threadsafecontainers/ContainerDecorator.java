package ru.astolbov.threadsafecontainers;

import ru.astolbov.ArrayContainer;
import ru.astolbov.SimpleContainer;
import java.util.Iterator;
import net.jcip.annotations.ThreadSafe;
import net.jcip.annotations.GuardedBy;

@ThreadSafe
public class ContainerDecorator<E> implements SimpleContainer<E> {

    @GuardedBy("this")
    private final SimpleContainer<E> container;

    public ContainerDecorator(SimpleContainer<E> container) {
        this.container = container;
    }

    public synchronized void add(E value) {
        container.add(value);
    }

    public synchronized E get(int index) {
        return container.get(index);
    }

    public synchronized Iterator<E> iterator() {
        return copy(this.container).iterator();
    }

    public synchronized int getSize() {
        return container.getSize();
    }

    private SimpleContainer<E> copy(SimpleContainer<E> container) {
        return ((ArrayContainer<E>) container).copy();
    }

}
