package com.oggy.expensetrackerapi.controller;

import com.oggy.expensetrackerapi.entity.Expense;
import com.oggy.expensetrackerapi.service.ExpenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ExpenseController {

    @Autowired
    private ExpenseService expenseService;

    @GetMapping("/expenses")
    public List<Expense> getAllExpenses(){
        return expenseService.getAllExpenses();
    }

    @GetMapping("/expensesById")
    public Expense getExpenseById(@RequestParam("expense-id") Long id){
        return expenseService.getExpenseById(id);
    }

    @ResponseStatus(value=HttpStatus.NO_CONTENT)
    @DeleteMapping("/expenses")
    public void deleteExpenseById(@RequestParam Long id){
        expenseService.deleteExpenseById(id);
    }

    @ResponseStatus(value = HttpStatus.CREATED)
    @PostMapping("/expenses")
    public Expense saveExpenseDetails(@RequestBody Expense expense){
        return expenseService.saveExpenseDetails(expense);
    }


    @PutMapping("/expenses/{id}")
    public Expense updateExpenseDetails(@RequestBody Expense expense, @PathVariable Long id){
        return expenseService.updateExpenseDetails(id, expense);
    }

}
