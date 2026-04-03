package com.example.campusnethelper.network

import com.example.campusnethelper.data.Account
import kotlinx.coroutines.delay

class LoginRepository {
    suspend fun authenticate(account: Account): Result<String> {
        delay(300)
        return if (account.password.isNotEmpty()) Result.success("10.20.10.15") else Result.failure(Exception("error"))
    }
}
