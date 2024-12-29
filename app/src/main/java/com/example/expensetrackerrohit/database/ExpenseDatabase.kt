package com.example.expensetrackerrohit.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.expensetrackerrohit.model.Expense

@Database(entities = [Expense::class], version = 1, exportSchema = false)
abstract class ExpenseDatabase : RoomDatabase() {
    abstract fun expenseDao(): ExpenseDao
}
