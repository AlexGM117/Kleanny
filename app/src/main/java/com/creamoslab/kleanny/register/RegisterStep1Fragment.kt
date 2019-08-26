package com.creamoslab.kleanny.register


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.creamoslab.kleanny.R
import kotlinx.android.synthetic.main.fragment_register_step1.*

/**
 * A simple [Fragment] subclass.
 *
 */
class RegisterStep1Fragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_register_step1, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        button_create_account.setOnClickListener {
            fragmentManager?.beginTransaction()?.add(R.id.fragment_container, RegisterStep2Fragment())?.
                addToBackStack(null)?.commit()
        }
    }
}
