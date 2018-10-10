package com.alfonso.basededatos_tareas;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.ArrayList;

public class Main3Activity extends AppCompatActivity {


    //DECLARACIONES
    TextView tv_id;
    TextView tv_duracion2;
    TextView tv_nombre2;
    Button btn_borrar;
    Button btn_editar;
    CheckBox ch_tarea_acabada2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        //FIND VIEWERS IDs
        tv_id = findViewById(R.id.tv_id);
        tv_duracion2 = findViewById(R.id.tv_duracion2);
        tv_nombre2 = findViewById(R.id.tv_nombre2);
        btn_borrar = findViewById(R.id.btn_borrar);
        btn_editar = findViewById(R.id.btn_editar);
        ch_tarea_acabada2 = findViewById(R.id.ch_tarea_acabada2);


        //BOOLEAN EDITAR
        final boolean boolean_editar = true;

        //CÓDIGO PARA EL 'ACTIONBAR' CON LA POSIBILIDAD DE VOLVER HACIA ATRÁS
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Tarea específica");


        //RECOGER VALOR DE "MainActivity"

        //RECOGEMOS LO GUARDADO EN LA TAREA ESPECÍFICA
        final long valor_id_base_datos = getIntent().getLongExtra("valor_id",0);//ID ÚNICA DE ESTA TAREA
        String valor_id_string = String.valueOf(valor_id_base_datos);
        tv_id.setText(valor_id_string);

        final int duracion = getIntent().getIntExtra("valor_duracion",0);
        String duracion_string = Integer.toString(duracion);
        tv_duracion2.setText(duracion_string);

        final String nombre = getIntent().getStringExtra("valor_nombre");
        tv_nombre2.setText(nombre);

        final boolean hecha = getIntent().getBooleanExtra("valor_hecha", false);

        ch_tarea_acabada2.setChecked(hecha);


        //BOTÓN "BORRAR"
        btn_borrar.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                //ELIMINAR LA TAREA DE LA BASE DE DATOS
                Tarea tarea = Tarea.findById(Tarea.class, valor_id_base_datos);
                //Eliminar de la base de datos:
                tarea.delete();

                finish();

            }

        });


        //BOTÓN "EDITAR"
        btn_editar.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                //add the function to perform here
                Intent intent = new Intent(Main3Activity.this, Main2Activity.class);

                //RECOGEMOS LO GUARDADO EN LA TAREA ESPECÍFICA
                intent.putExtra("valor_id",valor_id_base_datos);
                intent.putExtra("valor_duracion",duracion);
                intent.putExtra("valor_nombre",nombre);
                intent.putExtra("valor_hecha",hecha);

                intent.putExtra("boolean_editar",boolean_editar);

                startActivity(intent);//IR A 'Main2Activity'

            }

        });


    }


    //CÓDIGO PARA EL 'ACTIONBAR' CON LA POSIBILIDAD DE VOLVER HACIA ATRÁS.
    //EL 'finish()' cerrará la página actual
    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }

}
