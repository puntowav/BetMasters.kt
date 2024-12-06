package com.example.betmasters

import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import androidx.appcompat.app.AppCompatDelegate
import androidx.preference.Preference
import androidx.preference.PreferenceFragmentCompat
import androidx.preference.SwitchPreference
import java.util.Locale

class SettingsFragment : PreferenceFragmentCompat(), Preference.OnPreferenceChangeListener {
    private var source: String? = null

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.preferences, rootKey)
        val nightModeSwitch = findPreference<SwitchPreference>("nightMode")
        nightModeSwitch?.onPreferenceChangeListener = this

        source = arguments?.getString("source")

        val editProfile = findPreference<Preference>("custom_profile")
        editProfile?.onPreferenceClickListener = Preference.OnPreferenceClickListener {
            val intent = Intent(context, Edit_profile::class.java)
            intent.putExtra("source", source)
            startActivity(intent)
            true
        }
    }

    override fun onPreferenceChange(preference: Preference, newValue: Any?): Boolean {
        if (preference?.key == "nightMode") {
            val isNightMode = newValue as Boolean
            setNightMode(isNightMode)
        }
        if (preference?.key == "language") {
            val locale = Locale(newValue.toString())
            Locale.setDefault(locale)
            val config = Configuration(requireContext().resources.configuration)
            config.setLocale(locale)
            requireContext().createConfigurationContext(config)
            activity?.recreate()  // Recarga la actividad para aplicar el cambio de idioma
        }
        return true
    }
}

private fun setNightMode(isNightMode: Boolean) {
    if (isNightMode) {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
    } else {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
    }
}
