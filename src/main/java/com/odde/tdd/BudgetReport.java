package com.odde.tdd;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class BudgetReport {
    public static long totalBudget(LocalDate start, LocalDate end, List<Budget> budgets)
    {
        List<Budget> budgetList = getTargetBudgets(start, end, budgets);
        long total = 0;
        for (Budget budget: budgetList){
            int month = budget.getMonth().getMonthValue();
            if (month == start.getMonthValue() && month == end.getMonthValue()) {
                total += (end.getDayOfMonth() - start.getDayOfMonth() + 1) * budget.getAmount()/start.lengthOfMonth();
            }else if (month == start.getMonthValue()){
                total += (start.lengthOfMonth() - start.getDayOfMonth() + 1)* budget.getAmount()/start.lengthOfMonth() ;
            }else if (month == end.getMonthValue()){
                total += (end.getDayOfMonth()) * budget.getAmount()/end.lengthOfMonth() ;
            }else {
                total += budget.getAmount();
            }
        }
        return total;
    }
    private static List<Budget> getTargetBudgets(LocalDate start, LocalDate end, List<Budget> budgets) {
        List<Budget> budgetList = new ArrayList<>();
        for (Budget budget: budgets){
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
