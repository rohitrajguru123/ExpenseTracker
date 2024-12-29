package com.example.expensetrackerrohit.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.expensetrackerrohit.database.ExpenseRepository
import com.example.expensetrackerrohit.model.Expense
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class AddExpenseViewModel(private val expenseRepository: ExpenseRepository) : ViewModel() {

    private val _addExpenseStatus = MutableLiveData<Boolean>()
    val addExpenseStatus: LiveData<Boolean> get() = _addExpenseStatus

    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String> get() = _errorMessage

    fun addExpense(amount: Double, category: String, paymentMethod: String, date: String) {
        if (amount <= 0) {
            _errorMessage.value = "Amount must be greater than zero"
            return
        }
        if (category.isEmpty()) {
            _errorMessage.value = "Category cannot be empty"
            return
        }

        val expense = Expense(amount = amount, category = category, paymentMethod = paymentMethod, date = date)

        // You can perform the database insertion in a coroutine
        viewModelScope.launch {
            try {
                withContext(Dispatchers.IO) {
                    expenseRepository.addExpense(expense)
                }
                _addExpenseStatus.value = true
            } catch (e: Exception) {
                _errorMessage.value = "Error adding expense: ${e.message}"
            }
        }
    }
}
