package com.freecast.thatmovieapp.presentation.slider

import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnClickListener
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import androidx.viewpager2.widget.ViewPager2
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestListener
import com.freecast.thatmovieapp.R
import com.freecast.thatmovieapp.core.ui.BaseAdapter
import com.freecast.thatmovieapp.core.ui.BaseViewHolder
import com.freecast.thatmovieapp.domain.model.Movie
import com.freecast.thatmovieapp.util.Constants

class SliderAdapter(private val sliders: List<Movie>, private val onClickListener: OnClickListener) : BaseAdapter<Movie, SliderAdapter.ViewHolder>() {

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

    inner class ViewHolder(itemView: View) : BaseViewHolder<Movie>(itemView) {
        private val imageView: ImageView = findViewById(R.id.imageView)
        private val progressBar: ProgressBar = findViewById(R.id.progressBar)
        override fun bind(item: Movie) {
            Glide.with(context).load(Constants.BASE_IMAGE_URL + item.backdropPath).transform(CenterCrop(), RoundedCorners(32)).listener(object : RequestListener<Drawable> {
                override fun onLoadFailed(
                    e: GlideException?, model: Any?,
                    target: com.bumptech.glide.request.target.Target<Drawable>?,
                    isFirstResource: Boolean
                ): Boolean {
                    progressBar.visibility = View.GONE
                    return false
                }

                override fun onResourceReady(
                    resource: Drawable?,
                    model: Any?,
                    target: com.bumptech.glide.request.target.Target<Drawable>?,
                    dataSource: DataSource?,
                    isFirstResource: Boolean
                ): Boolean {
                    progressBar.visibility = View.GONE
                    return false
                }
            }).into(imageView)

        }
    }
}