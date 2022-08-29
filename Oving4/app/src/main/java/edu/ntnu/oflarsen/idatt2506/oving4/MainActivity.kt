package edu.ntnu.oflarsen.idatt2506.oving4

import android.app.AlertDialog
import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.fragment.app.FragmentTransaction

class MainActivity : AppCompatActivity() {

    private val solutions
        get() = supportFragmentManager.findFragmentById(R.id.solutions) as SolutionsFragment
    private fun toggleSolutions() = showSolutions(solutions.isVisible)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        showSolutions(false)
    }

    private fun setOrientation(config: Configuration) {
        val transaction: FragmentTransaction = supportFragmentManager.beginTransaction()
        val isPortrait: Boolean = config.orientation == Configuration.ORIENTATION_PORTRAIT
        transaction.replace(R.id.content, if (isPortrait) Fragment1() else Fragment2())
        transaction.commit()
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        setOrientation(newConfig)
    }

//    override fun onFragmentInteraction(text: String?) {
//        val fragment2 = supportFragmentManager.findFragmentById(R.id.fragment2) as Fragment2
//        fragment2.setText(text)
//    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_solution -> toggleSolutions()
            R.id.menu_about -> showAbout()
            R.id.menu_settings -> Toast.makeText(this, "Settings", Toast.LENGTH_SHORT).show()
            else -> return false
        }
        return true
    }

    private fun showSolutions(show: Boolean){
        val transaction: FragmentTransaction = supportFragmentManager.beginTransaction()
        if (show) transaction.show(solutions) else transaction.hide(solutions)
        transaction.commit()
    }

    private fun showAbout() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle(R.string.aboutContent)
        builder.setPositiveButton(R.string.ok) { dialog, _ -> dialog.dismiss() }
        builder.show()
    }
}