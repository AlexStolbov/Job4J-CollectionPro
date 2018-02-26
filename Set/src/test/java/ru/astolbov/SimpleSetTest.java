package ru.astolbov;

import org.junit.Test;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class SimpleSetTest {

    @Test
    public void whenAddLotsOfElementThenContainerHaveSameSize() {
        SimpleSet<Integer> container = new SimpleSet<>();
        int topLevel = 403;
        for (int i = 0; i < topLevel; i++) {
            container.add(i);
            assertThat(container.get(i), is(i));
        }
    }

    @Test
    public void whenAddTwoEqualsElementsThenContainerNoChangeSize() {
        SimpleSet<Integer> container = new SimpleSet<>();
        container.add(1);
        container.add(1);
        assertThat(container.getSize(), is(1));
    }

    @Test
    public void whenAddElementsThenCanIterate() {
        SimpleSet<Integer> container = new SimpleSet<>();
        int topLevel = 230;
        for (int i = 0; i < topLevel; i++) {
            container.add(i);
        }
        Integer pos = 0;
        for (Integer element : container) {
            assertThat(pos, is(element));
            pos++;
        }
    }

    @Test (expected = IndexOutOfBoundsException.class)
    public void whenGetNotExistElementThenTrowException() {
        SimpleSet<Integer> container = new SimpleSet<>();
        container.add(1);
        container.get(0);
        container.get(1);
    }

    @Test (expected = NoSuchElementException.class)
    public void whenIterateAboveSizeOfContainerThenTrowException() {
        SimpleSet<Integer> container = new SimpleSet<>();
        container.iterator().next();
    }

    @Test (expected = ConcurrentModificationException.class)
    public void whenModifyContainerWhileIterationTheThrowException() {
        SimpleSet<Integer> container = new SimpleSet<>();
        Iterator<Integer> iter = container.iterator();
        container.add(1);
        iter.next();
    }

}