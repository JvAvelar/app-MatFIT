package engsoft.matfit.view.funcionarios.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import engsoft.matfit.databinding.CardModelFuncionarioBinding
import engsoft.matfit.listener.OnFuncionarioListener
import engsoft.matfit.model.Aluno
import engsoft.matfit.model.Funcionario
import engsoft.matfit.view.funcionarios.viewholder.FuncionarioViewHolder

class FuncionarioAdapter : RecyclerView.Adapter<FuncionarioViewHolder>() {

    private var listFuncionarios: List<Funcionario> = arrayListOf()
    private lateinit var listener: OnFuncionarioListener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FuncionarioViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val itemBinding = CardModelFuncionarioBinding.inflate(inflater, parent, false)
        return FuncionarioViewHolder(itemBinding, listener)
    }

    override fun onBindViewHolder(holder: FuncionarioViewHolder, position: Int) {
        holder.bindData(listFuncionarios[position])
    }


    override fun getItemCount(): Int {
        return listFuncionarios.count()
    }

    fun attachListener(taskListener: OnFuncionarioListener) {
        listener = taskListener
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateAlunos(list: List<Funcionario>){
        listFuncionarios = list
        notifyDataSetChanged()
    }
 }