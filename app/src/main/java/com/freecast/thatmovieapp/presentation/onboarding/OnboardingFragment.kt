package com.freecast.thatmovieapp.presentation.onboarding

import android.view.View
import com.freecast.thatmovieapp.R
import com.freecast.thatmovieapp.presentation.home.HomeFragment
import com.freecast.thatmovieapp.core.ui.BaseFragmentWithoutVM
import com.google.android.material.button.MaterialButton

class OnboardingFragment : BaseFragmentWithoutVM(R.layout.fragment_onboarding), View.OnClickListener {

    override fun onInitViews() {
        super.onInitViews()
        findViewByID<MaterialButton>(R.id.button).setOnClickListener(this)
    }

    override fun onClick(view: View?) {
        transaction(
            R.id.fragment_container,
            HomeFragment.newInstance(),
            false
        )
    }

}