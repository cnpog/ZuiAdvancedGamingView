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

class TabShooterFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_tab_shooter, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val bottomHeadline: TextView = view.findViewById(R.id.tab_shooter_bottom_headline)
        bottomHeadline.text = getString(R.string.select_fov)

        val radioGroup: RadioGroup = view.findViewById(R.id.tab_shooter_radio_group_bottom)
        radioGroup.addView(RadioButton(context).apply { text = Mode.DEEP.getDescription(context); tag = Mode.DEEP.code })

        val mode = arguments?.getSerializable("mode", Mode::class.java)
        radioGroup.findViewWithTag<RadioButton>(mode?.code)?.let { radioGroup.check(it.id) }

    }
}
