package com.creamoslab.kleanny.ui.register

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProviders
import com.creamoslab.kleanny.R
import kotlinx.android.synthetic.main.fragment_register_step2.*

/**
 * A simple [Fragment] subclass.
 *
 */
class RegisterStep2Fragment : Fragment() {
    private lateinit var model: RegistroViewModel

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

        button_continuar.setOnClickListener {
            addDataToModel()
            loadFragmentStep2()
        }
    }

    private fun addDataToModel() {
        model.newUserData.dni = editTextDni.text.toString()
        model.newUserData.nombre = editTextName.text.toString()
        model.newUserData.primerApellido = editTextFirstLastName.text.toString()
        model.newUserData.segundoApellido = editTextSecondLastName.text.toString()
        model.newUserData.direccion = editTextAddress.text.toString()
    }

    private fun loadFragmentStep2() {
        fragmentManager?.beginTransaction()?.add(R.id.fragment_container, RegisterStep3Fragment())?.addToBackStack(null)
            ?.commit()
    }
}
