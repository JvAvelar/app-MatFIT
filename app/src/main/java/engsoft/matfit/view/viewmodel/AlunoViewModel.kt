package engsoft.matfit.view.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import engsoft.matfit.model.Aluno
import engsoft.matfit.model.AlunoRequest
import engsoft.matfit.model.AlunoResponse
import engsoft.matfit.model.AlunoUpdate
import engsoft.matfit.service.repository.AlunoRepository
import kotlinx.coroutines.launch

class AlunoViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = AlunoRepository(application.applicationContext)

    private val _listarAlunos = MutableLiveData<List<Aluno>>()
    val listarAlunos: LiveData<List<Aluno>> = _listarAlunos

    private val _cadastro = MutableLiveData<Boolean>()
    val cadastro: LiveData<Boolean> = _cadastro

    private val _deletar = MutableLiveData<Boolean?>()
    val deletar: LiveData<Boolean?> = _deletar

    private val _buscarAluno = MutableLiveData<AlunoResponse?>()
    val buscarAluno: LiveData<AlunoResponse?> = _buscarAluno

    private val _atualizarAluno = MutableLiveData<AlunoResponse?>()
    val atualizarAluno: LiveData<AlunoResponse?> = _atualizarAluno

    private val _realizarPagamento = MutableLiveData<Boolean>()
    val realizarPagamento : LiveData<Boolean> = _realizarPagamento

    private val _verificarPagamento = MutableLiveData<AlunoResponse?>()
    val verificarPagamento : LiveData<AlunoResponse?> = _verificarPagamento

    fun listarAlunos() {
        viewModelScope.launch {
            _listarAlunos.postValue(repository.listarAlunos())
        }
    }

    fun cadastrarAluno(aluno: AlunoRequest) {
        viewModelScope.launch {
            _cadastro.postValue(repository.cadastrarAluno(aluno))
        }
    }

    fun deletarAluno(cpf: String) {
        viewModelScope.launch {
            _deletar.postValue(repository.deletarAluno(cpf))
            listarAlunos()
        }
    }

    fun resetarDeletar(){
        _deletar.postValue(null)
    }

    fun buscarAluno(cpf: String) {
        viewModelScope.launch {
            try {
                _buscarAluno.postValue(repository.buscarAluno(cpf))
            } catch (e: Exception) {
                _buscarAluno.postValue(null)
                e.printStackTrace()
            }
        }
    }

    fun atualizarAluno(cpf: String, aluno: AlunoUpdate) {
        viewModelScope.launch {
            try {
                _atualizarAluno.postValue(repository.atualizarAluno(cpf, aluno))
            } catch (e: Exception) {
                _atualizarAluno.postValue(null)
                e.printStackTrace()
            }
        }
    }

    fun realizarPagamento(cpf: String){
        viewModelScope.launch {
            _realizarPagamento.postValue(repository.realizarPagamento(cpf))
        }
    }

    fun verificarPagamento(cpf: String){
        viewModelScope.launch {
            _verificarPagamento.postValue(repository.verificarPagamento(cpf))
        }
    }
}