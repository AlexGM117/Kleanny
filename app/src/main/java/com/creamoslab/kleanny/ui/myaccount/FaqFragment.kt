package com.creamoslab.kleanny.ui.myaccount

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.creamoslab.kleanny.R
import kotlinx.android.synthetic.main.fragment_faq.*

class FaqFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_faq, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setListeners()
        faqWebView.loadUrl("file:///android_res/raw/faqs.html")
    }

    private fun setListeners() {
        linearLayout_mail.setOnClickListener {
            textView_title.text = getString(R.string.mail_us_to)
            textView_content.text = getString(R.string.email_address_contact)
            linearLayout_contact.visibility = View.VISIBLE
        }

        linearLayout_call.setOnClickListener {
            textView_title.text = getString(R.string.call_us_to)
            textView_content.text = getString(R.string.phone_number_contact)
            linearLayout_contact.visibility = View.VISIBLE
        }
    }
}
