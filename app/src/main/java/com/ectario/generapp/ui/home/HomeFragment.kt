package com.ectario.generapp.ui.home

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.graphics.drawable.AnimationDrawable
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat.getSystemService
import androidx.fragment.app.Fragment
import com.daimajia.androidanimations.library.Techniques
import com.daimajia.androidanimations.library.YoYo
import com.ectario.generapp.HomePageActivity.Companion.applicationContext
import com.ectario.generapp.R
import com.ectario.generapp.hash.WordsHasher
import com.ectario.generapp.savemanagement.loadHistoric
import com.ectario.generapp.savemanagement.saveHistoric
import com.ectario.generapp.tools.*
import com.google.android.material.floatingactionbutton.FloatingActionButton


class HomeFragment : Fragment() {

    private lateinit var root : View
    private val mMARGINTOP_COEF = 0.25f
    private var mLengthPwd = 0
    private var mHistoric : ArrayList<WordsHasher?>? = null
    private lateinit var arrowsAnim : AnimationDrawable

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        root = inflater.inflate(R.layout.fragment_home, container, false)
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        instantiation(view)
        super.onViewCreated(view, savedInstanceState)
    }

    private fun instantiation(view: View = this.root){

        mHistoric = loadHistoric(applicationContext)

        var arrows = view.findViewById<ImageView>(R.id.arrowsId)
        var sizeTextview = view.findViewById<TextView>(R.id.sizeId)
        var generationButton = view.findViewById<FloatingActionButton>(R.id.generationBtnId)
        var passwordTextview = view.findViewById<TextView>(R.id.generatePassword)
        sizeTextview.setMargins(top = (getScreenHeight() * mMARGINTOP_COEF).toInt())

        arrows.setBackgroundResource(R.drawable.anim_list_arrow_down)
        arrowsAnim = arrows.background as AnimationDrawable
//
//        val arrowsDuration = resources.getInteger(R.integer.arrows)
//        arrowsAnim.setEnterFadeDuration(arrowsDuration/2)
//        arrowsAnim.setExitFadeDuration(arrowsDuration)
        arrowsAnim.start()

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
            passwordTextview.text = wh.getPasswordHashed()
            mHistoric?.add(wh)
            setClipboard(applicationContext, wh.getPasswordHashed())
            applicationContext.makeToast("${wh.getPasswordHashed()} copied !")
            saveHistoric(applicationContext, mHistoric)
        }

        view.setOnTouchListener(object : OnSwipeTouchListener(applicationContext,20,50) {
            override fun onSwipeRight() {
                super.onSwipeRight()
                mLengthPwd++
                sizeTextview.text = "size : $mLengthPwd"
            }

            override fun onSwipeLeft() {
                super.onSwipeRight()
                mLengthPwd--
                if (mLengthPwd <= 0) {
                    mLengthPwd = 0; sizeTextview.text = "Random size"
                } else sizeTextview!!.text = "size : $mLengthPwd"
            }

            override fun onSwipeUp() {
                super.onSwipeRight()
            }

            override fun onSwipeDown() {
                super.onSwipeRight()
            }
        })
    }

    private fun setClipboard(context: Context, text: String) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.HONEYCOMB) {
            val clipboard = context.getSystemService(Context.CLIPBOARD_SERVICE) as android.text.ClipboardManager
            clipboard.text = text
        } else {
            val clipboard = context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
            val clip = ClipData.newPlainText("$text copied !", text)
            clipboard.setPrimaryClip(clip)
        }
    }

}