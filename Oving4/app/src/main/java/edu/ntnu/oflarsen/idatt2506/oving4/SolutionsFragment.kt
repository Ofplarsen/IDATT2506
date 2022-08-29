package edu.ntnu.oflarsen.idatt2506.oving4

import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.fragment.app.ListFragment

class SolutionsFragment : ListFragment() {

    private var solution: Array<String> = arrayOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        solution = resources.getStringArray(R.array.answers)
        listAdapter = activity?.let {
            ArrayAdapter(it, android.R.layout.simple_list_item_1, solution)
        }
    }
}