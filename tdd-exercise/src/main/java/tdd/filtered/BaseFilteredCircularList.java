package tdd.filtered;

import tdd.BaseCircularList;
import tdd.CircularList;

import java.util.Optional;

public class BaseFilteredCircularList implements FilteredCircularList {

    private final CircularList circularList;

    public BaseFilteredCircularList() {
        circularList = new BaseCircularList();
    }

    @Override
    public Optional<Integer> filteredNext(Filter filter) {
        final int maxNumberOfIterations = circularList.size();
        for (int i = 0; i < maxNumberOfIterations; i++) {
            Optional<Integer> nextElement = circularList.next();
            if (filter.applyFilter(nextElement.orElseThrow())) {
                return nextElement;
            }
        }
        return Optional.empty();
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
    public Optional<Integer> next() {
        return circularList.next();
    }

    @Override
    public Optional<Integer> previous() {
        return circularList.previous();
    }

    @Override
    public void reset() {
        circularList.reset();
    }
}
