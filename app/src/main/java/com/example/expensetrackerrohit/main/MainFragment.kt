package com.example.expensetrackerrohit.main

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.expensetrackerrohit.R
import com.example.expensetrackerrohit.adapter.ExpenseAdapter
import com.example.expensetrackerrohit.viewmodel.MainViewModel

class MainFragment : Fragment(R.layout.fragment_main) {
    private lateinit var mainViewModel: MainViewModel
    private lateinit var adapter: ExpenseAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Initialize ViewModel
        mainViewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        // Find Views
        val recyclerView = view.findViewById<RecyclerView>(R.id.expensesRecyclerView)
        val totalExpenseText = view.findViewById<TextView>(R.id.totalExpenseText)
        val totalOnlineExpenseText = view.findViewById<TextView>(R.id.totalOnlineExpenseText)
        val totalCashExpenseText = view.findViewById<TextView>(R.id.totalCashExpenseText)
        val addExpenseButton = view.findViewById<Button>(R.id.addExpenseButton)

        // Set up RecyclerView
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        adapter = ExpenseAdapter(
            onItemClicked = { expense ->
                // Handle item click, navigate to expense details or perform action
                findNavController().navigate(R.id.action_mainFragment_to_expenseDetailFragment)
            }
        )
        recyclerView.adapter = adapter

        // Handle Add Expense Button Click
        addExpenseButton.setOnClickListener {
            findNavController().navigate(R.id.action_mainFragment_to_addExpenseFragment)
        }

        // Load expenses from ViewModel
        mainViewModel.loadExpenses()

        // Observe expenses data
        mainViewModel.expenses.observe(viewLifecycleOwner) { expenses ->
            if (expenses.isEmpty()) {
                totalExpenseText.text = "No expenses recorded"
            } else {
                adapter.submitList(expenses)
            }
        }

        // Observe total online expenses
        mainViewModel.totalOnlineExpenses.observe(viewLifecycleOwner) { onlineExpenses ->
            totalOnlineExpenseText.text = if (onlineExpenses == 0.0) {
                "No online expenses recorded"
            } else {
                "Total Online Expenses: $$onlineExpenses"
            }
        }

        // Observe total cash expenses
        mainViewModel.totalCashExpenses.observe(viewLifecycleOwner) { cashExpenses ->
            totalCashExpenseText.text = if (cashExpenses == 0.0) {
                "No cash expenses recorded"
            } else {
                "Total Cash Expenses: $$cashExpenses"
            }
        }
    }
}
