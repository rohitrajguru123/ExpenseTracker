package com.example.expensetrackerrohit.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.expensetrackerrohit.model.Expense

@Dao
interface ExpenseDao {
    @Insert
    suspend fun insertExpense(expense: Expense)

    @Query("SELECT * FROM expenses ORDER BY date DESC")
    suspend fun getAllExpenses(): List<Expense>

    @Query("SELECT SUM(amount) FROM expenses WHERE paymentMethod = 'Online'")
    suspend fun getTotalOnlineExpenses(): Double

    @Query("SELECT SUM(amount) FROM expenses WHERE paymentMethod = 'Cash'")
    suspend fun getTotalCashExpenses(): Double
}
