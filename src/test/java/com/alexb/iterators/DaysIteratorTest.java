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