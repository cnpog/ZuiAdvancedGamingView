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
import io.github.cnpog.gamingview.settings.Mode
import io.github.cnpog.gamingview.settings.Position

class TabMobaFragment : Fragment() {

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
        radioGroupTop.addView(RadioButton(context).apply { text = Position.CENTER.description; tag = Position.CENTER.code})
        radioGroupTop.addView(RadioButton(context).apply { text = Position.BOTTOM.description; tag = Position.BOTTOM.code})

        // Add buttons to the bottom RadioGroup
        radioGroupBottom.addView(RadioButton(context).apply { text = Mode.NORMAL.description; tag = Mode.NORMAL.code })
        radioGroupBottom.addView(RadioButton(context).apply { text = Mode.WIDE.description; tag = Mode.WIDE.code })
        radioGroupBottom.addView(RadioButton(context).apply { text = Mode.EXTREME.description; tag = Mode.EXTREME.code })
        radioGroupBottom.addView(RadioButton(context).apply { text = Mode.SIXTEEN_NINE.description; tag =  Mode.SIXTEEN_NINE.code })
    }
}
