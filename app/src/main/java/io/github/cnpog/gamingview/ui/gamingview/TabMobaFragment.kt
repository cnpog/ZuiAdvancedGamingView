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

        val topHeadline: TextView = view.findViewById(R.id.tab_moba_top_headline)
        val bottomHeadline: TextView = view.findViewById(R.id.tab_moba_bottom_headline)
        topHeadline.text = getString(R.string.game_position)
        bottomHeadline.text = getString(R.string.select_fov)

        val radioGroupTop: RadioGroup = view.findViewById(R.id.tab_moba_radio_group_top)
        val radioGroupBottom: RadioGroup = view.findViewById(R.id.tab_moba_radio_group_bottom)

        radioGroupTop.addView(RadioButton(context).apply { text = Position.CENTER.getDescription(context); tag = Position.CENTER.code})
        radioGroupTop.addView(RadioButton(context).apply { text = Position.BOTTOM.getDescription(context); tag = Position.BOTTOM.code})

        radioGroupBottom.addView(RadioButton(context).apply { text = Mode.WIDE.getDescription(context); tag = Mode.WIDE.code })
        radioGroupBottom.addView(RadioButton(context).apply { text = Mode.EXTREME.getDescription(context); tag = Mode.EXTREME.code })
        radioGroupBottom.addView(RadioButton(context).apply { text = Mode.SIXTEEN_NINE.getDescription(context); tag =  Mode.SIXTEEN_NINE.code })

        val mode = arguments?.getSerializable("mode", Mode::class.java)
        val position = arguments?.getSerializable("position", Position::class.java)
        mode?.let {
            val modeButton = radioGroupBottom.findViewWithTag<RadioButton>(it.code)
            if (modeButton != null) {
                radioGroupBottom.check(modeButton.id)
                position?.let { pos ->
                    radioGroupTop.findViewWithTag<RadioButton>(pos.code)?.let { radioGroupTop.check(it.id) }
                }
            }
        }
    }
}
