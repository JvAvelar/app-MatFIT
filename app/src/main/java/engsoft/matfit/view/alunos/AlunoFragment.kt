package engsoft.matfit.view.alunos

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import engsoft.matfit.R
import engsoft.matfit.databinding.FragmentAlunoBinding
import engsoft.matfit.listener.OnAlunoListener
import engsoft.matfit.model.Constantes
import engsoft.matfit.view.alunos.adapter.AlunoAdapter
import engsoft.matfit.view.viewmodel.AlunoViewModel

class AlunoFragment : Fragment() {

    private var _binding: FragmentAlunoBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: AlunoViewModel
    private val adapter = AlunoAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentAlunoBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(this)[AlunoViewModel::class.java]

        // Layout
        binding.recyclerListAlunos.layoutManager = LinearLayoutManager(context)
        // Adapter
        binding.recyclerListAlunos.adapter = adapter

        val listener = object : OnAlunoListener {
            override fun onUpdate(cpf: String) {
                clickAtualizar(cpf)
            }

            override fun onDelete(cpf: String) {
                AlertDialog.Builder(requireContext()).setTitle(getString(R.string.deleteAluno))
                    .setMessage(getString(R.string.textConfirmationDelete))
                    .setPositiveButton(getString(R.string.yes)) { dialog, which ->
                        viewModel.deletarAluno(cpf)
                    }.setNegativeButton(getString(R.string.cancel)) { dialog, which -> null }
                    .create().show()
            }

            override fun OnPayment(cpf: String) {
                viewModel.verificarPagamento(cpf)
                viewModel.verificarPagamento.observe(viewLifecycleOwner) { aluno ->
                    if(aluno != null) {
                        Log.i("info_OnPaymentAluno", "Operação bem-sucedida -> $aluno")
                        val intent = Intent(context, PagamentoAlunoActivity::class.java)
                        intent.putExtra(Constantes.Aluno.CPF, aluno.cpf)
                        intent.putExtra(Constantes.Aluno.NOME, aluno.nome)
                        intent.putExtra(Constantes.Aluno.ESPORTE, aluno.esporte)
                        intent.putExtra(Constantes.Aluno.DATA_PAGAMENTO, aluno.dataPagamento)
                        intent.putExtra(Constantes.Aluno.PAGAMENTO_ATRASADO, aluno.pagamentoAtrasado)
                        startActivity(intent)
                    } else {
                        Log.i("info_OnPaymentAluno", "Erro de execução -> $aluno")
                        toast(getString(R.string.textAlunoNotFound))
                    }
                }
            }
        }

        binding.btnAdd.setOnClickListener {
            startActivity(Intent(context, AddAlunoActivity::class.java))
        }

        viewModel.listarAlunos()

        adapter.attachListener(listener)

        observadores()

        return binding.root
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onResume() {
        super.onResume()
        viewModel.listarAlunos()
    }

    private fun clickAtualizar(cpf: String) {
        viewModel.buscarAluno(cpf)
        viewModel.buscarAluno.observe(viewLifecycleOwner) { aluno ->
            Log.i("info_onUpdate", "Bem sucedido -> $aluno")
            if (aluno != null) {
                val intent = Intent(context, UpdateAlunoActivity::class.java)
                intent.putExtra(Constantes.Aluno.CPF, aluno.cpf)
                intent.putExtra(Constantes.Aluno.NOME, aluno.nome)
                intent.putExtra(Constantes.Aluno.ESPORTE, aluno.esporte)
                startActivity(intent)
            } else {
                Log.i("info_onUpdate", "Erro de execução -> $aluno")
                toast(getString(R.string.textAlunoNotFound))
            }
        }
    }

    @SuppressLint("SetTextI18n")
    private fun observadores() {
        viewModel.listarAlunos.observe(viewLifecycleOwner) { listaAlunos ->
            if (listaAlunos.isNullOrEmpty())
                mostrarMsgListaVazia()
            else{
                mostrarCadModel()
                adapter.updateAlunos(listaAlunos)
            }
        }

        // valores invertidos para sucesso
        viewModel.deletar.observe(viewLifecycleOwner) { sucesso ->
            when (sucesso) {
                false -> {
                    toast(getString(R.string.textSucessDeletedAluno))
                    viewModel.resetarDeletar()
                }

                true -> {
                    toast(getString(R.string.textFailureDeletedAluno))
                    viewModel.resetarDeletar()
                }

                else -> {}
            }
        }
    }

    private fun mostrarMsgListaVazia() {
        binding.recyclerListAlunos.visibility = View.GONE
        binding.emptyView.visibility = View.VISIBLE
    }

    private fun mostrarCadModel(){
        binding.recyclerListAlunos.visibility = View.VISIBLE
        binding.emptyView.visibility = View.GONE
    }

    private fun toast(msg: String) {
        Toast.makeText(requireContext(), msg, Toast.LENGTH_SHORT).show()
    }
}