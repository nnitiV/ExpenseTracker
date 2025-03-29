package com.expensetracker.expensetracker.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.expensetracker.expensetracker.ExpensetrackerApplication;
import com.expensetracker.expensetracker.entities.Expense;
import com.expensetracker.expensetracker.repositories.ExpenseRepository;

@Controller
@RequestMapping("/")
public class HomepageController {

	private final ExpensetrackerApplication expensetrackerApplication;

	@Autowired
	private ExpenseRepository expenseRepository;

	HomepageController(ExpensetrackerApplication expensetrackerApplication) {
		this.expensetrackerApplication = expensetrackerApplication;
	}

	@GetMapping
	public String doGet(Model model, @RequestParam("expenses") Optional<List<Expense>> expensesFromFilter) {
		if (!expensesFromFilter.isPresent()) {
			List<Expense> expenses = expenseRepository.findAll();
			model.addAttribute("expenses", expenses);
			model.addAttribute("totalExpenses", expenses.stream().mapToDouble(expense -> expense.getAmount()).sum());
		}
		model.addAttribute("expense", new Expense());
		return "homepage";
	}
}
