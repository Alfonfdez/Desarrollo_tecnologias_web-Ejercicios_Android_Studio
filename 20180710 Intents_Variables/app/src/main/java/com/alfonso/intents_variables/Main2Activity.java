package com.alfonso.intents_variables;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class Main2Activity extends AppCompatActivity {


    //DECLARAMOS LAS VARIABLES
    TextView tv1;



    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        //CÓDIGO PARA EL 'ACTIONBAR' CON LA POSIBILIDAD DE VOLVER HACIA ATRÁS
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        getSupportActionBar().setTitle("Main2Activity Action Bar");


        //FIND VIEWS
        tv1 = findViewById(R.id.tv1);




        //RECOGER VALOR DE MAINACTIVITY
        String valor_recogido = getIntent().getStringExtra("valor_recogido");

        tv1.setText(valor_recogido);


    }


    //CÓDIGO PARA EL 'ACTIONBAR' CON LA POSIBILIDAD DE VOLVER HACIA ATRÁS.
    //EL 'finish()' cerrará la página actual
    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }


}
