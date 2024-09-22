package engsoft.matfit.service.repository

import android.content.Context
import android.util.Log
import engsoft.matfit.model.Aluno
import engsoft.matfit.model.AlunoRequest
import engsoft.matfit.model.AlunoResponse
import engsoft.matfit.model.AlunoUpdate
import engsoft.matfit.model.BaseValidacao
import engsoft.matfit.service.AlunoService
import engsoft.matfit.service.RetrofitService

class AlunoRepository(context: Context) : BaseValidacao(context) {

    private val remote = RetrofitService.getService(AlunoService::class.java)

    suspend fun listarAlunos(): List<Aluno> {
        try {
            val retorno = remote.listarAlunos()
            if (retorno.isSuccessful) {
                retorno.body()?.let { list ->
                    Log.i("info_listarAlunos", "Operação bem-sucedida = $list")
                    return list
                }
            }
        } catch (e: Exception) {
            Log.i("info_listarAlunos", "Erro durante a execução: ${e.message}")
            e.printStackTrace()
        }
        return emptyList()
    }

    suspend fun cadastrarAluno(aluno: AlunoRequest): Boolean {
        var sucesso = false
        try {
            val retorno = remote.cadastrarAluno(aluno)
            if (retorno.isSuccessful) {
                retorno.body()?.let {
                    Log.i("info_cadastrarAluno", "Operação bem-sucedida = $it")
                    sucesso = !it.pagamentoAtrasado
                    return sucesso
                }
            } else {
                Log.i(
                    "info_cadastrarAluno",
                    "Erro na operação: = ${retorno.code()} - ${retorno.message()}"
                )
            }
        } catch (e: Exception) {
            Log.i("info_cadastrarAluno", "Erro durante a execusão: ${e.message}")
            e.printStackTrace()
        }
        return sucesso
    }

    suspend fun deletarAluno(cpf: String): Boolean {
        var sucesso = false
        try {
            val retorno = remote.deletarAluno(cpf)
            if (retorno.isSuccessful) {
                retorno.body()?.let {
                    Log.i("info_deletarAluno", "Operação bem-sucedida = $it")
                    listarAlunos()
                    sucesso = it
                    return sucesso
                }
            } else {
                Log.i(
                    "info_deletarAluno",
                    "Erro na operação: = ${retorno.code()} - ${retorno.message()}"
                )
            }
        } catch (e: Exception) {
            Log.i("info_deletarAluno", "Erro durante a exclusão: ${e.message}")
            e.printStackTrace()
        }
        return sucesso
    }

    suspend fun buscarAluno(cpf: String): AlunoResponse? {
        var aluno: AlunoResponse? = null
        try {
            val retorno = remote.buscarAluno(cpf)
            if (retorno.isSuccessful) {
                retorno.body()?.let {
                    Log.i("info_buscarAluno", "Operação bem-sucedida = $it")
                    aluno = it
                    return aluno
                }
            } else {
                Log.i(
                    "info_buscarAluno",
                    "Erro na operação: = ${retorno.code()} - ${retorno.message()}"
                )
            }
        } catch (e: Exception) {
            Log.i("info_buscarAluno", "Erro durante a execusão: ${e.message}")
            e.printStackTrace()
        }
        return aluno
    }

    suspend fun atualizarAluno(cpf: String, aluno: AlunoUpdate): AlunoResponse? {
        var alunoRetorno: AlunoResponse? = null
        try {
            val retorno = remote.atualizarAluno(cpf, aluno)
            if (retorno.isSuccessful) {
                retorno.body()?.let {
                    Log.i("info_atualizarAluno", "Operação bem-sucedida: $it")
                    alunoRetorno = it
                    return alunoRetorno
                }
            } else {
                Log.i(
                    "info_atualizarAluno",
                    "Erro na Operação -> ${retorno.code()} - ${retorno.message()}"
                )
            }

        } catch (e: Exception) {
            Log.i("info_atualizarAluno", "Erro durante a execução ${e.message}")
            e.printStackTrace()
        }
        return alunoRetorno
    }

    suspend fun realizarPagamento(cpf: String): Boolean {
        var sucesso = false
        try {
            val retorno = remote.realizarPagamento(cpf)
            if (retorno.isSuccessful) {
                retorno.body()?.let {
                    Log.i("info_realizarPagamento", "Operação bem-sucedida: $it")
                    sucesso = it
                    return sucesso
                }
            } else {
                Log.i(
                    "info_realizarPagamento",
                    "Erro na Operação -> ${retorno.code()} - ${retorno.message()}"
                )
            }

        } catch (e: Exception) {
            Log.i("info_realizarPagamento", "Erro durante a execução ${e.message}")
            e.printStackTrace()
        }
        return sucesso
    }

    suspend fun verificarPagamento(cpf: String): Boolean {
        var sucesso = false
        try {
            val retorno = remote.verificarPagamento(cpf)
            if (retorno.isSuccessful) {
                retorno.body()?.let {
                    Log.i("info_verificarPagamento", "Operação bem-sucedida: $it")
                    sucesso = it.pagamentoAtrasado
                    return sucesso
                }
            } else {
                Log.i(
                    "info_verificarPagamento",
                    "Erro na Operação -> ${retorno.code()} - ${retorno.message()}"
                )
            }
        } catch (e: Exception) {
            Log.i("info_verificarPagamento", "Erro durante a execução ${e.message}")
            e.printStackTrace()
        }
        return sucesso
    }
}