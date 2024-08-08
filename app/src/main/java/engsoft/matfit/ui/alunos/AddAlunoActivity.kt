package engsoft.matfit.ui.alunos

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import engsoft.matfit.databinding.ActivityAddAlunoBinding

class AddAlunoActivity : AppCompatActivity() {
    private val binding by lazy {
        ActivityAddAlunoBinding.inflate(layoutInflater)
    }
    private lateinit var viewModel: AlunoViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        viewModel = ViewModelProvider(this)[AlunoViewModel::class.java]

    }
}