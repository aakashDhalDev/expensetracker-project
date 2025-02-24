package com.oggy.expensetrackerapi.service;

import com.oggy.expensetrackerapi.entity.Expense;

import java.util.List;
import java.util.Optional;

public interface ExpenseService  {
    List<Expense> getAllExpenses();

    Expense getExpenseById(Long id);

    void deleteExpenseById(Long id);

    Expense saveExpenseDetails(Expense expense);

    Expense updateExpenseDetails(Long id, Expense expense);
}
