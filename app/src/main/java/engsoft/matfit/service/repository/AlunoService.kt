package engsoft.matfit.service.repository

import engsoft.matfit.model.Aluno
import engsoft.matfit.model.AlunoRequest
import engsoft.matfit.model.AlunoResponse
import engsoft.matfit.model.AtualizarAlunoRequest
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.HTTP
import retrofit2.http.Headers
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface AlunoService {

    @GET("/aluno")
    suspend fun listarAlunos(): Response<List<Aluno>>

    @POST("/aluno")
    @Headers("Content-Type: application/json")
    suspend fun cadastrarAluno(
        @Body aluno: AlunoRequest,
    ): Response<AlunoResponse>

    @HTTP(method = "DELETE", path = "/aluno/{cpf}", hasBody = true)
    suspend fun deletarAluno(
        @Path("cpf") cpf: String,
    ): Response<Boolean>

    @PUT("/aluno/{cpf}")
    @Headers("Content-Type: application/json")
    suspend fun atualizarAluno(
        @Path("cpf") cpf: String,
        @Body aluno: AtualizarAlunoRequest,
    ): Response<AlunoResponse>

    @GET("/aluno/{cpf}")
    @Headers("Content-Type: application/json")
    suspend fun buscarAluno(
        @Path("cpf") cpf: String,
    ): Response<AlunoResponse>


    @PATCH("/aluno/pagar/cpf}")
    suspend fun realizarPagamento(
        @Path("cpf") cpf: String,
    ): Response<Aluno>

    @PATCH("/aluno/pagamento/cpf}")
    suspend fun verificarPagamento(
        @Path("cpf") cpf: String,
    ): Response<Aluno>

}