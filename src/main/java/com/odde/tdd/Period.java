package com.odde.tdd;

import java.time.LocalDate;

public class Period {
    private final LocalDate start;
    private final LocalDate end;

    public Period(LocalDate start, LocalDate end) {
        this.start = start;
        this.end = end;
    }

    public LocalDate getStart() {
        return start;
    }

    public LocalDate getEnd() {
        return end;
    }

    int getDayCount() {
        return start.isAfter(end) ? 0 : end.getDayOfMonth() - start.getDayOfMonth() + 1;
    }
}
