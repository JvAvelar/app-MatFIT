package engsoft.matfit.view.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import engsoft.matfit.model.FuncionarioDTO
import engsoft.matfit.model.FuncionarioUpdate
import engsoft.matfit.service.repository.FuncionarioRepository
import kotlinx.coroutines.launch

class FuncionarioViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = FuncionarioRepository(application.applicationContext)

    private val _listarFuncionarios = MutableLiveData<List<FuncionarioDTO>>()
    val listarFuncionario: LiveData<List<FuncionarioDTO>> = _listarFuncionarios

    private val _cadastro = MutableLiveData<Boolean>()
    val cadastroFuncionario: LiveData<Boolean> = _cadastro

    private val _deletar = MutableLiveData<Boolean>()
    val deletarFuncionario: LiveData<Boolean> = _deletar

    private val _buscar = MutableLiveData<FuncionarioDTO?>()
    val buscarFuncionario: LiveData<FuncionarioDTO?> = _buscar

    private val _atualizar = MutableLiveData<FuncionarioDTO?>()
    val atualizarFuncionario: LiveData<FuncionarioDTO?> = _atualizar


    fun listarFuncionarios(){
        viewModelScope.launch {
            _listarFuncionarios.postValue(repository.listarFuncionarios())
        }
    }

    fun cadastrarFuncionario(funcionario: FuncionarioDTO){
        viewModelScope.launch {
            _cadastro.postValue(repository.cadastrarFuncionario(funcionario))
        }
    }

    fun buscarFuncionario(cpf: String){
        viewModelScope.launch {
            try {
                _buscar.postValue(repository.buscarFuncionario(cpf))
            } catch (e: Exception){
                _buscar.postValue(null)
                e.printStackTrace()
            }
        }
    }

    fun atualizarFuncionario(cpf: String, funcionario: FuncionarioUpdate){
        viewModelScope.launch {
            try{
                _atualizar.postValue(repository.atualizarFuncionario(cpf, funcionario))
            } catch (e: Exception){
                _atualizar.postValue(null)
                e.printStackTrace()
            }
        }
    }

    fun deletarFuncionario(cpf:String){
        viewModelScope.launch {
            _deletar.postValue(repository.deletarFuncionario(cpf))
        }
    }
}