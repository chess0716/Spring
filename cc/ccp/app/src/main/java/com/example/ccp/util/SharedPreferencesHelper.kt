package com.example.ccp.util

import android.content.Context
import android.content.SharedPreferences

object SharedPreferencesHelper {
    private const val PREFS_NAME = "MyAppPrefs"
    private const val KEY_USERNAME = "username"
    private const val KEY_TOKEN = "token"

    private fun getSharedPreferences(context: Context): SharedPreferences {
        return context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
    }

    fun saveUsername(context: Context, username: String) {
        val editor = getSharedPreferences(context).edit()
        editor.putString(KEY_USERNAME, username)
        editor.apply()
    }

    fun getUsername(context: Context): String? {
        return getSharedPreferences(context).getString(KEY_USERNAME, null)
    }

    fun saveToken(context: Context, token: String) {
        val editor = getSharedPreferences(context).edit()
        editor.putString(KEY_TOKEN, token)
        editor.apply()
    }

    fun getToken(context: Context): String? {
        return getSharedPreferences(context).getString(KEY_TOKEN, null)
    }

    fun clearSharedPreferences(context: Context) {
        val editor = getSharedPreferences(context).edit()
        editor.clear()
        editor.apply()
    }
}
