package io.github.cnpog.gamingview.ui.gamingview

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import io.github.cnpog.gamingview.R

class TabVerticalFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_tab_moba, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Initialize the TextViews for headlines
        val topHeadline: TextView = view.findViewById(R.id.top_headline)
        val bottomHeadline: TextView = view.findViewById(R.id.bottom_headline)
        topHeadline.text = "Game Position"
        bottomHeadline.text = "Select FoV"

        // Initialize RadioGroups
        val radioGroupTop: RadioGroup = view.findViewById(R.id.radio_group_top)
        val radioGroupBottom: RadioGroup = view.findViewById(R.id.radio_group_bottom)

        // Add buttons to the top RadioGroup
        radioGroupTop.addView(RadioButton(context).apply { text = "Center"; tag = "1"})
        radioGroupTop.addView(RadioButton(context).apply { text = "Bottom"; tag = "2"})

        // Add buttons to the bottom RadioGroup
        radioGroupBottom.addView(RadioButton(context).apply { text = "Normal"; tag = "" })
        radioGroupBottom.addView(RadioButton(context).apply { text = "Wide"; tag = "1.6" })
        radioGroupBottom.addView(RadioButton(context).apply { text = "EXTREME"; tag = "1.8" })
    }
}
