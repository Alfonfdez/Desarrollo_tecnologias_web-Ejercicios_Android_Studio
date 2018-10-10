package com.example.administrador.intents1;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class ResultadoActivity extends AppCompatActivity {
    TextView txtResult;
    SharedPreferences prefs;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultado);

        txtResult = findViewById(R.id.txtResult);

        String resultado_texto = getIntent().getStringExtra("resultado");
        txtResult.setText(resultado_texto);


        //Exercici 2 Shared Preferences (podria estar en l'altre pantalla en un botó)
        prefs = getSharedPreferences("MisPreferencias", Context.MODE_PRIVATE);
        //Recollir el valor
        int i =prefs.getInt("times", 0);
        //Sumar-hi 1
        i++;
        //Mostrar en un Toast
        //Toast.makeText(this, i+" times.", Toast.LENGTH_LONG).show();
        //Guardar al shared preferences
        SharedPreferences.Editor editor = prefs.edit();
        editor.putInt("times", i);
        editor.commit();
    }
}
