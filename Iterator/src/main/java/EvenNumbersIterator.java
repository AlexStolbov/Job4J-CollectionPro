import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Iterator on int[] return only even numbers.
 */
public class EvenNumbersIterator implements Iterator{

    private final int[] iterationArray;
    private int pos = 0;

    public EvenNumbersIterator(final int[] iterationArray) {
        this.iterationArray = iterationArray;
        setPosToNextEvenNumber();
    }

    @Override
    public boolean hasNext() {
        return pos < iterationArray.length;
    }

    @Override
    public Object next() {

        if (!hasNext()) {
            throw new NoSuchElementException();
        }

        int res = iterationArray[pos++];
        setPosToNextEvenNumber();

        return res;
    }

    /**
     * Sets pointer on next even number.
     */
    private void setPosToNextEvenNumber() {
        while (pos < iterationArray.length && !(iterationArray[pos] % 2 == 0)) {
            pos ++;
        }
    }
}
