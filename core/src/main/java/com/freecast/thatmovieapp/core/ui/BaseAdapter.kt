package com.freecast.thatmovieapp.core.ui

import androidx.recyclerview.widget.RecyclerView

abstract class BaseAdapter<T, U>: RecyclerView.Adapter<U>() where U: BaseViewHolder<T> {
}