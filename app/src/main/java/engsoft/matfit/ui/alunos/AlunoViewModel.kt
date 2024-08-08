package engsoft.matfit.ui.alunos

import androidx.lifecycle.ViewModel

class AlunoViewModel : ViewModel() {



//    fun listAllAlunos() {
//        repository.listAllAlunos(object : APIListener<List<Aluno>> {
//            override fun onSuccess(result: List<Aluno>) {
//                _listAlunos.value = result
//            }
//
//            override fun onFailure(message: String) {
//                val s = message
//            }
//        })
//    }
//
//    fun deleteAluno(cpf: String) {
//        repository.deleteAluno(cpf, object : APIListener<Boolean> {
//            override fun onSuccess(result: Boolean) {
//                _deleteAluno.value = ValidationModel()
//                listAllAlunos()
//            }
//
//            override fun onFailure(message: String) {
//                _deleteAluno.value = ValidationModel(message)
//            }
//        })
//    }


    // Faz a validação do cpf
    private fun validateCpf(cpfOld: String): Boolean {
        // Remove caracteres não numéricos do CPF
        var cpf = cpfOld
        cpf = cpf.replace("[^0-9]".toRegex(), "")
            .replace("-", "").replace(".", "")

        // Verifica se o CPF tem 11 dígitos
        if (cpf.length != 11) {
            return false
        }

        // Verifica se todos os dígitos são iguais
        var digitosIguais = true
        for (i in 1..10) {
            if (cpf[i] != cpf[0]) {
                digitosIguais = false
                break
            }
        }
        if (digitosIguais) {
            return false
        }

        // Validação dos dígitos verificadores
        var soma = 0
        for (i in 0..8) {
            soma += (10 - i) * (cpf[i].code - '0'.code)
        }
        var digito1 = 11 - soma % 11
        if (digito1 > 9) {
            digito1 = 0
        }
        soma = 0
        for (i in 0..9) {
            soma += (11 - i) * (cpf[i].code - '0'.code)
        }
        var digito2 = 11 - soma % 11
        if (digito2 > 9) {
            digito2 = 0
        }
        return cpf[9].code - '0'.code == digito1 && cpf[10].code - '0'.code == digito2
    }

    // Faz a validação do nome
    private fun validateName(name: String) =
        (name.isNotBlank() && name.isNotEmpty() && name.length >= 4)

    // Faz a validação do esporte
    private fun validateSport(sport: String) =
        (sport.length >= 3 && sport.isNotBlank() && sport.isNotEmpty())
}