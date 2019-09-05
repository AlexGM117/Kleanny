package com.creamoslab.kleanny.ui.myaccount

import com.creamoslab.kleanny.ui.base.BaseViewModel
import kotlinx.coroutines.launch

class MyAccountViewModel: BaseViewModel() {
    fun callChangePassword(email: String, password: String, newPassword: String) {
        scope.launch {
            repository.makeRequest()
        }
    }
}