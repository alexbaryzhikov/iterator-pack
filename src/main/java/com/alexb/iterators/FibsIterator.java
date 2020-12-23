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

/**
 * Generates a sequence of Fibonacci numbers.
 * <p>
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
