package com.fake_coder.caretutor.delegates

import android.app.Activity
import androidx.annotation.LayoutRes
import androidx.databinding.BaseObservable
import androidx.databinding.ViewDataBinding

// ... @get:Bindable var dogName by bind("", BR.dogName)
fun <R: BaseObservable, T: Any> bind(
    value: T,
    bindingRes: Int
): PropertyDelegate<R, T> = PropertyDelegate(value, bindingRes)

// ... val binding: ActivityMainBinding by layoutResource(R.layoutResource.activity_main)
fun <R: Activity, T: ViewDataBinding> layoutResource(
    @LayoutRes layoutRes: Int
): ContentViewDelegate<R, T> = ContentViewDelegate(layoutRes)