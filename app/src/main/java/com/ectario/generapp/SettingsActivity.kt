package com.ectario.generapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.preference.Preference
import androidx.preference.PreferenceFragmentCompat
import androidx.preference.SeekBarPreference
import androidx.preference.SwitchPreferenceCompat
import com.ectario.generapp.tools.ThemeHelper
import com.ectario.generapp.ui.historic.HistoricFragment

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

    class SettingsFragment : PreferenceFragmentCompat() {

        override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
            setPreferencesFromResource(R.xml.settings_preferences, rootKey)
            val themePreferences =
                findPreference<SwitchPreferenceCompat>(getString(R.string.theme_header))
            val historicSwitchInfinitePreferences =
                findPreference<SwitchPreferenceCompat>(getString(R.string.switch_infinite_historic))
            val historicSeekbarLimitPreferences =
                findPreference<SeekBarPreference>(getString(R.string.seekbar_limit_historic))

            historicSwitchInfinitePreferences?.let {
                historicSeekbarLimitPreferences?.let {
                    refreshLengthHistoric(
                        historicSwitchInfinitePreferences,
                        historicSeekbarLimitPreferences
                    )
                }
            }

            (historicSwitchInfinitePreferences as Preference).setOnPreferenceClickListener {
                refreshLengthHistoric(
                    historicSwitchInfinitePreferences,
                    historicSeekbarLimitPreferences as Preference
                )
                true
            }

            (historicSeekbarLimitPreferences as SeekBarPreference).setOnPreferenceChangeListener { preference, newValue ->
                    refreshLengthHistoric(
                            historicSwitchInfinitePreferences,
                            historicSeekbarLimitPreferences as Preference
                    )
                    true
                }

            themePreferences?.let { refreshTheme(it) }
            themePreferences?.setOnPreferenceClickListener {
                refreshTheme(it)
                true
            }
        }
        
        private fun refreshTheme(pref : Preference){
            if (((pref as SwitchPreferenceCompat).isChecked)) {
                ThemeHelper.applyTheme("dark")
            }
            if (!(pref as SwitchPreferenceCompat).isChecked) {
                ThemeHelper.applyTheme("light")
            }
        }
        private fun refreshLengthHistoric(prefInfinity : Preference, seekbarPref : Preference){
            println((seekbarPref as SeekBarPreference).value)
            if (((prefInfinity as SwitchPreferenceCompat).isChecked)) {
                HistoricFragment.historicDeleteLimit = (seekbarPref as SeekBarPreference).value
            }
            if (!(prefInfinity as SwitchPreferenceCompat).isChecked) {
                HistoricFragment.historicDeleteLimit = -1
            }
        }
    }
}