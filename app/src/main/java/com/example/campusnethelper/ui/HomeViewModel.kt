package com.example.campusnethelper.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.campusnethelper.data.Account
import com.example.campusnethelper.network.LoginRepository
import kotlinx.coroutines.launch

sealed class NetState {
    object Disconnected : NetState()
    object Connecting : NetState()
    data class Connected(val ip: String) : NetState()
}

class HomeViewModel(private val repo: LoginRepository) : ViewModel() {
    val state = MutableLiveData<NetState>(NetState.Disconnected)
    val currentAccount = MutableLiveData<Account?>()
    fun loadDefaultAccount(a: Account?) {
        currentAccount.value = a
    }
    fun reconnect() {
        val acc = currentAccount.value ?: return
        state.value = NetState.Connecting
        viewModelScope.launch {
            val result = repo.authenticate(acc)
            result.onSuccess { ip -> state.value = NetState.Connected(ip) }
            result.onFailure { state.value = NetState.Disconnected }
        }
    }
}
