package com.expensetracker.expensetracker.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.expensetracker.expensetracker.entities.Expense;

public interface ExpenseRepository extends JpaRepository<Expense, Long> {
}
