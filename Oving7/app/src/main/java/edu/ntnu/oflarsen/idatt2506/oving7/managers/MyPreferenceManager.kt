package edu.ntnu.oflarsen.idatt2506.oving7.managers

import android.app.Activity
import android.content.SharedPreferences
import androidx.preference.PreferenceManager
import edu.ntnu.oflarsen.idatt2506.oving7.R
import edu.ntnu.oflarsen.idatt2506.oving7.SettingsActivity

class MyPreferenceManager(private val activity: Activity) {

	private val resources = activity.resources
	private val preferences = PreferenceManager.getDefaultSharedPreferences(activity)
	private val editor: SharedPreferences.Editor = preferences.edit()


	fun putString(key: String, value: Int) {
		editor.putString(key, value.toString())
		editor.apply()
	}

	fun getString(key: String, defaultValue: String): String {
		return preferences.getString(key, defaultValue) ?: defaultValue
	}

	fun updateBackgroundColor() {
		val darkModeValues = resources.getStringArray(R.array.bg_color_values)
		val value = getString(
			resources.getString(R.string.bg),
			resources.getString(R.string.bg_default_value)
		)
		when (value) {
			darkModeValues[0] -> putString("bg", R.color.bg_1)
			darkModeValues[1] -> putString("bg", R.color.bg_2)
			darkModeValues[2] -> putString("bg", R.color.bg_3)
		}

		activity.window.decorView.setBackgroundColor(resources.getColor(getString("bg", "#FFFFFFFF").toInt()))
	}

	fun registerListener(activity: SharedPreferences.OnSharedPreferenceChangeListener) {
		preferences.registerOnSharedPreferenceChangeListener(activity)
	}

	fun unregisterListener(activity: SharedPreferences.OnSharedPreferenceChangeListener) {
		preferences.unregisterOnSharedPreferenceChangeListener(activity)
	}
}
