package com.creamoslab.kleanny.ui.register

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.creamoslab.kleanny.R
import kotlinx.android.synthetic.main.fragment_register_step2.*

/**
 * A simple [Fragment] subclass.
 *
 */
class RegisterStep2Fragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_register_step2, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        button_continuar.setOnClickListener {
            fragmentManager?.beginTransaction()?.add(R.id.fragment_container, RegisterStep3Fragment())?.
                addToBackStack(null)?.commit()
        }
    }
}
