package ru.astolbov;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Итератор для массива int[], по простым числам.
 */
public class PrimeIterator implements Iterator {

    private int[] inputNumbers;
    /**
     * Pointer
     */
    private int pos = 0;

    /**
     * Constructor.
     * @param inputNumbers
     */
    public PrimeIterator(int[] inputNumbers) {
        this.inputNumbers = inputNumbers;
    }

    @Override
    public boolean hasNext() {
        setPosToNextPrimeNumber();
        return pos < inputNumbers.length;
    }

    @Override
    public Object next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        int res = inputNumbers[pos++];
        return res;
    }

    /**
     * Move pointer to next prime number.
     */
    private void setPosToNextPrimeNumber() {
        while (pos < inputNumbers.length && !numberIsPrime(inputNumbers[pos])) {
            pos++;
        }
    }

    /**
     * Determines that this number is prime.
     * @param number
     * @return - true if this number is prime, otherwise false.
     */
    private boolean numberIsPrime(int number) {
        boolean res = true;
        if (number > 1) {
            if (number > 2) {
                if (number % 2 == 0) {
                    res = false;
                } else {
                    for (int i = 2; i * i < number; i++) {
                        if (number % i == 0) {
                            res = false;
                        }
                    }
                }
            }
        } else {
            res = false;
        }
        return res;
    }
}
