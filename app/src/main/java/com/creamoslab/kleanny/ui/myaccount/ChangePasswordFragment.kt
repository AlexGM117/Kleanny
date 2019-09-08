package com.creamoslab.kleanny.ui.myaccount

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
import com.creamoslab.kleanny.data.manager.KleannyPreferences
import com.creamoslab.kleanny.ui.base.AbstractFragment
import kotlinx.android.synthetic.main.fragment_change_password.*
import kotlinx.android.synthetic.main.fragment_change_password.inputCurrentPassword

class ChangePasswordFragment : AbstractFragment() {

    private var mArePasswordsValid: Boolean = false
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

        setFieldsListeners()

        buttonAccept.setOnClickListener {
            if (validatePasswords()) {
                showProgressDialog(buttonAccept)
                mViewModel.callChangePassword(
                    KleannyPreferences().email!!, inputCurrentPassword.editText?.text.toString(),
                    inputNewPassword.editText?.text.toString()
                ).observe(this, Observer {
                    it?.let {
                        hideProgressDialog(buttonAccept)
                        showBottomMessage(it.message)
                    }
                })
            }
        }
    }

    /**
     * Si las contraseñas no coinciden, se alerta al usuario. Además este método te regresa
     * si las contaseñas son válidas
     */
    private fun validatePasswords(): Boolean {
        if (!mArePasswordsValid) {
            editText_new_password.error = getString(R.string.error_password)
            inputNewPassword.isPasswordVisibilityToggleEnabled = false
        }
        return  mArePasswordsValid
    }

    private fun setFieldsListeners() {
        editText_new_password.afterTextChanged { mViewModel.password1 = it }
        editText_new_password.setOnFocusChangeListener { _, hasFocus ->
            if (hasFocus && !inputNewPassword.isPasswordVisibilityToggleEnabled) {
                inputNewPassword.isPasswordVisibilityToggleEnabled = true
            }

            if (hasFocus && editText_new_password.error != null) {
                editText_new_password.setError(getString(R.string.error_password), null)
            }
        }
        editText_repeat_new_password.afterTextChanged { mViewModel.password2 = it}

        mViewModel.arePasswordsValid.observe(this, Observer { valid ->
            mArePasswordsValid = valid
        })
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