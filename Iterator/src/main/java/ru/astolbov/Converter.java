package ru.astolbov;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class Converter {

    Iterator<Integer> convert(Iterator<Iterator<Integer>> it) {

        Iterator<Integer> resIterator = (new ArrayList<Integer>()).iterator();

        if (it.hasNext()) {
            resIterator = new Iterator<Integer>() {
                Iterator<Integer> pos = it.next();

                @Override
                public boolean hasNext() {
                    return pos.hasNext();
                }

                @Override
                public Integer next() {
                    if (!hasNext()) {
                        throw new NoSuchElementException();
                    }
                    Integer next = pos.next();
                    moveToNextPosition();
                    return next;
                }

                private void moveToNextPosition() {
                    if (!pos.hasNext()) {
                        if (it.hasNext()) {
                            pos = it.next();
                        }
                    }
                }
            };
        }
        return resIterator;
    }
}
