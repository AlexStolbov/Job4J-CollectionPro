package ru.astolbov;

import org.junit.Test;
import ru.astolbov.threadsafecontainers.ContainerDecorator;

import javax.print.attribute.IntegerSyntax;
import java.util.Iterator;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class ArrayContainerConcurrencyTest {

    @Test
    public void whenAddInTwoThreadsThenResultIsDouble() throws InterruptedException {
        SimpleContainer<Integer> arr = new ArrayContainer<>();
        SimpleContainer<Integer> arrThreadSafe = new ContainerDecorator<>(arr);
        int size = 8000;
        int countThreads = 2;
        Thread one = new ThreadTest(arrThreadSafe, size);
        Thread two = new ThreadTest(arrThreadSafe, size);
        one.start();
        two.start();
        one.join();
        two.join();
        assertThat(arrThreadSafe.getSize(), is(size * countThreads));
    }

    class ThreadTest extends Thread {
        SimpleContainer<Integer> arr;
        private int size;
        ThreadTest(SimpleContainer<Integer> arr, int size) {
            this.arr = arr;
            this.size = size;
        }

        @Override
        public void run() {
            for (int i = 0; i < size; i++) {
                arr.add(i);
            }
        }
    }


    @Test
    public void whenAddFiveElementsThenHaveFiveIteration() throws InterruptedException {
        SimpleContainer<Integer> arr = new ContainerDecorator<>(new ArrayContainer<>());
        for (int i = 0; i < 5; i++) {
            arr.add(i);
        }
        Thread one = new IteratorTest(arr);
        Thread two = new IteratorTest(arr);
        one.start();
        two.start();
        one.join();
        two.join();
        assertThat(((IteratorTest) one).getRes(), is(arr.getSize()));
        assertThat(((IteratorTest) two).getRes(), is(arr.getSize()));
    }

    class IteratorTest extends Thread {
        private int res = 0;
        SimpleContainer<Integer> arr;
        IteratorTest(SimpleContainer<Integer> arr) {
            this.arr = arr;
        }

        @Override
        public void run() {
            for (int i : arr) {
                this.res++;
            }
        }

        public int getRes() {
            return res;
        }

    }

}
