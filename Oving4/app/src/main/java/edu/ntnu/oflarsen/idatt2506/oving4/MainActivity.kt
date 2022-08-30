package edu.ntnu.oflarsen.idatt2506.oving4

import android.content.res.Configuration
import android.graphics.drawable.Drawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction

class MainActivity : AppCompatActivity(), ListFragment.OnFragmentInteractionListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setOrientation(resources.configuration)
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        setOrientation(newConfig)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    private fun setOrientation(config: Configuration) {
        val isPortrait = config.orientation == Configuration.ORIENTATION_PORTRAIT

        if (isPortrait)
            setContentView(R.layout.activity_main_vertical); // it will use .xml from /res/layout
        else
            setContentView(R.layout.activity_main_horizontal); // it will use xml from /res/layout-land
    }

    override fun onFragmentInteraction(text: String?, picture: Drawable?, description: String?) {
        val fragment2 = supportFragmentManager.findFragmentById(R.id.fragment2) as InfoFragment
        fragment2.setMovie(text, picture, description)
    }
}