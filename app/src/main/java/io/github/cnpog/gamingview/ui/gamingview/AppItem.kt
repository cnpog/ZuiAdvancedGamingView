package io.github.cnpog.gamingview.ui.gamingview

import android.content.pm.ApplicationInfo
import io.github.cnpog.gamingview.settings.Mode
import io.github.cnpog.gamingview.settings.Position

data class AppItem(
    val applicationInfo: ApplicationInfo,
    val mode: Mode? = null,
    val position: Position? = null
)
