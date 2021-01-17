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

package com.alexb.iterators.date;

import java.util.Objects;

public class Day {
    public Weekday weekday;
    public Month month;
    public int monthday;
    public int year;

    public Day(Weekday weekday, Month month, int monthday, int year) {
        this.weekday = weekday;
        this.monthday = monthday;
        this.month = month;
        this.year = year;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Day day = (Day) o;
        return monthday == day.monthday &&
                year == day.year &&
                weekday == day.weekday &&
                month == day.month;
    }

    @Override
    public int hashCode() {
        return Objects.hash(weekday, monthday, month, year);
    }

    @Override
    public String toString() {
        return weekday + ", " + month + " " + monthday + ", " + year;
    }
}
