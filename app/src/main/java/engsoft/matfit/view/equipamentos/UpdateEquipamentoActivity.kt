package engsoft.matfit.view.equipamentos

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import engsoft.matfit.R
import engsoft.matfit.databinding.ActivityUpdateEquipamentoBinding
import engsoft.matfit.model.BaseValidacao
import engsoft.matfit.model.EquipamentoDTO
import engsoft.matfit.view.viewmodel.EquipamentoViewModel

class UpdateEquipamentoActivity : AppCompatActivity() {
    private val binding by lazy {
        ActivityUpdateEquipamentoBinding.inflate(layoutInflater)
    }
    private lateinit var viewModel: EquipamentoViewModel
    private val baseValidacao = BaseValidacao(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this)[EquipamentoViewModel::class.java]

        click()

        observador()

        dadosPadroes()
    }

    private fun click() {
        binding.btnRegister.setOnClickListener {
            atualizar()
        }
        binding.iconBack.setOnClickListener {
            finish()
        }
    }

    private fun atualizar() {
        val id = intent?.getIntExtra("id", 0) ?: 0
        val nome = binding.name.text.toString()
        val quantidade = binding.quantidade.text.toString().toInt()

        if (!baseValidacao.validarNome(nome) || nome.isEmpty())
            baseValidacao.toast(getString(R.string.textNameInvalid))
        else if (quantidade < 1)
            baseValidacao.toast(getString(R.string.textValidationQuantity))
        else
            viewModel.atualizarEquipamento(id, EquipamentoDTO(nome = nome, quantidade = quantidade))
    }

    private fun dadosPadroes(){
        val nome = intent?.getStringExtra("nome") ?: ""
        val quantidade = intent?.getIntExtra("quantidade", 0) ?: 0
        binding.name.setText(nome)
        binding.quantidade.setText(quantidade.toString())
    }

    private fun observador() {
        viewModel.atualizar.observe(this){ equipamento ->
            if (equipamento != null) {
                baseValidacao.toast(getString(R.string.textSuccessUpdateEquipamento))
                finish()
            }
            else
                baseValidacao.toast(getString(R.string.textFailureUpdateEquipamento))
        }
    }
}