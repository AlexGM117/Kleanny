package com.creamoslab.kleanny.ui.base

import android.graphics.Color
import android.os.Bundle
import android.view.View
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
}