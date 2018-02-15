import org.junit.Test;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class ArrayContainerTest {

    @Test
    public void whenAddLotsOfElementThenContainerHaveSameSize() {
        ArrayContainer<Integer> container = new ArrayContainer<>();
        int topLevel = 403;
        for (int i = 0; i < topLevel; i++) {
            container.add(i);
            assertThat(container.get(i), is(i));
        }
        assertThat(topLevel, is(container.getSize()));
    }

    @Test
    public void whenAddElementsThenCanIterate() {
        ArrayContainer<Integer> container = new ArrayContainer<>();
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
        ArrayContainer<Integer> container = new ArrayContainer<>();
        container.add(1);
        container.get(0);
        container.get(1);
    }

    @Test (expected = NoSuchElementException.class)
    public void whenIterateAboveSizeOfContainerThenTrowException() {
        ArrayContainer container = new ArrayContainer();
        container.iterator().next();
    }

    @Test (expected = ConcurrentModificationException.class)
    public void whenModifyContainerWhileIterationTheThrowException() {
        ArrayContainer<Integer> container = new ArrayContainer<>();
        Iterator<Integer> iter = container.iterator();
        container.add(1);
        iter.next();
    }

}