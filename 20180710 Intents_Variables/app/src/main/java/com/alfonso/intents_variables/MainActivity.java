package com.alfonso.intents_variables;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    //DECLARAMOS LAS VARIABLES
    EditText et1;
    Button btnOpen; //BOTÓN ABRIR - IR A MAIN2ACTIVITY
    Button btn1; // BOTÓN COMPARTIR
    Button btn2; // BOTÓN E-MAIL
    Button btn3; // BOTÓN WEB
    Button btn4; // BOTÓN MAPS
    Button btn5; // BOTÓN GUARDAR PREFERENCIAS
    Button btn6; // BOTÓN MOSTRAR NÚMERO DE VECES QUE ENTRAMOS EN 'Main2Activity'
    SharedPreferences prefs; //declaramos la variable de "SharedPreferences"


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //FIND VIEWS
        et1 = findViewById(R.id.et1);
        btnOpen = findViewById(R.id.btnOpen); //BOTÓN ABRIR - IR A MAIN2ACTIVITY
        btn1 = findViewById(R.id.btn1); // BOTÓN COMPARTIR
        btn2 = findViewById(R.id.btn2); // BOTÓN E-MAIL
        btn3 = findViewById(R.id.btn3); // BOTÓN WEB
        btn4 = findViewById(R.id.btn4); // BOTÓN MAPS
        btn5 = findViewById(R.id.btn5); // BOTÓN GUARDAR PREFERENCIAS
        btn6 = findViewById(R.id.btn6); // BOTÓN MOSTRAR NÚMERO DE VECES QUE ENTRAMOS EN 'Main2Activity'

        //INICIALIZAR LA VARIABLE "SHAREDPREFERENCES"
        prefs = getSharedPreferences("MisPreferencias", Context.MODE_PRIVATE);

        //MOSTRAR EL VALOR GUARDADO DE "SHAREDPREFERENCES". SI ESTÁ VACÍO MOSTRAR ""
        et1.setText(prefs.getString("oldtext",""));




        //BOTÓN IR A 'Main2Activity' Y GUARDAR TAMBIÉN EL NÚMERO DE VECES QUE VAMOS
        btnOpen.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                Intent intent = new Intent(MainActivity.this, Main2Activity.class);

                intent.putExtra("valor_recogido",et1.getText().toString()); //RECOGEMOS LO GUARDADO EN LA "EDITVIEW"

                startActivity(intent);//IR A 'Main2Activity'


                //RECOGER Y GUARDAR EL NÚMERO DE VECES QUE ENTRAMOS EN 'Main2Activity'

                int i = prefs.getInt("number",0); //ME RECOGE EL NÚMERO GUARDADO EN "PREFS"

                i++;

                SharedPreferences.Editor editor = prefs.edit();
                editor.putInt("number", i); //COGEMOS EL VALOR DE VECES QUE HEMOS ENTRADO EN MAIN2ACTIVITY

                editor.commit(); //CERRAMOS LOS CAMBIOS CON "COMMIT()"


                /*Intent intent = new Intent(MainActivity.this, SegundaActivity.class);
                startActivity(intent);*/

            }

        });

        //BOTÓN COMPARTIR
        btn1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("text/plain");
                intent.putExtra(Intent.EXTRA_TEXT, "Hello!");
                startActivity(Intent.createChooser(intent, "Title"));

            }

        });

        //BOTÓN ENVIAR E-MAIL
        btn2.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("text/plain");
                intent.putExtra(Intent.EXTRA_TEXT, "Texto a compartir");
                intent.putExtra(Intent.EXTRA_EMAIL, "hello@email.com");
                intent.putExtra(Intent.EXTRA_SUBJECT, "Hello");
                startActivity(intent);

            }

        });

        //BOTÓN ABRIR WEB
        btn3.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("http://www.google.com"));
                startActivity(intent);

            }

        });

        //BOTÓN ABRIR MAPS
        btn4.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                String uri = String.format("geo:0,0?q=address='calle lealtad Esplugues de Llobregat'");
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
                startActivity(intent);

            }

        });


        //BOTÓN GUARDAR PREFERENCIAS
        btn5.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                SharedPreferences.Editor editor = prefs.edit();
                editor.putString("oldtext", et1.getText().toString()); //COGEMOS EL TEXTO DENTRO DE LA 'EDITTEXT' Y LO METEMOS EN EL VALOR "OLDTEXT"

                editor.commit(); //CERRAMOS LOS CAMBIOS CON "COMMIT()"

            }

        });


        //BOTÓN MOSTRAR NÚMERO DE VECES QUE ENTRAMOS EN 'Main2Activity', CON "TOAST"
        btn6.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                int numero_veces = (prefs.getInt("number", 0));

                Toast.makeText(getApplicationContext(), "Has entrado "+numero_veces+" veces en la 'Main2Activity'", Toast.LENGTH_LONG).show();

            }

        });



    }






}

