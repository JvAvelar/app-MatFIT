package engsoft.matfit.view.alunos

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import engsoft.matfit.R
import engsoft.matfit.databinding.ActivityAddAlunoBinding
import engsoft.matfit.model.AlunoRequest
import engsoft.matfit.view.viewmodel.AlunoViewModel

class AddAlunoActivity : AppCompatActivity() {
    private val binding by lazy {
        ActivityAddAlunoBinding.inflate(layoutInflater)
    }
    private lateinit var viewModel: AlunoViewModel

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
                Toast.makeText(this, getString(R.string.textSucessRegister), Toast.LENGTH_SHORT)
                    .show()
            } else
                Toast.makeText(this, getString(R.string.textFailureRegister), Toast.LENGTH_SHORT)
                    .show()
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

    private fun toast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    private fun addAluno() {
        val cpf = binding.editCpf.text.toString()
        val nome = binding.editName.text.toString()
        val esporte = binding.editSport.text.toString()

        if (!validarCpf(cpf))
            toast(getString(R.string.textErrorCpf))
        else if (!validarNome(nome))
            toast(getString(R.string.textErrorName))
        else if (!validarEsporte(esporte))
            toast(getString(R.string.textErrorSport))
        else
            viewModel.cadastrarAluno(AlunoRequest(cpf, nome, esporte))
    }

    // Faz a validação do cpf
    private fun validarCpf(cpfAntigo: String): Boolean {
        // Remove caracteres não numéricos do CPF
        var cpf = cpfAntigo
        cpf = cpf.replace("[^0-9]".toRegex(), "")
            .replace("-", "").replace(".", "")

        // Verifica se o CPF tem 11 dígitos
        if (cpf.length != 11) {
            return false
        }

        // Verifica se todos os dígitos são iguais
        var digitosIguais = true
        for (i in 1..10) {
            if (cpf[i] != cpf[0]) {
                digitosIguais = false
                break
            }
        }
        if (digitosIguais) {
            return false
        }

        // Validação dos dígitos verificadores
        var soma = 0
        for (i in 0..8) {
            soma += (10 - i) * (cpf[i].code - '0'.code)
        }
        var digito1 = 11 - soma % 11
        if (digito1 > 9) {
            digito1 = 0
        }
        soma = 0
        for (i in 0..9) {
            soma += (11 - i) * (cpf[i].code - '0'.code)
        }
        var digito2 = 11 - soma % 11
        if (digito2 > 9) {
            digito2 = 0
        }
        return cpf[9].code - '0'.code == digito1 && cpf[10].code - '0'.code == digito2
    }

    // Faz a validação do nome
    private fun validarNome(nome: String) =
        (nome.isNotBlank() && nome.isNotEmpty() && nome.length >= 4)

    // Faz a validação do esporte
    private fun validarEsporte(esporte: String) =
        (esporte.length >= 3 && esporte.isNotBlank() && esporte.isNotEmpty())
}