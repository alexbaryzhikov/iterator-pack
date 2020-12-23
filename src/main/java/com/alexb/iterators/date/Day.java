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
