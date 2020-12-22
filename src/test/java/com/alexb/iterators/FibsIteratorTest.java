package com.alexb.iterators;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class FibsIteratorTest {
    private static final String FIBS = "0 1 1 2 3 5 8 13 21 34 55 89 144 233 377 610 987 1597 " +
            "2584 4181 6765 10946 17711 28657 46368 75025 121393 196418 317811 ";

    @Test
    public void generate_fibs() {
        FibsIterator iterator = new FibsIterator();
        StringBuilder result = new StringBuilder();
        for (long i = iterator.next(); iterator.hasNext() && i <= 317811; i = iterator.next()) {
            result.append(i).append(' ');
        }
        assertEquals(FIBS, result.toString());
    }
}

