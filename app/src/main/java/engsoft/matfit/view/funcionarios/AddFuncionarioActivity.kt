package engsoft.matfit.view.funcionarios

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import engsoft.matfit.R
import engsoft.matfit.databinding.ActivityAddFuncionarioBinding
import engsoft.matfit.model.BaseValidacao
import engsoft.matfit.model.FuncionarioDTO
import engsoft.matfit.view.viewmodel.FuncionarioViewModel

class AddFuncionarioActivity : AppCompatActivity() {
    private val binding by lazy {
        ActivityAddFuncionarioBinding.inflate(layoutInflater)
    }
    private lateinit var viewModel: FuncionarioViewModel
    private val baseValidacao = BaseValidacao(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        viewModel = ViewModelProvider(this)[FuncionarioViewModel::class.java]

        observadores()

        click()

    }

    private fun click() {
        binding.btnRegister.setOnClickListener {
            addFuncionario()
        }

        binding.iconBack.setOnClickListener {
            finish()
        }
    }

    private fun addFuncionario() {
        val cpf = binding.cpf.text.toString()
        val nome = binding.name.text.toString()
        val funcao = binding.funcao.text.toString()
        val cargaHoraria = binding.cargaHoraria.text.toString().toInt()
        val funcionario = FuncionarioDTO(cpf, nome, funcao, cargaHoraria)

        if (!baseValidacao.validarCpf(cpf))
            baseValidacao.toast(getString(R.string.textErrorCpf))
        else if (!baseValidacao.validarNome(nome))
            baseValidacao.toast(getString(R.string.textErrorName))
        else if (!baseValidacao.validarEsporte(funcao))
            baseValidacao.toast(getString(R.string.textErrorFuncao))
        else if (!baseValidacao.validarCargaHoraria(cargaHoraria))
            baseValidacao.toast(getString(R.string.textValidarCargaHoraria))
        else viewModel.cadastrarFuncionario(funcionario)
    }

    private fun observadores() {
        viewModel.cadastroFuncionario.observe(this) {
            if (it) {
                baseValidacao.toast(getString(R.string.textSuccessRegisterFuncionario))
                finish()
            } else
                baseValidacao.toast(getString(R.string.textFailureRegisterFuncionario))
        }
    }
}