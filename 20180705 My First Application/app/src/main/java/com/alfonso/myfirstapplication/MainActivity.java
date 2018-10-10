package com.alfonso.myfirstapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    //DECLARACIONES
    TextView tvNombre; // declaro un TextView
    Button btnPruebas; // declaro un botón
    EditText etNombre; // declaro un EditText



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //PONER LOS 'FindViewById'
        tvNombre = findViewById(R.id.tvnombre); //asignar el del layout
        btnPruebas = findViewById(R.id.btnPruebas); //asignar el botón del layout
        etNombre = findViewById(R.id.etNombre); //asignar el botón de EditText


        //TextView

        tvNombre.setText("texto modificado en el codigo"); //Modificar el texto

        String texto = tvNombre.getText().toString(); //Coger el texto del TextView

        tvNombre.setTextColor(getResources().getColor(R.color.colorAccent)); //Modificar color



        //Button
        //Programar que hacemos en el click corto
        btnPruebas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String texto_edittext = etNombre.getText().toString(); //Coger el texto cuando hago 'click'

                Toast.makeText(getApplicationContext(), "Click botón"+texto_edittext, Toast.LENGTH_SHORT).show(); //Con 'getApplicationContext' enseñaremos el mensaje en la pantalla, si pusiéramos 'this' sería en el botón

                tvNombre.setText(texto_edittext);
            }
        });

        //Programar que hacemos en un click largo
        btnPruebas.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                Toast.makeText(getApplicationContext(), "Click largo", Toast.LENGTH_LONG).show();

                return false; //'false' ejecuta "LongClick" y 'true' ejecuta el "LongClick" y cuando soltamos el "ShortClick"
            }
        });






        /*

        //'Log' sirve para debugar (muestra el texto por consola)
        Log.v( "PROVES",  "Hola"); //'Cntrl+Espacio' para importar

        //'Toast' Muestra el mensaje en la aplicación
        Toast.makeText(this, getString(R.string.id_hola),Toast.LENGTH_LONG).show();


        Persona persona = new Persona("Pablo", 33);

        Toast.makeText(this, persona.getNombre(), Toast.LENGTH_LONG).show();

        persona.setNombre("Raul");

        Toast.makeText(this, persona.getNombre(), Toast.LENGTH_LONG).show();

        if(persona.getEdad()>18){
            Toast.makeText(this, getString(R.string.mayor_edad), Toast.LENGTH_LONG).show();
        }else{
            Toast.makeText(this, getString(R.string.menor_edad), Toast.LENGTH_LONG).show();
        }

        */

       /*

       Persona[] personas = new Persona[5];

        for(int i=0; i< personas.length; i++){

            Random randomGenerator = new Random();

            int edad = randomGenerator.nextInt( 100 );

            personas[i] = new Persona( "Persona" + (i + 1 ), edad);

            if(personas[i].getEdad()>18){
                Toast.makeText(this, personas[i].getNombre()+ " " +getString(R.string.mayor_edad)+ " " +personas[i].getEdad(), Toast.LENGTH_LONG).show();
                Log. v ( "PROVES" , personas[i].getNombre() + " " +getString(R.string.mayor_edad) + " " +personas[i].getEdad());
            }

            else {
                Toast.makeText(this, personas[i].getNombre()+ " " +getString(R.string.menor_edad)+ " " +personas[i].getEdad(), Toast.LENGTH_LONG).show();
                Log. v ( "PROVES" , personas[i].getNombre()+ " " +getString(R.string.menor_edad)+ " " +personas[i].getEdad());
            }

        }

        */

       /*BUCLE INFINITO

       ArrayList<Persona> personas = new ArrayList<Persona>();


       while(true){

           int i = 0;

           Random randomGenerator = new Random();

           int edad = randomGenerator.nextInt( 100 );

           personas.add(new Persona( "Persona"+(i++), edad));


        }

        */

       /*
       //AÑADIR NUMERO DE PERSONAS
        ArrayList<Persona> personas = new ArrayList<Persona>();

        int num_personas = 5;

        for(int i = 0 ; i<num_personas; i++){

            Random randomGenerator = new Random();

            int edad = randomGenerator.nextInt( 100 );

            Persona p = new Persona( "Persona"+(i++), edad);

            personas.add(p);

        }

        for (Persona pers: personas){
            Log. v ( "PROVES" , pers.getNombre()+ " " +pers.getEdad());
        }
        */

    }
}
