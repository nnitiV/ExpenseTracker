package com.expensetracker.expensetracker.controllers;

import java.util.List;

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
		return "redirect:/";
	}

}
