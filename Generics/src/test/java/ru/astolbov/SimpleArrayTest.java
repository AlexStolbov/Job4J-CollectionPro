package ru.astolbov;

import org.junit.Test;

import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class SimpleArrayTest {

    @Test
    public void whenAddElementThenCanGetIt() {
        String[] testArray = new String[]{"one", "two", "three"};
        SimpleArray<String> sa = new SimpleArray<>();
        for (String testElement : testArray) {
            sa.add(testElement);
        }
        for (int i = 0; i < testArray.length; i++) {
            assertThat(sa.get(i), is(testArray[i]));
        }
        assertThat(sa.size(), is(testArray.length));
    }

    @Test
    public void whenDeleteElementThenSizeDecreaseByOne() {
        SimpleArray<String> sa = new SimpleArray<>();
        sa.add("one");
        sa.add("two");
        sa.add("three");
        int sizeBeforeDelete = sa.size();
        sa.delete(0);
        assertThat(sa.size(), is(sizeBeforeDelete - 1));
        sizeBeforeDelete = sa.size();
        sa.delete(1);
        assertThat(sa.size(), is(sizeBeforeDelete - 1));
        sizeBeforeDelete = sa.size();
        sa.delete(0);
        assertThat(sa.size(), is(sizeBeforeDelete - 1));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void whenInvalidIndexOnGetThenThrowException() {
        SimpleArray<String> sa = new SimpleArray<>();
        sa.add("one");
        sa.delete(1);
    }

    @Test(expected = NoSuchElementException.class)
    public void whenInvalidNextThenThrowException() {
        SimpleArray<String> sa = new SimpleArray<>();
        Iterator<String> it = sa.iterator();
        it.next();
    }

    @Test
    public void whenRemoveElementThenDoNotFountIt() {
        SimpleArray<Integer> sa = new SimpleArray<>();
        int testElement = 2;
        sa.add(1);
        sa.add(testElement);
        sa.add(3);
        sa.delete(1);
        boolean foundTestElemet = false;
        for (Integer element : sa) {
            if (element == testElement) {
                foundTestElemet = true;
            }
        }
        assertThat(foundTestElemet, is(false));
    }

    @Test
    public void whenLoopForEachThenItsCountEqualsArraySize() {
        SimpleArray<Integer> sa = new SimpleArray<>();
        sa.add(1);
        sa.add(2);
        sa.add(3);
        int countIterations = 0;
        for (Integer element : sa) {
            countIterations++;
        }
        assertThat(sa.size(), is(countIterations));
    }

}
