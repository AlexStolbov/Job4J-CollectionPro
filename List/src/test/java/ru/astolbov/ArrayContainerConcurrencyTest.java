package ru.astolbov;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class ArrayContainerConcurrencyTest {

    @Test
    public void whenAddInTwoThreadsThenResultIsDouble() throws InterruptedException {
        ArrayContainer<Integer> arr = new ArrayContainer<>();
        int size = 1000;
        int countThreads = 2;
        Thread one = new ThreadTest(arr, size);
        Thread two = new ThreadTest(arr, size);
        one.start();
        two.start();
        one.join();
        two.join();
        assertThat(arr.getSize(), is(size * countThreads));
    }

    class ThreadTest extends Thread {
        ArrayContainer<Integer> arr;
        int size;
        ThreadTest(ArrayContainer<Integer> arr, int size) {
            this.arr = arr;
            this.size = size;
        }

        @Override
        public void run() {
            for (int i = 0; i < 1000; i++) {
                arr.add(i);
            }
        }
    }

}
