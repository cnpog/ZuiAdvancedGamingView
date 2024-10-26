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
        topHeadline.text = getString(R.string.game_position)
        bottomHeadline.text = getString(R.string.select_fov)

        // Initialize RadioGroups
        val radioGroupTop: RadioGroup = view.findViewById(R.id.radio_group_top)
        val radioGroupBottom: RadioGroup = view.findViewById(R.id.radio_group_bottom)

        // Add buttons to the top RadioGroup
        radioGroupTop.addView(RadioButton(context).apply { text = Position.CENTER.getDescription(context); tag = Position.CENTER.code})
        radioGroupTop.addView(RadioButton(context).apply { text = Position.BOTTOM.getDescription(context); tag = Position.BOTTOM.code})

        // Add buttons to the bottom RadioGroup
        radioGroupBottom.addView(RadioButton(context).apply { text = Mode.NORMAL.getDescription(context); tag = Mode.NORMAL.code })
        radioGroupBottom.addView(RadioButton(context).apply { text = Mode.VERTICAL_WIDE.getDescription(context); tag = Mode.VERTICAL_WIDE.code })
        radioGroupBottom.addView(RadioButton(context).apply { text = Mode.EXTREME_VERTICAL_WIDE.getDescription(context); tag = Mode.EXTREME_VERTICAL_WIDE.code })
    }
}
