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

import java.util.*;

/**
 * Generates a sequence of prime numbers.
 * <p>
 * The beginning of the sequence is: 2 3 5 7 11 13 17 19 23 29 31 37 41 43 47 53 ...
 */
public class PrimesIterator implements Iterator<Long> {
    private long n = 2; // next candidate number
    private long p = 3; // next base prime
    private long q = 9; // square of the next base prime to keep track of in the sieve
    private final Map<Long, Long> sieve = new HashMap<>();
    private final List<Long> basePrimes = new ArrayList<>();

    @Override
    public boolean hasNext() {
        return true;
    }

    @Override
    public Long next() {
        if (n < 4) {
            if (n == 2) {
                n = 3;
                return 2L;
            } else {
                n = 5;
                return 3L;
            }
        }
        while (true) {
            if (!sieve.containsKey(n)) {
                if (n < q) {
                    // New prime found!
                    basePrimes.add(n);
                    n += 2;
                    return basePrimes.get(basePrimes.size() - 1);
                } else {
                    // Add new entry to sieve and pull another base prime.
                    sieve.put(q + p * 2, p * 2);
                    p = basePrimes.remove(0);
                    q = p * p;
                }
            } else {
                // We hit the marked number, update the sieve.
                long step = sieve.remove(n);
                long next = n + step;
                while (sieve.containsKey(next)) {
                    next += step;
                }
                sieve.put(next, step);
            }
            n += 2;
        }
    }
}
