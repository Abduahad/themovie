package com.freecast.thatmovieapp.core.ui

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.annotation.IdRes
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment

abstract class BaseFragment(@LayoutRes protected val layoutResID: Int) : Fragment(layoutResID) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        onInitViews()
        onInitListeners()
        onInitObservers()
    }

    protected fun <T : View> findViewByID(@IdRes id: Int): T {
        return requireView().findViewById(id)
    }

    protected fun transaction(
        containerId: Int,
        fragment: Fragment,
        addToBackStack: Boolean = false,
        tag: String? = null,
        isReplace: Boolean = true
    ) {
        activity?.let {
            it.supportFragmentManager.beginTransaction().apply {
                if (isReplace) {
                    replace(containerId, fragment, tag)
                } else {
                    add(containerId, fragment, tag)
                }
                if (addToBackStack) {
                    addToBackStack(tag)
                }
                commit()
            }
        }

    }

    protected fun showToast(message: String, duration: Int = Toast.LENGTH_SHORT) {
        context?.let {
            Toast.makeText(it, message, duration).show()
        }
    }

    protected open fun onInitViews() = Unit
    protected open fun onInitObservers() = Unit
    protected open fun onInitListeners() = Unit
}