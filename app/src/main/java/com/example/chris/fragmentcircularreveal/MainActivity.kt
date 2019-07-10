package com.example.chris.fragmentcircularreveal

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn_bottom.setOnClickListener {
            val positions = it.findLocationOfCenterOnTheScreen()

            fragmentManager.open {
                add(R.id.container, FragOne.newInstance(positions)).addToBackStack(null)
            }
        }
    }

    override fun onBackPressed() {
        with(fragmentManager.findFragmentById(R.id.container)) {
            if ((this as? ExitWithAnimation)?.isToBeExitedWithAnimation() == true) {
                if (this.posX == null || this.posY == null) {
                    super.onBackPressed()
                } else {
                    this.view?.exitCircularReveal(this.posX!!, this.posY!!) {
                        super.onBackPressed()
                    } ?: super.onBackPressed()
                }
            } else {
                super.onBackPressed()
            }
        }
    }
}
