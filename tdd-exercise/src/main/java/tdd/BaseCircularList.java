package tdd;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class BaseCircularList implements CircularList {

    private final List<Integer> elements;
    private int iteratorIndex;

    public BaseCircularList() {
        elements = new ArrayList<>();
        iteratorIndex = -1;
    }

    @Override
    public void add(int element) {
        elements.add(element);
    }

    @Override
    public int size() {
        return elements.size();
    }

    @Override
    public boolean isEmpty() {
        return elements.isEmpty();
    }

    @Override
    public Optional<Integer> next() {
        if (isEmpty()) {
            return Optional.empty();
        }
        iteratorIndex += 1;
        if (iteratorIndex % size() == 0) {
            iteratorIndex = 0;
        }
        return Optional.of(elements.get(iteratorIndex));
    }

    @Override
    public Optional<Integer> previous() {
        if (isEmpty()) {
            return Optional.empty();
        }
        iteratorIndex -= 1;
        if (iteratorIndex < 0) {
            iteratorIndex = size() - 1;
        }
        return Optional.of(elements.get(iteratorIndex));
    }

    @Override
    public void reset() {
        iteratorIndex = -1;
    }
}
