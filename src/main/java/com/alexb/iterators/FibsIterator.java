package com.alexb.iterators;

import java.util.Iterator;

/**
 * Generates a sequence of Fibonacci numbers.<br>
 * The beginning of the sequence is: 0 1 1 2 3 5 8 13 21 34 55 89 144 ...
 */
public class FibsIterator implements Iterator<Long> {
    private long a = 0;
    private long b = 1;

    @Override
    public boolean hasNext() {
        return true;
    }

    @Override
    public Long next() {
        long next = a;
        a = b;
        b = next + b;
        return next;
    }
}
