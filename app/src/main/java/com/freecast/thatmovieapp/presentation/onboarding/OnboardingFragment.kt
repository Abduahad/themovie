package com.freecast.thatmovieapp.presentation.onboarding

import android.view.View
import com.freecast.thatmovieapp.R
import com.freecast.thatmovieapp.core.ui.BaseFragment
import com.google.android.material.button.MaterialButton

class OnboardingFragment : BaseFragment(R.layout.fragment_onboarding), View.OnClickListener {

    override fun onInitViews() {
        super.onInitViews()
        findViewByID<MaterialButton>(R.id.button).setOnClickListener(this)
    }

    override fun onClick(view: View?) {

    }

    companion object {
        fun newInstance() = OnboardingFragment()
    }
}