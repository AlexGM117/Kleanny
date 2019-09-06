package com.creamoslab.kleanny.ui.myaccount

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.creamoslab.kleanny.R
import kotlinx.android.synthetic.main.fragment_setitngs.*

class SettingsFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_setitngs, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        changePassword.setOnClickListener {
            fragmentManager?.beginTransaction()
                ?.replace(R.id.nav_host_fragment, ChangePasswordFragment(), ChangePasswordFragment::class.simpleName)
                ?.addToBackStack(null)?.commit()
        }
    }
}