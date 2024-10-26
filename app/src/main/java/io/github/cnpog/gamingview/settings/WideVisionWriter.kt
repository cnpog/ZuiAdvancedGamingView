package io.github.cnpog.gamingview.settings

import android.content.Context
import android.provider.Settings
import android.util.Log
import android.widget.Toast
import androidx.annotation.StringRes
import io.github.cnpog.gamingview.R
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

data class AppSettings(
    val position: Position,
    val mode: Mode
)
enum class Position(val code: String, @StringRes val descriptionRes: Int) {
    CENTER("1", R.string.position_center),
    BOTTOM("2", R.string.position_bottom);

    fun getDescription(context: Context): String {
        return context.getString(descriptionRes)
    }

    companion object {
        fun fromCode(code: String): Position? = entries.find { it.code == code }
    }
}
enum class Mode(val code: String, @StringRes val descriptionRes: Int, val priority: Int, val tabIndex: Int) {
    WIDE("2.1", R.string.mode_wide, 1, 0),
    EXTREME("2.4", R.string.mode_extreme,1, 0),
    SIXTEEN_NINE("1.777", R.string.mode_sixteen_nine,1, 0),
    DEEP("1.2", R.string.mode_deep,1, 1),
    VERTICAL_WIDE("1.6", R.string.mode_vertical_wide,1, 2),
    EXTREME_VERTICAL_WIDE("1.8", R.string.mode_extreme_vertical_wide,1, 2);

    fun getDescription(context: Context): String {
        return context.getString(descriptionRes)
    }

    companion object {
        fun fromCode(code: String): Mode? = entries.find { it.code == code }
    }
}
class WideVisionWriter(private val context: Context) {

    fun processSelectedOptions(selectedOptions: Map<String, String>, appPkgName: String) {
        val wideFieldPackages = Settings.System.getString(context.contentResolver, "enable_wide_field_packages") ?: ""
        if (!wideFieldPackages.contains(appPkgName)) {
            val updatedWideFieldPackages = if (wideFieldPackages.isEmpty()) {
                appPkgName
            } else {
                "$wideFieldPackages:$appPkgName"
            }
            Settings.System.putString(context.contentResolver, "enable_wide_field_packages", updatedWideFieldPackages)
        }
        val settingsName = "zui_ultra_wide_" + appPkgName.replace(".", "_")
        val positionCode = selectedOptions["position"] ?: Position.CENTER.code
        val modeCode = selectedOptions["mode"] ?: Mode.SIXTEEN_NINE.code

        val position = Position.fromCode(positionCode) ?: Position.CENTER
        val mode = Mode.fromCode(modeCode) ?: Mode.SIXTEEN_NINE

        val settingsValue = when (mode) {
            Mode.DEEP -> "1:${Mode.DEEP.code}"
            else -> "${position.code}:${mode.code}"
        }

        Settings.Global.putString(context.contentResolver, settingsName, settingsValue)

        Toast.makeText(context, context.getString(R.string.please_restart_game), Toast.LENGTH_SHORT).show()
    }

    fun resetSettings(appPkgName: String) {
        val settingsName = "zui_ultra_wide_" + appPkgName.replace(".", "_")
        Settings.Global.putString(context.contentResolver, settingsName, "")
        Toast.makeText(context, context.getString(R.string.please_restart_game), Toast.LENGTH_SHORT).show()
    }

    suspend fun getSettings(appPkgName: String): AppSettings? = withContext(Dispatchers.IO) {
        val enabledWideFieldPackagesString = Settings.System.getString(context.contentResolver, "enable_wide_field_packages") ?: ""
        val enabledWideFieldPackages = enabledWideFieldPackagesString.split(":").map { it.trim() }.filter { it.isNotBlank() }

        if (enabledWideFieldPackages.contains(appPkgName)) {
            val settingsName = "zui_ultra_wide_" + appPkgName.replace(".", "_")
            val settingsString = Settings.Global.getString(context.contentResolver, settingsName) ?: ""
            val settings = settingsString.split(":").map { it.trim() }.filter { it.isNotBlank() }

            if (settings.size == 2) {
                val position = Position.fromCode(settings[0]) ?: Position.BOTTOM
                val mode = Mode.fromCode(settings[1]) ?: Mode.SIXTEEN_NINE
                return@withContext AppSettings(position, mode)
            } else {
                Log.w("WideVisionWriter", "Malformed settings for $appPkgName: '$settingsString'")
            }
        } else {
            Log.d("WideVisionWriter", "$appPkgName is not in the enabled wide field packages.")
        }

        return@withContext null
    }
}
