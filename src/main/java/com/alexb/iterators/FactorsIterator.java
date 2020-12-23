package com.alexb.iterators;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Generates prime factors of the input number.
 * <p>
 * 5 -> 5 <br>
 * 6 -> 2 3 <br>
 * 864 -> 2 2 2 2 2 3 3 3
 */
public class FactorsIterator implements Iterator<Long> {
    private long n;
    private long p;
    private final Iterator<Long> primes = new PrimesIterator();

    /**
     * @param n the input parameter, assumed to be positive
     */
    public FactorsIterator(long n) {
        this.n = n;
        p = primes.next();
    }

    @Override
    public boolean hasNext() {
        return n > 1;
    }

    @Override
    public Long next() {
        if (n <= 1) {
            throw new NoSuchElementException();
        }
        while (n % p != 0) {
            p = primes.next();
        }
        n /= p;
        return p;
    }
}
