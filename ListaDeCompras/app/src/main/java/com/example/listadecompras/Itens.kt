package com.example.listadecompras

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.SeekBar
import android.widget.SeekBar.OnSeekBarChangeListener
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class Itens : AppCompatActivity() {
    var meusProdutos = Produtos()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_itens)

        /*Preços dos produtos em ordem
        Banana, Maça, Arroz, Feijao, Carne, Peixe */
        val precos = floatArrayOf(0.4f, 0.3f, 1.1f, 1.5f, 3.2f, 3.9f)
        val barraBanana = findViewById<SeekBar>(R.id.barraBanana)
        val numeroBanana = findViewById<TextView>(R.id.undBanana)
        val precoBanana = findViewById<TextView>(R.id.precoBanana)
        val barraMaca = findViewById<SeekBar>(R.id.barraMaça)
        val numeroMaca = findViewById<TextView>(R.id.undMaca)
        val precoMaca = findViewById<TextView>(R.id.precoMaça)
        val barraArroz = findViewById<SeekBar>(R.id.barraArroz)
        val numeroArroz = findViewById<TextView>(R.id.undArroz)
        val precoArroz = findViewById<TextView>(R.id.precoArroz)
        val barraFeijao = findViewById<SeekBar>(R.id.barraFeijao)
        val numeroFeijao = findViewById<TextView>(R.id.undFeijao)
        val precoFeijao = findViewById<TextView>(R.id.precoFeijao)
        val barraCarne = findViewById<SeekBar>(R.id.barraCarne)
        val numeroCarne = findViewById<TextView>(R.id.undCarne)
        val precoCarne = findViewById<TextView>(R.id.precoCarne)
        val barraPeixe = findViewById<SeekBar>(R.id.barraPeixe)
        val numeroPeixe = findViewById<TextView>(R.id.undPeixe)
        val precoPeixe = findViewById<TextView>(R.id.precoPeixe)

        //Resposta ao rolar a barra
        barraBanana.setOnSeekBarChangeListener(object : OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
                numeroBanana.text = "Qnt " + Integer.toString(progress)
                totalBan = "R$ " + java.lang.Float.toString(Math.round(progress.toFloat() * precos[0]).toFloat())
                precoBanana.text = totalBan
            }

            override fun onStartTrackingTouch(seekBar: SeekBar) {}
            override fun onStopTrackingTouch(seekBar: SeekBar) {}
        })
        barraMaca.setOnSeekBarChangeListener(object : OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
                numeroMaca.text = "Qnt " + Integer.toString(progress)
                totalMaca = "R$ " + java.lang.Float.toString(Math.round(progress.toFloat() * precos[1]).toFloat())
                precoMaca.text = totalMaca
            }

            override fun onStartTrackingTouch(seekBar: SeekBar) {}
            override fun onStopTrackingTouch(seekBar: SeekBar) {}
        })
        barraArroz.setOnSeekBarChangeListener(object : OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
                numeroArroz.text = "Qnt " + Integer.toString(progress)
                totalArroz = "R$ " + java.lang.Float.toString(Math.round(progress.toFloat() * precos[2]).toFloat())
                precoArroz.text = totalArroz
            }

            override fun onStartTrackingTouch(seekBar: SeekBar) {}
            override fun onStopTrackingTouch(seekBar: SeekBar) {}
        })
        barraFeijao.setOnSeekBarChangeListener(object : OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
                numeroFeijao.text = "Qnt " + Integer.toString(progress)
                totalFei = "R$ " + java.lang.Float.toString(Math.round(progress.toFloat() * precos[3]).toFloat())
                precoFeijao.text = totalFei
            }

            override fun onStartTrackingTouch(seekBar: SeekBar) {}
            override fun onStopTrackingTouch(seekBar: SeekBar) {}
        })
        barraCarne.setOnSeekBarChangeListener(object : OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
                numeroCarne.text = "Qnt " + Integer.toString(progress)
                totalCar = "R$ " + java.lang.Float.toString(Math.round(progress.toFloat() * precos[4]).toFloat())
                precoCarne.text = totalCar
            }

            override fun onStartTrackingTouch(seekBar: SeekBar) {}
            override fun onStopTrackingTouch(seekBar: SeekBar) {}
        })
        barraPeixe.setOnSeekBarChangeListener(object : OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
                numeroPeixe.text = "Qnt " + Integer.toString(progress)
                totalPeix = "R$ " + java.lang.Float.toString(Math.round(progress.toFloat() * precos[5]).toFloat())
                precoPeixe.text = totalPeix
            }

            override fun onStartTrackingTouch(seekBar: SeekBar) {}
            override fun onStopTrackingTouch(seekBar: SeekBar) {}
        })
    }

    fun checkOut(view: View?) {
        val tela3 = Intent(applicationContext, Checkout1::class.java)
        startActivity(tela3)
    }

    companion object {
        @JvmField
        var totalBan = "R$ 0"
        @JvmField
        var totalMaca = "R$ 0"
        @JvmField
        var totalArroz = "R$ 0"
        @JvmField
        var totalFei = "R$ 0"
        @JvmField
        var totalCar = "R$ 0"
        @JvmField
        var totalPeix = "R$ 0"
    }
}