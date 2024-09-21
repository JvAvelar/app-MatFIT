package engsoft.matfit.view.equipamentos

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
import engsoft.matfit.databinding.FragmentEquipamentoBinding
import engsoft.matfit.listener.OnEquipamentoListener
import engsoft.matfit.view.equipamentos.adapter.EquipamentoAdapter
import engsoft.matfit.view.funcionarios.UpdateFuncionarioActivity
import engsoft.matfit.view.viewmodel.EquipamentoViewModel

class EquipamentoFragment : Fragment() {

    private var _binding: FragmentEquipamentoBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: EquipamentoViewModel
    private val adapter = EquipamentoAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentEquipamentoBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(this)[EquipamentoViewModel::class.java]

        binding.recyclerListEquipamento.layoutManager = LinearLayoutManager(context)
        binding.recyclerListEquipamento.adapter = adapter

        val listener = object : OnEquipamentoListener {
            override fun onDelete(id: Int) {
                AlertDialog.Builder(requireContext())
                    .setTitle(getString(R.string.deleteEquipamento))
                    .setMessage(getString(R.string.textConfirmationDelete))
                    .setPositiveButton(getString(R.string.yes)) { dialog, which ->
                        viewModel.deletarEquipamento(id)
                    }
                    .setNegativeButton(getString(R.string.cancel)) { dialog, which -> null }
                    .create()
                    .show()
            }

            override fun onUpdate(id: Int) {
                viewModel.buscarEquipamento(id)
                viewModel.buscarEquipamento.observe(viewLifecycleOwner) { equipamento ->
                    if (equipamento != null) {
                        Log.i("info_onUpdateEquipamento", "Operação bem-sucedida -> $equipamento")
                        val intent = Intent(context, UpdateEquipamentoActivity::class.java)
                        intent.putExtra("id", equipamento.id)
                        intent.putExtra("nome", equipamento.nome)
                        intent.putExtra("quantidade", equipamento.quantidade)
                        startActivity(intent)
                    } else {
                        Log.i("info_onUpdateEquipamento", "Erro de execução -> $equipamento")
                        toast(getString(R.string.textEquipamentoNotFound))
                    }
                }
            }
        }

        adapter.attachListener(listener)

        viewModel.listarEquipamentos()

        observadores()

        click()

        return binding.root
    }

    override fun onResume() {
        super.onResume()
        viewModel.listarEquipamentos()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun observadores() {
        viewModel.listarEquipamentos.observe(viewLifecycleOwner) {
            adapter.updateEquipamentos(it)
        }

        // valores invertidos para sucesso
        viewModel.deletar.observe(viewLifecycleOwner) { sucesso ->
            when (sucesso) {
                false -> {
                    toast(getString(R.string.textSuccessDeleteEquipamento))
                    viewModel.resetarDeletar()
                }
                true -> {
                    toast(getString(R.string.textFailureDeleteEquipamento))
                    viewModel.resetarDeletar()
                }
                else -> {}
            }
        }
    }

    private fun click() {
        binding.btnAdd.setOnClickListener {
            startActivity(Intent(context, AddEquipamentoActivity::class.java))
        }
    }

    private fun toast(msg: String) {
        Toast.makeText(requireContext(), msg, Toast.LENGTH_SHORT).show()
    }
}
