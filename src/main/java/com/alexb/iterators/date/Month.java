package com.alexb.iterators.date;

public enum Month {
    JAN, FEB, MAR, APR, MAY, JUN, JUL, AUG, SEP, OCT, NOV, DEC;

    public Month next() {
        return values()[(ordinal() + 1) % 12];
    }

    public int days(int year) {
        switch (this) {
            case SEP:
            case APR:
            case JUN:
            case NOV:
                return 30;
            case FEB:
                return year % 4 == 0 && (year % 100 != 0 || year % 400 == 0) ? 29 : 28;
            default:
                return 31;
        }
    }

    @Override
    public String toString() {
        switch (this) {
            case JAN:
                return "January";
            case FEB:
                return "February";
            case MAR:
                return "March";
            case APR:
                return "April";
            case MAY:
                return "May";
            case JUN:
                return "June";
            case JUL:
                return "July";
            case AUG:
                return "August";
            case SEP:
                return "September";
            case OCT:
                return "October";
            case NOV:
                return "November";
            case DEC:
                return "December";
        }
        return null;
    }
}
