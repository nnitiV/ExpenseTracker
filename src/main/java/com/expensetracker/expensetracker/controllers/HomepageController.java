package com.expensetracker.expensetracker.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.expensetracker.expensetracker.ExpensetrackerApplication;
import com.expensetracker.expensetracker.entities.Expense;
import com.expensetracker.expensetracker.repositories.ExpenseRepository;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/")
public class HomepageController {

	private final ExpensetrackerApplication expensetrackerApplication;
	private static final String[] MONTH_NAME = { "January", "February", "March", "April", "May", "June", "July",
			"August", "September", "October", "November", "December" };
	
	@Autowired
	private ExpenseRepository expenseRepository;

	HomepageController(ExpensetrackerApplication expensetrackerApplication) {
		this.expensetrackerApplication = expensetrackerApplication;
	}

	@GetMapping
	public String doGet(Model model, HttpSession session) {
		List<Expense> expenses = expenseRepository.findAll();
		
		Optional<Integer> numberOfMonth = Optional.ofNullable(session.getAttribute("numberOfMonth"))
				.map(attr -> attr instanceof Integer ? (Integer) attr : Integer.valueOf(attr.toString()));
		if (numberOfMonth.isPresent()) {
			expenses = expenses.stream()
					.filter(expense -> expense.getDateOfEvent().getMonthValue() == numberOfMonth.get()).toList();
			model.addAttribute("numberOfMonth", numberOfMonth.get());
		} else {
			model.addAttribute("numberOfMonth", null);
		}
		
		Optional<Integer> totalByMonth = Optional.ofNullable(session.getAttribute("totalByMonth"))
				.map(attr -> attr instanceof Integer ? (Integer) attr : Integer.valueOf(attr.toString()));
		if (totalByMonth.isPresent()) {
			model.addAttribute("totalExpenses",
					"Total expenses by " + MONTH_NAME[totalByMonth.get()-1 < 0 ? totalByMonth.get() : totalByMonth.get()-1] + ": $" + expenses.stream()
							.filter(expense -> expense.getDateOfEvent().getMonthValue() == totalByMonth.get())
							.mapToDouble(expense -> expense.getAmount()).sum());
			model.addAttribute("totalbyMonth", totalByMonth.get());
		} else {
			model.addAttribute("totalExpenses",
					"Total expenses: $" + expenses.stream().mapToDouble(expense -> expense.getAmount()).sum());
			model.addAttribute("totalbyMonth", null);
		}
		
		model.addAttribute("expenses", expenses);
		model.addAttribute("expense", new Expense());
		return "homepage";
	}
}
