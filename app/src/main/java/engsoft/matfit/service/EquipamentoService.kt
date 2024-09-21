package engsoft.matfit.service

import engsoft.matfit.model.EquipamentoDTO
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.HTTP
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface EquipamentoService {

    @GET("/equipamento")
    @Headers("Content-Type: application/json")
    suspend fun listarEquipamentos(): Response<List<EquipamentoDTO>>

    @GET("/equipamento/{id}")
    @Headers("Content-Type: application/json")
    suspend fun buscarEquipamento(
        @Path("id") id: Int
    ): Response<EquipamentoDTO>

    @POST("/equipamento")
    @Headers("Content-Type: application/json")
    suspend fun cadastrarEquipamento(
        @Body equipamento: EquipamentoDTO
    ): Response<EquipamentoDTO>

    @PUT("/equipamento/{id}")
    @Headers("Content-Type: application/json")
    suspend fun atualizarEquipamento(
        @Path("id") id: Int,
        @Body equipamento: EquipamentoDTO,
    ): Response<EquipamentoDTO>

    @HTTP(method = "DELETE", path = "/equipamento/{id}", hasBody = true)
    @Headers("Content-Type: application/json")
    suspend fun deletarEquipamento(
        @Path("id") id: Int
    ): Response<Boolean>

}