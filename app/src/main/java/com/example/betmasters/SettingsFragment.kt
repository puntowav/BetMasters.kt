package com.example.betmasters

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatDelegate
import androidx.preference.Preference
import androidx.preference.PreferenceFragmentCompat
import androidx.preference.SwitchPreference

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