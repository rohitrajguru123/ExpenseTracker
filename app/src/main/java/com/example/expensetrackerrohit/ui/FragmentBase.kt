package com.example.expensetrackerrohit.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.google.android.ads.mediationtestsuite.viewmodels.ViewModelFactory

abstract class FragmentBase<VM : ViewModel> : Fragment() {

    // Abstract property to get the ViewModel
    protected lateinit var viewModel: VM

    // Abstract method to initialize the views in the fragment
    abstract fun initializeViews(view: View)

    // Abstract method to observe LiveData or other ViewModel data
    abstract fun observeViewModel()

    // This method will be called when the fragment's view is created
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // Initialize the ViewModel using a custom ViewModelProvider
        viewModel = ViewModelProvider(this, createViewModelFactory())[getViewModelClass()]
        // Initialize the views
        initializeViews(view)
        // Observe the ViewModel data
        observeViewModel()
    }

    // Method to create the ViewModelFactory (custom factory if needed)
    private fun createViewModelFactory(): ViewModelFactory {
        // Example: If you need to pass a repository to the ViewModel, create it here
        val repository = YourRepository() // Example repository, replace it with your actual repository
        return ViewModelFactory(repository) // Pass the repository or any other dependencies
    }

    // A helper method to get the ViewModel class
    private fun getViewModelClass(): Class<VM> {
        val viewModelClass = (javaClass.genericSuperclass as java.lang.reflect.ParameterizedType).actualTypeArguments[0] as Class<VM>
        return viewModelClass
    }
}
