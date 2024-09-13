package engsoft.matfit.service.repository

import android.content.Context
import android.util.Log
import engsoft.matfit.model.BaseValidacao
import engsoft.matfit.model.FuncionarioDTO
import engsoft.matfit.model.FuncionarioUpdate
import engsoft.matfit.service.FuncionarioService
import engsoft.matfit.service.RetrofitService

class FuncionarioRepository(context: Context) : BaseValidacao(context) {

    private val remote = RetrofitService.getService(FuncionarioService::class.java)

    suspend fun listarFuncionarios(): List<FuncionarioDTO> {
        try {
            val retorno = remote.listarFuncionarios()
            if (retorno.isSuccessful) {
                retorno.body()?.let { list ->
                    Log.i("info_listarFuncionario", "Operação bem-sucedida = $list")
                    return list
                }
            }
        } catch (e: Exception) {
            Log.i("info_listarFuncionario", "Falhou -> ${e.message}")
            e.printStackTrace()
        }
        return emptyList()
    }

    suspend fun cadastrarFuncionario(funcionario: FuncionarioDTO): Boolean {
        var sucesso = false
        try {
            val retorno = remote.cadastrarFuncionario(funcionario)
            if (retorno.isSuccessful) {
                retorno.body()?.let {
                    Log.i("info_cadastrarFuncionario", "Operação bem-sucedida = $it")
                    sucesso = true
                    return sucesso
                }
            } else {
                Log.i(
                    "info_cadastrarAluno",
                    "Erro na operação: = ${retorno.code()} - ${retorno.message()}"
                )
            }

        } catch (e: Exception) {
            Log.i("info_cadastrarFuncionario", "Falhou -> ${e.message}")
            e.printStackTrace()
        }
        return sucesso
    }

    suspend fun buscarFuncionario(cpf: String): FuncionarioDTO? {
        var funcionario: FuncionarioDTO? = null
        try {
            val retorno = remote.buscarFuncionario(cpf)
            if (retorno.isSuccessful) {
                retorno.body()?.let {
                    Log.i("info_cadastrarFuncionario", "Operação bem-sucedida = $it")
                    funcionario = it
                    return funcionario
                }
            } else {
                Log.i(
                    "info_buscarFuncionario",
                    "Erro na operação: = ${retorno.code()} - ${retorno.message()}"
                )
            }

        } catch (e: Exception) {
            Log.i("info_buscarFuncionario", "${e.message}")
            e.printStackTrace()
        }
        return funcionario
    }

    suspend fun atualizarFuncionario(cpf: String, funcionario: FuncionarioUpdate): FuncionarioDTO? {
        var funcionarioRetornado: FuncionarioDTO? = null
        try {
            val retorno = remote.atualizarFuncionario(cpf, funcionario)
            if (retorno.isSuccessful) {
                retorno.body()?.let {
                    Log.i("info_atualizarFuncionario", "Operação bem-sucedida = $it")
                    funcionarioRetornado = it
                    return funcionarioRetornado
                }
            } else {
                Log.i(
                    "info_atualizarFuncionario",
                    "Erro na operação: = ${retorno.code()} - ${retorno.message()}"
                )
            }

        } catch (e: Exception) {
            Log.i("info_atualizarFuncionario", "${e.message}")
            e.printStackTrace()
        }
        return funcionarioRetornado
    }

    suspend fun deletarFuncionario(cpf: String): Boolean {
        var sucesso = false
        try {
            val retorno = remote.deletarFuncionario(cpf)
            if (retorno.isSuccessful){
                retorno.body()?.let {
                    Log.i("info_deletarFuncionario", "Operação bem-sucedida = $it")
                    sucesso = it
                    return sucesso
                }
            } else{
                Log.i(
                    "info_deletarFuncionario",
                    "Erro na operação: = ${retorno.code()} - ${retorno.message()}"
                )
            }
        } catch (e: Exception){
            Log.i("info_deletarFuncionario", "${e.message}")
            e.printStackTrace()
        }
        return sucesso
    }
}