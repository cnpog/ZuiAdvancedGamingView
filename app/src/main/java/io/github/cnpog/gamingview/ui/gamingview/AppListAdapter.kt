// AppListAdapter.kt
package io.github.cnpog.gamingview.ui.gamingview

import android.content.Context
import android.content.pm.ApplicationInfo
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.collection.LruCache
import androidx.recyclerview.widget.RecyclerView
import io.github.cnpog.gamingview.R
import kotlinx.coroutines.*

class AppListAdapter(
    private val context: Context,
    private val onItemClick: (String) -> Unit
) : RecyclerView.Adapter<AppListAdapter.AppViewHolder>() {

    private var appList: List<ApplicationInfo> = listOf()
    private val iconCache: LruCache<String, Drawable> = LruCache(100) // Adjust cache size as needed
    private val coroutineScope = CoroutineScope(Dispatchers.Main + Job())

    inner class AppViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val appIcon: ImageView = itemView.findViewById(R.id.app_icon)
        private val appName: TextView = itemView.findViewById(R.id.app_name)

        fun bind(app: ApplicationInfo) {
            appName.text = app.loadLabel(context.packageManager)

            // Check if the icon is cached
            val cachedIcon = iconCache[app.packageName]
            if (cachedIcon != null) {
                appIcon.setImageDrawable(cachedIcon)
            } else {
                // Load icon asynchronously
                coroutineScope.launch {
                    val icon = withContext(Dispatchers.IO) {
                        app.loadIcon(context.packageManager)
                    }
                    iconCache.put(app.packageName, icon)
                    appIcon.setImageDrawable(icon)
                }
            }

            itemView.setOnClickListener { onItemClick(app.packageName) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AppViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_app, parent, false)
        return AppViewHolder(view)
    }

    override fun onBindViewHolder(holder: AppViewHolder, position: Int) {
        holder.bind(appList[position])
    }

    override fun getItemCount(): Int = appList.size

    // Method to update the data
    fun updateData(newAppList: List<ApplicationInfo>) {
        appList = newAppList
        notifyDataSetChanged()
    }

    // Clean up coroutine scope to prevent memory leaks
    fun clear() {
        coroutineScope.cancel()
    }
}
