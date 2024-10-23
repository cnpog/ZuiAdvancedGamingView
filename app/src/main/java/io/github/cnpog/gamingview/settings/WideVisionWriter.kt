package io.github.cnpog.gamingview.settings

import android.content.Context
import android.provider.Settings
import android.widget.Toast

class WideVisionWriter(private val context: Context) {

    fun processSelectedOptions(selectedOptions: Map<String, String>, appPkgName: String) {
        val wideFieldPackages = Settings.System.getString(context.contentResolver, "enable_wide_field_packages") ?: ""
        if (!wideFieldPackages.contains(appPkgName)) {
            val updatedWideFieldPackages = if (wideFieldPackages.isEmpty()) {
                appPkgName
            } else {
                "$wideFieldPackages:$appPkgName" // First package name
            }
            Settings.System.putString(context.contentResolver, "enable_wide_field_packages", updatedWideFieldPackages)
        }
        val settingsName = "zui_ultra_wide_" + appPkgName.replace(".", "_")
        val position = selectedOptions["position"]
        val mode = selectedOptions["mode"]
        if (mode.isNullOrEmpty()) {
            Settings.Global.putString(context.contentResolver, settingsName, "")
        } else if (mode == "1.2") {
            Settings.Global.putString(context.contentResolver, settingsName, "1:1.2")
        } else {
            Settings.Global.putString(context.contentResolver, settingsName, "$position:$mode")
        }

        Toast.makeText(context, "Restart game for changes to take effect", Toast.LENGTH_SHORT).show()
    }
}
