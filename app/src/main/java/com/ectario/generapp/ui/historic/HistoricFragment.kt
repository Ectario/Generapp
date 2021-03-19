package com.ectario.generapp.ui.historic

import android.os.Bundle
import android.view.*
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.ectario.generapp.R
import com.ectario.generapp.hash.WordsHasher
import com.ectario.generapp.savemanagement.deleteOldHistoric


class HistoricFragment : Fragment() {
    private var mHistoric : ArrayList<WordsHasher?>? = null

    companion object {
        var historicDeleteLimit = 30
        var numberToDelete =  -1
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_historic, container, false)
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val pullToRefresh: SwipeRefreshLayout = view.findViewById(R.id.pull_to_refresh_historic)
        pullToRefresh.setOnRefreshListener {
            drawHistoric()
            pullToRefresh.isRefreshing = false
        }
        drawHistoric()
        super.onViewCreated(view, savedInstanceState)
    }

    private fun drawHistoric(){
        activity?.findViewById<LinearLayout>(R.id.column_of_historic)?.removeAllViews()
        mHistoric = deleteOldHistoric(
            activity?.applicationContext!!,
            deleteLimit = historicDeleteLimit,
            numberToDeleteArg = numberToDelete,
        )
        mHistoric?.reverse()
        mHistoric?.forEach{
            var textPwd = TextView(context)
            textPwd.text = (it as WordsHasher).getPasswordHashed()
            textPwd.textSize = 20f
            activity?.findViewById<LinearLayout>(R.id.column_of_historic)?.addView(textPwd)
        }
    }
}