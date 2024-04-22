package com.freecast.thatmovieapp.core.util

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer

class SingleLiveData<T> : MutableLiveData<T>() {

    private var isHandled = false

    override fun observe(owner: LifecycleOwner, observer: Observer<in T>) {
        super.observe(owner) {
            if (isHandled) return@observe
            observer.onChanged(it)
            isHandled = true
        }
    }

    override fun setValue(value: T?) {
        isHandled = false
        super.setValue(value)
    }

    /**
     * Sets the value and allows to observe it even if it was handled before.
     * @param value The new value
     */
    fun setValueAllowReplay(value: T?) {
        isHandled = false
        super.setValue(value)
    }

    /**
     * Sets the value and allows to observe it even if it was handled before, but only once.
     * @param value The new value
     */
    fun setValueOnce(value: T?) {
        isHandled = true
        super.setValue(value)
    }
}