package com.alfonso.basededatos_tareas;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class Main2Activity extends AppCompatActivity {


    //DECLARACIONES
    EditText et_nombre;
    EditText et_duracion;
    CheckBox ch_tarea_acabada;
    Button btn_guardar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);


        //FIND VIEW IDs
        et_nombre = findViewById(R.id.et_nombre);
        et_duracion = findViewById(R.id.et_duracion);
        ch_tarea_acabada = findViewById(R.id.ch_tarea_acabada);
        btn_guardar = findViewById(R.id.btn_guardar);


        //CÓDIGO PARA EL 'ACTIONBAR' CON LA POSIBILIDAD DE VOLVER HACIA ATRÁS
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Formulario tarea");


        //BOOLEAN "EDITAR" - RECOGEMOS LO GUARDADO EN LA TAREA ESPECÍFICA
        final boolean boolean_editar = getIntent().getBooleanExtra("boolean_editar", false);


        //CONDICIONES PARA PASAR POR MODO 'EDITAR' (boolean==true) o MODO 'AÑADIR' (boolean==false)
        if(boolean_editar==true){

            //RECOGEMOS LO GUARDADO EN LA TAREA ESPECÍFICA
            final long valor_id_base_datos = getIntent().getLongExtra("valor_id",0);//ID ÚNICA DE ESTA TAREA

            final int duracion = getIntent().getIntExtra("valor_duracion",0);
            String duracion_string = Integer.toString(duracion);
            et_duracion.setText(duracion_string);

            final String nombre = getIntent().getStringExtra("valor_nombre");
            et_nombre.setText(nombre);

            final boolean hecha = getIntent().getBooleanExtra("valor_hecha", false);
            ch_tarea_acabada.setChecked(hecha);


            //CLICK AL BOTÓN "GUARDAR"
            btn_guardar.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View view) {

                    String nombre = et_nombre.getText().toString();

                    String duracion_string = et_duracion.getText().toString();
                    int duracion = Integer.parseInt(duracion_string); //PARSEAMOS A 'INT' EL VALOR 'STRING'

                    //Exemple Select by ID:
                    Tarea tarea = Tarea.findById(Tarea.class,valor_id_base_datos);

                    //HACEMOS 'UPDATE' DE LA TAREA
                    tarea.setNombre(nombre);
                    tarea.setDuracion(duracion);
                    tarea.setHecha(ch_tarea_acabada.isChecked());

                    tarea.save();

                    finish();

                }

            });


        }


        else {


            //CLICK AL BOTÓN "GUARDAR"
            btn_guardar.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View view) {

                    String nombre = et_nombre.getText().toString();

                    String duracion_string = et_duracion.getText().toString();
                    int duracion = Integer.parseInt(duracion_string); //PARSEAMOS A 'INT' EL VALOR 'STRING'

                    //AÑADIR NUEVO TAREA A LA BASE DE DATOS - NUEVA LÍNEA BBDD (BASE DE DATOS)
                    Tarea tarea = new Tarea(nombre, duracion, ch_tarea_acabada.isChecked());

                    tarea.save();

                    finish();

                }

            });



        }



    }


    //CÓDIGO PARA EL 'ACTIONBAR' CON LA POSIBILIDAD DE VOLVER HACIA ATRÁS.
    //EL 'finish()' cerrará la página actual
    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }


}
