package com.example.chris.fragmentcircularreveal

import android.app.FragmentManager
import android.app.FragmentTransaction

/**
 * Created by Chris on 7/10/19.
 */
inline fun FragmentManager.open(block: FragmentTransaction.() -> Unit) {
    beginTransaction().apply {
        block()
    }.commit()
}