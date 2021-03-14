package com.ectario.generapp.ui.home

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.daimajia.androidanimations.library.Techniques
import com.daimajia.androidanimations.library.YoYo
import com.ectario.generapp.HomePageActivity.Companion.applicationContext
import com.ectario.generapp.R
import com.ectario.generapp.hash.WordsHasher
import com.ectario.generapp.tools.OnSwipeTouchListener
import com.ectario.generapp.tools.getScreenHeight
import com.ectario.generapp.tools.makeToast
import com.ectario.generapp.tools.setMargins
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type


class HomeFragment : Fragment() {

    private lateinit var root : View
    private val mMARGINTOP_COEF = 0.25f
    private var mLengthPwd = 0

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        root = inflater.inflate(R.layout.fragment_home, container, false)
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        instantiation(view)
        super.onViewCreated(view, savedInstanceState)
    }

    private fun instantiation(view : View = this.root){
        var sizeTextview = view.findViewById<TextView>(R.id.sizeId)
        var generationButton = view.findViewById<FloatingActionButton>(R.id.generationBtnId)
        var passwordTextview = view.findViewById<TextView>(R.id.generatePassword)
        sizeTextview.setMargins(top = (getScreenHeight() * mMARGINTOP_COEF).toInt())

        generationButton.setOnClickListener {
            YoYo.with(Techniques.RotateOut)
                    .duration(200)
                    .onEnd {
                        YoYo.with(Techniques.RotateIn)
                                .duration(200)
                                .playOn(generationButton)
                    }
                    .playOn(it)
            var wh = WordsHasher(lenghtPasswordArg = mLengthPwd)
            applicationContext.makeToast(wh.getPasswordHashed())
            passwordTextview.text = wh.getPasswordHashed()
        }

        view.setOnTouchListener(object : OnSwipeTouchListener(applicationContext) {
            override fun onSwipeRight() {
                super.onSwipeRight()
                mLengthPwd++
                sizeTextview.text = "size : $mLengthPwd"
            }

            override fun onSwipeLeft() {
                super.onSwipeRight()
                mLengthPwd--
                if(mLengthPwd<=0) { mLengthPwd = 0; sizeTextview.text = "Random size" }
                else sizeTextview!!.text = "size : $mLengthPwd"
            }

            override fun onSwipeUp() {
                super.onSwipeRight()
            }

            override fun onSwipeDown() {
                super.onSwipeRight()
            }
        })
    }
//    private fun saveHistoric() {
//        val sharedPreferences: SharedPreferences? = activity?.applicationContext?.getSharedPreferences("shared preferences", Context.MODE_PRIVATE)
//        val editor = sharedPreferences?.edit()
//        val gson = Gson()
//        val json = gson.toJson(mHistoric)
//        editor?.putString("historic", json)
//        editor?.apply()
//    }
//
//    private fun loadHistoric() {
//        val sharedPreferences: SharedPreferences? = activity?.applicationContext?.getSharedPreferences("shared preferences", Context.MODE_PRIVATE)
//        val gson = Gson()
//        val json = sharedPreferences?.getString("historic", null)
//        val type: Type = object : TypeToken<ArrayList<WordsHasher?>>() {}.type
//        mHistoric = if (json!=null) gson?.fromJson(json, type) else null
//        if (mHistoric == null) {
//            mHistoric = ArrayList()
//        }
//    }


    private fun hideSystemUI() {
        // Enables regular immersive mode.
        // For "lean back" mode, remove SYSTEM_UI_FLAG_IMMERSIVE.
        // Or for "sticky immersive," replace it with SYSTEM_UI_FLAG_IMMERSIVE_STICKY
        activity?.window?.decorView?.systemUiVisibility = (View.SYSTEM_UI_FLAG_IMMERSIVE
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
        activity?.window?.decorView?.systemUiVisibility = (View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN)
    }
}