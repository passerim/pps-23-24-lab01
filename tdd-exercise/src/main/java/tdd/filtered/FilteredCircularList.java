package tdd.filtered;

import tdd.CircularList;

import java.util.Optional;


public interface FilteredCircularList extends CircularList {

    Optional<Integer> filteredNext(Filter filter);
}
