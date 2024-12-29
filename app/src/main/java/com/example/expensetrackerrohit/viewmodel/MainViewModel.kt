package com.example.expensetrackerrohit.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.expensetrackerrohit.database.ExpenseRepository
import com.example.expensetrackerrohit.model.Expense
import kotlinx.coroutines.launch

class MainViewModel(private val expenseRepository: ExpenseRepository) : ViewModel() {

    // LiveData to expose data to the UI
    private val _expenses = MutableLiveData<List<Expense>>()
    val expenses: LiveData<List<Expense>> get() = _expenses

    private val _totalOnlineExpenses = MutableLiveData<Double>()
    val totalOnlineExpenses: LiveData<Double> get() = _totalOnlineExpenses

    private val _totalCashExpenses = MutableLiveData<Double>()
    val totalCashExpenses: LiveData<Double> get() = _totalCashExpenses

    // Function to load expenses
    fun loadExpenses() {
        viewModelScope.launch {
            try {
                val allExpenses = expenseRepository.getAllExpenses()
                val onlineExpenses = expenseRepository.getTotalOnlineExpenses()
                val cashExpenses = expenseRepository.getTotalCashExpenses()

                // Post data to LiveData
                _expenses.postValue(allExpenses)
                _totalOnlineExpenses.postValue(onlineExpenses)
                _totalCashExpenses.postValue(cashExpenses)

            } catch (e: Exception) {
                // Handle any errors (e.g., network issues or database issues)
                _expenses.postValue(emptyList())  // Post empty list in case of error
                _totalOnlineExpenses.postValue(0.0)
                _totalCashExpenses.postValue(0.0)
            }
        }
    }
}
