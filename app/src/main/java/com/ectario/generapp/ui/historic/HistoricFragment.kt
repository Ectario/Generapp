package com.ectario.generapp.ui.historic

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.ectario.generapp.R

class HistoricFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val root = inflater.inflate(R.layout.fragment_historic, container, false)
        return root
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)

        requireActivity().window.addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN)
        requireActivity().window.addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)

    }
}