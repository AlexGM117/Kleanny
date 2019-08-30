package com.creamoslab.kleanny.ui.register

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.creamoslab.kleanny.R
import com.creamoslab.kleanny.ui.base.AbstractFragment
import kotlinx.android.synthetic.main.fragment_register_step1.*

class RegisterStep1Fragment : AbstractFragment() {
    private lateinit var model: RegistroViewModel

    var isEmailValid = false
    var arePasswordsValid = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        model = ViewModelProviders.of(activity!!).get(RegistroViewModel::class.java)
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_register_step1, container, false)
    }

    private fun setFieldsListeners() {

        editTextEmail.afterTextChanged { model.email = it.trim() }

        editTextPassword.afterTextChanged { model.password1 = it }

        editTextPassword.setOnFocusChangeListener { view, hasFocus ->
            if (hasFocus && !inputCurrentPassword.isPasswordVisibilityToggleEnabled) {
                inputCurrentPassword.isPasswordVisibilityToggleEnabled = true
            }

            if (hasFocus && editTextPassword.error != null) {
                editTextPassword.setError(getString(R.string.error_password), null)
            }
        }

        editTextRepeatPassword.afterTextChanged { model.password2 = it }

        model.isEmailValid.observe(this, Observer { valid ->
            isEmailValid = valid
        })

        model.arePasswordsValid.observe(this, Observer { valid ->
            arePasswordsValid = valid
        })
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setFieldsListeners()

        button_create_account.setOnClickListener {
            if (validateFields()) {
                addDataToModel()
                loadFragmentStep2()
            }
        }
    }

    private fun validateFields(): Boolean {

        if (!isEmailValid) {
            editTextEmail.error = getString(R.string.error_email)
        }

        if (!arePasswordsValid) {
            editTextPassword.error = getString(R.string.error_password)
            inputCurrentPassword.isPasswordVisibilityToggleEnabled = false
        }

        return isEmailValid && arePasswordsValid
    }

    private fun addDataToModel() {
        model.newUser.correo = editTextEmail.text!!.trim().toString()
        model.newUser.contraseña = editTextPassword.text.toString()
        model.newUser.fecha = ""
    }

    private fun loadFragmentStep2() {
        fragmentManager?.beginTransaction()?.add(R.id.fragment_container, RegisterStep2Fragment())
            ?.addToBackStack(null)
            ?.commit()
    }

    private fun EditText.afterTextChanged(afterTextChanged: (String) -> Unit) {
        this.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun afterTextChanged(editable: Editable?) {
                afterTextChanged.invoke(editable.toString())
            }
        })
    }
}
