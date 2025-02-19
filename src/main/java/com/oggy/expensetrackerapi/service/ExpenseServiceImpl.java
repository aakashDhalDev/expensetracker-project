package com.oggy.expensetrackerapi.service;

import com.oggy.expensetrackerapi.entity.Expense;
import com.oggy.expensetrackerapi.repository.ExpenseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExpenseServiceImpl implements ExpenseService{
    @Autowired
    private ExpenseRepository expenseRepo;

    @Override
    public List<Expense> getAllExpenses(){
        return expenseRepo.findAll();
    }
}
