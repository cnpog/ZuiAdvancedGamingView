package io.github.cnpog.gamingview.ui.gamingview

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.RadioGroup
import androidx.fragment.app.Fragment
import io.github.cnpog.gamingview.R

class TabShooterFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_tab_shooter, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Initialize RadioGroup
        val radioGroup: RadioGroup = view.findViewById(R.id.radio_group)

        // Add buttons to the RadioGroup
        radioGroup.addView(RadioButton(context).apply { text = "Normal" })
        radioGroup.addView(RadioButton(context).apply { text = "Deep"; tag = "1.2" })
    }
}