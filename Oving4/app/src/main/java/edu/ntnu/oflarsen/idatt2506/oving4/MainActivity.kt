package edu.ntnu.oflarsen.idatt2506.oving4

import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setOrientation(resources.configuration)
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        setOrientation(newConfig)
    }

    private fun setOrientation(config: Configuration) {
        val isPortrait = config.orientation == Configuration.ORIENTATION_PORTRAIT

        if (isPortrait)
            setContentView(R.layout.activity_main_vertical); // it will use .xml from /res/layout
        else
            setContentView(R.layout.activity_main_horizontal); // it will use xml from /res/layout-land

    }
}