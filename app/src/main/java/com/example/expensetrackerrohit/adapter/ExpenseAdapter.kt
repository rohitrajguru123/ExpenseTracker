package com.example.expensetrackerrohit.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.expensetrackerrohit.databinding.ItemExpenseBinding
import com.example.expensetrackerrohit.model.Expense
import java.text.NumberFormat
import java.text.SimpleDateFormat
import java.util.*

class ExpenseAdapter(
    private val onItemClicked: (Expense) -> Unit // Callback for item interactions
) : ListAdapter<Expense, ExpenseAdapter.ExpenseViewHolder>(ExpenseDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExpenseViewHolder {
        val binding = ItemExpenseBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ExpenseViewHolder(binding, onItemClicked)
    }

    override fun onBindViewHolder(holder: ExpenseViewHolder, position: Int) {
        val expense = getItem(position)
        holder.bind(expense)
    }

    class ExpenseViewHolder(
        private val binding: ItemExpenseBinding,
        private val onItemClicked: (Expense) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(expense: Expense) {
            // Format currency
            val formattedAmount = NumberFormat.getCurrencyInstance(Locale.getDefault()).format(expense.amount)

            // Format date
            val formattedDate = try {
                val dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
                dateFormat.format(SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).parse(expense.date))
            } catch (e: Exception) {
                expense.date // Use raw date if parsing fails
            }

            // Bind data to views
            binding.expenseAmountText.text = formattedAmount
            binding.expenseCategoryText.text = expense.category ?: "Unknown"
//            binding.dateTextView.text = formattedDate

            // Set click listener
            binding.root.setOnClickListener {
                onItemClicked(expense)
            }
        }
    }

    class ExpenseDiffCallback : DiffUtil.ItemCallback<Expense>() {
        override fun areItemsTheSame(oldItem: Expense, newItem: Expense): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Expense, newItem: Expense): Boolean {
            return oldItem == newItem
        }
    }
}
