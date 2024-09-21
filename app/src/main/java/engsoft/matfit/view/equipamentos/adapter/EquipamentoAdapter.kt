package engsoft.matfit.view.equipamentos.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import engsoft.matfit.databinding.CardModelEquipamentoBinding
import engsoft.matfit.listener.OnEquipamentoListener
import engsoft.matfit.model.EquipamentoDTO
import engsoft.matfit.view.equipamentos.viewholder.EquipamentoViewHolder

class EquipamentoAdapter : RecyclerView.Adapter<EquipamentoViewHolder>() {

    private var listEquipamentos: List<EquipamentoDTO> = arrayListOf()
    private lateinit var listener: OnEquipamentoListener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EquipamentoViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val itemBinding = CardModelEquipamentoBinding.inflate(inflater, parent, false)
        return EquipamentoViewHolder(itemBinding, listener)
    }

    override fun onBindViewHolder(holder: EquipamentoViewHolder, position: Int) {
        holder.bindData(listEquipamentos[position])
    }

    override fun getItemCount(): Int {
        return listEquipamentos.count()
    }

    fun attachListener(taskListener: OnEquipamentoListener) {
        listener = taskListener
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateEquipamentos(list: List<EquipamentoDTO>){
        listEquipamentos = list
        notifyDataSetChanged()
    }
 }