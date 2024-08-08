package engsoft.matfit.ui.alunos

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import engsoft.matfit.R
import engsoft.matfit.databinding.FragmentAlunoBinding
import engsoft.matfit.service.listener.OnAlunoListener
import engsoft.matfit.service.model.Aluno
import engsoft.matfit.ui.alunos.adapter.AlunoAdapter

class AlunoFragment : Fragment() {

    private var _binding: FragmentAlunoBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: AlunoViewModel
    private val adapter = AlunoAdapter()
    private lateinit var aluno: Aluno // TODO -> Atribuir aluno

    // coroutine

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAlunoBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(this)[AlunoViewModel::class.java]

        // Layout
        binding.recyclerListAlunos.layoutManager = LinearLayoutManager(context)
        // Adapter
        binding.recyclerListAlunos.adapter = adapter

        val listener = object : OnAlunoListener {
            override fun onUpdate(cpf: String) {
                val intent = Intent(context, UpdateAlunoActivity::class.java)
                intent.putExtra("cpf", aluno.cpf)
                intent.putExtra("nome", aluno.nome)
                intent.putExtra("esporte", aluno.esporte)
                startActivity(intent)
            }

            override fun onDelete(cpf: String) {
                AlertDialog.Builder(requireContext())
                    .setTitle(getString(R.string.deleteAluno))
                    .setMessage(getString(R.string.textConfirmationDelete))
                    .setPositiveButton(getString(R.string.yes)) { dialog, which ->
                     //   viewModel.deleteAluno(cpf)
                        //viewModel.listAllAlunos()
                    }
                    .setNegativeButton(getString(R.string.cancel)) { dialog, which -> null }
                    .create()
                    .show()
            }

            override fun OnPayment(cpf: String) {
                // startActivity(Intent(context, Payment::class.java))
            }
        }

        adapter.attachListener(listener)

        observer()

        clickable()

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onResume() {
        super.onResume()
      //  viewModel.listAllAlunos()
    }

    private fun clickable() {
        binding.btnAdd.setOnClickListener {
            startActivity(Intent(context, AddAlunoActivity::class.java))
        }
    }

    private fun observer(){

    }
}