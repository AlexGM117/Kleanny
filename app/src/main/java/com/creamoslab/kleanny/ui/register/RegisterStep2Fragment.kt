package com.creamoslab.kleanny.ui.register

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.creamoslab.kleanny.R
import com.creamoslab.kleanny.ui.base.AbstractFragment
import kotlinx.android.synthetic.main.fragment_register_step2.*

/**
 * A simple [Fragment] subclass.
 *
 */
class RegisterStep2Fragment : AbstractFragment() {
    private lateinit var model: RegistroViewModel

    private val listOfItems = arrayOf("CC", "CE", "TI", "NIT", "PA")

    private var isDocumentNumberValid = false
    private var isNameValid = false
    private var isFirstNameValid = false
    private var isSecondNameValid = false
    private var isAddressValid = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        model = ViewModelProviders.of(activity!!).get(RegistroViewModel::class.java)
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_register_step2, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setSpinnerItems()
        setFieldsListeners()
        buttonContinuar.setOnClickListener {
            if (validateFields()) {
                addDataToModel()
                loadFragmentStep2()
            }
        }
    }

    private fun setSpinnerItems() {
        val adapter = ArrayAdapter(context!!, android.R.layout.simple_spinner_item, listOfItems)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = adapter
    }

    private fun setFieldsListeners() {
        editTextDni.afterTextChanged { model.documentNumber = it }
        editTextName.afterTextChanged { model.name = it }
        editTextFirstLastName.afterTextChanged { model.firstName = it }
        editTextSecondLastName.afterTextChanged { model.secondName = it }
        editTextAddress.afterTextChanged { model.address = it }

        model.isDocumentNumberValid.observe(this, Observer { valid ->
            isDocumentNumberValid = valid
        })

        model.isNameValid.observe(this, Observer { valid ->
            isNameValid = valid
        })

        model.isFirstNameValid.observe(this, Observer { valid ->
            isFirstNameValid = valid
        })

        model.isSecondNameValid.observe(this, Observer { valid ->
            isSecondNameValid = valid
        })

        model.isAddressValid.observe(this, Observer { valid ->
            isAddressValid = valid
        })
    }

    private fun validateFields(): Boolean {
        val inputFields = listOf(
            Pair(isDocumentNumberValid, editTextDni),
            Pair(isNameValid, editTextName),
            Pair(isFirstNameValid, editTextFirstLastName),
            Pair(isSecondNameValid, editTextSecondLastName),
            Pair(isAddressValid, editTextAddress)
        )

        inputFields.forEach {
            if (!it.first) {
                it.second.error = "Este campo es requerido"
            }
        }

        if (!checkboxTerms.isChecked) {
            checkboxTerms.error = "Debes aceptar los términos y condiciones"
            return false
        }

        inputFields.forEach {
            if (!it.first) {
                return false
            }
        }

        return true
    }

    private fun addDataToModel() {
        model.newUser.dni = editTextDni.text.toString()
        model.newUser.nombre = editTextName.text.toString()
        model.newUser.primerApellido = editTextFirstLastName.text.toString()
        model.newUser.segundoApellido = editTextSecondLastName.text.toString()
        model.newUser.direccion = editTextAddress.text.toString()
    }

    private fun loadFragmentStep2() {
        fragmentManager?.beginTransaction()?.add(R.id.fragment_container, RegisterStep3Fragment())
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
