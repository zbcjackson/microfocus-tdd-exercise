package com.odde.tdd;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class BudgetReport {
    private final BudgetRepo repo;

    public BudgetReport(BudgetRepo repo) {

        this.repo = repo;
    }

    public long totalBudget(LocalDate start, LocalDate end)
    {
        List<Budget> budgetList = getTargetBudgets(start, end);
        long total = 0;
        for (Budget budget: budgetList){
            LocalDate start1 = LocalDate.now();
            LocalDate end1 = LocalDate.now().minusDays(1);
            if (budget.getMonth().atDay(1).isEqual(start.withDayOfMonth(1)) && budget.getMonth().atDay(1).isEqual(end.withDayOfMonth(1))) {
                end1 = end;
                start1 = start;
            }else if (budget.getMonth().atDay(1).isEqual(start.withDayOfMonth(1))){
                end1 = budget.getMonth().atEndOfMonth();
                start1 = start;
            }else if (budget.getMonth().atDay(1).isEqual(end.withDayOfMonth(1))){
                start1 = budget.getMonth().atDay(1);
                end1 = end;
            }else if (budget.getMonth().atDay(1).isAfter(start.withDayOfMonth(1)) && budget.getMonth().atDay(1).isBefore(end.withDayOfMonth(1))) {
                end1 = budget.getMonth().atEndOfMonth();
                start1 = budget.getMonth().atDay(1);
            }
            int dayCount = end1.getDayOfMonth() - start1.getDayOfMonth() + 1;
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
