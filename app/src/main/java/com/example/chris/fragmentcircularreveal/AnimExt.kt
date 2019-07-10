package com.example.chris.fragmentcircularreveal

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.view.View
import android.view.ViewAnimationUtils
import android.view.animation.DecelerateInterpolator

/**
 * Created by Chris on 7/10/19.
 */
fun View.startCircularReveal(fromLeft: Boolean) {
    addOnLayoutChangeListener(object : View.OnLayoutChangeListener {
        override fun onLayoutChange(v: View, left: Int, top: Int, right: Int, bottom: Int, oldLeft: Int, oldTop: Int,
                                    oldRight: Int, oldBottom: Int) {
            v.removeOnLayoutChangeListener(this)

            val cx = if (fromLeft) v.left else v.right
            val cy = v.bottom
            val radius = Math.hypot(right.toDouble(), bottom.toDouble()).toInt()

            ViewAnimationUtils.createCircularReveal(v, cx, cy, 0f, radius.toFloat()).apply {
                interpolator = DecelerateInterpolator(2f)
                duration = 1000
                start()
            }
        }
    })
}

fun View.exitCircularReveal(exitX: Int, exitY: Int, block: () -> Unit) {
    val startRadius = Math.hypot(width.toDouble(), height.toDouble())

    ViewAnimationUtils.createCircularReveal(this, exitX, exitY, startRadius.toFloat(), 0f).apply {
        duration = 350
        interpolator = DecelerateInterpolator(1f)
        addListener(object : AnimatorListenerAdapter() {
            override fun onAnimationEnd(animation: Animator?) {
                block()
                super.onAnimationEnd(animation)
            }
        })

        start()
    }
}

fun View.findLocationOfCenterOnTheScreen(): IntArray {
    val positions = intArrayOf(0, 0)
    getLocationInWindow(positions)

    positions[0] = positions[0] + width/2
    positions[1] = positions[1] + height / 2

    return positions
}

interface ExitWithAnimation {
    var posX: Int?
    var posY: Int?

    fun isToBeExitedWithAnimation(): Boolean
}