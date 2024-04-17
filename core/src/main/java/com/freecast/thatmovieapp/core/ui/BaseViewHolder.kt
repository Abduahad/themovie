package com.freecast.thatmovieapp.core.ui

import android.content.Context
import android.view.View
import androidx.recyclerview.widget.RecyclerView

abstract class BaseViewHolder<T>(protected val itemView: View) : RecyclerView.ViewHolder(itemView) {

    protected val context: Context by lazy {
        itemView.context
    }

    protected fun <T : View> findViewById(id: Int): T {
        return itemView.findViewById(id)
    }

    abstract fun bind(item: T)
}