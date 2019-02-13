package com.hmomeni.testableapp.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import com.hmomeni.testableapp.R
import com.hmomeni.testableapp.utils.app
import com.hmomeni.testableapp.utils.iomain
import com.hmomeni.testableapp.utils.toast
import com.hmomeni.testableapp.vms.LoginViewModel
import com.jakewharton.rxbinding3.view.clicks
import io.reactivex.disposables.Disposable
import kotlinx.android.synthetic.main.fragment_login.*
import timber.log.Timber

class LoginFragment : Fragment() {
    private lateinit var viewModel: LoginViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProviders.of(this)[LoginViewModel::class.java].apply {
            inject(context!!.app())
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    private var disposable: Disposable? = null
    private var inProgress = false
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        disposable = button.clicks()
            .filter { !inProgress }
            .subscribe {
                viewModel
                    .login(email.text.toString(), password.text.toString())
                    .iomain()
                    .doOnSubscribe { inProgress = true }
                    .doAfterTerminate { inProgress = false }
                    .subscribe({
                        findNavController().navigate(
                            LoginFragmentDirections.actionLoginFragmentToListFragment(),
                            NavOptions.Builder().setPopUpTo(R.id.loginFragment, true).build()
                        )
                    }, {
                        toast(it.message ?: "Error")
                        Timber.e(it)
                    })
            }
    }

    override fun onDestroyView() {
        disposable?.dispose()
        super.onDestroyView()
    }
}