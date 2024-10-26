package io.github.cnpog.gamingview.ui.gamingview

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import io.github.cnpog.gamingview.settings.Mode
import io.github.cnpog.gamingview.settings.Position

class TabAdapter(
    fragmentActivity: FragmentActivity,
     private val mode: Mode?,
     private val appPosition: Position?
) : FragmentStateAdapter(fragmentActivity) {
    override fun getItemCount(): Int = 3

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> TabMobaFragment().apply {
                arguments = Bundle().apply {
                    putSerializable("mode", mode)
                    putSerializable("position", this@TabAdapter.appPosition)
                }
            }
            1 -> TabShooterFragment().apply {
                arguments = Bundle().apply {
                    putSerializable("mode", mode)
                    putSerializable("position", this@TabAdapter.appPosition)
                }
            }
            2 -> TabVerticalFragment().apply {
                arguments = Bundle().apply {
                    putSerializable("mode", mode)
                    putSerializable("position", this@TabAdapter.appPosition)
                }
            }
            else -> throw IllegalStateException("Invalid position")
        }
    }

}
