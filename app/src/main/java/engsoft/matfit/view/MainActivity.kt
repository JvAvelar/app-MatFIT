package engsoft.matfit.view

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.widget.TextView
import com.google.android.material.navigation.NavigationView
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.drawerlayout.widget.DrawerLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.ui.NavigationUI
import engsoft.matfit.R
import engsoft.matfit.databinding.ActivityMainBinding
import engsoft.matfit.view.viewmodel.MainViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(binding.root)

        setSupportActionBar(binding.appBarMain.toolbar)

        viewModel = ViewModelProvider(this)[MainViewModel::class.java]

        setupNavigation()

        viewModel.nomeUsuario()
        observador()
    }

    // Responsável por gerenciar a navegação das fragments associadas à main
    private fun setupNavigation() {
        val drawerLayout: DrawerLayout = binding.drawerLayout
        val navView: NavigationView = binding.navView
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        appBarConfiguration = AppBarConfiguration(
            setOf(R.id.nav_aluno, R.id.nav_funcionario, R.id.nav_equipamento),
            drawerLayout
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

        navView.setNavigationItemSelectedListener {
            if (it.itemId == R.id.nav_sair)
                sair()
            else {
                NavigationUI.onNavDestinationSelected(it, navController)
                drawerLayout.closeDrawer(GravityCompat.START)
            }
            true
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

    private fun sair() {
        viewModel.sair()
        startActivity(Intent(this, LoginActivity::class.java))
        finish()
    }

    @SuppressLint("SetTextI18n")
    private fun observador() {
        viewModel.nome.observe(this) {
            val header = binding.navView.getHeaderView(0)
            header.findViewById<TextView>(R.id.textName).text = "Olá, $it"
        }
    }
}