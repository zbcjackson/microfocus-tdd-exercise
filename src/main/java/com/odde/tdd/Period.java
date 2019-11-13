package com.odde.tdd;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Period {
    private final LocalDate start;
    private final LocalDate end;

    public Period(LocalDate start, LocalDate end) {
        this.start = start;
        this.end = end;
    }

    private long getDayCount() {
        return start.isAfter(end) ? 0 : start.until(end, ChronoUnit.DAYS) + 1;
    }

    long getOverlappingDayCount(Period another) {
        LocalDate newStart = start.isAfter(another.start) ? start : another.start;
        LocalDate newEnd = end.isBefore(another.end) ? end : another.end;
        return new Period(newStart, newEnd).getDayCount();
    }
}
