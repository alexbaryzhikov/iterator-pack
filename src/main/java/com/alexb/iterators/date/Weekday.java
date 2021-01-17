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
