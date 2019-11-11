package com.odde.tdd;

import org.junit.Test;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.Arrays;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class BudgetReportTest {
    @Test
    public void testDecPartial(){
        BudgetRepo repo = mock(BudgetRepo.class);
        when(repo.findAll()).thenReturn(Arrays.asList(
                new Budget(YearMonth.of(2018, 12), 3100)
                        ));
        assertEquals(1, repo.findAll().size());
        assertEquals(2000, BudgetReport.totalBudget(LocalDate.parse("2018-12-01"), LocalDate.parse("2018-12-20"), repo.findAll()));
    }
    @Test
    public void testJanToMay(){
        BudgetRepo repo = mock(BudgetRepo.class);
        when(repo.findAll()).thenReturn(Arrays.asList(
                new Budget(YearMonth.of(2018, 1), 3100),
                new Budget(YearMonth.of(2018, 2), 2800),
                new Budget(YearMonth.of(2018, 3), 3100),
                new Budget(YearMonth.of(2018, 4), 0),
                new Budget(YearMonth.of(2018, 5), 3100)

        ));
        assertEquals(5, repo.findAll().size());
        assertEquals(2800+3100+1700+600, BudgetReport.totalBudget(LocalDate.parse("2018-01-15"), LocalDate.parse("2018-05-06"), repo.findAll()));
    }
    @Test
    public void testWholeMonth(){
        BudgetRepo repo = mock(BudgetRepo.class);
        when(repo.findAll()).thenReturn(Arrays.asList(
                new Budget(YearMonth.of(2018, 1), 2000)
        ));
        assertEquals(1, repo.findAll().size());
        assertEquals(2000, BudgetReport.totalBudget(LocalDate.parse("2018-01-01"), LocalDate.parse("2018-01-31"), repo.findAll()));
    }
}
