package ru.astolbov;

import org.junit.Test;
import ru.astolbov.LinkedListContainer;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class LinkedListContainerTest {
    @Test
    public void whenAddLotsOfElementThenContainerHaveSameSize() {
        LinkedListContainer<Integer> container = new LinkedListContainer<>();
        int topLevel = 403;
        for (int i = 0; i < topLevel; i++) {
            container.add(i);
            assertThat(container.get(i), is(i));
        }
        assertThat(topLevel, is(container.getSize()));
    }

    @Test
    public void whenAddElementsThenCanIterate() {
        LinkedListContainer<Integer> container = new LinkedListContainer<>();
        int topLevel = 230;
        for (int i = 0; i < topLevel; i++) {
            container.add(i);
        }
        Integer pos = 0;
        for (Integer element : container) {
            assertThat(element, is(pos));
            pos++;
        }
    }

    @Test (expected = IndexOutOfBoundsException.class)
    public void whenGetNotExistElementThenTrowException() {
        LinkedListContainer<Integer> container = new LinkedListContainer<>();
        container.add(1);
        container.get(0);
        container.get(1);
    }

    @Test (expected = NoSuchElementException.class)
    public void whenIterateAboveSizeOfContainerThenTrowException() {
        LinkedListContainer container = new LinkedListContainer();
        container.iterator().next();
    }

    @Test (expected = ConcurrentModificationException.class)
    public void whenModifyContainerWhileIterationTheThrowException() {
        LinkedListContainer<Integer> container = new LinkedListContainer<>();
        Iterator<Integer> iter = container.iterator();
        container.add(1);
        iter.next();
    }
}