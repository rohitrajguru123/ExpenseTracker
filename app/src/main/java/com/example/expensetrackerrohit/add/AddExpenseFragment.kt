package com.example.expensetrackerrohit.viewmodel

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.expensetrackerrohit.R
import com.example.expensetrackerrohit.viewmodel.AddExpenseViewModel

class AddExpenseFragment : Fragment(R.layout.fragment_add_expense) {

    private lateinit var addExpenseViewModel: AddExpenseViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Initialize ViewModel
        addExpenseViewModel = ViewModelProvider(this).get(AddExpenseViewModel::class.java)

        // Views from layout
        val amountEditText = view.findViewById<EditText>(R.id.amountEditText)
//        val categoryEditText = view.findViewById<EditText>(R.id.categoryEditText)
        val paymentMethodSpinner = view.findViewById<Spinner>(R.id.paymentMethodSpinner)
        val dateEditText = view.findViewById<EditText>(R.id.dateEditText)
        val addExpenseButton = view.findViewById<Button>(R.id.addExpenseButton)

        // Observe success status from ViewModel
        addExpenseViewModel.addExpenseStatus.observe(viewLifecycleOwner) { success ->
            if (success) {
                // Show success message or navigate back
                Toast.makeText(requireContext(), "Expense added successfully", Toast.LENGTH_SHORT).show()
                // You can add navigation logic here to go back or reset the form
            }
        }

        // Observe error message from ViewModel
        addExpenseViewModel.errorMessage.observe(viewLifecycleOwner) { error ->
            // Show error message in the UI (e.g., Toast or TextView)
            Toast.makeText(requireContext(), error, Toast.LENGTH_SHORT).show()
        }

        // Trigger the add expense action when button is clicked
        addExpenseButton.setOnClickListener {
            val amount = amountEditText.text.toString().toDoubleOrNull() ?: 0.0
//            val category = categoryEditText.text.toString()
            val paymentMethod = paymentMethodSpinner.selectedItem.toString()
            val date = dateEditText.text.toString()

            // Call ViewModel to add the expense
//            addExpenseViewModel.addExpense(amount, category, paymentMethod, date)
        }
    }
}
