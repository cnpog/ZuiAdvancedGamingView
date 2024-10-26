package io.github.cnpog.gamingview.ui.gamingview

import android.content.Context
import android.content.pm.ApplicationInfo
import android.content.pm.PackageManager
import android.util.Log
import androidx.collection.LruCache
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.github.cnpog.gamingview.settings.AppSettings
import io.github.cnpog.gamingview.settings.WideVisionWriter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import android.graphics.drawable.Drawable
class GamingviewViewModel(
    private val context: Context,
    private val wideVisionWriter: WideVisionWriter
) : ViewModel() {

    private val _installedApps = MutableLiveData<List<AppItem>>()
    val installedApps: LiveData<List<AppItem>> = _installedApps

    val iconCache: LruCache<String, Drawable> = LruCache(100)

    fun loadInstalledApps() {
        viewModelScope.launch {
            val installedApps = withContext(Dispatchers.IO) {
                val packageManager: PackageManager = context.packageManager
                val allApps: List<ApplicationInfo> = packageManager.getInstalledApplications(PackageManager.GET_META_DATA)
                allApps.filter { it.flags and ApplicationInfo.FLAG_SYSTEM == 0 }
            }

            val appItemsList = installedApps.map { appInfo ->
                val settings: AppSettings? = wideVisionWriter.getSettings(appInfo.packageName)
                if (settings != null) {
                    AppItem(
                        applicationInfo = appInfo,
                        mode = settings.mode,
                        position = settings.position
                    )
                } else {
                    AppItem(
                        applicationInfo = appInfo,
                    )
                }
            }
            val sortedAppItemsList = appItemsList.sortedWith(compareBy<AppItem> {
                it.mode?.priority ?: Int.MAX_VALUE
            }.thenBy { it.mode?.priority })
            _installedApps.value = sortedAppItemsList
            Log.d("GamingviewViewModel", "Loaded ${appItemsList.size} apps with settings.")
        }
    }
}
