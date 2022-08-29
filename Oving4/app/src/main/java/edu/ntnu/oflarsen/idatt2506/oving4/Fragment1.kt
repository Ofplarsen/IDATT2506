package edu.ntnu.oflarsen.idatt2506.oving4

import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.fragment.app.Fragment
import kotlin.ClassCastException

class Fragment1 : Fragment() {

    private var mListener: OnFragmentInteractionListener? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view: View = inflater.inflate(R.layout.fragment_1, container, false)
        view.findViewById<EditText>(R.id.editText).addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                mListener!!.onFragmentInteraction(s.toString())
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

            override fun afterTextChanged(s: Editable?) {}

        })
        return view
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mListener = try {
            activity as OnFragmentInteractionListener
        } catch (e: ClassCastException){
            throw ClassCastException("$activity must implement OnFragmentInteractionListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        mListener = null
    }

    interface OnFragmentInteractionListener {
        fun onFragmentInteraction(text: String?)
    }
}