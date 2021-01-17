/*
Copyright 2020-present, Alex Baryzhikov.

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
*/

package com.alexb.iterators;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Generates prime factors of the input number.
 * <p>
 * 5 &rarr; 5 <br>
 * 6 &rarr; 2 3 <br>
 * 864 &rarr; 2 2 2 2 2 3 3 3
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
