package ru.astolbov;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class SimpleLinkedSetTest {

    @Test
    public void whenAddLotsOfElementThenContainerHaveSameSize() {
        SimpleLinkedSet<Integer> container = new SimpleLinkedSet<>();
        int topLevel = 403;
        for (int i = 0; i < topLevel; i++) {
            container.add(i);
            assertThat(container.contains(i), is(true));
        }
    }

    @Test
    public void whenAddTwoEqualsElementsThenContainerNoChangeSize() {
        SimpleLinkedSet<Integer> container = new SimpleLinkedSet<>();
        container.add(1);
        container.add(1);
        assertThat(container.getSize(), is(1));
    }

    @Test
    public void whenAddElementsThenCanIterate() {
        SimpleLinkedSet<Integer> container = new SimpleLinkedSet<>();
        int topLevel = 230;
        for (int i = 0; i < topLevel; i++) {
            container.add(i);
        }
        for (Integer element : container) {
            assertThat(container.contains(element), is(true));
        }
    }

}