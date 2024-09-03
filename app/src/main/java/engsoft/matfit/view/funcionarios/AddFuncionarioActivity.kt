package engsoft.matfit.view.funcionarios

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import engsoft.matfit.R
import engsoft.matfit.databinding.ActivityAddAlunoBinding

class AddFuncionarioActivity : AppCompatActivity() {
    private val binding by lazy {
        ActivityAddAlunoBinding.inflate(layoutInflater)
    }
    private lateinit var viewModel: FuncionarioViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_funcionario)



    }
}