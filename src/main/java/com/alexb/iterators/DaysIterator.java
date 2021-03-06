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
import com.alexb.iterators.date.Month;
import com.alexb.iterators.date.Weekday;

import java.util.Iterator;

import static com.alexb.iterators.date.Month.JAN;
import static com.alexb.iterators.date.Weekday.MON;

/**
 * Generates consecutive days starting from Monday, January 1, 1900.
 * <p>
 * Each day includes day of the week, day of the month, month, and year.
 */
public class DaysIterator implements Iterator<Day> {
    private Day day = new Day(MON, JAN, 1, 1900);

    @Override
    public boolean hasNext() {
        return true;
    }

    @Override
    public Day next() {
        Weekday weekday = day.weekday.next();
        int day = this.day.monthday == this.day.month.days(this.day.year) ? 1 : this.day.monthday + 1;
        Month month = day == 1 ? this.day.month.next() : this.day.month;
        int year = month == JAN && day == 1 ? this.day.year + 1 : this.day.year;
        Day result = this.day;
        this.day = new Day(weekday, month, day, year);
        return result;
    }
}
