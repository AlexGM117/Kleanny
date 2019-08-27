package com.creamoslab.kleanny.ui.register


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProviders
import com.creamoslab.kleanny.R
import kotlinx.android.synthetic.main.fragment_register_step1.*

class RegisterStep1Fragment : Fragment() {
    private lateinit var model: RegistroViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        model = ViewModelProviders.of(activity!!).get(RegistroViewModel::class.java)
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_register_step1, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        button_create_account.setOnClickListener {
            addDataToModel()
            loadFragmentStep2()
        }
    }

    private fun addDataToModel() {
        model.newUser.correo = editTextEmail.text.toString()
        model.newUser.contraseña = editTextPassword.text.toString()
        model.newUser.fecha = ""
    }

    private fun loadFragmentStep2() {
        fragmentManager?.beginTransaction()?.add(R.id.fragment_container, RegisterStep2Fragment())?.addToBackStack(null)
            ?.commit()
    }
}
