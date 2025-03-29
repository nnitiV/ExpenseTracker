package com.expensetracker.expensetracker.controllers;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.List;

import org.eclipse.tags.shaded.org.apache.bcel.generic.NEW;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.expensetracker.expensetracker.ExpensetrackerApplication;
import com.expensetracker.expensetracker.entities.Expense;
import com.expensetracker.expensetracker.repositories.ExpenseRepository;

@Controller
public class ExpenseController {

	private final ExpensetrackerApplication expensetrackerApplication;
	private static final String[] monthNames = { "January", "February", "March", "April", "May", "June", "July",
			"August", "September", "October", "November", "December" };

	@Autowired
	private ExpenseRepository expenseRepository;

	ExpenseController(ExpensetrackerApplication expensetrackerApplication) {
		this.expensetrackerApplication = expensetrackerApplication;
	}

	@RequestMapping(value = "/", method = RequestMethod.POST)
	public String saveExpense(@ModelAttribute Expense expense) {
		expenseRepository.save(expense);
		return "redirect:/";
	}

	@RequestMapping("/update_task")
	public String updateExpense(@ModelAttribute Expense expense) {
		expenseRepository.save(expense);
		return "redirect:/";
	}

	@RequestMapping("/delete_task")
	public String deleteTask(@RequestParam("task_id") long taskId) {
		expenseRepository.deleteById(taskId);
		return "redirect:/";
	}

	@RequestMapping("/filter_tasks")
	public String filterTasks(@RequestParam("numberOfMonth") int numberofMonth, Model model) {
		List<Expense> expenses = expenseRepository.findAll();
		expenses = expenses.stream().filter(expense -> expense.getDateOfEvent().getMonthValue() == numberofMonth)
				.toList();
		model.addAttribute("expenses", expenses);
		model.addAttribute("expense", new Expense());
		return "homepage";
	}

	@RequestMapping("/get_total_by_month")
	public String getTotalByMonth(Model model, @RequestParam int month) {
		List<Expense> expenses = expenseRepository.findAll();
		model.addAttribute("expenses", expenses);
		model.addAttribute("totalExpenses", "Total expenses from month " + monthNames[month-1] + ": " + expenses.stream().filter(expense -> expense.getDateOfEvent().getMonthValue() == month).toList().stream().mapToDouble(Expense::getAmount).sum());
		model.addAttribute("expense", new Expense());
		return "homepage";
	}

}
