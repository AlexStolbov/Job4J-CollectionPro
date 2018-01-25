import java.util.Iterator;
import java.util.NoSuchElementException;

public class MatrixIterator implements Iterator {

    private final int[][] iterationArray;
    private int posX = 0;
    private int posY = 0;

    public MatrixIterator(int[][] iterationArray) {
        this.iterationArray = iterationArray;
    }

    @Override
    public boolean hasNext() {
        boolean result = false;
        if (posY < iterationArray.length) {
            result = true;
        }
        return result;
    }

    @Override
    public Object next() {

        if (!hasNext()) {
            throw new NoSuchElementException();
        }

        int result = iterationArray[posY][posX];
        if (posX < iterationArray[posY].length - 1) {
            posX++;
        } else {
            posX = 0;
            posY++;
        }
        return result;
    }
}
