package engsoft.matfit.view.funcionarios.viewholder

import android.annotation.SuppressLint
import androidx.recyclerview.widget.RecyclerView
import engsoft.matfit.databinding.CardModelFuncionarioBinding
import engsoft.matfit.listener.OnFuncionarioListener
import engsoft.matfit.model.Funcionario

class FuncionarioViewHolder(
    private val bind: CardModelFuncionarioBinding,
    private val listener: OnFuncionarioListener, ) : RecyclerView.ViewHolder(bind.root) {

    /**
     * Atribui valores aos elementos de interface do card model
     */
    @SuppressLint("SimpleDateFormat", "SetTextI18n")
    fun bindData(func: Funcionario) {
        bind.textDoCpf.text = " ${func.cpf}"
        bind.textDoName.text = " ${func.nome}"
        bind.textDoSport.text = " ${func.funcao}"
        bind.textDoDay.text = " ${func.cargaHoraria}"

        bind.iconEdit.setOnClickListener {
            listener.onUpdate(func.cpf)
        }

        bind.iconDelete.setOnClickListener {
            listener.onDelete(func.cpf)
        }
    }
}