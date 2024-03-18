package pl.wsei.pam.lectures.lecture4.fragments.ui.main

import android.os.Bundle
import androidx.preference.PreferenceFragmentCompat
import pl.wsei.pam.lectures.R

class Lab04FragmentSettings : PreferenceFragmentCompat() {

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.root_preferences, rootKey)
    }
}