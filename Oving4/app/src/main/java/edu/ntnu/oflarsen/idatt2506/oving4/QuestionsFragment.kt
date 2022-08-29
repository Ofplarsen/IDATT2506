package edu.ntnu.oflarsen.idatt2506.oving4

import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.TextView
import androidx.fragment.app.ListFragment
import edu.ntnu.oflarsen.idatt2506.oving4.AnswerFragment.Companion.answerFragment



class QuestionsFragment : ListFragment(), AnswerFragment.Callback {

	private var questions: Array<String> = arrayOf()
	private var answers: Array<String> = arrayOf()
	private var clicked = -1

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		questions = resources.getStringArray(R.array.questions)
		answers = resources.getStringArray(R.array.answers)
		listAdapter = activity?.let {
			ArrayAdapter(it, android.R.layout.simple_list_item_1, android.R.id.text1, questions)
		}
	}

	override fun onListItemClick(l: ListView, v: View, position: Int, id: Long) {
		super.onListItemClick(l, v, position, id)
		val dialog = answerFragment(questions[position], answers[position], this)
		dialog.show(parentFragmentManager, null)
		clicked = position
	}

	override fun showResponse(correct: Boolean) {
		val listView = listView
		val textView = listView.getChildAt(clicked) as TextView
		if (correct) textView.setBackgroundColor(Color.GREEN)
		else textView.setBackgroundColor(Color.RED)
	}
}
