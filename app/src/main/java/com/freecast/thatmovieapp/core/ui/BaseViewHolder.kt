package com.freecast.thatmovieapp.core.ui

import android.content.Context
import android.graphics.drawable.Drawable
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestListener

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

    fun loadImage(imageView: ImageView, progressBar:ProgressBar, url: String) {
        Glide.with(context).load(url).transform(RoundedCorners(16)).listener(object : RequestListener<Drawable> {
            override fun onLoadFailed(e: GlideException?, model: Any?, target: com.bumptech.glide.request.target.Target<Drawable>?, isFirstResource: Boolean): Boolean {
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