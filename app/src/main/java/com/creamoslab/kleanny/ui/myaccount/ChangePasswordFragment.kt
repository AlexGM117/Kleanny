package com.creamoslab.kleanny.ui.myaccount


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.creamoslab.kleanny.R
import com.creamoslab.kleanny.data.manager.KleannyPreferences
import com.creamoslab.kleanny.ui.base.AbstractFragment
import kotlinx.android.synthetic.main.fragment_change_password.*

class ChangePasswordFragment : AbstractFragment() {

    private val mViewModel: MyAccountViewModel by lazy {
        ViewModelProviders.of(this).get(MyAccountViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_change_password, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        buttonAccept.setOnClickListener {
            showProgressDialog(buttonAccept)
            mViewModel.callChangePassword(KleannyPreferences().email!!, inputCurrentPassword.editText?.text.toString(),
                inputNewPassword.editText?.text.toString()).observe(this, Observer {
                it?.let {
                    hideProgressDialog(buttonAccept)
                    showBottomMessage(it.message)
                }
            })
        }
    }
}