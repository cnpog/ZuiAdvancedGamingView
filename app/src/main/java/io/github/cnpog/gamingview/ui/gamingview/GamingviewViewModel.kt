package io.github.cnpog.gamingview.ui.gamingview

import android.content.pm.ApplicationInfo
import android.content.pm.PackageManager
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class GamingviewViewModel : ViewModel() {

    private val _installedApps = MutableLiveData<List<ApplicationInfo>>() // Change to ApplicationInfo
    val installedApps: LiveData<List<ApplicationInfo>> = _installedApps

    fun loadInstalledApps(packageManager: PackageManager) {
        val packages: List<ApplicationInfo> = packageManager.getInstalledApplications(PackageManager.GET_META_DATA)
        val userInstalledApps = packages.filter { it.flags and ApplicationInfo.FLAG_SYSTEM == 0 } // Filter user-installed apps
        _installedApps.value = userInstalledApps
    }
}
