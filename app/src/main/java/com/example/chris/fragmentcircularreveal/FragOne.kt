package com.example.chris.fragmentcircularreveal

import android.app.Fragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

/**
 * Created by Chris on 7/10/19.
 */
class FragOne: Fragment(), ExitWithAnimation {
    override var posX: Int? = null
    override var posY: Int? = null

    override fun isToBeExitedWithAnimation(): Boolean = true

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return inflater?.inflate(R.layout.fragment_one, container, false)!!
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view?.startCircularReveal(false)
    }

    companion object {
        fun newInstance(exit: IntArray? = null): FragOne = FragOne().apply {
            if (exit != null && exit.size == 2) {
                posX = exit[0]
                posY = exit[1]
            }
        }
    }
}