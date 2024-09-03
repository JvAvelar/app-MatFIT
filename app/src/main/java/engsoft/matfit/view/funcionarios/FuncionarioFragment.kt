package engsoft.matfit.view.funcionarios

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
import engsoft.matfit.databinding.FragmentFuncionarioBinding
import engsoft.matfit.listener.OnFuncionarioListener
import engsoft.matfit.view.funcionarios.adapter.FuncionarioAdapter

class FuncionarioFragment : Fragment() {

    private var _binding: FragmentFuncionarioBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: FuncionarioViewModel
    private val adapter = FuncionarioAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentFuncionarioBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(this)[FuncionarioViewModel::class.java]

        binding.recyclerListFuncionario.layoutManager = LinearLayoutManager(context)
        binding.recyclerListFuncionario.adapter = adapter

        val listener = object : OnFuncionarioListener{
            override fun onUpdate(cpf: String) {
                startActivity(Intent(context, UpdateFuncionarioActivity::class.java))
            }

            override fun onDelete(cpf: String) {
                AlertDialog.Builder(requireContext()).setTitle(getString(R.string.deleteAluno))
                    .setMessage(getString(R.string.textConfirmationDelete))
                    .setPositiveButton(getString(R.string.yes)) { dialog, which -> null
                       // viewModel.deletarFuncionario(cpf)
                    }.setNegativeButton(getString(R.string.cancel)) { dialog, which -> null }
                    .create().show()
            }
        }

        adapter.attachListener(listener)

        binding.btnAdd.setOnClickListener {
            startActivity(Intent(context, AddFuncionarioActivity::class.java))
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onResume() {
        super.onResume()
    }



}