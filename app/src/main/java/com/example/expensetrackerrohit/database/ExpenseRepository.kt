package com.example.expensetrackerrohit.database

import com.example.expensetrackerrohit.model.Expense

class ExpenseRepository(private val expenseDao: ExpenseDao) {

    suspend fun addExpense(expense: Expense) {
        expenseDao.insertExpense(expense)
    }

    suspend fun getAllExpenses(): List<Expense> {
        return expenseDao.getAllExpenses()
    }

    suspend fun getTotalOnlineExpenses(): Double {
        return expenseDao.getTotalOnlineExpenses()
    }

    suspend fun getTotalCashExpenses(): Double {
        return expenseDao.getTotalCashExpenses()
    }
}
