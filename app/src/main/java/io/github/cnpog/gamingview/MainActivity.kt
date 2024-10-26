package io.github.cnpog.gamingview

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AlertDialog
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
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
        val languageMappings = listOf(
            "English" to "en",
            "Deutsch" to "de",
            "Español" to "es",
            "Français" to "fr",
            "Русский" to "ru",
            "Türkçe" to "tr",
            "Tiếng Việt" to "vi",
            "中文" to "zh-rCN"
        )

        // Extract the names for display
        val languages = languageMappings.map { it.first }.toTypedArray()

        val builder = AlertDialog.Builder(this)
        builder.setTitle(getString(R.string.action_language))
        builder.setItems(languages) { _, which ->
            val selectedLanguageCode = languageMappings[which].second
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