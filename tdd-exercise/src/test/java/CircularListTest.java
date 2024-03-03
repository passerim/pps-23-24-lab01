import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import tdd.BaseCircularList;
import tdd.CircularList;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

/**
 * The test suite for testing the CircularList implementation
 */
class CircularListTest {

    private final List<Integer> TEST_LIST = Arrays.asList(1, 2, 3);
    protected CircularList circularList;

    private void addTestElements() {
        for (Integer element : TEST_LIST) {
            circularList.add(element);
        }
    }

    @BeforeEach
    void createCircularList() {
        circularList = new BaseCircularList();
    }

    @Test
    void testAddOneElement() {
        final int element = 1;
        circularList.add(element);
        assertEquals(1, circularList.size());
    }

    @Test
    void testAddMultipleElements() {
        final List<Integer> nextElementsList = new ArrayList<>();
        addTestElements();
        for (int i = 0; i < circularList.size(); i++) {
            nextElementsList.add(circularList.next().orElseThrow());
        }
        assertArrayEquals(TEST_LIST.toArray(), nextElementsList.toArray());
    }

    @Test
    void testAddElementAfterNext() {
        final int element = 1;
        addTestElements();
        for (int i = 0; i < circularList.size(); i++) {
            circularList.next();
        }
        circularList.add(element);
        assertEquals(TEST_LIST.size() + 1, circularList.size());
        assertEquals(Optional.of(element), circularList.next());
    }

    @Test
    void testAddElementAfterPrevious() {
        final int element = 1;
        addTestElements();
        circularList.previous();
        circularList.add(element);
        assertEquals(TEST_LIST.size() + 1, circularList.size());
        assertEquals(Optional.of(element), circularList.next());
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
    void testNextOnEmptyCircularList() {
        assertFalse(circularList.next().isPresent());
    }

    @Test
    void testPreviousOnEmptyCircularList() {
        assertFalse(circularList.previous().isPresent());
    }

    @Test
    void testNext() {
        final int element = 1;
        circularList.add(element);
        assertEquals(circularList.next(), Optional.of(element));
    }

    @Test
    void testPrevious() {
        final int element = 1;
        circularList.add(element);
        assertEquals(circularList.previous(), Optional.of(element));
    }

    @Test
    void testReset() {
        addTestElements();
        for (int i = 0; i < circularList.size(); i++) {
            circularList.next();
        }
        circularList.previous();
        circularList.reset();
        assertEquals(Optional.of(TEST_LIST.get(0)), circularList.next());
    }
}
