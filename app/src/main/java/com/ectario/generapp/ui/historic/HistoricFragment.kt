package com.ectario.generapp.ui.historic

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.ectario.generapp.R
import com.ectario.generapp.hash.WordsHasher
import com.ectario.generapp.savemanagement.loadHistoric

class HistoricFragment : Fragment() {
    private var mHistoric : ArrayList<WordsHasher?>? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val root = inflater.inflate(R.layout.fragment_historic, container, false)
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        mHistoric = loadHistoric(activity?.applicationContext!!)
        mHistoric?.reverse()
        mHistoric?.forEach{
            var textPwd = TextView(context)
            textPwd.text = (it as WordsHasher).getPasswordHashed()
            textPwd.textSize = 20f
            activity?.findViewById<LinearLayout>(R.id.column_of_historic)?.addView(textPwd)
        }
        super.onViewCreated(view, savedInstanceState)
    }
}