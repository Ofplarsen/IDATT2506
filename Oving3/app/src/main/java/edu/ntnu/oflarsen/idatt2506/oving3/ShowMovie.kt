package edu.ntnu.oflarsen.idatt2506.oving3

import android.app.Activity
import android.os.Bundle
import android.view.View
import android.widget.ImageView

class ShowMovie : Activity(), View.OnClickListener {

    private lateinit var imageView: ImageView

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.show_movies)

        imageView = findViewById(R.id.image)
        imageView.setOnClickListener(this)


    }

    private fun showMovie() {
        val nr = intent.getIntExtra("nr", -1)
        if(nr < 0)
            return
        val imageArray = resources.obtainTypedArray(R.array.pictures)
        val image = imageArray.getDrawable(nr)
        imageView.setImageDrawable(image)

    }

    override fun onClick(v: View?) {
        finish()
    }


}