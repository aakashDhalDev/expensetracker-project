package com.oggy.expensetrackerapi.service;

import com.oggy.expensetrackerapi.entity.Expense;

import java.util.List;

public interface ExpenseService  {
    List<Expense> getAllExpenses();
}
