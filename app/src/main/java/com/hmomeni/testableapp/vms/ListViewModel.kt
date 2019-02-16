package com.hmomeni.testableapp.vms

import androidx.lifecycle.ViewModel
import com.hmomeni.testableapp.api.Api
import com.hmomeni.testableapp.entities.User
import javax.inject.Inject

class ListViewModel @Inject constructor() : ViewModel() {

    @Inject
    lateinit var api: Api

    val users = mutableListOf<User>()

    fun fetchUsers() = api.fetchUsers().doOnSuccess { users.addAll(it) }
}