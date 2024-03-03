import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import tdd.iterators.BaseCircularListWithIterators;
import tdd.iterators.CircularListIterators;

import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class CircularListWithIteratorsTest {

    private final List<Integer> TEST_LIST = Arrays.asList(1, 2, 3);
    private CircularListIterators circularList;

    private void addTestElements() {
        for (Integer element : TEST_LIST) {
            circularList.add(element);
        }
    }

    @BeforeEach
    void createCircularList() {
        circularList = new BaseCircularListWithIterators();
    }

    @Test
    void testAddOneElement() {
        final int element = 1;
        circularList.add(element);
        assertEquals(1, circularList.size());
    }

    @Test
    void testAddMultipleElements() {
        addTestElements();
        assertEquals(TEST_LIST.size(), circularList.size());
    }

    @Test
    void testSize() {
        addTestElements();
        assertEquals(TEST_LIST.size(), circularList.size());
    }

    @Test
    void testIsEmpty() {
        assertEquals(0, circularList.size());
        assertTrue(circularList.isEmpty());
    }

    @Test
    void testIsNotEmpty() {
        addTestElements();
        assertFalse(circularList.isEmpty());
    }

    @Test
    void testForwardIteratorOnEmptyCircularList() {
        assertEquals(Collections.emptyIterator(), circularList.forwardIterator());
    }

    @Test
    void testBackwardIteratorOnEmptyCircularList() {
        assertEquals(Collections.emptyIterator(), circularList.backwardIterator());
    }

    @Test
    public void testForwardIterator() {
        addTestElements();
        Iterator<Integer> iterator = circularList.forwardIterator();
        for (Integer element : TEST_LIST) {
            assertEquals(element, iterator.next());
        }
        assertEquals(TEST_LIST.get(0), iterator.next());
    }

    @Test
    public void testBackwardIterator() {
        addTestElements();
        Iterator<Integer> iterator = circularList.backwardIterator();
        for (int i = TEST_LIST.size() - 1; i >= 0; i--) {
            assertEquals(TEST_LIST.get(i), iterator.next());
        }
        assertEquals(TEST_LIST.get(TEST_LIST.size() - 1), iterator.next());
    }
}
