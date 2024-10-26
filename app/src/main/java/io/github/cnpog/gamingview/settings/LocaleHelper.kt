// src/main/java/io/github/cnpog/gamingview/utils/LocaleHelper.kt
package io.github.cnpog.gamingview.utils

import android.content.Context
import android.content.SharedPreferences
import android.content.res.Configuration
import android.preference.PreferenceManager
import java.util.Locale

object LocaleHelper {

    private const val SELECTED_LANGUAGE = "Locale.Helper.Selected.Language"

    fun setLocale(context: Context, languageCode: String) {
        persist(context, languageCode)
        updateResources(context, languageCode)
    }

    fun loadLocale(context: Context) {
        val language = getPersistedData(context, Locale.getDefault().language)
        updateResources(context, language)
    }

    private fun getPersistedData(context: Context, defaultLanguage: String): String {
        val preferences: SharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)
        return preferences.getString(SELECTED_LANGUAGE, defaultLanguage) ?: defaultLanguage
    }

    private fun persist(context: Context, languageCode: String) {
        val preferences: SharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)
        preferences.edit().putString(SELECTED_LANGUAGE, languageCode).apply()
    }

    private fun updateResources(context: Context, languageCode: String) {
        val locale = if (languageCode.contains("-r")) {
            // For locales like zh-rCN
            val parts = languageCode.split("-r")
            Locale(parts[0], parts[1])
        } else {
            Locale(languageCode)
        }
        Locale.setDefault(locale)

        val config = Configuration(context.resources.configuration)
        config.setLocale(locale)
        context.createConfigurationContext(config)
        context.resources.updateConfiguration(config, context.resources.displayMetrics)
    }
}
