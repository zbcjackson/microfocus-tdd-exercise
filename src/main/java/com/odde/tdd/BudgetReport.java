package com.odde.tdd;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class BudgetReport {
    private final BudgetRepo repo;

    public BudgetReport(BudgetRepo repo) {

        this.repo = repo;
    }

    public long totalBudget(Period period)
    {
        List<Budget> budgetList = getTargetBudgets(period.getStart(), period.getEnd());
        long total = 0;
        for (Budget budget: budgetList){
            int dayCount = getOverlappingDayCount(period, new Period(budget.getMonth().atDay(1), budget.getMonth().atEndOfMonth()));
            total += dayCount * budget.getAmount() / budget.getMonth().lengthOfMonth();
        }
        return total;
    }

    private int getOverlappingDayCount(Period period, Period another) {
        LocalDate start = period.getStart().isAfter(another.getStart()) ? period.getStart() : another.getStart();
        LocalDate end = period.getEnd().isBefore(another.getEnd()) ? period.getEnd() : another.getEnd();
        return new Period(start, end).getDayCount();
    }

    private List<Budget> getTargetBudgets(LocalDate start, LocalDate end) {
        List<Budget> budgetList = new ArrayList<>();
        for (Budget budget: repo.findAll()){
            if (budget.getMonth().atDay(1).isEqual(start.withDayOfMonth(1))
                    || budget.getMonth().atDay(1).isAfter(start.withDayOfMonth(1))
                    && budget.getMonth().atDay(1).isEqual(end.withDayOfMonth(1))
                    || budget.getMonth().atDay(1).isBefore(end.withDayOfMonth(1))
            ){
                budgetList.add(budget);
            }
        }
        return budgetList;
    }
}
