package engsoft.matfit.view.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import engsoft.matfit.service.SharedPreferences

class MainViewModel(application: Application) : AndroidViewModel(application) {

    private val sharedPreferences = SharedPreferences(application.applicationContext)

    private val _nome = MutableLiveData<String>()
    val nome: LiveData<String>  = _nome

    fun nomeUsuario(){
        _nome.value = sharedPreferences.getName()
    }

    fun sair(){
        sharedPreferences.remove("nome")
    }
}