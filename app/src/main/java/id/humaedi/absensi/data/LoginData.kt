package id.humaedi.absensi.data

import android.content.Context
import android.preference.PreferenceManager
internal class LoginData (context: Context) {
    companion object {
        private const val PREFS_NAME = "login-pref"
        const val TOKEN = "token"
        private const val IS_LOGIN = "is login"
    }

    private var preferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
    val editor = preferences.edit()

    fun setToken(value: String) {
        editor.putString(TOKEN, value)
        editor.apply()
    }

    fun getToken(): String {
        return preferences.getString(TOKEN, "").toString()
    }

    fun removeData() {
        editor.clear()
        editor.commit()
        editor.apply()
    }

    fun logout(context: Context?) {
        preferences = context!!.getSharedPreferences(TOKEN, Context.MODE_PRIVATE)
        preferences.edit().clear().apply()
    }

    fun setLogin(value: Boolean) {
        editor.putBoolean(IS_LOGIN, value)
        editor.apply()
    }
    fun getLogin(): Boolean {
        return preferences.getBoolean(IS_LOGIN,false)
    }
}