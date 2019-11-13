package com.odde.tdd;

import java.time.LocalDate;
import java.time.YearMonth;

public class Budget {
    private final YearMonth month;
    private final long amount;

    public Budget(YearMonth month, long amount) {
        this.month = month;
        this.amount = amount;
    }
    YearMonth getMonth(){
        return month;
    }
    long getAmount(){
        return amount;
    }

    private LocalDate getStart() {
        return month.atDay(1);
    }

    private LocalDate getEnd() {
        return month.atEndOfMonth();
    }

    private Period getPeriod() {
        return new Period(getStart(), getEnd());
    }

    long getOverlappingAmount(Period period) {
        return amount / month.lengthOfMonth() * period.getOverlappingDayCount(getPeriod());
    }
}
