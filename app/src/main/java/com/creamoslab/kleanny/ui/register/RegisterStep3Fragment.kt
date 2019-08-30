package com.creamoslab.kleanny.ui.register

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.creamoslab.kleanny.R
import com.creamoslab.kleanny.ui.LoginFragment
import com.creamoslab.kleanny.ui.base.AbstractFragment
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_register_step3.*

class RegisterStep3Fragment : AbstractFragment() {
    private lateinit var model: RegistroViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        model = ViewModelProviders.of(activity!!).get(RegistroViewModel::class.java)
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_register_step3, container, false)
    }

    private fun addDataToModel() {
        showProgressDialog()
        model.newUserData.telefono = editTextPhone.text.toString()
        model.requestRegisterUser().observe(this, Observer {
            hideProgressDialog()
            if (it.success && it.message == "Registro Guardado") {
                returnToLogin()
            } else {
                Snackbar.make(
                    getView()!!,
                    it.message,
                    Snackbar.LENGTH_SHORT
                ).show()
            }
        })
    }

    private fun returnToLogin() {
        fragmentManager?.beginTransaction()?.replace(R.id.fragment_container, LoginFragment())?.commit()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        button_continuar.setOnClickListener {
            addDataToModel()
        }
    }
}