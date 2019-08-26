package com.creamoslab.kleanny.ui

import android.content.Intent
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.creamoslab.kleanny.R
import com.creamoslab.kleanny.ui.register.RegisterStep1Fragment
import kotlinx.android.synthetic.main.fragment_login.*

/**
 * A simple [Fragment] subclass.
 *
 */
class LoginFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val spannableString = SpannableString(resources.getString(R.string.text_sign_up))
        spannableString.setSpan(ForegroundColorSpan(resources.getColor(R.color.colorPrimary)), 22, 28, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        textSignUp.text = spannableString

        textSignUp.setOnClickListener {
            fragmentManager?.beginTransaction()?.add(R.id.fragment_container, RegisterStep1Fragment())?.
                addToBackStack(null)?.commit()
        }

        buttonLogin.setOnClickListener {
            startActivity(Intent(activity, HomeActivity::class.java))
            activity?.finish()
        }
    }
}
