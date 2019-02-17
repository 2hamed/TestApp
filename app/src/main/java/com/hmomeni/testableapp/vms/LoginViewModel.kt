package com.hmomeni.testableapp.vms

import android.util.Patterns
import androidx.lifecycle.ViewModel
import com.google.gson.Gson
import com.hmomeni.testableapp.api.Api
import com.pixplicity.easyprefs.library.Prefs
import io.reactivex.Completable
import javax.inject.Inject

class LoginViewModel : ViewModel() {

    @Inject
    lateinit var api: Api

    fun login(email: String, password: String): Completable {
        if (!isEmailValid(email)) {
            return Completable.error(Exception())
        }
        return api.login(email, password)
            .doOnSuccess {
                Prefs.putString("user", Gson().toJson(it))
            }
            .ignoreElement()
    }

    fun isEmailValid(email: String) = Patterns.EMAIL_ADDRESS.matcher(email).matches()
}