package engsoft.matfit.view

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import engsoft.matfit.R
import engsoft.matfit.databinding.ActivityLoginBinding
import engsoft.matfit.service.SharedPreferences

class LoginActivity : AppCompatActivity() {
    private val binding by lazy {
        ActivityLoginBinding.inflate(layoutInflater)
    }
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        sharedPreferences = SharedPreferences(this)

        binding.bntEnter.setOnClickListener {
            salvarNome()
        }

        supportActionBar?.hide()

        verificarNome()

    }

    private fun salvarNome() {
        val nome = binding.editName.text.toString()
        if (nome.isBlank())
            Toast.makeText(this, getString(R.string.textFieldEmptyLogin), Toast.LENGTH_SHORT).show()
        else if (nome.length <= 2)
            Toast.makeText(this, getString(R.string.textNameInvalid), Toast.LENGTH_SHORT).show()
        else {
            sharedPreferences.saveName(nome)
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
    }

    private fun verificarNome(){
        val nome = sharedPreferences.getName()
        if (nome != "") {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
        else return
    }
}

