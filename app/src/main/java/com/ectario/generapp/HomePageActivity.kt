package com.ectario.generapp

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.daimajia.androidanimations.library.Techniques
import com.daimajia.androidanimations.library.YoYo
import com.ectario.generapp.hash.WordsHasher
import com.ectario.generapp.tools.getScreenHeight
import com.ectario.generapp.tools.makeToast
import com.ectario.generapp.tools.setMargins


class HomePageActivity : AppCompatActivity() {
    private val mMARGINTOP_COEF = 0.25f
    private var mLengthPwd = 0
    init { INSTANCE = this }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        overridePendingTransition(R.anim.swap_activity_fadein, R.anim.swap_activity_fadeout)
        setContentView(R.layout.layout_home_page)
        var size_textview = findViewById<TextView>(R.id.sizeId)
        var plusBtn = findViewById<Button>(R.id.plusBtnId)
        var minusBtn = findViewById<Button>(R.id.minusBtnId)
        var generation_button = findViewById<Button>(R.id.generationBtnId)
        size_textview.setMargins(top = (getScreenHeight() * mMARGINTOP_COEF).toInt())

        generation_button.setOnClickListener {
            YoYo.with(Techniques.RotateOut)
                    .duration(200)
                    .onEnd {
                        YoYo.with(Techniques.RotateIn)
                                .duration(200)
                                .playOn(generation_button)
                    }
                    .playOn(it)
            var wh = WordsHasher(lenghtPasswordArg = mLengthPwd)
            makeToast(wh.getPasswordHashed())
            findViewById<TextView>(R.id.generatePassword).text = wh.getPasswordHashed()
        }

        plusBtn.setOnClickListener {
            mLengthPwd++
            size_textview.text = "size : $mLengthPwd"
        }

        minusBtn.setOnClickListener {
            mLengthPwd--
            if(mLengthPwd<=0) { mLengthPwd = 0; size_textview.text = "Random size" }
            else size_textview.text = "size : $mLengthPwd"
        }
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

    companion object {

        lateinit var INSTANCE: HomePageActivity
            private set

        val applicationContext: Context get() { return INSTANCE.applicationContext }

    }
}