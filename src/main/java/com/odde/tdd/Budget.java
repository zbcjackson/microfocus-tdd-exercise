package com.odde.tdd;

import java.time.YearMonth;

public class Budget {
    private final YearMonth month;
    private final long amount;

    public Budget(YearMonth month, long amount) {
        this.month = month;
        this.amount = amount;
    }

    private Period getPeriod() {
        return new Period(month.atDay(1), month.atEndOfMonth());
    }

    long getOverlappingAmount(Period period) {
        return period.getOverlappingDayCount(getPeriod()) * amount / month.lengthOfMonth();
    }
}
