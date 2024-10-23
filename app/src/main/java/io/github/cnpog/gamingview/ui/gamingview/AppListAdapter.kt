package io.github.cnpog.gamingview.ui.gamingview

import android.content.Context
import android.content.pm.ApplicationInfo
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import io.github.cnpog.gamingview.R

class AppListAdapter(
    private val context: Context,
    private val appList: List<ApplicationInfo>,
    private val onItemClick: (String) -> Unit
) : RecyclerView.Adapter<AppListAdapter.AppViewHolder>() {

    inner class AppViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val appIcon: ImageView = itemView.findViewById(R.id.app_icon)
        private val appName: TextView = itemView.findViewById(R.id.app_name)

        fun bind(app: ApplicationInfo) {
            appName.text = app.loadLabel(context.packageManager)
            appIcon.setImageDrawable(app.loadIcon(context.packageManager))
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

    override fun getItemCount(): Int {
        return appList.size
    }
}
