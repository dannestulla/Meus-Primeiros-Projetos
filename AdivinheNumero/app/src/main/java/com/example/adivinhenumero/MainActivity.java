package com.example.adivinhenumero;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.lang.Math;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        System.out.println(resultado);
    }

    int numeroint = 0;
    double resultado = Math.random() * 10;
    int resultado1 = (int) resultado;
    String chute;
    int chute1;
    int progresso = 0;

    public void numeroMais(View view) {

        TextView numero = findViewById(R.id.numero);
        numeroint += 1;
        numero.setText(Integer.toString(numeroint));
        System.out.println(resultado1);


    }

    public void numeroMenos(View view) {
        TextView numero = findViewById(R.id.numero);
        numeroint -= 1;
        numero.setText(Integer.toString(numeroint));
        System.out.println(resultado1);
    }

    public void cliqueBotao(View view) {
        //pegar as Ids
        TextView numero = findViewById(R.id.numero);
        TextView textoAbaixo = findViewById(R.id.textoDois);
        chute = numero.getText().toString();
        chute1 = Integer.parseInt(chute);
        if (chute1 == resultado1) {
            textoAbaixo.setText("Você Acertou!");
            Button botao = (Button) findViewById(R.id.botao);
            botao.setEnabled(false);
        } else if (chute1 < resultado1) {
            textoAbaixo.setText("Você chutou " + chute + ", seu número deve ser maior!");
        } else {
            textoAbaixo.setText("Você chutou " + chute + ", seu número deve ser menor!");
        }

        ProgressBar barraProgresso = findViewById(R.id.barraDeProgresso);
        progresso += 20;
        barraProgresso.setProgress(progresso);
        if (barraProgresso.getProgress() == 100) {
            textoAbaixo.setText("Acabaram as chances, você perdeu!");
            Button botao = (Button) findViewById(R.id.botao);
            botao.setEnabled(false);
        }

    }
}