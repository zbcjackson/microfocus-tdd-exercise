package com.odde.tdd;

public class BudgetReport {
    private final BudgetRepo repo;

    public BudgetReport(BudgetRepo repo) {

        this.repo = repo;
    }

    public long totalBudget(Period period)
    {
        return repo.findAll().stream().mapToLong(budget -> budget.getOverlappingAmount(period)).sum();
    }

}
