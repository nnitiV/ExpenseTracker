package com.expensetracker.expensetracker.controllers;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.expensetracker.expensetracker.ExpensetrackerApplication;
import com.expensetracker.expensetracker.SecurityConfiguration;
import com.expensetracker.expensetracker.entities.Expense;
import com.expensetracker.expensetracker.repositories.ExpenseRepository;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@Controller
public class ExpenseController {

	private final SecurityConfiguration securityConfiguration;

	private final ExpensetrackerApplication expensetrackerApplication;
	private static final String[] MONTH_NAME = { "January", "February", "March", "April", "May", "June", "July",
			"August", "September", "October", "November", "December" };

	@Autowired
	private ExpenseRepository expenseRepository;

	ExpenseController(ExpensetrackerApplication expensetrackerApplication,
			SecurityConfiguration securityConfiguration) {
		this.expensetrackerApplication = expensetrackerApplication;
		this.securityConfiguration = securityConfiguration;
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
	public String filterTasks(HttpSession session, 
			@RequestParam Optional<Integer> numberOfMonth,
			@RequestParam Optional<Integer> totalByMonth) {
		if (totalByMonth.isEmpty()) {
			session.setAttribute("totalByMonth", null);
		} else {
			session.setAttribute("totalByMonth", totalByMonth.get());
		}

		if (numberOfMonth.isPresent()) {
			session.setAttribute("numberOfMonth", numberOfMonth.get());
		} else {
			session.setAttribute("numberOfMonth", null);
		}
		return "redirect:/";
	}

	@RequestMapping("/clear")
	public String clearFilters(HttpSession session) {
		session.setAttribute("totalByMonth", null);
		session.setAttribute("numberOfMonth", null);
		return "redirect:/";
	}

}
