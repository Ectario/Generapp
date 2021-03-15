package com.ectario.generapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.preference.Preference
import androidx.preference.PreferenceFragmentCompat
import androidx.preference.SwitchPreferenceCompat
import com.ectario.generapp.tools.ThemeHelper

class SettingsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.settings_activity)
        if (savedInstanceState == null) {
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.settings, SettingsFragment())
                .commit()
        }
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onSupportNavigateUp(): Boolean {
        return super.onSupportNavigateUp()
    }

    class SettingsFragment : PreferenceFragmentCompat() {

        override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
            setPreferencesFromResource(R.xml.settings_preferences, rootKey)
            val themePreferences = findPreference<SwitchPreferenceCompat>(getString(R.string.theme_header))

            themePreferences?.let { refreshTheme(it) }

            themePreferences?.setOnPreferenceClickListener {
                refreshTheme(it)
                true
            }
        }
        private fun refreshTheme(pref : Preference){
            if (!((pref as SwitchPreferenceCompat).isChecked)) {
                ThemeHelper.applyTheme("dark")
            }
            if ((pref as SwitchPreferenceCompat).isChecked) {
                ThemeHelper.applyTheme("light")
            }
        }
    }
}