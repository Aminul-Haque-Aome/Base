package com.fake_coder.caretutor.delegates

import androidx.databinding.BaseObservable
import kotlin.reflect.KProperty

class PropertyDelegate<in R: BaseObservable, T: Any>(
    private var value: T,
    private val bindingRes: Int
) {
    operator fun getValue(thisRef: R, property: KProperty<*>): T = value

    operator fun setValue(thisRef: R, property: KProperty<*>, value: T) {
        this.value = value
        thisRef.notifyPropertyChanged(bindingRes)
    }
}