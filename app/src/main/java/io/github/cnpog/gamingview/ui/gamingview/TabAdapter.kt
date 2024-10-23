package io.github.cnpog.gamingview.ui.gamingview

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class TabAdapter(fragmentActivity: FragmentActivity) : FragmentStateAdapter(fragmentActivity) {
    override fun getItemCount(): Int = 2 // Number of tabs

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> TabMobaFragment() // First tab
            1 -> TabShooterFragment() // Second tab
            else -> throw IllegalStateException("Invalid position")
        }
    }
}
