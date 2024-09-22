package engsoft.matfit.view.alunos

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import engsoft.matfit.R
import engsoft.matfit.R.drawable.ic_cancel
import engsoft.matfit.R.drawable.ic_done
import engsoft.matfit.databinding.ActivityPagamentoAlunoBinding
import engsoft.matfit.model.BaseValidacao
import engsoft.matfit.model.Constantes
import engsoft.matfit.view.viewmodel.AlunoViewModel

@SuppressLint("UseCompatLoadingForDrawables", "SetTextI18n")
class PagamentoAlunoActivity : AppCompatActivity() {
    private val binding by lazy {
        ActivityPagamentoAlunoBinding.inflate(layoutInflater)
    }
    private lateinit var viewModel: AlunoViewModel
    private val baseValidacao = BaseValidacao(this)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        viewModel = ViewModelProvider(this)[AlunoViewModel::class.java]
        alterarDados()
        click()
        observadores()
    }

    private fun alterarDados() {
        val cpf = intent?.getStringExtra(Constantes.Aluno.CPF) ?: ""
        val pagamentoAtrasado = intent?.getBooleanExtra(Constantes.Aluno.PAGAMENTO_ATRASADO, true) ?: true
        val nome = intent?.getStringExtra(Constantes.Aluno.NOME) ?: ""
        val esporte = intent?.getStringExtra(Constantes.Aluno.ESPORTE) ?: ""
        val dataPagamento = intent?.getStringExtra(Constantes.Aluno.DATA_PAGAMENTO) ?: ""

        binding.textDoCpf.text = " $cpf"
        binding.textDoName.text = " $nome"
        binding.textDoSport.text = " $esporte"
        binding.textDoDay.text = " $dataPagamento"

        Log.i(
            "info_pagamentoAlterarDados",
            "Operação bem sucedida -> $cpf, $nome, $esporte, $dataPagamento, $pagamentoAtrasado"
        )

        if (!pagamentoAtrasado)
            binding.imageStatus.setImageDrawable(getDrawable(ic_done))
        else
            binding.imageStatus.setImageDrawable(getDrawable(ic_cancel))
    }

    private fun fazerPagamento() {
        val cpf = intent?.getStringExtra(Constantes.Aluno.CPF) ?: ""
        val pagamentoAtrasado = intent?.getBooleanExtra(Constantes.Aluno.PAGAMENTO_ATRASADO, true) ?: true

        if (pagamentoAtrasado) {
            viewModel.realizarPagamento(cpf)
        } else {
            baseValidacao.toast("A mensalidade deste mês já foi paga.")
        }
    }

    private fun click() {
        binding.btnPagar.setOnClickListener {
            fazerPagamento()
        }

        binding.imageBack.setOnClickListener {
            finish()
        }
    }

    private fun observadores() {
        viewModel.realizarPagamento.observe(this) {
            if (it) {
                baseValidacao.toast(getString(R.string.textSuccessPayment))
                binding.imageStatus.setImageDrawable(getDrawable(ic_done))
            }
            else {
                baseValidacao.toast(getString(R.string.textFailurePayment))
                binding.imageStatus.setImageDrawable(getDrawable(ic_cancel))
            }
        }
    }
}