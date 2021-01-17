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
