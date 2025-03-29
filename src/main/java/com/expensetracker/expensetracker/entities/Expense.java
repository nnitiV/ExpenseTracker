package com.expensetracker.expensetracker.entities;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Expense {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	private String expenseDescription;
	private double amount;
	LocalDate dateOfEvent;

	public Expense() {
		this.dateOfEvent = LocalDate.now();
	}
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getExpenseDescription() {
		return expenseDescription;
	}

	public void setExpenseDescription(String expenseDescription) {
		this.expenseDescription = expenseDescription;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public LocalDate getDateOfEvent() {
		return dateOfEvent;
	}

	public void setDateOfEvent(LocalDate dateOfEvent) {
		this.dateOfEvent = dateOfEvent;
	}

	@Override
	public String toString() {
		return "Expense [id=" + id + ", expenseDescription=" + expenseDescription + ", amount=" + amount
				+ ", dateAndTimeOfEvent=" + dateOfEvent + "]";
	}

}
