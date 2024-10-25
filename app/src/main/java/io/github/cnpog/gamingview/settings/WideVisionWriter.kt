package io.github.cnpog.gamingview.settings

import android.content.Context
import android.provider.Settings
import android.util.Log
import android.widget.Toast
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

data class AppSettings(
    val position: Position,
    val mode: Mode
)
enum class Position(val code: String, val description: String) {
    CENTER("1", "Center"),
    BOTTOM("2", "Bottom");

    companion object {
        fun fromCode(code: String): Position? = values().find { it.code == code }
    }
}
enum class Mode(val code: String, val description: String, val priority: Int) {
    NORMAL("", "Normal", 2),
    WIDE("2.1", "Wide", 1),
    EXTREME("2.4", "Extreme",1),
    SIXTEEN_NINE("1.777", "16:9",1),
    DEEP("1.2", "Deep",1),
    VERTICAL_WIDE("1.6", "Vertical Wide",1),
    EXTREME_VERTICAL_WIDE("1.8", "Extreme Vertical Wide",1);

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
        val modeCode = selectedOptions["mode"] ?: Mode.NORMAL.code

        val position = Position.fromCode(positionCode) ?: Position.CENTER
        val mode = Mode.fromCode(modeCode) ?: Mode.NORMAL

        val settingsValue = when (mode) {
            Mode.NORMAL -> ""
            Mode.DEEP -> "1:${Mode.DEEP.code}"
            else -> "${position.code}:${mode.code}"
        }

        Settings.Global.putString(context.contentResolver, settingsName, settingsValue)

        Toast.makeText(context, "Restart game for changes to take effect", Toast.LENGTH_SHORT).show()
    }

    // New suspend function to get settings
    suspend fun getSettings(appPkgName: String): AppSettings? = withContext(Dispatchers.IO) {
        val enabledWideFieldPackagesString = Settings.System.getString(context.contentResolver, "enable_wide_field_packages") ?: ""
        val enabledWideFieldPackages = enabledWideFieldPackagesString.split(":").map { it.trim() }.filter { it.isNotBlank() }

        if (enabledWideFieldPackages.contains(appPkgName)) {
            val settingsName = "zui_ultra_wide_" + appPkgName.replace(".", "_")
            val settingsString = Settings.Global.getString(context.contentResolver, settingsName) ?: ""
            val settings = settingsString.split(":").map { it.trim() }.filter { it.isNotBlank() }

            if (settings.size == 2) {
                val position = Position.fromCode(settings[0]) ?: Position.CENTER
                val mode = Mode.fromCode(settings[1]) ?: Mode.NORMAL
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
