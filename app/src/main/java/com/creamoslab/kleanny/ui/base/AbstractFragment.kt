package com.creamoslab.kleanny.ui.base

import android.os.Bundle
import android.view.View
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import com.creamoslab.kleanny.ui.DialogGeneralFragment

abstract class AbstractFragment : Fragment() {
    private var dialog: DialogFragment? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dialog = DialogGeneralFragment()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    fun showProgressDialog(){
        dialog?.show(fragmentManager, "Dialog")
    }

    fun hideProgressDialog(){
        dialog?.dismiss()
    }
}