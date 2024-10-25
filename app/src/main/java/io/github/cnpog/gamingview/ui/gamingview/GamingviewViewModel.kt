// GamingviewViewModel.kt
package io.github.cnpog.gamingview.ui.gamingview

import android.content.pm.ApplicationInfo
import android.content.pm.PackageManager
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class GamingviewViewModel : ViewModel() {

    private val _installedApps = MutableLiveData<List<ApplicationInfo>>() // Internal mutable LiveData
    val installedApps: LiveData<List<ApplicationInfo>> = _installedApps // Exposed immutable LiveData

    fun loadInstalledApps(packageManager: PackageManager) {
        viewModelScope.launch {
            val userInstalledApps = withContext(Dispatchers.IO) {
                val packages: List<ApplicationInfo> =
                    packageManager.getInstalledApplications(PackageManager.GET_META_DATA)
                packages.filter { it.flags and ApplicationInfo.FLAG_SYSTEM == 0 } // Filter user-installed apps
            }
            _installedApps.value = userInstalledApps
        }
    }
}
