package com.freecast.thatmovieapp.core.ui

import android.content.Context
import android.content.ContextParams
import android.os.Build
import android.view.View
import androidx.recyclerview.widget.RecyclerView

abstract class BaseViewHolder<T>(protected val view: View) : RecyclerView.ViewHolder(view) {

    protected val context: Context by lazy {
        this.view.context
    }

    protected fun <T : View> findViewById(id: Int): T {
        return view.findViewById(id)
    }

    protected fun getColor(resId: Int): Int {
        return context.getColor(resId)
    }

    abstract fun bind(item: T)
}