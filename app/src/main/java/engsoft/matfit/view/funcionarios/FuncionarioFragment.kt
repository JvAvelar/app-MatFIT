package engsoft.matfit.view.funcionarios

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import engsoft.matfit.databinding.FragmentFuncionarioBinding

class FuncionarioFragment : Fragment() {

    private var _binding: FragmentFuncionarioBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: FuncionarioViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentFuncionarioBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(this)[FuncionarioViewModel::class.java]

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}