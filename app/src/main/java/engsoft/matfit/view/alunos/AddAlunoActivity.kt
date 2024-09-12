package engsoft.matfit.view.alunos

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import engsoft.matfit.R
import engsoft.matfit.databinding.ActivityAddAlunoBinding
import engsoft.matfit.model.AlunoRequest
import engsoft.matfit.model.BaseValidacao
import engsoft.matfit.view.viewmodel.AlunoViewModel

class AddAlunoActivity : AppCompatActivity() {
    private val binding by lazy {
        ActivityAddAlunoBinding.inflate(layoutInflater)
    }
    private lateinit var viewModel: AlunoViewModel
    private val baseValidacao = BaseValidacao(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        viewModel = ViewModelProvider(this)[AlunoViewModel::class.java]

        observadores()

        click()

    }

    private fun observadores() {
        viewModel.cadastro.observe(this) {
            if (it) {
                finish()
                baseValidacao.toast(getString(R.string.textSucessRegisterAluno))
            } else
                baseValidacao.toast(getString(R.string.textFailureRegisterAluno))
        }

    }

    private fun click() {
        binding.buttonAdd.setOnClickListener {
            addAluno()
        }
        binding.iconBack.setOnClickListener {
            finish()
        }
    }

    private fun addAluno() {
        val cpf = binding.editCpf.text.toString()
        val nome = binding.editName.text.toString()
        val esporte = binding.editSport.text.toString()

        if (!baseValidacao.validarCpf(cpf))
            baseValidacao.toast(getString(R.string.textErrorCpf))
        else if (!baseValidacao.validarNome(nome))
            baseValidacao.toast(getString(R.string.textErrorName))
        else if (!baseValidacao.validarEsporte(esporte))
            baseValidacao.toast(getString(R.string.textErrorSport))
        else
            viewModel.cadastrarAluno(AlunoRequest(cpf, nome, esporte))
    }
}