package edu.ntnu.oflarsen.idatt2506.oving4

import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import androidx.fragment.app.DialogFragment

class AnswerFragment : DialogFragment() {

	var callback: Callback? = null

	override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
		val title = arguments?.getString("title")
		val builder = AlertDialog.Builder(activity)
		builder.setTitle(title)
		builder.setPositiveButton(R.string.yes, MyListener())
		builder.setNegativeButton(R.string.no, MyListener())
		return builder.create()
	}

	internal inner class MyListener : DialogInterface.OnClickListener {

		private var answer = arguments?.getString("answer")

		override fun onClick(dialog: DialogInterface, which: Int) {
			var ans = Dialog.BUTTON_POSITIVE
			if (answer == resources.getString(R.string.no)) ans = Dialog.BUTTON_NEGATIVE
			callback?.showResponse(which == ans)
		}
	}

	interface Callback {
		fun showResponse(correct: Boolean)
	}

	companion object {
		fun answerFragment(title: String?, answer: String?, callback: Callback?): AnswerFragment {
			val fragment = AnswerFragment()
			fragment.callback = callback
			val args = Bundle()
			args.putString("title", title)
			args.putString("answer", answer)
			fragment.arguments = args
			return fragment
		}
	}
}
