import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import tdd.filtered.BaseFilteredCircularList;
import tdd.filtered.FilteredCircularList;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;


class FilteredCircularListTest extends CircularListTest {

    @BeforeEach
    void createList() {
        circularList = new BaseFilteredCircularList();
    }

    @Test
    void testSuccessfulSearchForNextEven() {
        final List<Integer> listWithEven = Arrays.asList(1, 3, 5, 7, 9, 10);
        for (Integer element : listWithEven) {
            circularList.add(element);
        }
        assertEquals(Optional.of(10), ((FilteredCircularList) circularList).filteredNext(element -> (element % 2) == 0));
    }

    @Test
    void testUnsuccessfulSearchForNextEven() {
        final List<Integer> listWithoutEven = Arrays.asList(1, 3, 5, 7, 9, 11);
        for (Integer element : listWithoutEven) {
            circularList.add(element);
        }
        assertEquals(Optional.empty(), ((FilteredCircularList) circularList).filteredNext(element -> (element % 2) == 0));
    }
}
