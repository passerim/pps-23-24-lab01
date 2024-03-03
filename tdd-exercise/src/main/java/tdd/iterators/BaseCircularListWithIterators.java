package tdd.iterators;

import tdd.BaseCircularList;
import tdd.CircularList;

import java.util.Collections;
import java.util.Iterator;
import java.util.stream.IntStream;

public class BaseCircularListWithIterators implements CircularListWithIterators {

    private final CircularList circularList;

    public BaseCircularListWithIterators() {
        circularList = new BaseCircularList();
    }

    @Override
    public void add(int element) {
        circularList.add(element);
    }

    @Override
    public int size() {
        return circularList.size();
    }

    @Override
    public boolean isEmpty() {
        return circularList.isEmpty();
    }

    @Override
    public Iterator<Integer> forwardIterator() {
        if (this.isEmpty()) {
            return Collections.emptyIterator();
        }
        return IntStream.generate(() -> this.circularList.next().orElseThrow()).iterator();
    }

    @Override
    public Iterator<Integer> backwardIterator() {
        if (this.isEmpty()) {
            return Collections.emptyIterator();
        }
        return IntStream.generate(() -> this.circularList.previous().orElseThrow()).iterator();
    }
}
