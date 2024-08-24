package engsoft.matfit.service

import android.content.Context
import android.content.SharedPreferences

class SharedPreferences(context: Context) {

    private val preferences: SharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)

    companion object{
        private const val PREFS_NAME = "my_prefs"
        private const val USER_NAME = "nome"
    }

    fun saveName(userName: String){
        preferences.edit().putString(USER_NAME, userName).apply()
    }

    fun getName(): String {
        return preferences.getString(USER_NAME, "") ?: ""
    }

    fun remove(key: String){
        preferences.edit().remove(key).apply()
    }
}