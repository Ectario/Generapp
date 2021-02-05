package com.ectario.generapp.ui.home

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.Button
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.coordinatorlayout.widget.CoordinatorLayout
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

class HomeFragment : Fragment() {

    private lateinit var root : View
    private val mMARGINTOP_COEF = 0.25f
    private var mLengthPwd = 0

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        root = inflater.inflate(R.layout.fragment_home, container, false)
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var homeFragment = view?.findViewById<RelativeLayout>(R.id.home_page_id)
        var sizeTextview = homeFragment?.findViewById<TextView>(R.id.sizeId)
        var plusBtn = homeFragment?.findViewById<Button>(R.id.plusBtnId)
        var minusBtn = homeFragment?.findViewById<Button>(R.id.minusBtnId)
        var generationButton = activity?.findViewById<CoordinatorLayout>(R.id.app_bar_main)?.findViewById<FloatingActionButton>(R.id.generationBtnId)
        sizeTextview!!.setMargins(top = (getScreenHeight() * mMARGINTOP_COEF).toInt())

        generationButton!!.setOnClickListener {
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
            view?.findViewById<TextView>(R.id.generatePassword)!!.text = wh.getPasswordHashed()
        }

        plusBtn!!.setOnClickListener {
            YoYo.with(Techniques.RubberBand)
                    .duration(200)
                    .playOn(it)
            mLengthPwd++
            sizeTextview!!.text = "size : $mLengthPwd"

        }

        minusBtn!!.setOnClickListener {
            YoYo.with(Techniques.RubberBand)
                    .duration(200)
                    .playOn(it)
            mLengthPwd--
            if(mLengthPwd<=0) { mLengthPwd = 0; sizeTextview.text = "Random size" }
            else sizeTextview!!.text = "size : $mLengthPwd"

        }

        view?.findViewById<View>(R.id.home_page_id)!!.setOnTouchListener(object : OnSwipeTouchListener(applicationContext){
            override fun onSwipeRight() {
                applicationContext.makeToast("Right swipe")
                super.onSwipeRight()
            }
            override fun onSwipeLeft() {
                super.onSwipeRight()
            }
            override fun onSwipeUp() {
                super.onSwipeRight()
            }
            override fun onSwipeDown() {
                super.onSwipeRight()
            }
        })
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)

        requireActivity().window.addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN)
        requireActivity().window.addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)

    }
}