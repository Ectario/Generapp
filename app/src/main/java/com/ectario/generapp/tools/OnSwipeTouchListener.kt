package com.ectario.generapp.tools

import android.annotation.SuppressLint
import android.content.Context
import android.view.GestureDetector
import android.view.MotionEvent
import android.view.View
import kotlin.math.abs

open class OnSwipeTouchListener(ctx: Context, swipeThreshhold : Int = 100, swipeVelocityThreshhold : Int = 100) : View.OnTouchListener {

    private val gestureDetector: GestureDetector
    private val swipeTH : Int
    private val swipeVelocityTH : Int

    init {
        gestureDetector = GestureDetector(ctx, GestureListener())
        swipeTH = swipeThreshhold
        swipeVelocityTH = swipeVelocityThreshhold
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onTouch(v: View, event: MotionEvent): Boolean {
        return gestureDetector.onTouchEvent(event)
    }

    private inner class GestureListener : GestureDetector.SimpleOnGestureListener() {


        override fun onDown(e: MotionEvent): Boolean {
            return true
        }

        override fun onFling(e1: MotionEvent, e2: MotionEvent, velocityX: Float, velocityY: Float): Boolean {
            var result = false
            try {
                val diffY = e2.y - e1.y
                val diffX = e2.x - e1.x
                if (abs(diffX) > abs(diffY)) {
                    if (abs(diffX) > swipeTH && abs(velocityX) > swipeVelocityTH) {
                        if (diffX > 0) {
                            onSwipeRight()
                        } else {
                            onSwipeLeft()
                        }
                        result = true
                    }
                } else if (abs(diffY) > swipeTH && abs(velocityY) > swipeVelocityTH) {
                    if (diffY > 0) {
                        onSwipeUp()
                    } else {
                        onSwipeDown()
                    }
                    result = true
                }
            } catch (exception: Exception) {
                exception.printStackTrace()
            }

            return result
        }


    }

    open fun onSwipeRight() {}

    open fun onSwipeLeft() {}

    open fun onSwipeUp() {}

    open fun onSwipeDown() {}
}