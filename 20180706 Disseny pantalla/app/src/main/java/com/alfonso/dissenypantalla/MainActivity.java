package com.alfonso.dissenypantalla;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {


    //DECLARACIONES
    EditText editexto; // declaro un EditText
    TextView textview; // declaro un TextView
    Button btnaceptar; // declaro un botón
    Button btncancelar; // declaro un botón
    CheckBox chkSelect; // declaro un check box


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //PONER LOS 'FindViewById'
        editexto = findViewById(R.id.editexto); //asignar el del 'edittexto' -> Texto donde escribo
        textview = findViewById(R.id.textview); //asignar el del 'textview' -> Texto que leo
        btnaceptar = findViewById(R.id.btnaceptar); //asignar el botón de Aceptar
        btncancelar = findViewById(R.id.btncancelar); //asignar el botón de Cancelar
        chkSelect = findViewById(R.id.chk_select); // asignar el id del checkbox


        //BOTÓN ACEPTAR
        btnaceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { //Si pulso el botón de 'Aceptar'

                String texto = editexto.getText().toString(); //Cojo el texto escrito en la 'EditText'

                textview.setText("¡Hola "+texto+"!, ¿qué tal el día?"); //Inserto el texto de la 'EditText' en la 'TextView'

               if(chkSelect.isChecked()) { //Checkbox seleccionada
                   textview.setText("¡Hola "+texto+"!, ¿qué tal el día? - El checkbox está seleccionado" );
               }

            }
        });

        //BOTÓN CANCELAR
        btncancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { //Si pulso el botón de 'Cancelar'

                editexto.setText(""); //Reseteo a nada el texto de la 'EditText'
                textview.setText("¡Hola!, ¿qué tal el día?"); //Reseteo el texto de la 'TextView'

                chkSelect.setChecked(false); //Checkbox NO seleccionada
            }
        });



        }



    }

