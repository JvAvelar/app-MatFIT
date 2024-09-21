package engsoft.matfit.view.equipamentos

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import engsoft.matfit.R
import engsoft.matfit.databinding.ActivityAddEquipamentoBinding
import engsoft.matfit.model.BaseValidacao
import engsoft.matfit.model.EquipamentoDTO
import engsoft.matfit.view.viewmodel.EquipamentoViewModel

class AddEquipamentoActivity : AppCompatActivity() {
    private val binding by lazy {
        ActivityAddEquipamentoBinding.inflate(layoutInflater)
    }
    private lateinit var viewModel: EquipamentoViewModel
    private val baseValidacao = BaseValidacao(this)

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this)[EquipamentoViewModel::class.java]

        binding.editQuantidade.setText("1")

        observador()

        click()

    }

    private fun click() {
        binding.btnRegister.setOnClickListener {
            addEquipamento()
        }
        binding.iconBack.setOnClickListener {
            finish()
        }
    }

    private fun addEquipamento() {
        val nome = binding.editName.text.toString()
        val quantidade = binding.editQuantidade.text.toString().toInt()

        if (!baseValidacao.validarNome(nome) || nome.isEmpty())
            baseValidacao.toast(getString(R.string.textNameInvalid))
        else if (quantidade < 1)
            baseValidacao.toast(getString(R.string.textValidationQuantity))
        else
            viewModel.cadastrarEquipamento(EquipamentoDTO(nome = nome, quantidade = quantidade))

    }

    private fun observador() {
        viewModel.cadastrar.observe(this) {
            if (it) {
                baseValidacao.toast(getString(R.string.textSuccessRegisterEquipamento))
                finish()
            } else
                baseValidacao.toast(getString(R.string.textFailureRegisterEquipamento))
        }
    }
}