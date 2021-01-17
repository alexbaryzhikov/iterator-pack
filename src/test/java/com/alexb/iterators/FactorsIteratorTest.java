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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class FactorsIteratorTest {

    @Test
    public void factors_of_0() {
        assertEquals(Collections.emptyList(), getFactors(0));
    }

    @Test
    public void factors_of_1() {
        assertEquals(Collections.emptyList(), getFactors(1));
    }

    @Test
    public void factors_of_2() {
        assertEquals(Collections.singletonList(2L), getFactors(2));
    }

    @Test
    public void factors_of_3() {
        assertEquals(Collections.singletonList(3L), getFactors(3));
    }

    @Test
    public void factors_of_4() {
        assertEquals(Arrays.asList(2L, 2L), getFactors(4));
    }

    @Test
    public void factors_of_5() {
        assertEquals(Collections.singletonList(5L), getFactors(5));
    }

    @Test
    public void factors_of_6() {
        assertEquals(Arrays.asList(2L, 3L), getFactors(6));
    }

    @Test
    public void factors_of_7() {
        assertEquals(Collections.singletonList(7L), getFactors(7));
    }

    @Test
    public void factors_of_720() {
        assertEquals(Arrays.asList(2L, 2L, 2L, 2L, 3L, 3L, 5L), getFactors(720));
    }

    @Test
    public void factors_of_864() {
        assertEquals(Arrays.asList(2L, 2L, 2L, 2L, 2L, 3L, 3L, 3L), getFactors(864));
    }

    @Test
    public void factors_of_72990720() {
        assertEquals(
                Arrays.asList(2L, 2L, 2L, 2L, 2L, 2L, 2L, 2L, 2L, 2L, 2L, 2L, 2L, 2L, 3L, 3L, 3L, 3L, 5L, 11L),
                getFactors(72_990_720)
        );
    }

    private List<Long> getFactors(long x) {
        FactorsIterator iterator = new FactorsIterator(x);
        List<Long> result = new ArrayList<>();
        while (iterator.hasNext()) {
            result.add(iterator.next());
        }
        return result;
    }
}