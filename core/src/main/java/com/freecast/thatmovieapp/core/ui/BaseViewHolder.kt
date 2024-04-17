package com.freecast.thatmovieapp.core.ui

import android.content.Context
import android.view.View
import androidx.recyclerview.widget.RecyclerView

abstract class BaseViewHolder<T>(protected val view: View) : RecyclerView.ViewHolder(view) {

    protected val context: Context by lazy {
        this.view.context
    }

    protected fun <T : View> findViewById(id: Int): T {
        return view.findViewById(id)
    }

    abstract fun bind(item: T)
}