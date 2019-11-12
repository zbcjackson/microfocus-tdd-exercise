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
            LocalDate end1 = period.getEnd().isBefore(budget.getMonth().atEndOfMonth()) ? period.getEnd() : budget.getMonth().atEndOfMonth();
            LocalDate start1 = period.getStart().isAfter(budget.getMonth().atDay(1)) ? period.getStart() : budget.getMonth().atDay(1);
            int dayCount = new Period(start1, end1).getDayCount();
            total += dayCount * budget.getAmount() / budget.getMonth().lengthOfMonth();
        }
        return total;
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
