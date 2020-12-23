package com.alexb.iterators.date;

public enum Weekday {
    SUN, MON, TUE, WED, THU, FRI, SAT;

    public Weekday next() {
        return values()[(ordinal() + 1) % 7];
    }

    @Override
    public String toString() {
        switch (this) {
            case SUN:
                return "Sunday";
            case MON:
                return "Monday";
            case TUE:
                return "Tuesday";
            case WED:
                return "Wednesday";
            case THU:
                return "Thursday";
            case FRI:
                return "Friday";
            case SAT:
                return "Saturday";
        }
        return null;
    }
}
