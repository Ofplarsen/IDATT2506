package edu.ntnu.oflarsen.idatt2506.oving1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onCreateOptionsMenu(meny: Menu): Boolean {
        super.onCreateOptionsMenu(meny)
        meny.add("Olav Finne Præsteng")
        meny.add("Larsen")
        Log.d("Oving1" , "Menu created")
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.title!!.equals("Olav Finne Præsteng")) {
            Log.w("Oving1", "Olav Finne Præsteng")
        }
        if(item.title!!.equals("Larsen")){
            Log.e("Oving1", "Larsen")
        }
        return true
    }
}