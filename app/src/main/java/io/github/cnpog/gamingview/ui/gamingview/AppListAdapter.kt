package io.github.cnpog.gamingview.ui.gamingview

import android.content.Context
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
    private val onItemClick: (AppItem) -> Unit
) : RecyclerView.Adapter<AppListAdapter.AppViewHolder>() {

    private var appList: List<AppItem> = listOf()
    private val iconCache: LruCache<String, Drawable> = LruCache(100) // Adjust cache size as needed
    private val coroutineScope = CoroutineScope(Dispatchers.Main + SupervisorJob())

    inner class AppViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val appIcon: ImageView = itemView.findViewById(R.id.app_icon)
        private val appName: TextView = itemView.findViewById(R.id.app_name)
        private val appPosition: TextView = itemView.findViewById(R.id.app_position)
        private val appMode: TextView = itemView.findViewById(R.id.app_mode)

        fun bind(appItem: AppItem) {
            appName.text = appItem.applicationInfo.loadLabel(context.packageManager)
            appPosition.text = appItem.position?.let {
                "Position: ${it.description}"
            } ?: ""
            appMode.text = appItem.mode?.let {
                "Mode: ${it.description}"
            } ?: ""

            // Check if the icon is cached
            val cachedIcon = iconCache[appItem.applicationInfo.packageName]
            if (cachedIcon != null) {
                appIcon.setImageDrawable(cachedIcon)
            } else {
                // Load icon asynchronously
                coroutineScope.launch {
                    val icon = withContext(Dispatchers.IO) {
                        appItem.applicationInfo.loadIcon(context.packageManager)
                    }
                    iconCache.put(appItem.applicationInfo.packageName, icon)
                    appIcon.setImageDrawable(icon)
                }
            }

            // Set click listener
            itemView.setOnClickListener { onItemClick(appItem) }
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
    fun updateData(newAppList: List<AppItem>) {
        appList = newAppList
        notifyDataSetChanged()
    }

    // Clean up coroutine scope to prevent memory leaks
    fun clear() {
        coroutineScope.cancel()
    }
}
