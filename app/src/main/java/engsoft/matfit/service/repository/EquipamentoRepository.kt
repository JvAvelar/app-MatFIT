package engsoft.matfit.service.repository

import android.util.Log
import engsoft.matfit.model.EquipamentoDTO
import engsoft.matfit.service.EquipamentoService
import engsoft.matfit.service.RetrofitService

class EquipamentoRepository {

    private val remote = RetrofitService.getService(EquipamentoService::class.java)

    suspend fun cadastrarEquipamento(equipamento: EquipamentoDTO): Boolean {
        var sucesso = false
        try {
            val retorno = remote.cadastrarEquipamento(equipamento)
            if (retorno.isSuccessful) {
                retorno.body()?.let {
                    Log.i("info_cadastrarEquipamento", "Operação bem-sucedida: $it")
                    sucesso = true
                    return sucesso
                }
            } else {
                Log.i(
                    "info_cadastrarEquipamento",
                    "Erro na operação: = ${retorno.code()} - ${retorno.message()}"
                )
            }
        } catch (e: Exception) {
            Log.i("info_cadastrarEquipamento", "Falhou -> ${e.message}")
            e.printStackTrace()
        }
        return sucesso
    }

    suspend fun buscarEquipamento(id: Int): EquipamentoDTO? {
        var equipamento: EquipamentoDTO? = null
        try {
            val retorno = remote.buscarEquipamento(id)
            if(retorno.isSuccessful){
                retorno.body()?.let {
                    Log.i("info_buscarEquipamento", "Operação bem-sucedida: $it")
                    equipamento = it
                    return equipamento
                }
            } else {
                Log.i(
                    "info_buscarEquipamento",
                    "Erro na operação: = ${retorno.code()} - ${retorno.message()}"
                )
            }
        } catch (e: Exception) {
            Log.i("info_buscarEquipamento", "Falhou -> ${e.message}")
            e.printStackTrace()
        }
        return equipamento
    }

    suspend fun atualizarEquipamento(id: Int, equipamento: EquipamentoDTO): EquipamentoDTO?{
        var equipamentoRetornado: EquipamentoDTO? = null
        try {
            val retorno = remote.atualizarEquipamento(id, equipamento)
            if (retorno.isSuccessful){
                retorno.body()?.let {
                    Log.i("info_atualizarEquipamento", "Operação bem-sucedida: $it")
                    equipamentoRetornado = it
                    return equipamentoRetornado
                }
            } else{
                Log.i(
                    "info_atualizarEquipamento",
                    "Erro na operação: = ${retorno.code()} - ${retorno.message()}"
                )
            }
        }catch (e: Exception){
            Log.i("info_atualizarEquipamento", "Falhou -> ${e.message}")
            e.printStackTrace()
        }
        return equipamentoRetornado
    }

    suspend fun deletarEquipamento(id: Int): Boolean{
        var sucesso = false
        try {
            val retorno = remote.deletarEquipamento(id)
            if (retorno.isSuccessful){
                retorno.body()?.let {
                    Log.i("info_deletarEquipamento", "Operação bem-sucedida: $it")
                    sucesso = it
                    return sucesso
                }
            } else{
                Log.i(
                    "info_deletarEquipamento",
                    "Erro na operação: = ${retorno.code()} - ${retorno.message()}"
                )
            }
        } catch (e: Exception){
            Log.i("info_deletarEquipamento", "Falhou -> ${e.message}")
            e.printStackTrace()
        }
        return sucesso
    }

    suspend fun listarEquipamentos(): List<EquipamentoDTO>{
        try {
            val retorno = remote.listarEquipamentos()
            if(retorno.isSuccessful){
                retorno.body()?.let { list ->
                    Log.i("info_listarEquipamentos", "Operação bem-sucedida: $list")
                    return list
                }
            } else{
                Log.i("info_listarEquipamentos", "Erro na operação -> ${retorno.code()} - ${retorno.message()}")
            }
        } catch (e: Exception){
            Log.i("info_listarEquipamentos", "Falhou -> ${e.message}")
            e.printStackTrace()
        }
        return emptyList()
    }
}