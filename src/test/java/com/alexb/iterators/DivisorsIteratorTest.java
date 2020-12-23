package com.alexb.iterators;

import org.junit.Test;

import java.util.*;

import static org.junit.Assert.assertEquals;

public class DivisorsIteratorTest {

    @Test
    public void divisors_of_0() {
        assertEquals(Collections.emptyList(), getDivisors(0));
    }

    @Test
    public void divisors_of_1() {
        assertEquals(Collections.emptyList(), getDivisors(1));
    }

    @Test
    public void divisors_of_2() {
        assertEquals(Collections.singletonList(1L), getDivisors(2));
    }

    @Test
    public void divisors_of_3() {
        assertEquals(Collections.singletonList(1L), getDivisors(3));
    }

    @Test
    public void divisors_of_4() {
        assertEquals(Arrays.asList(1L, 2L), getDivisors(4));
    }

    @Test
    public void divisors_of_5() {
        assertEquals(Collections.singletonList(1L), getDivisors(5));
    }

    @Test
    public void divisors_of_6() {
        assertEquals(Arrays.asList(1L, 2L, 3L), getDivisors(6));
    }

    @Test
    public void divisors_of_7() {
        assertEquals(Collections.singletonList(1L), getDivisors(7));
    }

    @Test
    public void divisors_of_8() {
        assertEquals(Arrays.asList(1L, 2L, 4L), getDivisors(8));
    }

    @Test
    public void divisors_of_12() {
        assertEquals(Arrays.asList(1L, 2L, 3L, 4L, 6L), getDivisors(12));
    }

    @Test
    public void divisors_of_15120() {
        assertEquals(79, getDivisors(15120).size());
    }

    private List<Long> getDivisors(long x) {
        DivisorsIterator iterator = new DivisorsIterator(x);
        List<Long> result = new ArrayList<>();
        while (iterator.hasNext()) {
            result.add(iterator.next());
        }
        result.sort(Comparator.naturalOrder());
        return result;
    }
}