package com.expensetracker.expensetracker.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.expensetracker.expensetracker.entities.Expense;

public interface ExpenseRepository extends JpaRepository<Expense, Long> {
}
