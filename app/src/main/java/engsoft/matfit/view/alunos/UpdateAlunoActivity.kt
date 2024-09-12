package engsoft.matfit.view.alunos

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import engsoft.matfit.R
import engsoft.matfit.databinding.ActivityUpdateAlunoBinding
import engsoft.matfit.model.AlunoUpdate
import engsoft.matfit.model.BaseValidacao
import engsoft.matfit.view.viewmodel.AlunoViewModel

class UpdateAlunoActivity : AppCompatActivity() {
    private val binding by lazy {
        ActivityUpdateAlunoBinding.inflate(layoutInflater)
    }
    private lateinit var viewModel: AlunoViewModel
    private lateinit var cpf: String
    private val baseValidacao = BaseValidacao(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        viewModel = ViewModelProvider(this)[AlunoViewModel::class.java]

        click()

        dadosPadroes()

        observadores()

    }

    private fun click() {
        binding.buttonUpdate.setOnClickListener {
            atualizar()
        }
        binding.iconBack.setOnClickListener {
            finish()
        }
    }

    private fun atualizar() {
        val nome = binding.editName.text.toString()
        val esporte = binding.editSport.text.toString()

        if (!baseValidacao.validarNome(nome))
            baseValidacao.toast(getString(R.string.textErrorName))
        else if (!baseValidacao.validarEsporte(esporte))
            baseValidacao.toast(getString(R.string.textErrorSport))
        else {
            val aluno = AlunoUpdate(nome, esporte)
            viewModel.atualizarAluno(cpf, aluno)
        }
        Log.i("info_UpdateActivity -> atualizar", "Atualização bem sucedida")
    }

    private fun dadosPadroes() {
        cpf = intent?.getStringExtra("cpf") ?: ""
        val nome = intent?.getStringExtra("nome") ?: ""
        val esporte = intent?.getStringExtra("esporte") ?: ""
        Log.i("info_UpdateActivity -> dadosPadroes", "dadosPadroes: $cpf")

        binding.editName.setText(nome)
        binding.editSport.setText(esporte)
    }

    private fun observadores() {
        viewModel.atualizarAluno.observe(this) { aluno ->
            if (aluno != null) {
                baseValidacao.toast(getString(R.string.textSucessUpdatedAluno))
                finish()
            } else
                baseValidacao.toast(getString(R.string.textFailureUpdatedAluno))
        }
    }
}