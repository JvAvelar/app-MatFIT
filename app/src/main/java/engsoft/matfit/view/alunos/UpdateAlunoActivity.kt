package engsoft.matfit.view.alunos

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import engsoft.matfit.R
import engsoft.matfit.databinding.ActivityUpdateAlunoBinding
import engsoft.matfit.model.AtualizarAlunoRequest
import engsoft.matfit.view.viewmodel.AlunoViewModel

class UpdateAlunoActivity : AppCompatActivity() {
    private val binding by lazy {
        ActivityUpdateAlunoBinding.inflate(layoutInflater)
    }
    private lateinit var viewModel: AlunoViewModel
    private lateinit var cpf: String

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

        if (!validarNome(nome))
            toast(getString(R.string.textErrorName))
        else if (!validarEsporte(esporte))
            toast(getString(R.string.textErrorSport))
        else {
            val aluno = AtualizarAlunoRequest(nome, esporte)
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
                finish()
                Toast.makeText(
                    applicationContext,
                    getString(R.string.textSucessUpdated),
                    Toast.LENGTH_SHORT
                ).show()
            } else
                Toast.makeText(
                    applicationContext,
                    getString(R.string.textFailureUpdated), Toast.LENGTH_SHORT
                ).show()
        }
    }

    private fun toast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    // Faz a validação do nome
    private fun validarNome(nome: String) =
        (nome.isNotBlank() && nome.isNotEmpty() && nome.length >= 4)

    // Faz a validação do esporte
    private fun validarEsporte(esporte: String) =
        (esporte.length >= 3 && esporte.isNotBlank() && esporte.isNotEmpty())
}