package com.odde.tdd;

import org.junit.Test;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.Arrays;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class BudgetReportTest {
    private BudgetRepo repo = mock(BudgetRepo.class);
    private BudgetReport report = new BudgetReport(repo);

    @Test
    public void no_budget() {
        givenBudgets();
        assertEquals(0, report.totalBudget(new Period(LocalDate.parse("2018-12-01"), LocalDate.parse("2018-12-20"))));
    }

    @Test
    public void query_whole_budget(){
        givenBudgets(new Budget(YearMonth.of(2018, 1), 3100));
        assertEquals(3100, report.totalBudget(new Period(LocalDate.parse("2018-01-01"), LocalDate.parse("2018-01-31"))));
    }

    @Test
    public void query_within_one_budget(){
        givenBudgets(new Budget(YearMonth.of(2018, 12), 3100));
        assertEquals(2000, report.totalBudget(new Period(LocalDate.parse("2018-12-01"), LocalDate.parse("2018-12-20"))));
    }

    @Test
    public void query_before_budget() {
        givenBudgets(new Budget(YearMonth.of(2018, 12), 3100));
        assertEquals(1000, report.totalBudget(new Period(LocalDate.parse("2018-11-25"), LocalDate.parse("2018-12-10"))));
    }

    @Test
    public void query_after_budget() {
        givenBudgets(new Budget(YearMonth.of(2018, 12), 3100));
        assertEquals(700, report.totalBudget(new Period(LocalDate.parse("2018-12-25"), LocalDate.parse("2019-01-10"))));
    }

    @Test
    public void query_out_of_budget() {
        givenBudgets(new Budget(YearMonth.of(2018, 12), 3100));
        assertEquals(0, report.totalBudget(new Period(LocalDate.parse("2019-01-05"), LocalDate.parse("2019-01-10"))));
    }

    @Test
    public void query_across_years() {
        givenBudgets(
                new Budget(YearMonth.of(2017, 12), 3100),
                new Budget(YearMonth.of(2018, 1), 3100),
                new Budget(YearMonth.of(2018, 12), 3100),
                new Budget(YearMonth.of(2019, 1), 3100)
        );
        assertEquals(2700+3100+3100+1000, report.totalBudget(new Period(LocalDate.parse("2017-12-05"), LocalDate.parse("2019-01-10"))));
    }

    @Test
    public void query_multiple_budgets(){
        givenBudgets(new Budget(YearMonth.of(2018, 1), 3100),
                new Budget(YearMonth.of(2018, 2), 2800),
                new Budget(YearMonth.of(2018, 3), 3100),
                new Budget(YearMonth.of(2018, 4), 0),
                new Budget(YearMonth.of(2018, 5), 3100));
        assertEquals(1700 + 2800 + 3100 + 600, report.totalBudget(new Period(LocalDate.parse("2018-01-15"), LocalDate.parse("2018-05-06"))));
    }

    private void givenBudgets(Budget... budgets) {
        when(repo.findAll()).thenReturn(Arrays.asList(budgets));
    }
}
