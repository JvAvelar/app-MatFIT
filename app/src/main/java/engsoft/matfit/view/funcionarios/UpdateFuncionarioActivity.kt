package engsoft.matfit.view.funcionarios

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import engsoft.matfit.R
import engsoft.matfit.databinding.ActivityUpdateAlunoBinding

class UpdateFuncionarioActivity : AppCompatActivity() {
    private val binding by lazy {
        ActivityUpdateAlunoBinding.inflate(layoutInflater)
    }
    private lateinit var viewModel: FuncionarioViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update_funcionario)

    }
}