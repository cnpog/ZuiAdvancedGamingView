package io.github.cnpog.gamingview.ui.gamingview

import android.content.Context
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import io.github.cnpog.gamingview.R
import kotlinx.coroutines.*

class AppListAdapter(
    private val context: Context,
    private val viewModel: GamingviewViewModel,
    private val onItemClick: (AppItem) -> Unit
) : RecyclerView.Adapter<AppListAdapter.AppViewHolder>() {

    private var appList: List<AppItem> = listOf()
    private val coroutineScope = CoroutineScope(Dispatchers.Main + SupervisorJob())

    init {
        setHasStableIds(true)
    }

    override fun getItemId(position: Int): Long {
        return appList[position].applicationInfo.packageName.hashCode().toLong()
    }

    inner class AppViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val appIcon: ImageView = itemView.findViewById(R.id.app_icon)
        private val appName: TextView = itemView.findViewById(R.id.app_name)
        private val appPosition: TextView = itemView.findViewById(R.id.app_position)
        private val appMode: TextView = itemView.findViewById(R.id.app_mode)

        fun bind(appItem: AppItem) {
            appName.text = appItem.applicationInfo.loadLabel(context.packageManager)
            appPosition.text = appItem.position?.let {
                "${context.getString(R.string.position)}: ${it.getDescription(context)}"
            } ?: ""
            appMode.text = appItem.mode?.let {
                "${context.getString(R.string.mode)}: ${it.getDescription(context)}"
            } ?: ""

            appIcon.visibility = View.INVISIBLE

            val packageName = appItem.applicationInfo.packageName
            val cachedIcon = viewModel.iconCache[packageName]
            if (cachedIcon != null) {
                appIcon.setImageDrawable(cachedIcon)
                appIcon.visibility = View.VISIBLE
            } else {
                coroutineScope.launch {
                    val icon = withContext(Dispatchers.IO) {
                        appItem.applicationInfo.loadIcon(context.packageManager)
                    }
                    viewModel.iconCache.put(packageName, icon)
                    appIcon.setImageDrawable(icon)
                    appIcon.visibility = View.VISIBLE
                }
            }

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

    fun updateData(newAppList: List<AppItem>) {
        appList = newAppList
        notifyDataSetChanged()
    }

    fun clear() {
        coroutineScope.cancel()
    }
}

