package engsoft.matfit.view.equipamentos.viewholder

import android.annotation.SuppressLint
import androidx.recyclerview.widget.RecyclerView
import engsoft.matfit.databinding.CardModelEquipamentoBinding
import engsoft.matfit.listener.OnEquipamentoListener
import engsoft.matfit.model.EquipamentoDTO

class EquipamentoViewHolder(private val bind: CardModelEquipamentoBinding, private val listener: OnEquipamentoListener) :
    RecyclerView.ViewHolder(bind.root) {

    /**
     * Atribui valores aos elementos de interface do card model
     */
    @SuppressLint("SimpleDateFormat", "SetTextI18n")
    fun bindData(equipamento: EquipamentoDTO) {
        bind.textDoCpf.text = " ${equipamento.nome}"
        bind.textDoName.text = " ${equipamento.quantidade}"

        bind.iconEdit.setOnClickListener {
            listener.onUpdate(equipamento.id)
        }

        bind.iconDelete.setOnClickListener{
            listener.onDelete(equipamento.id)
        }
    }
 }