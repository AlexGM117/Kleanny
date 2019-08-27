package com.creamoslab.kleanny.ui.register

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProviders
import com.creamoslab.kleanny.R
import com.creamoslab.kleanny.ui.LoginFragment
import kotlinx.android.synthetic.main.fragment_register_step3.*

class RegisterStep3Fragment : Fragment() {
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
        model.newUserData.telefono = editTextPhone.text.toString()
        model.requestRegisterUser()
        returnToLogin()
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