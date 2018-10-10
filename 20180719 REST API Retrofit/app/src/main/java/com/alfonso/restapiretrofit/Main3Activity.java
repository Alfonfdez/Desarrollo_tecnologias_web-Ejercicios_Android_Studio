package com.alfonso.restapiretrofit;

import android.app.ProgressDialog;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Main3Activity extends AppCompatActivity {


    //DECLARACIONES
    EditText et_name;
    EditText et_email;
    EditText et_username;
    Button btn_guardar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        //FIND VIEWS BY ID
        et_name = findViewById(R.id.et_name);
        et_email = findViewById(R.id.et_email);
        et_username = findViewById(R.id.et_username);
        btn_guardar = findViewById(R.id.btn_guardar);


        //CÓDIGO PARA EL 'ACTIONBAR' CON LA POSIBILIDAD DE VOLVER HACIA ATRÁS
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Añadir una nueva Persona");


        //CLIC EN EL BOTÓN "GUARDAR" PARA CREAR UNA NUEVA PERSONA CON SUS ATRIBUTOS
        btn_guardar.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                String name = et_name.getText().toString();
                String email = et_email.getText().toString();
                String username = et_username.getText().toString();


                //INICIALIZAR LA CLASE CREADA (PARA LIBRERÍA 'RETROFIT')
                MyService service = RetrofitClientInstance.getRetrofitInstance().create(MyService.class);

                //Exemple de crida per a crear una nova persona:
                Persona nueva_persona = new Persona();
                nueva_persona.setName(name);
                nueva_persona.setEmail(email);
                nueva_persona.setUsername(username);

                Call<Persona> create_persona = service.savePersona(nueva_persona);

                create_persona.enqueue(new Callback<Persona>() {
                    @Override
                    public void onResponse(Call<Persona> call, Response<Persona> response) {
                        //TODO: Mostrar Toast indicant que s'ha creat correctament.
                        //En un cas real, on la creació es fa desde el formulari, el tancariem despres
                        //de la creació.
                        Toast.makeText(getApplicationContext(), "ID: " + response.body().getId() + "", Toast.LENGTH_LONG).show();

                        finish();
                    }

                    @Override
                    public void onFailure(Call<Persona> call, Throwable t) {
                        Toast.makeText(Main3Activity.this, "Something went wrong...Please try later!", Toast.LENGTH_SHORT).show();

                    }
                });




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
