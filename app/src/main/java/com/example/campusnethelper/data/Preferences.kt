package com.example.campusnethelper.data

import android.content.Context

class Preferences(context: Context) {
    private val prefs = context.getSharedPreferences("campusnet", Context.MODE_PRIVATE)
    fun setDefaultAccountId(id: String?) {
        prefs.edit().putString("default_account_id", id).apply()
    }
    fun defaultAccountId(): String? = prefs.getString("default_account_id", null)
    fun setRememberCredentials(enabled: Boolean) {
        prefs.edit().putBoolean("remember_credentials", enabled).apply()
    }
    fun rememberCredentials(): Boolean = prefs.getBoolean("remember_credentials", true)
    fun setAutoLogin(enabled: Boolean) {
        prefs.edit().putBoolean("auto_login_enabled", enabled).apply()
    }
    fun autoLoginEnabled(): Boolean = prefs.getBoolean("auto_login_enabled", true)
    fun setWifiDetect(enabled: Boolean) {
        prefs.edit().putBoolean("wifi_detect_enabled", enabled).apply()
    }
    fun wifiDetectEnabled(): Boolean = prefs.getBoolean("wifi_detect_enabled", true)
}
