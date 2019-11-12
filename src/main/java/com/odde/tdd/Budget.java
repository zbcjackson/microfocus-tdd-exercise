package com.odde.tdd;

import java.time.YearMonth;

public class Budget {
    private final YearMonth month;
    private final long amount;

    public Budget(YearMonth month, long amount) {
        this.month = month;
        this.amount = amount;
    }
    public YearMonth getMonth(){
        return month;
    }
    public long getAmount(){
        return amount;
    }

    Period getPeriod() {
        return new Period(month.atDay(1), month.atEndOfMonth());
    }
}
