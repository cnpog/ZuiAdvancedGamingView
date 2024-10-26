package io.github.cnpog.gamingview

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AlertDialog
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import io.github.cnpog.gamingview.databinding.ActivityMainBinding
import io.github.cnpog.gamingview.utils.LocaleHelper

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        loadLocale()

        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        val navView: BottomNavigationView = binding.navView

        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.nav_host_fragment_activity_main) as NavHostFragment
        val navController = navHostFragment.navController
        // not needed for now
//        val appBarConfiguration = AppBarConfiguration(
//            setOf(
//                 R.id.navigation_gamingview
//            )
//        )
//        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.toolbar_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_language -> {
                showLanguageSelectionDialog()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun showLanguageSelectionDialog() {
        val languages = arrayOf("English", "Deutsch", "Español", "Français", "Türkçe", "Tiếng Việt", "中文")
        val languageCodes = arrayOf("en", "de", "es", "fr", "tr", "vi", "zh-rCN")

        val builder = AlertDialog.Builder(this)
        builder.setTitle(getString(R.string.action_language))
        builder.setItems(languages) { _, which ->
            val selectedLanguageCode = languageCodes[which]
            setLocale(selectedLanguageCode)
        }
        builder.show()
    }

    private fun setLocale(languageCode: String) {
        LocaleHelper.setLocale(this, languageCode)
        recreate()
    }

    private fun loadLocale() {
        LocaleHelper.loadLocale(this)
    }
}