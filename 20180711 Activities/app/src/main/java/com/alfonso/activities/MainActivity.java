package com.alfonso.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


    //HAREMOS DISTINTOS 'LOGS' PARA VER QUE OCURRE CUANDO INICIAMOS LA APP ('onCreate()')
    //CUANDO SALIMOS DE ELLA ('onPause()') Y VOLVEMOS A LA APP ('onPause()')
    //TAMBIÉN CUANDO CERRAMOS LA APP ('onStop()' y 'onDestroy()')

    //MIRAREMOS LOS 'LOGS' EN "6.LogCat" Y BUSCAR "PRUEBAS"
    // o VEREMOS LOS 'TOASTS'



    //BOTÓN DERECHO / GENERATE... [Alt+Insert] / OVERRIDE METHODS [Cntrl + O]


    //ACTIVITY 'onCreate()' -> ES DONDE SE CREA LA 'ACTIVITY'
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.v("Pruebas", "onCreate() MainActivity");
        Toast.makeText(getApplicationContext(), "onCreate() MainActivity", Toast.LENGTH_LONG).show();
    }





    //ACTIVITY 'onResume()' -> Surt sempre que entres a la pantalla, primer
    //cop, al tornar a obrir la app després de obrir
    //una altra, al tornar d’altre Activity
    @Override
    protected void onResume() {
        super.onResume();

        Log.v("Pruebas", "onResume() MainActivity");
        Toast.makeText(getApplicationContext(), "onResume() MainActivity", Toast.LENGTH_LONG).show();
    }




    //ACTIVITY 'onPause()' -> Surt sempre que marxem de la pantalla
    @Override
    protected void onPause() {
        super.onPause();

        Log.v("Pruebas", "onPause() MainActivity");
        Toast.makeText(getApplicationContext(), "onPause() MainActivity", Toast.LENGTH_LONG).show();
    }



    //ACTIVITY 'onStop()' ->
    @Override
    protected void onStop() {
        super.onStop();

        Log.v("Pruebas", "onStop() MainActivity");
        Toast.makeText(getApplicationContext(), "onStop() MainActivity", Toast.LENGTH_LONG).show();
    }


    //ACTIVITY 'onDestroy()' ->
    @Override
    protected void onDestroy() {
        super.onDestroy();

        Log.v("Pruebas", "onDestroy() MainActivity");
        Toast.makeText(getApplicationContext(), "onDestroy() MainActivity", Toast.LENGTH_LONG).show();
    }





}
