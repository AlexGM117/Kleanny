package com.creamoslab.kleanny.ui.base

import android.app.Activity
import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import com.creamoslab.kleanny.R
import com.creamoslab.kleanny.ui.DialogGeneralFragment
import com.github.razir.progressbutton.attachTextChangeAnimator
import com.github.razir.progressbutton.bindProgressButton
import com.github.razir.progressbutton.hideProgress
import com.github.razir.progressbutton.showProgress
import com.google.android.material.snackbar.Snackbar

abstract class AbstractFragment : Fragment() {
    private var dialog: DialogFragment? = null
    private lateinit var buttonText: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dialog = DialogGeneralFragment()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.setOnTouchListener { view, motionEvent ->
            if (motionEvent.action == MotionEvent.ACTION_MOVE) {
                println("touch screen")
                activity?.let {
                    it.hideKeyboard(view)
                }
            }
            true
        }
    }

    fun showProgressDialog(button: Button) {
        button.isEnabled = false
        buttonText = button.text.toString()
        bindProgressButton(button)
        button.attachTextChangeAnimator()

        button.showProgress {
            buttonTextRes = R.string.loading
            progressColor = Color.WHITE
        }
    }

    fun hideProgressDialog(button: Button){
        button.hideProgress(buttonText)
        button.isEnabled = true
    }

    fun showBottomMessage(message: String) {
        Snackbar.make(
            view!!,
            message,
            Snackbar.LENGTH_SHORT
        ).show()
    }

    fun Context.hideKeyboard(view: View) {
        val inputMethodManager = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
    }
}