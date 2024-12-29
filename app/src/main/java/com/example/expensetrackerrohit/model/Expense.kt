package com.example.expensetrackerrohit.model

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Represents an expense record.
 *
 * @param id The unique identifier for the expense.
 * @param amount The amount spent.
 * @param category The category of the expense (e.g., "Groceries", "Entertainment").
 * @param date The date the expense occurred.
 * @param paymentMethod The payment method used (e.g., "Cash", "Credit").
 */
@Entity(tableName = "expenses")
data class Expense(
    @PrimaryKey(autoGenerate = true) val id: Long = 0, // Auto-generated primary key
    val amount: Double,                           // Amount of the expense
    val category: String,                         // Category of the expense
    val date: String,                             // Date of the expense
    val paymentMethod: String                    // Payment method used for the expense
)
