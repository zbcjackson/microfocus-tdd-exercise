package com.odde.tdd;

import org.junit.Test;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.Arrays;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class BudgetPlanTest {
    private BudgetRepo repo = mock(BudgetRepo.class);
    private BudgetPlan plan = new BudgetPlan(repo);
    @Test
    public void no_budget() {
        givenBudgets();
        assertEquals(0, plan.query(new Period(LocalDate.of(2019, 10, 4), LocalDate.of(2019, 11, 4))));
    }

    @Test
    public void query_whole_month() {
        givenBudgets(new Budget(YearMonth.of(2019, 10), 3100));
        assertEquals(3100, plan.query(new Period(LocalDate.of(2019, 10, 1), LocalDate.of(2019, 10, 31))));
    }

    @Test
    public void query_one_day_within_one_month() {
        givenBudgets(new Budget(YearMonth.of(2019, 10), 3100));
        assertEquals(100, plan.query(new Period(LocalDate.of(2019, 10, 3), LocalDate.of(2019, 10, 3))));
    }

    @Test
    public void query_two_day_with_one_month() {
        givenBudgets(new Budget(YearMonth.of(2019, 10), 3100));
        assertEquals(200, plan.query(new Period(LocalDate.of(2019, 10, 3), LocalDate.of(2019, 10, 4))));
    }

    @Test
    public void query_before_budget() {
        givenBudgets(new Budget(YearMonth.of(2019, 10), 3100));
        assertEquals(400, plan.query(new Period(LocalDate.of(2019, 9, 25), LocalDate.of(2019, 10, 4))));
    }

    @Test
    public void query_after_budget() {
        givenBudgets(new Budget(YearMonth.of(2019, 10), 3100));
        assertEquals(700, plan.query(new Period(LocalDate.of(2019, 10, 25), LocalDate.of(2019, 11, 4))));
    }

    @Test
    public void query_out_of_budget() {
        givenBudgets(new Budget(YearMonth.of(2019, 10), 3100));
        assertEquals(0, plan.query(new Period(LocalDate.of(2019,9, 2), LocalDate.of(2019, 9, 4))));
    }

    @Test
    public void query_multiple_budgets() {
        givenBudgets(
                new Budget(YearMonth.of(2019, 10), 3100),
        new Budget(YearMonth.of(2019, 11), 300)
        );
        assertEquals(3000 + 40, plan.query(new Period(LocalDate.of(2019,10, 2), LocalDate.of(2019, 11, 4))));
    }

    private void givenBudgets(Budget... budgets) {
        when(repo.findAll()).thenReturn(Arrays.asList(budgets));
    }
}
