package com.odde.tdd;

import java.time.LocalDate;

public class Period {
    private final LocalDate start;
    private final LocalDate end;

    public Period(LocalDate start, LocalDate end) {
        this.start = start;
        this.end = end;
    }

    private int getDayCount() {
        return start.isAfter(end) ? 0 : end.getDayOfMonth() - start.getDayOfMonth() + 1;
    }

    int getOverlappingDayCount(Period another) {
        LocalDate startOfOverlapping = start.isAfter(another.start) ? start : another.start;
        LocalDate endOfOverlapping = end.isBefore(another.end) ? end : another.end;
        return new Period(startOfOverlapping, endOfOverlapping).getDayCount();
    }
}
