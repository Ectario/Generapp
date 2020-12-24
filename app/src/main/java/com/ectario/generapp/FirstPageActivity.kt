package com.ectario.generapp

import android.content.res.Resources
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.marginBottom
import androidx.core.view.marginLeft
import androidx.core.view.marginRight
import com.daimajia.androidanimations.library.Techniques
import com.daimajia.androidanimations.library.YoYo


class FirstPageActivity : AppCompatActivity() {
    private val MARGINTOP_COEF = 0.25 //Use like percentage
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.layout_firstpage)
        var credit_textview = findViewById<RelativeLayout>(R.id.firstpage_credits)
        var welcome_textview = findViewById<TextView>(R.id.firstpage_welcometext)

        var param = welcome_textview.layoutParams as ViewGroup.MarginLayoutParams
        param.setMargins(welcome_textview.marginLeft,
                (Resources.getSystem().displayMetrics.heightPixels * MARGINTOP_COEF).toInt(),
                welcome_textview.marginRight,
                welcome_textview.marginBottom)
        welcome_textview.layoutParams = param

        var handler = Handler()
        credit_textview.visibility = View.INVISIBLE
        welcome_textview.visibility = View.INVISIBLE

        handler.postDelayed(
                {
                    welcome_textview.visibility = View.VISIBLE
                    YoYo.with(Techniques.FadeIn)
                            .duration(1200)
                            .playOn(welcome_textview)
                }
                ,500)

        handler.postDelayed(
                {
                    credit_textview.visibility = View.VISIBLE
                    YoYo.with(Techniques.FadeIn)
                            .duration(1000)
                            .playOn(credit_textview)
                }
                , 700)

    }


    // FULLSCREEN METHODS BELOW
    override fun onWindowFocusChanged(hasFocus: Boolean) {
        super.onWindowFocusChanged(hasFocus)
        if (hasFocus) hideSystemUI()
    }

    private fun hideSystemUI() {
        // Enables regular immersive mode.
        // For "lean back" mode, remove SYSTEM_UI_FLAG_IMMERSIVE.
        // Or for "sticky immersive," replace it with SYSTEM_UI_FLAG_IMMERSIVE_STICKY
        window.decorView.systemUiVisibility = (View.SYSTEM_UI_FLAG_IMMERSIVE
                // Set the content to appear under the system bars so that the
                // content doesn't resize when the system bars hide and show.
                or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                // Hide the nav bar and status bar
                or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                or View.SYSTEM_UI_FLAG_FULLSCREEN)
    }

    // Shows the system bars by removing all the flags
    // except for the ones that make the content appear under the system bars.
    private fun showSystemUI() {
        window.decorView.systemUiVisibility = (View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN)
    }

    /*override fun onClick(arg0: View?) {
        ParticleSystem(this, 100, android.R.drawable.star_on, 800)
                .setFadeOut(500)
                .setSpeedRange(0.1f, 0.25f)
                .oneShot(arg0, 100)

    }*/
}
