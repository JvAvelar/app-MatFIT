package engsoft.matfit.view.equipamentos

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import engsoft.matfit.databinding.ActivityUpdateEquipamentoBinding
import engsoft.matfit.view.viewmodel.EquipamentoViewModel

class UpdateEquipamentoActivity : AppCompatActivity() {
    private val binding by lazy {
        ActivityUpdateEquipamentoBinding.inflate(layoutInflater)
    }
    private lateinit var viewModel: EquipamentoViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this)[EquipamentoViewModel::class.java]

    }
}