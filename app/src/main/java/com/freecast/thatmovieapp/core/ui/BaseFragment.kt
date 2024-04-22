package com.freecast.thatmovieapp.core.ui

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.annotation.LayoutRes
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.freecast.thatmovieapp.core.util.ErrorHandler

abstract class BaseFragment<VM : BaseViewModel>(@LayoutRes layoutResID: Int, protected val mVModelClass: Class<VM>) : BaseFragmentWithoutVM(layoutResID) {
    protected lateinit var viewModel: VM
    override fun onAttach(context: Context) {
        super.onAttach(context)
        viewModel = ViewModelProvider(this)[mVModelClass]
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.errorHandler.observe(viewLifecycleOwner) {
            ErrorHandler(requireContext()).handleError(it)
        }
        viewModel.isLoading.observe(viewLifecycleOwner, Observer {
            isLoading(it)
        })
    }
}