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

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class FibsIteratorTest {
    private static final String FIBS = "0 1 1 2 3 5 8 13 21 34 55 89 144 233 377 610 987 1597 " +
            "2584 4181 6765 10946 17711 28657 46368 75025 121393 196418 317811 ";

    @Test
    public void generate_fibs() {
        FibsIterator iterator = new FibsIterator();
        StringBuilder result = new StringBuilder();
        while (iterator.hasNext()) {
            long i = iterator.next();
            result.append(i).append(' ');
            if (i >= 317811) {
                break;
            }
        }
        assertEquals(FIBS, result.toString());
    }
}
