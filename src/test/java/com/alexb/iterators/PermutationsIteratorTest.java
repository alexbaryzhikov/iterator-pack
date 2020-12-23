package com.alexb.iterators;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class PermutationsIteratorTest {

    @Test
    public void permutations_of_empty_list() {
        PermutationsIterator<Integer> iterator =
                new PermutationsIterator<>(Collections.emptyList(), 0);
        List<List<Integer>> result = new ArrayList<>();
        while (iterator.hasNext()) {
            result.add(iterator.next());
        }
        assertEquals(Collections.<List<Integer>>emptyList(), result);
    }

    @Test
    public void permutations_of_size_0() {
        PermutationsIterator<Integer> iterator =
                new PermutationsIterator<>(Arrays.asList(0, 1, 2), 0);
        List<List<Integer>> result = new ArrayList<>();
        while (iterator.hasNext()) {
            result.add(iterator.next());
        }
        assertEquals(Collections.<List<Integer>>emptyList(), result);
    }

    @Test
    public void permutations_of_size_1() {
        PermutationsIterator<Integer> iterator =
                new PermutationsIterator<>(Arrays.asList(0, 1, 2), 1);
        List<List<Integer>> result = new ArrayList<>();
        while (iterator.hasNext()) {
            result.add(iterator.next());
        }
        List<List<Integer>> expected = Arrays.asList(
                Collections.singletonList(0),
                Collections.singletonList(1),
                Collections.singletonList(2));
        assertEquals(expected, result);
    }

    @Test
    public void permutations_of_size_2() {
        PermutationsIterator<Integer> iterator =
                new PermutationsIterator<>(Arrays.asList(0, 1, 2), 2);
        List<List<Integer>> result = new ArrayList<>();
        while (iterator.hasNext()) {
            result.add(iterator.next());
        }
        List<List<Integer>> expected = Arrays.asList(
                Arrays.asList(0, 1),
                Arrays.asList(0, 2),
                Arrays.asList(1, 0),
                Arrays.asList(1, 2),
                Arrays.asList(2, 0),
                Arrays.asList(2, 1));
        assertEquals(expected, result);
    }

    @Test
    public void permutations_of_size_3() {
        PermutationsIterator<Integer> iterator =
                new PermutationsIterator<>(Arrays.asList(0, 1, 2), 3);
        List<List<Integer>> result = new ArrayList<>();
        while (iterator.hasNext()) {
            result.add(iterator.next());
        }
        List<List<Integer>> expected = Arrays.asList(
                Arrays.asList(0, 1, 2),
                Arrays.asList(0, 2, 1),
                Arrays.asList(1, 0, 2),
                Arrays.asList(1, 2, 0),
                Arrays.asList(2, 0, 1),
                Arrays.asList(2, 1, 0));
        assertEquals(expected, result);
    }

    @Test
    public void permutations_of_size_4() {
        PermutationsIterator<Integer> iterator =
                new PermutationsIterator<>(Arrays.asList(0, 1, 2), 4);
        List<List<Integer>> result = new ArrayList<>();
        while (iterator.hasNext()) {
            result.add(iterator.next());
        }
        assertEquals(Collections.<List<Integer>>emptyList(), result);
    }
}
