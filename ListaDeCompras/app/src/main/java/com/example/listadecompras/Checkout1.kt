package com.example.listadecompras

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class Checkout1 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_checkout1)
        val itensArquivo = Itens()
        total = """
            ${Itens.totalBan}
            ${Itens.totalMaca}
            ${Itens.totalArroz}
            ${Itens.totalFei}
            ${Itens.totalCar}
            ${Itens.totalPeix}
            """.trimIndent()
        val total1 = findViewById<TextView>(R.id.total)
        total1.text = total
    }

    fun mandarCompra(view: View?) {
        val emailFormulario = findViewById<EditText>(R.id.email)
        val listadecompras = findViewById<TextView>(R.id.listadecompras)
        val listadecompras2 = listadecompras.text.toString()
        val formulario = emailFormulario.text
        val formulario1 = formulario.toString()
        val email = Intent(Intent.ACTION_SEND)
        email.putExtra(Intent.EXTRA_EMAIL, arrayOf(formulario1))
        email.putExtra(Intent.EXTRA_SUBJECT, "Lista de Compras")
        email.putExtra(Intent.EXTRA_TEXT, """
     $listadecompras2
     $total
     """.trimIndent())
        email.type = "message/rfc822"
        startActivity(Intent.createChooser(email, "Choose an Email client :"))
    }

    companion object {
        var total: String? = null
    }
}