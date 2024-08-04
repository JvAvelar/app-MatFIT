package engsoft.matfit.ui.equipamentos

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import engsoft.matfit.databinding.FragmentEquipamentoBinding

class EquipamentoFragment : Fragment() {

    private var _binding: FragmentEquipamentoBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: EquipamentoViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentEquipamentoBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(this)[EquipamentoViewModel::class.java]

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}