package com.fake_coder.caretutor.delegates

import android.app.Activity

import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding

import kotlin.reflect.KProperty

class ContentViewDelegate<in R: Activity, out T: ViewDataBinding>(
    @LayoutRes private val layoutRes: Int
) {
    private var value: T? = null

    operator fun getValue(thisRef: R, property: KProperty<*>): T {
        if (value == null) {
            value = DataBindingUtil.setContentView(thisRef, layoutRes)
        }
        return value!!
    }
}