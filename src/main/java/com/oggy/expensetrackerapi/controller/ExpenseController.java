package com.oggy.expensetrackerapi.controller;

import com.oggy.expensetrackerapi.entity.Expense;
import com.oggy.expensetrackerapi.service.ExpenseService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.List;

@RestController
public class ExpenseController {

    @Autowired
    private ExpenseService expenseService;

    @GetMapping("/expenses")
    public List<Expense> getAllExpenses(Pageable page){
        return expenseService.getAllExpenses(page).toList();
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
    public Expense saveExpenseDetails(@Valid @RequestBody Expense expense){
        return expenseService.saveExpenseDetails(expense);
    }


    @PutMapping("/expenses/{id}")
    public Expense updateExpenseDetails(@RequestBody Expense expense, @PathVariable Long id){
        return expenseService.updateExpenseDetails(id, expense);
    }

    @GetMapping("/expenses/category")
    public List<Expense> getExpensesByCategory(@RequestParam String category, Pageable page){
        return expenseService.readByCategory(category,page);
    }

    @GetMapping("/expenses/name")
    public List<Expense> getExpensesByName(@RequestParam String name, Pageable page){
        return expenseService.readByName(name,page);
    }

    @GetMapping("/expenses/date")
    public List<Expense> getExpensesByDates(@RequestParam(required = false)Date startDate,
                                            @RequestParam(required = false)Date endDate,
                                            Pageable page){
        return expenseService.readByDate(startDate, endDate, page);
    }

}
