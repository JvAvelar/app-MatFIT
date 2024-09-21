package engsoft.matfit.view.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import engsoft.matfit.model.EquipamentoDTO
import engsoft.matfit.service.repository.EquipamentoRepository
import kotlinx.coroutines.launch

class EquipamentoViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = EquipamentoRepository()

    private val _listar = MutableLiveData<List<EquipamentoDTO>>()
    val listarEquipamentos: LiveData<List<EquipamentoDTO>> = _listar

    private val _buscarEquipamento = MutableLiveData<EquipamentoDTO>()
    val buscarEquipamento: LiveData<EquipamentoDTO> = _buscarEquipamento

    private val _cadastrar = MutableLiveData<Boolean>()
    val cadastrar: LiveData<Boolean> = _cadastrar

    private val _deletar = MutableLiveData<Boolean?>()
    val deletar: LiveData<Boolean?> = _deletar

    private val _atualizar = MutableLiveData<EquipamentoDTO?>()
    val atualizar: LiveData<EquipamentoDTO?> = _atualizar

    fun listarEquipamentos() {
        viewModelScope.launch {
            _listar.postValue(repository.listarEquipamentos())
        }
    }

    fun cadastrarEquipamento(equipamentoDTO: EquipamentoDTO) {
        viewModelScope.launch {
            _cadastrar.postValue(repository.cadastrarEquipamento(equipamentoDTO))
        }
    }

    fun atualizarEquipamento(id: Int, equipamentoDTO: EquipamentoDTO) {
        viewModelScope.launch {
            try {
                _atualizar.postValue(repository.atualizarEquipamento(id, equipamentoDTO))
            } catch (e: Exception){
                _atualizar.postValue(null)
                e.printStackTrace()
            }
        }
    }

    fun deletarEquipamento(id: Int) {
        viewModelScope.launch {
           _deletar.postValue(repository.deletarEquipamento(id))
            listarEquipamentos()
        }
    }

    fun resetarDeletar(){
        _deletar.postValue(null)
    }

    fun buscarEquipamento(id: Int) {
        viewModelScope.launch {
            _buscarEquipamento.postValue(repository.buscarEquipamento(id))
        }
    }
}