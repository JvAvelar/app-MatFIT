package engsoft.matfit.view.funcionarios

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import engsoft.matfit.R
import engsoft.matfit.databinding.ActivityUpdateFuncionarioBinding
import engsoft.matfit.model.BaseValidacao
import engsoft.matfit.model.FuncionarioUpdate
import engsoft.matfit.view.viewmodel.FuncionarioViewModel

class UpdateFuncionarioActivity : AppCompatActivity() {
    private val binding by lazy {
        ActivityUpdateFuncionarioBinding.inflate(layoutInflater)
    }
    private lateinit var viewModel: FuncionarioViewModel
    private val baseValidacao = BaseValidacao(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        viewModel = ViewModelProvider(this)[FuncionarioViewModel::class.java]

        click()

        observadores()

        dadosPadroes()
    }

    private fun click() {
        binding.iconBack.setOnClickListener {
            finish()
        }
        binding.btnRegister.setOnClickListener {
            atualizar()
        }
    }
private fun dadosPadroes(){
    val nome = intent?.getStringExtra("nome") ?: ""
    val funcao = intent?.getStringExtra("funcao") ?: ""
    val cargaHoraria = intent?.getIntExtra("cargaHoraria", 0).toString()

    binding.name.setText(nome)
    binding.funcao.setText(funcao)
    binding.cargaHoraria.setText(cargaHoraria)
    Log.i("info_UpdateFuncionarioActiviy -> dadosPadroes", "dadosPadroes: $nome")

}
    private fun atualizar() {
        val cpf = intent.getStringExtra("cpf") ?: ""
        val nome = binding.name.text.toString()
        val funcao = binding.funcao.text.toString()
        val cargaHoraria = binding.cargaHoraria.text.toString().toInt()
        val funcionarioUpdate = FuncionarioUpdate(nome, funcao, cargaHoraria)

        if (!baseValidacao.validarNome(nome))
            baseValidacao.toast(getString(R.string.textErrorName))
        else if (!baseValidacao.validarEsporte(funcao))
            baseValidacao.toast(getString(R.string.textErrorFuncao))
        else if (!baseValidacao.validarCargaHoraria(cargaHoraria))
            baseValidacao.toast(getString(R.string.textValidarCargaHoraria))
        else viewModel.atualizarFuncionario(cpf, funcionarioUpdate)
    }

    private fun observadores() {
        viewModel.atualizarFuncionario.observe(this){ funcionario ->
            if(funcionario != null){
                baseValidacao.toast("Funcionário atualizado com sucesso!")
                finish()
            } else
                baseValidacao.toast("Falha ao atualizar o funcionário. Verifique!")
        }
    }
}