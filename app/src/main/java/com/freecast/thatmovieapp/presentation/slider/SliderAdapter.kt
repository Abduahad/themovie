package com.freecast.thatmovieapp.presentation.slider

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.viewpager2.widget.ViewPager2
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.freecast.thatmovieapp.R
import com.freecast.thatmovieapp.core.ui.BaseAdapter
import com.freecast.thatmovieapp.core.ui.BaseViewHolder

class SliderAdapter(private val sliders: ArrayList<Slide>, private val viewPager2: ViewPager2) : BaseAdapter<Slide, SliderAdapter.ViewHolder>() {

    override fun onCreateViewHolder(vg: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(vg.context).inflate(R.layout.item_slider, vg, false))
    }

    override fun getItemCount(): Int {
        return sliders.size
    }

    override fun onBindViewHolder(vh: ViewHolder, position: Int) {
        vh.bind(sliders[position])
        if (position == sliders.size - 2) {
            viewPager2.post(runnable)
        }
    }

    inner class ViewHolder(itemView: View) : BaseViewHolder<Slide>(itemView) {
        private val imageView: ImageView = findViewById(R.id.imageView)
        override fun bind(item: Slide) {
            Glide.with(context).load(item.imageURl).transform(CenterCrop(), RoundedCorners(32)).into(imageView)
        }
    }

    private val runnable = Runnable {
        sliders.addAll(sliders)
        notifyDataSetChanged()
    }
}