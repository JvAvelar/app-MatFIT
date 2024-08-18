package engsoft.matfit.service.repository

import android.util.Log
import engsoft.matfit.model.Aluno
import engsoft.matfit.model.AlunoRequest
import engsoft.matfit.model.AlunoResponse
import engsoft.matfit.model.AtualizarAlunoRequest

class AlunoRepository {

    private val remote = RetrofitService.getService(AlunoService::class.java)

    suspend fun listarAlunos(): List<Aluno> {
        try {
            val retorno = remote.listarAlunos()
            if (retorno.isSuccessful) {
                retorno.body()?.let { list ->
                    return list
                }
            }
        } catch (e: Exception) {
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
            Log.i("info_cadastrarAluno", "Erro durante a exclusão: ${e.message}")
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

    suspend fun atualizarAluno(cpf: String, aluno: AtualizarAlunoRequest): AlunoResponse? {
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
}