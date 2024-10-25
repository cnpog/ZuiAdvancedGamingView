package io.github.cnpog.gamingview.ui.gamingview

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import io.github.cnpog.gamingview.settings.WideVisionWriter

class GamingviewViewModelFactory(
    private val context: Context
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(GamingviewViewModel::class.java)) {
            val wideVisionWriter = WideVisionWriter(context)
            @Suppress("UNCHECKED_CAST")
            return GamingviewViewModel(context, wideVisionWriter) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
    }
}
