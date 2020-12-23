package com.alexb.iterators;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class CombinationsIteratorTest {

    @Test
    public void combinations_of_empty_list() {
        CombinationsIterator<Integer> iterator =
                new CombinationsIterator<>(Collections.emptyList(), 0);
        List<List<Integer>> result = new ArrayList<>();
        while (iterator.hasNext()) {
            result.add(iterator.next());
        }
        assertEquals(Collections.<List<Integer>>emptyList(), result);
    }

    @Test
    public void combinations_of_size_0() {
        CombinationsIterator<Integer> iterator =
                new CombinationsIterator<>(Arrays.asList(0, 1, 2, 3), 0);
        List<List<Integer>> result = new ArrayList<>();
        while (iterator.hasNext()) {
            result.add(iterator.next());
        }
        assertEquals(Collections.<List<Integer>>emptyList(), result);
    }

    @Test
    public void combinations_of_size_1() {
        CombinationsIterator<Integer> iterator =
                new CombinationsIterator<>(Arrays.asList(0, 1, 2, 3), 1);
        List<List<Integer>> result = new ArrayList<>();
        while (iterator.hasNext()) {
            result.add(iterator.next());
        }
        List<List<Integer>> expected = Arrays.asList(
                Collections.singletonList(0),
                Collections.singletonList(1),
                Collections.singletonList(2),
                Collections.singletonList(3));
        assertEquals(expected, result);
    }

    @Test
    public void combinations_of_size_2() {
        CombinationsIterator<Integer> iterator =
                new CombinationsIterator<>(Arrays.asList(0, 1, 2, 3), 2);
        List<List<Integer>> result = new ArrayList<>();
        while (iterator.hasNext()) {
            result.add(iterator.next());
        }
        List<List<Integer>> expected = Arrays.asList(
                Arrays.asList(0, 1),
                Arrays.asList(0, 2),
                Arrays.asList(0, 3),
                Arrays.asList(1, 2),
                Arrays.asList(1, 3),
                Arrays.asList(2, 3));
        assertEquals(expected, result);
    }

    @Test
    public void combinations_of_size_3() {
        CombinationsIterator<Integer> iterator =
                new CombinationsIterator<>(Arrays.asList(0, 1, 2, 3), 3);
        List<List<Integer>> result = new ArrayList<>();
        while (iterator.hasNext()) {
            result.add(iterator.next());
        }
        List<List<Integer>> expected = Arrays.asList(
                Arrays.asList(0, 1, 2),
                Arrays.asList(0, 1, 3),
                Arrays.asList(0, 2, 3),
                Arrays.asList(1, 2, 3));
        assertEquals(expected, result);
    }

    @Test
    public void combinations_of_size_4() {
        CombinationsIterator<Integer> iterator =
                new CombinationsIterator<>(Arrays.asList(0, 1, 2, 3), 4);
        List<List<Integer>> result = new ArrayList<>();
        while (iterator.hasNext()) {
            result.add(iterator.next());
        }
        List<List<Integer>> expected = Collections.singletonList(
                Arrays.asList(0, 1, 2, 3)
        );
        assertEquals(expected, result);
    }

    @Test
    public void combinations_of_size_5() {
        CombinationsIterator<Integer> iterator =
                new CombinationsIterator<>(Arrays.asList(0, 1, 2, 3), 5);
        List<List<Integer>> result = new ArrayList<>();
        while (iterator.hasNext()) {
            result.add(iterator.next());
        }
        assertEquals(Collections.<List<Integer>>emptyList(), result);
    }
}