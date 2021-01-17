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

import com.alexb.iterators.date.Day;
import org.junit.Test;

import static com.alexb.iterators.date.Month.*;
import static com.alexb.iterators.date.Weekday.*;
import static org.junit.Assert.assertEquals;

public class DaysIteratorTest {

    @Test
    public void first_day() {
        DaysIterator iterator = new DaysIterator();
        Day result = iterator.hasNext() ? iterator.next() : null;
        assertEquals(new Day(MON, JAN, 1, 1900), result);
    }

    @Test
    public void next_day() {
        DaysIterator iterator = new DaysIterator();
        Day result = null;
        int i = 0;
        while (iterator.hasNext()) {
            result = iterator.next();
            if (i++ == 1) {
                break;
            }
        }
        assertEquals(new Day(TUE, JAN, 2, 1900), result);
    }

    @Test
    public void next_month() {
        DaysIterator iterator = new DaysIterator();
        Day result = null;
        int i = 0;
        while (iterator.hasNext()) {
            result = iterator.next();
            if (i++ == JAN.days(1900)) {
                break;
            }
        }
        assertEquals(new Day(THU, FEB, 1, 1900), result);
    }

    @Test
    public void next_year() {
        DaysIterator iterator = new DaysIterator();
        Day result = null;
        int i = 0;
        while (iterator.hasNext()) {
            result = iterator.next();
            if (i++ == 365) {
                break;
            }
        }
        assertEquals(new Day(TUE, JAN, 1, 1901), result);
    }

    @Test
    public void weekday() {
        DaysIterator iterator = new DaysIterator();
        Day result = null;
        while (iterator.hasNext()) {
            result = iterator.next();
            if (result.year == 2020 && result.month == DEC && result.monthday == 23) {
                break;
            }
        }
        assertEquals(new Day(WED, DEC, 23, 2020), result);
    }
}