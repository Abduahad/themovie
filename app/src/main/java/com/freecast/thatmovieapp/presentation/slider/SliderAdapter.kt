package com.freecast.thatmovieapp.presentation.slider

import android.view.LayoutInflater
import android.view.View
import android.view.View.OnClickListener
import android.view.ViewGroup
import com.freecast.thatmovieapp.R
import com.freecast.thatmovieapp.core.ui.BaseAdapter
import com.freecast.thatmovieapp.core.ui.BaseViewHolder
import com.freecast.thatmovieapp.domain.model.MovieEntity
import com.freecast.thatmovieapp.util.Constants

class SliderAdapter(private val sliders: List<MovieEntity>, private val onClickListener: OnClickListener) : BaseAdapter<MovieEntity, SliderAdapter.ViewHolder>() {

    override fun onCreateViewHolder(vg: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(vg.context).inflate(R.layout.item_slider, vg, false))
    }

    override fun getItemCount(): Int {
        return sliders.size
    }

    override fun onBindViewHolder(vh: ViewHolder, position: Int) {
        vh.bind(sliders[position])
        vh.itemView.apply {
            setOnClickListener(onClickListener)
            tag = sliders[position].id
        }
    }

    class ViewHolder(itemView: View) : BaseViewHolder<MovieEntity>(itemView) {
        override fun bind(item: MovieEntity) {
            loadImage(findViewById(R.id.imageView), findViewById(R.id.progressBar), Constants.BASE_IMAGE_URL + item.backdropPath)
        }
    }
}